package utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import entities.Person;

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

    public static Person valueOf(String personString) throws PersonIOException {

        DateTime date = new DateTime(0L);

        Pattern patternName = Pattern.compile(personNameRexExp);

        Pattern patternDate = Pattern.compile(dateRexExp);

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

}
