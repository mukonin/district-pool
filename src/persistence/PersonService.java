package persistence;

import java.util.Collection;

import javax.persistence.*;

import entities.Person;

public class PersonService {
	
	protected EntityManager entityManager;
	
	public PersonService(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Collection<Person> getAllPersons() {
		Query query = entityManager.createQuery("SELECT e FROM users e");
		return (Collection<Person>) query.getResultList();
	}

}
