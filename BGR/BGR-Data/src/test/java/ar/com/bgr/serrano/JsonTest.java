package ar.com.bgr.serrano;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.bgr.serrano.model.Categoria;
import ar.com.bgr.serrano.model.Lote;
import ar.com.bgr.serrano.model.Producto;
import ar.com.bgr.serrano.model.ProductoProveedor;
import ar.com.bgr.serrano.model.Proveedor;
import ar.com.bgr.serrano.model.UnidadDeMedida;
import ar.com.bgr.serrano.utils.MixImpresentation;



@RunWith(MockitoJUnitRunner.class)
public class JsonTest {
	
	
	public enum State {
		DISPONIBLE, DESACTIVADO;
		
		private static Map<Boolean, State> stateMap;				
	    public static State getState(Boolean state) {
	        if (stateMap == null)       
	        initMap();	        
	        return stateMap.get(state);
	    }
	    
		private static void initMap() {
			stateMap = new HashMap<Boolean, State>();
	        stateMap.put(Boolean.TRUE, State.DISPONIBLE);
	        stateMap.put(Boolean.FALSE, State.DESACTIVADO);	
		}
	}
	
	@Test public void testEnum(){
		
		
		System.out.println(State.getState(true));
		System.out.println(State.getState(false));
		
	}
	
	
	@Test
	public void testJsonLote() throws JsonGenerationException, JsonMappingException, IOException{
		
		Lote l = new Lote();
		l.setFechaDeElaboracion(new Date());
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(l));
		
	}
	
	
	@Test
	public void testJsonClass() throws JsonGenerationException, JsonMappingException, IOException{


		Producto p = new Producto();
		p.setName("un nombre deccc producto");
		p.setId(11);
		p.setActualizador_precio((double)123);
		
		
		
		Categoria categoria = new Categoria();
		categoria.setId(2);
		categoria.setName("nombre de categoria");
		categoria.setDescripcion("descripcion de categoria");
		p.setCategoria(categoria );
		Set<ProductoProveedor> productoProveedorList = new TreeSet<ProductoProveedor>();
		Proveedor pr = new Proveedor();
		pr.setId(1);
		pr.setName("un proveedor");
		
		ProductoProveedor pp= new ProductoProveedor(p,pr);
		pp.setCantidad((double) 18);
		pp.setPrecio_reposicion((double) 90);
		productoProveedorList.add(pp);
		p.setProductoProveedor(productoProveedorList );
		
		
		List<UnidadDeMedida> unidadesDeMedida = new ArrayList<UnidadDeMedida>();
		UnidadDeMedida e = new UnidadDeMedida();
		e.setName("uuuuu");
		unidadesDeMedida.add(e);
		//p.setUnidadesDeMedida(unidadesDeMedida);
		
		
		
	ObjectMapper mapper = new ObjectMapper();
	System.out.println(mapper.writeValueAsString(p));
	

	 String ss = "{\"id\":2,\"name\":\"producto00\",\"categoria\":{\"id\":1,\"name\":\"zxcvbn\",\"descripcion\":\"siiiiekjkhg\"},\"unidadesDeMedida\":[{\"id\":1,\"name\":\"Kg\",\"descripcion\":\"kilo\",\"divisible\":null,\"equivalencia\":null,\"derivaDe\":null}],\"proveedores\":[{\"id\":2,\"name\":\"dos\"}],\"actualizador_precio\":\"12\",\"productoProveedor\":[{\"cantidad\":1,\"precio_reposicion\":1,\"proveedor\":{\"id\":1,\"name\":\"uno\"}},{\"cantidad\":2,\"precio_reposicion\":2,\"proveedor\":{\"id\":2,\"name\":\"dos\"}}]}";
	 Producto rp2 = mapper.readValue(ss,Producto.class);
	 
	 System.out.println("Serialization 2: " + mapper.writeValueAsString(rp2));

	 for(ProductoProveedor ppp: rp2.getProductoProveedor()){
		 System.out.println("producto proveedor"+ppp);
	 }
	 
		String sss = "{\"cantidad\":2,\"precio_reposicion\":2,\"proveedor\":{\"id\":2,\"name\":\"dos\"},\"producto\":{\"id\":3,\"name\":\"producto2\",\"categoria\":{\"id\":1,\"name\":\"zxcvbn\",\"descripcion\":\"siiiiekjkhg\"},\"unidadesDeMedida\":[],\"proveedores\":[{\"id\":2,\"name\":\"dos\"},{\"id\":1,\"name\":\"uno\"}],\"actualizador_precio\":\"123\",\"productoProveedor\":[{\"cantidad\":1,\"precio_reposicion\":1,\"proveedor\":{\"id\":1,\"name\":\"uno\"}},{\"cantidad\":2,\"precio_reposicion\":2,\"proveedor\":{\"id\":2,\"name\":\"dos\"}}]}}";

		ProductoProveedor rp3 = mapper.readValue(sss, ProductoProveedor.class);
		System.out.println(mapper.writeValueAsString(rp3));
		System.out.println("producto proveedor");

	
		ss   ="{\"cantidad\":2," +
		"\"precio_reposicion\":\"22\",                    "+
		"\"proveedor\":{\"id\":2,\"name\":\"dos\"},       "+
		"\"producto\":{\"id\":3,\"name\":\"producto2\",   "+
		"\"categoria\":{\"id\":1,\"name\":\"zxcvbn\",     "+
		"\"descripcion\":\"siiiiekjkhg\"},                "+
		"\"unidadesDeMedida\":[],                         "+
		"\"actualizador_precio\":[],                      "+
		"\"proveedores\":[{\"id\":1,\"name\":\"uno\"},{\"id\":2,\"name\":\"dos\"}]," +
		"\"productoProveedor\":[{\"cantidad\":1,          "+
		"\"precio_reposicion\":1,                         "+
		"\"proveedor\":{\"id\":1,\"name\":\"uno\"}},      "+
		"{\"cantidad\":2,\"precio_reposicion\":2,         "+
		"\"proveedor\":{\"id\":2,\"name\":\"dos\"}}]}}";
		
		

		ProductoProveedor rp4 = mapper.readValue(ss, ProductoProveedor.class);
		rp4.getCantidad();
		
		
		
		String jsonLote ="{\"precio_compra\":\"12\",\"descripcion\":\"un lote\",\"fechaDeVencimiento\":\"2014-07-19\",\"fechaDeElaboracion\":\"2014-06-25\",\"producto\":{\"id\":3,\"name\":\"producto2432\",\"actualizador_precio\":26,\"categoria\":{\"id\":1,\"name\":\"zxcvbn\",\"descripcion\":\"siiiiekjkhg\"},\"productoProveedor\":[{\"precio_reposicion\":26,\"proveedor\":{\"id\":2,\"name\":\"dos\"}},{\"precio_reposicion\":26,\"proveedor\":{\"id\":1,\"name\":\"uno\"}}],\"unidadesDeMedida\":[]}}";
		
		
		Lote lote = mapper.readValue(jsonLote, Lote.class);
		lote.getPrecio_compra();
		
		
		//String jsonPresentacion = "{\"descripcion\":\"una presentacion\",\"active\":true,\"fraccionable\":false,\"producto\":{\"id\":2,\"name\":\"nuevo\",\"actualizador_precio\":26,\"categoria\":{\"id\":1,\"name\":\"zxcvbn\",\"descripcion\":\"siiiiekjkhg\"},\"productoProveedor\":[{\"precio_reposicion\":26,\"proveedor\":{\"id\":2,\"name\":\"dos\"}},{\"precio_reposicion\":26,\"proveedor\":{\"id\":1,\"name\":\"uno\"}}],\"unidadesDeMedida\":[{\"id\":2,\"name\":\"Kg\",\"descripcion\":\"kilorr\"}]},\"peso_neto\":\"10\",\"peso_escurrido\":\"9\",\"cantidad_paquetes\":\"10\",\"cantidad\":\"10\",\"proveedor\":{\"id\":1,\"name\":\"uno\"},\"lote\":{\"id\":2,\"productoId\":0,\"descripcion\":\"un lote\",\"fechaDeElaboracion\":\"2014-06-25\",\"fechaDeVencimiento\":\"2014-07-23\",\"precio_compra\":12,\"proveedor_id\":0},\"date_create\":\"2014-06-26\",\"date_update\":\"2014-06-26\",\"material\":{\"id\":3,\"name\":\"otro material\",\"descripcion\":\"desc\",\"peso\":2,\"alto\":1,\"ancho\":1,\"largo\":1},\"unidad_de_medida\":{\"id\":2,\"name\":\"Kg\",\"descripcion\":\"kilorr\"}}";
		
		//Presentacion presentacion = mapper.readValue(jsonPresentacion, Presentacion.class);
		
		
		
		
	}
	
	@Test
	public void testMix() throws JsonParseException, JsonMappingException, IOException{
		
		String mixJson = "{\"nombre\":\"un nombre\",\"vencimiento\":\"2014-06-24\",\"unidad_de_medida\":\"2\",\"categoria\":\"1\",\"paquetes_mix\":[{\"id\":2,\"paquetes\":[{\"id\":1,\"presentacion_id\":3,\"codigo\":\"codigo disponible\",\"estado\":\"DISPONIBLE\",\"cantidad\":21,\"unidad\":2,\"nombre\":null}],\"cantidad\":21,\"cant_elegida\":\"3\"}]}";
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.readValue(mixJson, MixImpresentation.class));
	}

}
