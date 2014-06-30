package ar.com.bgr.serrano.dao;

import org.springframework.stereotype.Repository;

import ar.com.bgr.serrano.model.Contacto;

/**
 * 
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
public class ContactoDAO extends AbstractDAO<Contacto>{

	public ContactoDAO() {
		setClasz(Contacto.class);
	}

}
