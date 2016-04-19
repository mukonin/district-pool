package entity;

public class Patient extends Person {
	
	private Person doctor;	

	public Person getDoctor() {
		return doctor;
	}

	public void setDoctor(Person doctor) {
		this.doctor = doctor;
	}	

}
