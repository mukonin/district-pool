package entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.TreeSet;

/**
 * @ author Mukonin Oleksandr
 */
@XmlRootElement(name = "hospital")
public class Hospital {

    @XmlElement(name = "doctor")
    private TreeSet<Doctor> doctors = new TreeSet<>();

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public void addPatient(Doctor doctor, Person patient) {
        doctor.getPatients().add(patient);
    }

    public TreeSet<Doctor> getDoctors() {
        return doctors;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Doctor doctor : doctors) {
            builder.append(doctor.getPerson().toString());
            builder.append(" {");
            for (Person patient : doctor.getPatients()) {
                builder.append(patient.toString());
                if (!(doctor.getPatients().last() == patient)) {
                    builder.append(", ");
                }
            }
            builder.append("}");
            if (!(doctor == this.getDoctors().last())) {
                builder.append(",");
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
