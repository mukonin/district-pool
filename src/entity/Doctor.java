package entity;

import javax.persistence.*;

import java.util.TreeSet;

/**
 * @ author Mukonin Oleksandr
 *
 */

@Entity
@DiscriminatorValue("d")
@Table(name="doctors")
public class Doctor extends Person {
	
    public Doctor() {
    	
	}
	
	public Doctor(Person person) {
		this.setId(person.getId());
		this.setFirstName(person.getFirstName());
		this.setLastName(person.getLastName());
		this.setDate(person.getDate());	
	}
	
	@OneToMany(mappedBy="doctor")
    private TreeSet<Patient> patients;

    public TreeSet<Patient> getPatients() {
        return patients;
    }

    public void setPatients(TreeSet<Patient> patients) {
        this.patients = patients;
    }
    
}
