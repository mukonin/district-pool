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

}
