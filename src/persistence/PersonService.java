package persistence;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import org.joda.time.DateTime;

import entities.Patient;
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
    	@SuppressWarnings("unchecked")
		List<Person> list = (List<Person>) entitymanager.createQuery("SELECT p FROM roles JOIN Person p ON roles.id = users.id WHERE role = 'doctor';").getResultList();
    	entitymanager.close();
    	emfactory.close();
    	return (ArrayList<Person>) list;
    }
    
    public static ArrayList<Patient> getPatients() {
    	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "hospital" );
	    EntityManager entitymanager = emfactory.createEntityManager();
    	@SuppressWarnings("unchecked")
		List<Person> list = (List<Person>) entitymanager.createQuery("SELECT p FROM roles JOIN Person p ON roles.id = users.id WHERE role = 'patient';").getResultList();
    	ArrayList<Patient> list2 = new ArrayList<>();
    	for (Person person : list) {
    		Patient patient = (Patient) person;
    		Person doctor = getDoctor(person);
    		patient.setDoctor(doctor);
    		list2.add(patient);
    	}    	
    	entitymanager.close();
    	emfactory.close();
    	return list2;
    }
    
    public static ArrayList<Person> getPatients(Person doctor) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "hospital" );
	    EntityManager entitymanager = emfactory.createEntityManager();
    	@SuppressWarnings("unchecked")
		List<Person> list = (List<Person>) entitymanager.createQuery("SELECT p FROM binds JOIN Person p ON binds.patient_id = users.id WHERE binds.doctor_id = '" +
                    doctor.getId() + "';").getResultList();
    	entitymanager.close();
    	emfactory.close();
    	return (ArrayList<Person>) list;
    }
    
    public static String getRole (Person person) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "hospital" );
	    EntityManager entitymanager = emfactory.createEntityManager();
	    String result =  (String) entitymanager.createQuery("SELECT * FROM roles WHERE (id = '" + person.getId() + "');").getSingleResult();
    	entitymanager.close();
    	emfactory.close();
    	return result;
    }
    
    public static boolean isDoctor(Person person) {
    	if (getRole(person).equals("doctor")) return true;
    	return false;
	}
    
    public static void updateUser(Person person) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "hospital" );
	    EntityManager entitymanager = emfactory.createEntityManager();
	    entitymanager.getTransaction().begin();
	    entitymanager.merge(person);
	    entitymanager.getTransaction().commit();
    	entitymanager.close();
    	emfactory.close();
    } 
    
    public static void unlinkDoctorPatient(Person person1, Person person2) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "hospital" );
	    EntityManager entitymanager = emfactory.createEntityManager();
	    entitymanager.createQuery("DELETE FROM binds WHERE ( doctor_id = " + person1.getId() + " AND patient_id = " + 
       		 person2.getId() + " ) OR ( doctor_id = " + person2.getId() + " AND patient_id = " + 
       		 person1.getId() + " );").executeUpdate();
    	entitymanager.close();
    	emfactory.close();
    }

    public static void linkDoctorPatient(Person doctor, Person patient) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "hospital" );
	    EntityManager entitymanager = emfactory.createEntityManager();
	    entitymanager.createQuery("INSERT INTO binds (doctor_id, patient_id) VALUES ('" + doctor.getId() + "', '" +
                patient.getId() + "');").executeUpdate();
    	entitymanager.close();
    	emfactory.close();
	}

}
