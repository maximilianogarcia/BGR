/**
 * 
 */
package ar.com.bgr.serrano.dao;

import org.springframework.stereotype.Repository;

import ar.com.bgr.serrano.model.Producto;

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
@Repository
public class ProductoDao extends AbstractDAO<Producto> {

	public ProductoDao(){
		setClasz(Producto.class);
	}
	
}
