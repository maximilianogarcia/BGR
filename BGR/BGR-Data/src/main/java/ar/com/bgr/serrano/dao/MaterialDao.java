/**
 * 
 */
package ar.com.bgr.serrano.dao;

import org.springframework.stereotype.Repository;

import ar.com.bgr.serrano.model.Material;

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
public class MaterialDao extends AbstractDAO<Material>{

	public MaterialDao() {
		setClasz(Material.class);
	}
}
