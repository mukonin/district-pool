package utils;


import java.util.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@Converter(autoApply=true)
public class JpaDateTimeConverter  implements AttributeConverter<DateTime, Date>{
	
	@Override
	public Date convertToDatabaseColumn(DateTime dateTime) {	  
		return dateTime.toDate();
	}
		
	@Override
	public DateTime convertToEntityAttribute(Date date) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-mm-dd");
        return formatter.parseDateTime(date.toString().split(" ")[0]);
	}
	
}
