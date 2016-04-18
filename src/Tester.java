import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBException;

import dao.DAO;
import entities.*;
import io.*;
import persistence.PersonService;
import utils.PersonUtils;

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
        }*/

        //System.out.println(hospital1);

    	
    	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "hospital" );
        EntityManager entitymanager = emfactory.createEntityManager();
        List<Person> list = (List<Person>) entitymanager.createQuery("SELECT p FROM Person p").getResultList();
        System.out.println(list);




        System.out.println("----------------------------------------------");
        //System.out.println(hospital);

    }
}