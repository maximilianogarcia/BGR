package ar.com.bgr.serrano.utils;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import ar.com.bgr.serrano.model.Producto;
import ar.com.bgr.serrano.model.ProductoProveedor;
import ar.com.bgr.serrano.model.UnidadDeMedida;

public class ProductoJsonSerializer extends JsonSerializer<Producto> {

	@Override
	public void serialize(Producto value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		
		
	    jgen.writeStartObject();
	    if(value.getId() != null)
	    jgen.writeNumberField("id", value.getId());
        
	    if(value.getName() != null)
        jgen.writeStringField("name", value.getName());
        if(value.getActualizador_precio() != null)
        jgen.writeNumberField("actualizador_precio", value.getActualizador_precio());
        
        if(value.getCategoria() != null){
        	jgen.writeFieldName("categoria");
        	jgen.writeStartObject();
        	
        	if(value.getCategoria().getId() != null)
        	jgen.writeNumberField("id", value.getCategoria().getId());
        	if(value.getCategoria().getName() != null)
        	jgen.writeStringField("name", value.getCategoria().getName());
        	if(value.getCategoria().getName() != null)
            jgen.writeStringField("descripcion", value.getCategoria().getDescripcion());
        	
        	jgen.writeEndObject();
        }
        
        jgen.writeFieldName("productoProveedor");
        jgen.writeStartArray();
        if(value.getProductoProveedor() != null){
        	for(ProductoProveedor pp: value.getProductoProveedor()){
        		jgen.writeStartObject();

        		if(pp.getCantidad()!= null)
        		jgen.writeNumberField("cantidad", pp.getCantidad());

        		if(pp.getPrecio_reposicion()!= null)
        		jgen.writeNumberField("precio_reposicion", pp.getPrecio_reposicion());
        		
        		if(pp.getProveedor() != null){
        			jgen.writeFieldName("proveedor");
        			jgen.writeStartObject();
        			if(pp.getProveedor().getId() != null)
        			jgen.writeNumberField("id", pp.getProveedor().getId());	
        			if(pp.getProveedor().getName() != null)
        			jgen.writeStringField("name", pp.getProveedor().getName());
        			jgen.writeEndObject();
        		}
        		
        		jgen.writeEndObject();
        	}
        }
        jgen.writeEndArray();
        
        
        jgen.writeFieldName("unidadesDeMedida");
        jgen.writeStartArray();
        if(value.getUnidadesDeMedida() != null){
        	for(UnidadDeMedida u : value.getUnidadesDeMedida()){
        		jgen.writeStartObject();
        		if(u.getId() != null)
        		jgen.writeNumberField("id", u.getId() );
        		if(u.getName() != null)
        		jgen.writeStringField("name", u.getName() );
        		if(u.getDescripcion() != null)
        		jgen.writeStringField("descripcion", u.getDescripcion() );
           		if(u.getDivisible() != null){
           			jgen.writeFieldName("divisible");
           			jgen.writeBoolean(u.getDivisible());
           		}	
           		if(u.getEquivalencia() != null)
           			jgen.writeNumberField("equivalencia", u.getEquivalencia() );
           		
           		if(u.getDerivaDe() != null)
           			jgen.writeNumberField("deriva_de", u.getDerivaDe().getId() );
           		
           		if(u.getDescripcion() != null)
            		jgen.writeStringField("descripcion", u.getDescripcion() );
        		jgen.writeEndObject();
        	}
        }
        jgen.writeEndArray(); 
           
        jgen.writeEndObject();
		
	}

}
