package dao;

import org.joda.time.DateTime;

import entities.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

/**
 * @ author Mukonin Oleksandr
 */
public class DAO {

    private static String conStr = "jdbc:mysql://91.209.24.68:43306/hospital?useSSL=false";
    private static String usr = "user1";
    private static String pass = "user1";

    private static Statement connectToDB() {
        Statement statement = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(conStr, usr, pass);
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return statement;
    }

    public static void dropDB() {
        try {
            Statement statement = connectToDB();
            String sql = "DROP DATABASE hospital;";
            statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public static void createDB() {
        try {Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://91.209.24.68:43306?useSSL=false", usr, pass);
            Statement statement = connection.createStatement();

            String sql = "CREATE DATABASE hospital;";
            statement.executeUpdate(sql);
            sql = "USE hospital;";
            statement.executeUpdate(sql);
            sql = "CREATE TABLE users (id BIGINT NOT NULL PRIMARY KEY, firstname VARCHAR(30), lastname VARCHAR(30)," +
                    " date DATE);";
            statement.executeUpdate(sql);
            sql = "CREATE TABLE binds (doctor_id BIGINT, patient_id BIGINT);";
            statement.executeUpdate(sql);
            sql = "CREATE TABLE roles (id BIGINT NOT NULL PRIMARY KEY, role VARCHAR(10));";
            statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public static void addUser(Person person) {
        try {
            Statement statement = connectToDB();
        	Random random = new Random();
            String sql = "SELECT * FROM users WHERE id = '"+ person.getId() +"';";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
            	resultSet.close();
		        person.setId((long) (random.nextDouble() * 9000000000L) + 1000000000L);
            	sql = "SELECT * FROM users WHERE id = '"+ person.getId() +"';";
                resultSet = statement.executeQuery(sql);
            }
            sql = "INSERT INTO users (id, firstname, lastname, date) VALUES ('" + person.getId() +
                    "','" + person.getFirstName() + "','" +
                    person.getLastName() + "','" +
                    person.getDate().toString("yyyy-MM-dd hh:mm:ss") + "');";
            statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public static void addDoctor(Person doctor) {
        addUser(doctor);
        setRole(doctor, "doctor");
    }

    public static void addPatient(Person patient) {
        addUser(patient);
        setRole(patient, "patient");
    }

    public static void addPatients(ArrayList<Person> list) {
        for (Person patient :list) {
            addUser(patient);
            setRole(patient, "patient");
        }
    }

    public static void setRole(Person person, String role) {
        try {
            Statement statement = connectToDB();
            String sql = "INSERT INTO roles (id, role) VALUES ('" + person.getId() + "','" + role + "');";
            statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public static void deleteUser(Person person) {
        try {
            Statement statement = connectToDB();
            String sql = "DELETE FROM users WHERE id = " + person.getId() + ";";
            statement.executeUpdate(sql);
            sql = "DELETE FROM binds WHERE doctor_id = " + person.getId() + ";";
            statement.executeUpdate(sql);
            sql = "DELETE FROM binds WHERE patient_id = " + person.getId() + ";";
            statement.executeUpdate(sql);
            sql = "DELETE FROM roles WHERE id = " + person.getId() + ";";
            statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public static void updateUser(Person person) {
        try {
            Statement statement = connectToDB();
            String sql = "UPDATE users SET firstname = '" + person.getFirstName() + "', " +
                    "lastname = '" + person.getLastName() + "', " +
                    "date = '" + person.getDate().toString("yyyy-MM-dd") +
                    "' WHERE id = " + person.getId() + ";";
            statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public static void linkDoctorPatient(Person doctor, Person patient) {
        try {
            Statement statement = connectToDB();
            String sql = "INSERT INTO binds (doctor_id, patient_id) VALUES ('" + doctor.getId() + "', '" +
                    patient.getId() + "');";
            statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
    
    public static void unlinkDoctorPatient(Person person1, Person person2) {
    	 try {
             Statement statement = connectToDB();
             String sql = "DELETE FROM binds WHERE ( doctor_id = " + person1.getId() + " AND patient_id = " + 
            		 person2.getId() + " ) OR ( doctor_id = " + person2.getId() + " AND patient_id = " + 
            		 person1.getId() + " );";
             statement.executeUpdate(sql);
         } catch (Exception e) {
             e.printStackTrace(System.out);
         }
    }

    public static void linkDoctorPatients(Person doctor, ArrayList<Person> list) {
        for (Person patient :list) {
            linkDoctorPatient(doctor, patient);
        }
    }

    public static ArrayList<Person> getByFullName(String fullName) {
        ArrayList<Person> list = new ArrayList<Person>();
        try {
            String lastName = fullName.split(" ")[0];
            String firstName = fullName.split(" ")[1];
            Statement statement = connectToDB();
            String sql = "SELECT * FROM users WHERE (firstname = '" + firstName + "' AND lastname = '" + lastName +
                    "') OR (firstname = '" + lastName + "' AND lastname = '" + firstName + "');";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next())
            {
                Person person = new Person();
                person.setId(resultSet.getLong("id"));
                person.setFirstName(resultSet.getString("firstname"));
                person.setLastName(resultSet.getString("lastname"));
                person.setDate(new DateTime(resultSet.getDate("date").getTime()));
                list.add(person);
            }
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return list;
    }

    public static ArrayList<Person> getDoctors() {
        ArrayList<Person> list = new ArrayList<>();
        try {
            Statement statement = connectToDB();
            String sql = "SELECT * FROM roles JOIN users ON roles.id = users.id WHERE role = 'doctor';";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next())
            {
                Person person = new Person();
                person.setId(resultSet.getLong("id"));
                person.setFirstName(resultSet.getString("firstname"));
                person.setLastName(resultSet.getString("lastname"));
                person.setDate(new DateTime(resultSet.getDate("date").getTime()));
                list.add(person);
            }
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return list;
    }
    
    public static Person getDoctor(Person patient) {
        try {
            Statement statement = connectToDB();
            String sql = "SELECT * FROM binds JOIN users ON binds.doctor_id = users.id WHERE binds.patient_id = '" + patient.getId() + "';";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next())
            {
                Person person = new Person();
                person.setId(resultSet.getLong("id"));
                person.setFirstName(resultSet.getString("firstname"));
                person.setLastName(resultSet.getString("lastname"));
                person.setDate(new DateTime(resultSet.getDate("date").getTime()));
                return person;
            }
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
    
    public static ArrayList<Person> getPersons() {
        ArrayList<Person> list = new ArrayList<>();
        try {
            Statement statement = connectToDB();
            String sql = "SELECT * FROM users;";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next())
            {
                Person person = new Person();
                person.setId(resultSet.getLong("id"));
                person.setFirstName(resultSet.getString("firstname"));
                person.setLastName(resultSet.getString("lastname"));
                person.setDate(new DateTime(resultSet.getDate("date").getTime()));
                list.add(person);
            }
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return list;
    }

    public static ArrayList<Patient> getPatients() {
        ArrayList<Patient> list = new ArrayList<>();
        try {
            Statement statement = connectToDB();
            
            /*String sql = "SELECT * FROM roles JOIN users ON roles.id = users.id WHERE role = 'patient';";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next())
            {
                Patient person = new Patient();
                person.setId(resultSet.getLong("id"));
                person.setFirstName(resultSet.getString("firstname"));
                person.setLastName(resultSet.getString("lastname"));
                person.setDate(new DateTime(resultSet.getDate("date").getTime()));
                list.add(person);
            }
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }*/
            
            String sql = "select * from roles join users on roles.id = users.id left join binds on roles.id" + 
            " = binds.patient_id left join users as doctors on doctors.id = binds.doctor_id where role = 'patient';";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next())
            {
                Patient patient = new Patient();
                patient.setId(resultSet.getLong("users.id"));
                patient.setFirstName(resultSet.getString("users.firstname"));
                patient.setLastName(resultSet.getString("users.lastname"));
                patient.setDate(new DateTime(resultSet.getDate("users.date").getTime()));
                if ( ! (resultSet.getString("doctors.firstname") == null)) {
                Person doctor = new Person();
                doctor.setId(resultSet.getLong("doctors.id"));
                doctor.setFirstName(resultSet.getString("doctors.firstname"));
                doctor.setLastName(resultSet.getString("doctors.lastname"));
                doctor.setDate(new DateTime(resultSet.getDate("doctors.date").getTime()));
                patient.setDoctor(doctor);
                } else {
                	patient.setDoctor(null);
                }
                list.add(patient);
            }
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        
        return list;
    }

    public static ArrayList<Person> getPatients(Person doctor) {
        ArrayList<Person> list = new ArrayList<>();
        try {
            Statement statement = connectToDB();
            String sql = "SELECT * FROM binds JOIN users ON binds.patient_id = users.id WHERE binds.doctor_id = '" +
                    doctor.getId() + "';";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next())
            {
                Person person = new Person();
                person.setId(resultSet.getLong("id"));
                person.setFirstName(resultSet.getString("firstname"));
                person.setLastName(resultSet.getString("lastname"));
                person.setDate(new DateTime(resultSet.getDate("date").getTime()));
                list.add(person);
            }
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return list;
    }

    public static Person getById(Long id) {
        Person person = new Person();
        try {
            String sql = "SELECT * FROM users WHERE (id = '" + id + "');";
            Statement statement = connectToDB();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next())
            {
                person.setId(resultSet.getLong("id"));
                person.setFirstName(resultSet.getString("firstname"));
                person.setLastName(resultSet.getString("lastname"));
                person.setDate(new DateTime(resultSet.getDate("date").getTime()));
            }
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return person;
    }
    
    public static boolean isDoctor(Person person) {
    	 try {
             String sql = "SELECT * FROM roles WHERE (id = '" + person.getId() + "');";
             Statement statement = connectToDB();
             ResultSet resultSet = statement.executeQuery(sql);
             while (resultSet.next())
             {
            	 if (resultSet.getString("role").equals("doctor")) return true;
             }
             resultSet.close();
         } catch (Exception e) {
             e.printStackTrace(System.out);
         }
    	 return false;
    }
    
    public static String getRole (Person person) {
    	try {
            String sql = "SELECT * FROM roles WHERE (id = '" + person.getId() + "');";
            Statement statement = connectToDB();
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            return resultSet.getString("role");
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
   	 return "user";
    }
    
}