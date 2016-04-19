package io;

import java.io.IOException;

import entity.Hospital;

public interface HospitalIO {

    void writeHospital(Hospital hospital, String fileName) throws IOException;

    Hospital readHospital(String fileName) throws IOException;

}
