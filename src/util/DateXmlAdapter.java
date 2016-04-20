package util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @ author Mukonin Oleksandr
 *
 */
public class DateXmlAdapter extends XmlAdapter<String, DateTime> {

    @Override
    public String marshal(DateTime date) throws Exception {
        return date.toString("dd.MM.yyyy");
    }

    @Override
    public DateTime unmarshal(String dateString) throws Exception {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.MM.yyyy");
        return formatter.parseDateTime(dateString);
    }
}
