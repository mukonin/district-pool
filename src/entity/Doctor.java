package entity;

import javax.persistence.*;

import java.util.HashSet;

/**
 * @ author Mukonin Oleksandr
 *
 */

@Entity
@DiscriminatorValue("d")
@Table(name="doctors")
public class Doctor extends Person {
	
	@OneToMany(mappedBy="doctor")
    private HashSet<Patient> patients;

    public HashSet<Patient> getPatients() {
        return patients;
    }

    public void setPatients(HashSet<Patient> patients) {
        this.patients = patients;
    }
    
}
