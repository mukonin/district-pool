package entity;

import javax.persistence.*;

/**
 * @ author Mukonin Oleksandr
 *
 */

@Entity
@DiscriminatorValue("p")
@Table(name="patients")
public class Patient extends Person {
	
    public Patient() {
    	
	}
	
	public Patient(Person person) {
		this.setId(person.getId());
		this.setFirstName(person.getFirstName());
		this.setLastName(person.getLastName());
		this.setDate(person.getDate());	
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="patient_id")
	private Doctor doctor;	

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}	

}
