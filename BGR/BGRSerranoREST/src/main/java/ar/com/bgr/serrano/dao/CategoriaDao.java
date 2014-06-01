/**
 * 
 */
package ar.com.bgr.serrano.dao;

import org.springframework.stereotype.Repository;

import ar.com.bgr.serrano.model.Categoria;

/**
 * 
 * Descripcion:
 * 
 * {}
 *
 * @author matias
 * 
 * @since 31/05/2014
 */
@Repository
public class CategoriaDao extends AbstractDAO<Categoria> {

	public CategoriaDao() {
		setClasz(Categoria.class);
	}
	
}
