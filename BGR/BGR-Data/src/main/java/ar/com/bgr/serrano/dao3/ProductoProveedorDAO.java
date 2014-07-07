/**
 * 
 */
package ar.com.bgr.serrano.dao3;

import org.springframework.stereotype.Repository;

import ar.com.bgr.serrano.model.ProductoProveedor;

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
public class ProductoProveedorDAO extends AbstractDAO<ProductoProveedor> {

	public ProductoProveedorDAO(){
		setClasz(ProductoProveedor.class);
	}

	public void remove(ProductoProveedor pp) {
		getCurrentSession().delete(pp);
	}

}
