/**
 * 
 */
package ar.com.bgr.serrano.dao;

import java.util.List;
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

	private final boolean NO_DIVISBLE = false;

	public UnidadDeMedidaDAO(){
		setClasz(UnidadDeMedida.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<UnidadDeMedida> listNoDivisibles(){
		Criteria criteria =  getCurrentSession().createCriteria(getClasz());
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.add(Restrictions.eq("divisible", NO_DIVISBLE)).list();
	}
}
