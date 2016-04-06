package utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;

/**
 * @ author Mukonin Oleksandr
 *
 */
public class JsonDateAdapter extends JsonDeserializer<DateTime>{

    @Override
    public DateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException,
            JsonProcessingException {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.MM.yyyy");
        return formatter.parseDateTime(p.getText());
    }
}
