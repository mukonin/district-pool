package io;

import org.joda.time.DateTime;

import entities.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @ author Mukonin Oleksandr
 */
public class SQL {

    static String conStr = "jdbc:mysql://91.209.24.68:43306/hospital?useSSL=false";
    static String usr = "user1";
    static String pass = "user1";

    public static Hospital readHospital() throws Exception {

        Hospital hospital = new Hospital();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(conStr, usr, pass);
            Statement statement = connection.createStatement();
            String sql;
            sql = "SELECT * FROM roles JOIN users ON roles.id = users.id WHERE role = 'doctor'";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next())
            {
                Person person = new Person();
                person.setId(resultSet.getLong("id"));
                person.setFirstName(resultSet.getString("firstname"));
                person.setLastName(resultSet.getString("lastname"));
                person.setDate(new DateTime(resultSet.getDate("date").getTime()));
                Doctor doctor = new Doctor();
                doctor.setDoctor(person);
                hospital.addDoctor(doctor);
            }

            for (Doctor doctor : hospital.getDoctors()) {
                Person person = doctor.getPerson();
                sql = "SELECT * FROM binds JOIN users ON users.id = binds.patient_id WHERE doctor_id = " + person.getId() + ";";
                resultSet = statement.executeQuery(sql);
                while (resultSet.next())
                {
                    person = new Person();
                    person.setId(resultSet.getLong("id"));
                    person.setFirstName(resultSet.getString("firstname"));
                    person.setLastName(resultSet.getString("lastname"));
                    person.setDate(new DateTime(resultSet.getDate("date").getTime()));
                    doctor.addPatient(person);
                }
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        return hospital;
    }

    public static void writeHospital(Hospital hospital) throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(conStr, usr, pass);
            Statement statement = connection.createStatement();

            for (Doctor doctor : hospital.getDoctors()) {
                Person person = doctor.getPerson();

                String sql = "INSERT INTO users (id, firstname, lastname, date) VALUES ('" + person.getId() + "','" +
                        person.getFirstName() + "','" +
                        person.getLastName() + "','" +
                        person.getDate().toString("yyyy-MM-dd hh:mm:ss") + "');";
                statement.executeUpdate(sql);
                sql = "INSERT INTO roles (id, role) VALUES ('" + person.getId() + "','doctor');";
                statement.executeUpdate(sql);

                for (Person patient : doctor.getPatients()) {
                    sql = "INSERT INTO users (id, firstname, lastname, date) VALUES ('" + patient.getId() + "','" +
                            patient.getFirstName() + "','" +
                            patient.getLastName() + "','" +
                            patient.getDate().toString("yyyy-MM-dd hh:mm:ss") + "');";
                    statement.executeUpdate(sql);
                    sql = "INSERT INTO roles (id, role) VALUES ('" + patient.getId() + "','patient');";
                    statement.executeUpdate(sql);
                    sql = "INSERT INTO binds (doctor_id, patient_id) VALUES ('" + person.getId() + "','" +
                            patient.getId() + "');";
                    statement.executeUpdate(sql);
                }

            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }


}
