package ar.com.bgr.serrano.jackson;

import java.util.Date;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.map.ser.CustomSerializerFactory;


public class CustomObjectMapper extends ObjectMapper {
    
    public CustomObjectMapper()  {
        CustomSerializerFactory factory = new CustomSerializerFactory();
        factory.addSpecificMapping(Date.class, new DateJsonSerializer());
        this.setSerializerFactory(factory).setSerializationInclusion(Inclusion.NON_NULL);
    }
}