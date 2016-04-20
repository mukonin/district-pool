import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBException;

import org.joda.time.DateTime;

import dao.DAO;
import entity.*;
import io.*;
import persistence.PersonService;
import util.PersonUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @ author Mukonin Oleksandr
 *
 * for test
 */
public class Tester {
    public static void main(String[] args) throws IOException, JAXBException {
        

    	Person person = new Person();
    	person.setFirstName("John");
    	person.setLastName("Smith");
    	person.setDate(new DateTime(1980, 05, 01, 0, 0));
    	
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "hospital" );
	    EntityManager entitymanager = emfactory.createEntityManager();
	    entitymanager.getTransaction().begin();
	        
	    
	    entitymanager.persist(person);
	    
	    entitymanager.getTransaction().commit();
	    
	    
	    
	    
    	entitymanager.close();
    	emfactory.close();
    
    	
    	
    	
    	/*Hospital hospital;

       
        HospitalIO io = new TXT();
        hospital = io.readHospital("hospital.txt");

        try {
            DAO.dropDB();
            DAO.createDB();
            SQL.writeHospital(hospital);





            //System.out.println("----------------------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
*/
        //System.out.println(hospital1);



        System.out.println("----------------------------------------------");
        //System.out.println(hospital);

    }
}