/**
 * 
 */
package ar.com.bgr.serrano.dao;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import ar.com.bgr.serrano.dao.exception.ConstraintViolatedException;
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
public class CategoriaDAO extends AbstractDAO<Categoria> {

	public CategoriaDAO() {
		setClasz(Categoria.class);
	}

	@Override
	public void remove(int id){
		try{
			super.remove(id);
		}catch(DataIntegrityViolationException e){
			throw new ConstraintViolatedException();
		}
	}
}
