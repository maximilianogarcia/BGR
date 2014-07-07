/**
 * 
 */
package ar.com.bgr.serrano.dao;

import org.springframework.stereotype.Repository;

import ar.com.bgr.serrano.model.Proveedor;

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
public class ProveedorDAO extends AbstractDAO<Proveedor> {

	public ProveedorDAO(){
		setClasz(Proveedor.class);
	}
	
}
