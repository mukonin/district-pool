package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.xml.bind.annotation.XmlElement;
import java.util.TreeSet;

/**
 * @ author Mukonin Oleksandr
 *
 */
public class Doctor implements Comparable<Doctor> {

    private Person doctor = new Person();
    private TreeSet<Person> patients = new TreeSet<>();

    public Doctor() {
    }

    public Doctor(Person person) {
        doctor = person;
    }

    public void setDoctor(Person doctor) {
        this.doctor = doctor;
    }

    @XmlElement
    public Person getDoctor() {
        return doctor;
    }

    @JsonIgnore
    public Person getPerson() {
        return doctor;
    }

    @XmlElement(name = "patient")
    public TreeSet<Person> getPatients() {
        return patients;
    }

    public void setPatients(TreeSet<Person> patients) {
        this.patients = patients;
    }

    public void addPatient(Person patient) {
        patients.add(patient);
    }

    @Override
    public int compareTo(Doctor doctor) {
        return this.getPerson().compareTo(doctor.getPerson());
    }
}
