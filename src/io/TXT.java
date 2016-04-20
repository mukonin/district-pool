package io;

import java.io.*;

import entity.*;
import util.PersonIOException;
import util.PersonUtils;

/**
 * @ author Mukonin Oleksandr
 *
 */
public class TXT implements HospitalIO {

    @Override
    public void writeHospital(Hospital hospital, String fileName) throws IOException {
        try
                (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Doctor doctor : hospital.getDoctors()) {
                Person person = doctor.getPerson();
                writer.write(person.toString());
                writer.write(" {");
                for (Person patient : doctor.getPatients()) {
                    writer.write(patient.toString());
                    if (! (doctor.getPatients().last() == patient)) {
                        writer.write(", ");
                    }
                }
                writer.write("}");
                if (! (doctor == hospital.getDoctors().last())) {
                    writer.write(",");
                }
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Hospital readHospital(String fileName) throws IOException {
        Hospital hospital = new Hospital();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream errorStream = new PrintStream(baos,true,"utf-8");

        try
                (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.ready()) {
                Doctor doctor = new Doctor();
                Person person;
                String temp = reader.readLine();
                String[] personsLine = temp.split("[{},]");

                for (String personString : personsLine) {
                    try {
                        person = PersonUtils.valueOf(personString.trim());
                    } catch (PersonIOException e) {
                        e.printStackTrace(errorStream);
                        continue;
                    }
                    if ( ! (personString.equals(personsLine[0]))) {
                        hospital.addPatient(doctor, person);
                    } else {
                        doctor.setDoctor(person);
                        hospital.addDoctor(doctor);
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace(errorStream);
        }
        System.out.println(baos.toString());
        return hospital;
    }
}
