package io;

import java.io.IOException;

import entities.Hospital;

public interface HospitalIO {

    void writeHospital(Hospital hospital, String fileName) throws IOException;

    Hospital readHospital(String fileName) throws IOException;

}
