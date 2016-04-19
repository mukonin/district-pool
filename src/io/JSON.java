package io;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import entity.Hospital;

import java.io.File;
import java.io.IOException;

/**
 *  @ author Mukonin Oleksandr
 *
 */

public class JSON implements HospitalIO {
	
    @Override
    public void writeHospital(Hospital hospital, String fileName) throws IOException{
        try {
            File file = new File(fileName);
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, hospital);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Hospital readHospital(String fileName) throws IOException {
        Hospital hospital = new Hospital();
        try {
            File file = new File(fileName);
            ObjectMapper mapper = new ObjectMapper();
            hospital = mapper.readValue(file, new TypeReference<Hospital>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hospital;
    }
}
