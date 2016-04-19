package entities;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;

import java.util.Set;

/**
 * @ author Mukonin Oleksandr
 *
 */

@Entity
@Table(name="doctors")
public class Doctor implements Comparable<Doctor> {

    private Person person;
    private Set<Person> patients;

    @Id
	public long getId() {
		return person.getId();
	}

	public void setId(long id) {
		this.person.setId(id);
	}

    public void setPerson(Person person) {
        this.person = person;
    }

    //@XmlElement
    @OneToOne
    public Person getPerson() {
        return person;
    }

    //@XmlElement(name = "patient")
    @ManyToOne
    @JoinColumn(name="id")
    public Set<Person> getPatients() {
        return patients;
    }

    public void setPatients(Set<Person> patients) {
        this.patients = patients;
    }

    @Override
    public int compareTo(Doctor doctor) {
        return this.getPerson().compareTo(doctor.getPerson());
    }
}
