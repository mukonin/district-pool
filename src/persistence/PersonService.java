package persistence;

import java.util.ArrayList;
import javax.persistence.*;

import entity.Doctor;
import entity.Patient;

public class PersonService {
	
	// create/update

    public static void addPatient(Patient patient) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "hospital" );
	    EntityManager entitymanager = emfactory.createEntityManager();
	    entitymanager.getTransaction().begin();
	    entitymanager.merge(patient);    
	    entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
    }
	
	public static void addDoctor(Doctor doctor) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "hospital" );
	    EntityManager entitymanager = emfactory.createEntityManager();
	    entitymanager.getTransaction().begin(); 
	    entitymanager.merge(doctor);
	    entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
    }
	
	// read
	
    public static Patient getPatientById(Long id) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "hospital" );
	    EntityManager entitymanager = emfactory.createEntityManager();
	    Patient patient = entitymanager.find(Patient.class, id);
    	entitymanager.close();    	
    	emfactory.close();
        return patient;
    }
	
    public static Doctor getDoctorById(Long id) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "hospital" );
	    EntityManager entitymanager = emfactory.createEntityManager();
	    Doctor doctor = entitymanager.find(Doctor.class, id);
    	entitymanager.close();    	
    	emfactory.close();
        return doctor;
    }
    
    // update
    
    public static void updatePatient(Patient patient) {
    	addPatient(patient);
    }
    
    public static void updateDoctor(Doctor doctor) {
    	addDoctor(doctor);
    }
    
    // delete
    
    public static void deletePatient(Patient patient) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "hospital" );
	    EntityManager entitymanager = emfactory.createEntityManager();
	    entitymanager.getTransaction().begin();
	    patient = entitymanager.getReference(Patient.class, patient.getId());
	    entitymanager.remove(patient);
	    entitymanager.getTransaction().commit();
    	entitymanager.close();    	
    	emfactory.close();
    }
    
    public static void deleteDoctor(Doctor doctor) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "hospital" );
	    EntityManager entitymanager = emfactory.createEntityManager();
	    entitymanager.getTransaction().begin();
	    doctor = entitymanager.getReference(Doctor.class, doctor.getId());
	    entitymanager.remove(doctor);
	    entitymanager.getTransaction().commit();
    	entitymanager.close();    	
    	emfactory.close();
    }
    
    // read collections
    
    public static ArrayList<Doctor> getDoctors() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "hospital" );
	    EntityManager entitymanager = emfactory.createEntityManager();
    	@SuppressWarnings("unchecked")
    	ArrayList<Doctor> list = new ArrayList<>(entitymanager.createQuery("SELECT d FROM Doctor d").getResultList());
    	entitymanager.close();
    	emfactory.close();
    	return list;
    }
    
    public static ArrayList<Patient> getPatients() {
    	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "hospital" );
	    EntityManager entitymanager = emfactory.createEntityManager();
    	@SuppressWarnings("unchecked")
    	ArrayList<Patient> list = new ArrayList<>(entitymanager.createQuery("SELECT p FROM Patient p").getResultList());
    	System.out.println(list);
    	entitymanager.close();
    	emfactory.close();
    	return list;
    }
    
    // utils
    
    public static String getRoleById (Long id) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "hospital" );
	    EntityManager entitymanager = emfactory.createEntityManager();
	    switch ((String) entitymanager.createNativeQuery("SELECT role FROM hospital WHERE id = '" + id + "'").getSingleResult()) {
	    case "d" : 
	    	return "doctor";
	    }
	    return "patient";
    }
    
    // not usable anymore	
    
	/*public static ArrayList<Person> getPersons() {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "hospital" );
	    EntityManager entitymanager = emfactory.createEntityManager();
    	@SuppressWarnings("unchecked")
    	ArrayList<Person> list = new ArrayList<>(entitymanager.createQuery("SELECT p FROM Person p").getResultList());
    	entitymanager.close();
    	emfactory.close();
    	return list;
	}
	
	public static void setRole(Person person, String role) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "hospital" );
	    EntityManager entitymanager = emfactory.createEntityManager();
	    entitymanager.createQuery("INSERT INTO roles (id, role) VALUES ('" + person.getId() + "','" + role + "');").executeUpdate();
    	entitymanager.close();
    	emfactory.close();
	}
    
    public static Person getDoctor(Person patient) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "hospital" );
	    EntityManager entitymanager = emfactory.createEntityManager();
	    Person doctor = (Person) entitymanager.createQuery("SELECT p FROM binds JOIN Person p ON binds.doctor_id = users.id WHERE binds.patient_id = '" + patient.getId() + "';").getSingleResult();
    	entitymanager.close();    	
    	emfactory.close();
	    return doctor;
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
	}*/

}
