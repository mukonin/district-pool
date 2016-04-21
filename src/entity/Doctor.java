package entity;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

/**
 * @ author Mukonin Oleksandr
 *
 */

@Entity
@Table(name="doctors")
public class Doctor implements Comparable<Doctor> {

    private Person person;
    private HashSet<Person> patients = new HashSet<Person>();

    @OneToOne
    @Id
    @JoinColumn(name="doctor_id")
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @ManyToOne(targetEntity=Person.class)
    @JoinColumn(name="patient_id")
    public HashSet<Person> getPatients() {
        return patients;
    }

    public void setPatients(HashSet<Person> patients) {
        this.patients = patients;
    }

    @Override
    public int compareTo(Doctor doctor) {
        return this.getPerson().compareTo(doctor.getPerson());
    }

	@Override
	public String toString() {
		return person.toString();
	}
    
}
