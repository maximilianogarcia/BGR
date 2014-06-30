package ar.com.bgr.serrano.jackson;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

/**
 * Descripcion:
 * 
 * Custom DateSerializer
 * 
 * @author matias
 *
 */
public class DateJsonSerializer extends JsonSerializer<Date> {

	private static final SimpleDateFormat DATEFORMAT = new SimpleDateFormat("dd/MM/yyyy");
	
	@Override
	public void serialize(Date value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		jgen.writeString(DATEFORMAT.format(value));
	}
	
}
