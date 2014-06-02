/**
 * 
 */
package ar.com.bgr.serrano.integration.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ar.com.bgr.serrano.model.Categoria;
import ar.com.bgr.serrano.model.Producto;
import ar.com.bgr.serrano.service.ProductoService;

/**
 * 
 * Descripcion:
 * 
 * {}
 *
 * @author matias
 * 
 * @since 01/06/2014
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@Transactional
public class ProductoServiceTest {
	
	@Autowired
	ProductoService service;
	
	@Test
	public void testSave() {
		//estos test hay que tirarlos y hacer mejores test
		
		Producto producto= new Producto();
		producto.setName("producto00");
		Categoria categoria = new Categoria();
		categoria.setId(1);
		producto.setCategoria(categoria);
		Producto result = service.save(producto);
		assertNotNull(result);
		assertNotNull(service.getById(result.getId()));
	}

	@Test
	public void testList() {
		System.out.println(service.list());
	}

	@Test
	public void testRemove(){
	}

}
