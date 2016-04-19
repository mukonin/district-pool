package io;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import entity.*;

import java.io.File;
import java.io.IOException;

/**
 * @ author Mukonin Oleksandr
 *
 */
public class XML implements HospitalIO {

    @Override
    public void writeHospital(Hospital hospital, String fileName) throws IOException {
        try {
            File file = new File(fileName);
            JAXBContext jaxbContext = JAXBContext.newInstance(Hospital.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(hospital, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Hospital readHospital(String fileName) throws IOException {
        Hospital hospital = new Hospital();
        try {
            File file = new File(fileName);
            JAXBContext jaxbContext = JAXBContext.newInstance(Hospital.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            hospital = (Hospital) jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return hospital;
    }
}
