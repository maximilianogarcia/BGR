package ar.com.bgr.serrano.dao;

import org.springframework.stereotype.Repository;

import ar.com.bgr.serrano.model.Mix;

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
public class MixDAO extends AbstractDAO<Mix> {

	public MixDAO(){
		super();
		setClasz(Mix.class);
	}
	
}
