/**
 * 
 */
package ar.com.bgr.serrano.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.com.bgr.serrano.model.UnidadDeMedida;

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
public class UnidadDeMedidaDao extends AbstractDAO<UnidadDeMedida> {

	private final Integer NO_DIVISBLE = 0;

	public UnidadDeMedidaDao(){
		setClasz(UnidadDeMedida.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<UnidadDeMedida> listNoDivisibles(){
		Criteria criteria =  getCurrentSession().createCriteria(getClasz());
		return (List<UnidadDeMedida>) criteria.add(Restrictions.eq("divisible", NO_DIVISBLE));
	}
}
