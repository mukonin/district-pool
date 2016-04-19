package entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="binds")
public class Bind {
	
	
	Person doctor;
	Set<Person> patients;
	
	
	
	
	
}
