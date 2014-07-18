package ar.com.bgr.serrano.utils;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.ObjectMapper;

import ar.com.bgr.serrano.model.Categoria;
import ar.com.bgr.serrano.model.Producto;
import ar.com.bgr.serrano.model.ProductoProveedor;
import ar.com.bgr.serrano.model.Proveedor;
import ar.com.bgr.serrano.model.UnidadDeMedida;

/**
 * Custom deserializer to avoid cyclic reference exception
 * @author matias
 *
 */
public class ProductoJsonDeserializer extends JsonDeserializer<Producto> {

	private ObjectMapper mapper;

	@Override
	public Producto deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {

		JsonNode node = jp.getCodec().readTree(jp);
		
	
		Producto p = new Producto();
		if(node.get("name")!=null)
		p.setName(node.get("name").asText());
		if(node.get("id")!=null)
		p.setId(node.get("id").asInt());
		if(node.get("categoria")!=null)
		p.setCategoria(getMapper().readValue(node.get("categoria"), Categoria.class));
		if(node.get("actualizador_precio")!=null)
		p.setActualizador_precio(node.get("actualizador_precio").asDouble());
		
		if(node.get("unidadesDeMedida")!=null){			
			Set<UnidadDeMedida> listU = getMapper().readValue(node.get("unidadesDeMedida"), mapper.getTypeFactory().constructCollectionType(Set.class, UnidadDeMedida.class));
			p.setUnidadesDeMedida(listU);
		}
		
		if(node.get("productoProveedor") != null){
			Set<ProductoProveedor> list = getMapper().readValue(node.get("productoProveedor"), mapper.getTypeFactory().constructCollectionType(Set.class, ProductoProveedor.class));
	        for(ProductoProveedor pp : list){
	        	pp.setProducto(p);
	        }	
	    	p.setProductoProveedor(list);
		}else{
			if(node.get("proveedores") != null){
				List<Proveedor> list = getMapper().readValue(node.get("proveedores"), mapper.getTypeFactory().constructCollectionType(List.class, Proveedor.class));
		        for(Proveedor pp : list){
		        	p.getProductoProveedor().add(new ProductoProveedor(p,pp));
		        }	
		    	
			}
		}

		return p; 
	}

	private ObjectMapper getMapper(){
		if(mapper == null){ 
		    mapper = new ObjectMapper();
		}
		return mapper;
	}
}
