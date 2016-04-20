package util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import entity.Person;

import java.util.Comparator;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * @ author Mukonin Oleksandr
 *
 */
public abstract class PersonUtils {

    public static final String personNameRexExp = "[A-Z][a-z]+";
    public static final String dateRexExp = "\\(\\d{2}.\\d{2}.\\d{4}\\)";
    public static final String dateFormatString = "dd.MM.yyyy";
    public static final Pattern patternName = Pattern.compile(personNameRexExp);
    public static final Pattern patternDate = Pattern.compile(dateRexExp);

    public static Person valueOf(String personString) throws PersonIOException {

        DateTime date = new DateTime(0L);

        String errorMsg[] = new String[4];

        boolean personStringInvalid = false;

        /***
         * values.length - number of values in personString separated by whitespace, Last Name, First Name and Date in
         * format dd.MM.yyyy in parentheses
         */
        String[] values = personString.split(" ");
        if (! (values.length == 3)) {
            throw new PersonIOException("Invalid input format (invalid number of values) for String: " + personString) ;
        }

        if (! patternName.matcher(values[0]).matches()) {
            personStringInvalid = true;
            errorMsg[1] = values[0];
        }

        if (! patternName.matcher(values[1]).matches()) {
            personStringInvalid = true;
            errorMsg[2] = values[1];
        }

        try {
            date = getDateFromString(values[2].replaceAll("[()]", ""));
            if (date.isAfter(new DateTime())) {
                personStringInvalid = true;
                errorMsg[3] = values[2];
            }
        } catch (IllegalArgumentException e) {
            personStringInvalid = true;
            errorMsg[3] = values[2];
        }

        if (personStringInvalid) {
            errorMsg[0] = personString;
            throw new PersonIOException(errorMsg);
        }

        Random random = new Random();

        Person person = new Person();
        person.setLastName(values[0]);
        person.setFirstName(values[1]);
        person.setDate(date);
        person.setId((long) (random.nextDouble() * 9000000000L) + 1000000000L);

        return person;
    }

    public static String getStringFromDate(DateTime date){
        return date.toString(dateFormatString);
    }

    public static DateTime getDateFromString(String dateString) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(dateFormatString);
        return formatter.parseDateTime(dateString);
    }
    
    public static boolean validateName(String name) {	
    	return patternName.matcher(name).matches();
    }

    public static boolean validateDateString(String date) {	
    	try {
            DateTime dateTime = getDateFromString(date.replaceAll("[()]", ""));
            if (dateTime.isAfter(new DateTime())) return false;
            return true;
        } catch (IllegalArgumentException e) {
        	return false;
        }
    }
    
    public static final Comparator<Person> DATA_SORT = new Comparator<Person>() {
    	public int compare(Person p1, Person p2) {
    		return p1.getDate().compareTo(p2.getDate());
    	}
    };
    
}
