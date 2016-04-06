package utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;

/**
 * @ author Mukonin Oleksandr
 *
 */
public class DateJsonAdapter extends JsonSerializer<DateTime> {
    DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.MM.yyyy");

    @Override
    public void serialize(DateTime date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException, JsonProcessingException {
        jsonGenerator.writeString(formatter.print(date));
    }


}
