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

        //System.out.println(hospital1);






        System.out.println("----------------------------------------------");
        //System.out.println(hospital);

    }
}