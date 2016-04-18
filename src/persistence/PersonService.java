package persistence;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import javax.persistence.*;

import org.joda.time.DateTime;

import entities.Person;

public class PersonService {
	
    
	public static ArrayList<Person> getPersons() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "hospital" );
	    EntityManager entitymanager = emfactory.createEntityManager();
    	@SuppressWarnings("unchecked")
		List<Person> list = (List<Person>) entitymanager.createQuery("SELECT p FROM Person p").getResultList();
    	entitymanager.close();
    	emfactory.close();
    	return (ArrayList<Person>) list;
	}
	
	public static void addUser(Person person) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "hospital" );
	    EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.persist(person);
    	entitymanager.close();
    	emfactory.close();
	}
	
	public static void setRole(Person person, String role) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "hospital" );
	    EntityManager entitymanager = emfactory.createEntityManager();
	    entitymanager.createQuery("INSERT INTO roles (id, role) VALUES ('" + person.getId() + "','" + role + "');").executeUpdate();
    	entitymanager.close();
    	emfactory.close();
	}
	
	public static void addDoctor(Person doctor) {
        addUser(doctor);
        setRole(doctor, "doctor");
    }

    public static void addPatient(Person patient) {
        addUser(patient);
        setRole(patient, "patient");
    }
    
    public static void deleteUser(Person person) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "hospital" );
	    EntityManager entitymanager = emfactory.createEntityManager();
	    entitymanager.getTransaction().begin();
	    entitymanager.remove(person);
	    entitymanager.getTransaction().commit();
	    entitymanager.createQuery("DELETE FROM binds WHERE doctor_id = " + person.getId() + ";").executeUpdate();
	    entitymanager.createQuery("DELETE FROM binds WHERE patient_id = " + person.getId() + ";").executeUpdate();
	    entitymanager.createQuery("DELETE FROM roles WHERE id = " + person.getId() + ";").executeUpdate();
    	entitymanager.close();    	
    	emfactory.close();
    }
	
    public static Person getById(Long id) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "hospital" );
	    EntityManager entitymanager = emfactory.createEntityManager();
	    entitymanager.getTransaction().begin();
	    Person person = entitymanager.find(Person.class, id);
    	entitymanager.close();    	
    	emfactory.close();
        return person;
    }
    
    public static Person getDoctor(Person patient) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "hospital" );
	    EntityManager entitymanager = emfactory.createEntityManager();
	    Person doctor = (Person) entitymanager.createQuery("SELECT p FROM binds JOIN Person p ON binds.doctor_id = users.id WHERE binds.patient_id = '" + patient.getId() + "';").getSingleResult();
    	entitymanager.close();    	
    	emfactory.close();
	    return doctor;
    }
    
    public static ArrayList<Person> getDoctors() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "hospital" );
	    EntityManager entitymanager = emfactory.createEntityManager();
	    
    	
    	
        ArrayList<Person> list = new ArrayList<>();
        try {
            Statement statement = connectToDB();
            String sql = "SELECT * FROM roles JOIN users ON roles.id = users.id WHERE role = 'doctor';";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next())
            {
                Person person = new Person();
                person.setId(resultSet.getLong("id"));
                person.setFirstName(resultSet.getString("firstname"));
                person.setLastName(resultSet.getString("lastname"));
                person.setDate(new DateTime(resultSet.getDate("date").getTime()));
                list.add(person);
            }
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return list;
    }

}
