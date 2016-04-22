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
