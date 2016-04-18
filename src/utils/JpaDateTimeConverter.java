package utils;

import java.util.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.joda.time.DateTime;

@Converter(autoApply=true)
public class JpaDateTimeConverter  implements AttributeConverter<DateTime, Date>{
	
	@Override
	public Date convertToDatabaseColumn(DateTime arg0) {	  
		return arg0.toDate();
	}
		
	@Override
	public DateTime convertToEntityAttribute(Date arg0) {
		return new DateTime(arg0.getTime());
		//return utils.PersonUtils.getDateFromString(arg0.toString());
	}
	
}
