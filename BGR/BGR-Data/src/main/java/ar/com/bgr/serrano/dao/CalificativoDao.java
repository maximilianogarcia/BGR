package ar.com.bgr.serrano.dao;

import org.springframework.stereotype.Repository;

import ar.com.bgr.serrano.model.Calificativo;

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
public class CalificativoDao extends AbstractDAO<Calificativo>{
    
	public CalificativoDao() {
		setClasz(Calificativo.class);
	}
}
