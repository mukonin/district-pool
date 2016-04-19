package entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="binds")
public class Bind {
	
	private Person doctor;
    private long id;
    private Set<Person> patients;
    
    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@OneToOne
	public Person getDoctor() {
		return doctor;
	}

	public void setDoctor(Person doctor) {
		this.doctor = doctor;
	}

	@OneToMany(mappedBy="bind")
	public Set<Person> getPatients() {
		return patients;
	}

	public void setPatients(Set<Person> patients) {
		this.patients = patients;
	}
	
	
}
