package ar.com.bgr.serrano.dao;

import org.springframework.stereotype.Repository;

import ar.com.bgr.serrano.model.Etiqueta;

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
public class EtiquetaDao extends AbstractDAO<Etiqueta>{
   
	public EtiquetaDao() {
		setClasz(Etiqueta.class);
	}
}
