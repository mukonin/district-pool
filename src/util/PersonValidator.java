package util;

import java.util.regex.Pattern;

import org.joda.time.DateTime;

import entity.Person;

public class PersonValidator {
	
	public PersonValidator(String firstName, String lastName, String dateString) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateString = dateString;
		person = null;
		validate();
		if (valid) {			
			person = new Person();
			person.setDate(date);
			person.setFirstName(firstName);
			person.setLastName(lastName);
		}
	}
	
	private Person person;
	private String firstName;
	private String lastName;
	private String dateString;
    private DateTime date;
    
    private String errorMessage;
    private boolean valid = false;
    
    private static final String personNameRexExp = "[A-Z][a-z]+";
    private static final Pattern patternName = Pattern.compile(personNameRexExp);
    
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
    	try {
    		date = util.PersonUtils.getDateFromString(dateString);
    		if (date.isAfter(new DateTime().getMillis())) {
        		valid = false;
        		builder.append("wrong date (after today) ");
        	}
    	} catch (Exception e) {
    		valid = false;
    		builder.append("error converting date");
    	}
    	errorMessage = builder.toString();
    }	
}
