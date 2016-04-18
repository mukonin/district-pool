package persistence;

import java.util.ArrayList;

import javax.persistence.*;

import entities.Person;

public class PersistenceTest {
	
	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Eclipselink_JPA" );
    EntityManager entitymanager = emfactory.createEntityManager();
    ArrayList<Person> list = (ArrayList<Person>) entitymanager.createQuery("SELECT * FROM users").getResultList();
    
    /*list = entitymanager.createQuery(
    	    "SELECT c FROM Customer c WHERE c.name LIKE :custName")
    	    .setParameter("custName", name)
    	    .setMaxResults(10)
    	    .getResultList(););*/

}
