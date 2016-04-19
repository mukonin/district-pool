package utils;


import java.util.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.joda.time.DateTime;

@Converter(autoApply=true)
public class JpaDateTimeConverter  implements AttributeConverter<DateTime, Date>{
	
	@Override
	public Date convertToDatabaseColumn(DateTime dateTime) {	  
		return dateTime.toDate();
	}
		
	@Override
	public DateTime convertToEntityAttribute(Date date) {
		return new DateTime(date.getTime());
		//return utils.PersonUtils.getDateFromString(arg0.toString());
	}
	
}
