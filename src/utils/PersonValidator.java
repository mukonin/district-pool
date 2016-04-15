package utils;

import java.util.Random;
import java.util.regex.Pattern;

import org.joda.time.DateTime;

import entities.Person;

public class PersonValidator {
	
	public PersonValidator(String firstName, String lastName, DateTime date) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.date = date;
		person = null;
		validate();
		if (valid) {
			Random random = new Random();
			person = new Person();
			person.setId((long) (random.nextDouble() * 9000000000L) + 1000000000L);
			person.setDate(date);
			person.setFirstName(firstName);
			person.setLastName(lastName);
		}
	}
	
	private Person person;
	private String firstName;
	private String lastName;
    private DateTime date;
    
    private String errorMessage;
    private boolean valid;
    
    private static final String personNameRexExp = "[A-Z][a-z]+";
    private static final String dateRexExp = "\\(\\d{2}.\\d{2}.\\d{4}\\)";
    private static final String dateFormatString = "dd.MM.yyyy";
    private static final Pattern patternName = Pattern.compile(personNameRexExp);
    private static final Pattern patternDate = Pattern.compile(dateRexExp);
    
	public Person getPerson() {
		return person;
	}	

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public boolean isValid() {
		return valid;
	}

    public void validate() {
    	StringBuilder builder = new StringBuilder();
    	valid = true;
    	if ( ! patternName.matcher(firstName).matches()) {
    		valid = false;
    		builder.append("wrong first name format ");
    	};
    	if ( ! patternName.matcher(lastName).matches()) {
    		valid = false;
    		builder.append("wrong last name format ");
    	};
    	if ( ! patternDate.matcher(date.toString(dateFormatString)).matches()) {
    		valid = false;
    		builder.append("wrong date format ");
    	};
    	if (date.isAfter(new DateTime().getMillis())) {
    		valid = false;
    		builder.append("wrong date (after today) ");
    	}
    	errorMessage = builder.toString();
    }	
}
