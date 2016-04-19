package entities;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="roles")
public class Role {
	
	private int id;
	private String role;
	Set<Person> persons;
	
	@OneToMany(mappedBy="role")
	public Set<Person> getPersons() {
		return persons;
	}

	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}	

}
