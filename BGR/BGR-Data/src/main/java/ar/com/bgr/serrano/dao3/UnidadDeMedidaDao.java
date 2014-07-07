/**
 * 
 */
package ar.com.bgr.serrano.dao3;

import java.util.Set;

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
public class UnidadDeMedidaDAO extends AbstractDAO<UnidadDeMedida> {

	private final Integer NO_DIVISBLE = 0;

	public UnidadDeMedidaDAO(){
		setClasz(UnidadDeMedida.class);
	}
	
	@SuppressWarnings("unchecked")
	public Set<UnidadDeMedida> listNoDivisibles(){
		Criteria criteria =  getCurrentSession().createCriteria(getClasz());
		return (Set<UnidadDeMedida>) criteria.add(Restrictions.eq("divisible", NO_DIVISBLE));
	}
}
