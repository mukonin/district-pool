import javax.xml.bind.JAXBException;

import dao.DAO;
import entities.*;
import io.*;
import utils.PersonUtils;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @ author Mukonin Oleksandr
 *
 * for test
 */
public class Tester {
    public static void main(String[] args) throws IOException, JAXBException {
        Hospital hospital;
        Hospital hospital1 = new Hospital();

       
        HospitalIO io = new TXT();
        hospital = io.readHospital("hospital.txt");

        try {
            DAO.dropDB();
            DAO.createDB();
            SQL.writeHospital(hospital);

            Person person1 = PersonUtils.valueOf("Daniels Jack (12.12.1940)");
            Person person2 = PersonUtils.valueOf("Jack Daniels (12.12.1941)");


            DAO.addUser(person1);
            DAO.addUser(person2);

            ArrayList<Person> list = DAO.getByFullName("Jack Daniels");
            System.out.println(list);





            //System.out.println("----------------------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //System.out.println(hospital1);






        System.out.println("----------------------------------------------");
        //System.out.println(hospital);

    }
}