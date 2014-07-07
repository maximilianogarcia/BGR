/**
 * 
 */
package ar.com.bgr.serrano.dao3;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.com.bgr.serrano.model.Remanente;

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
public class RemanenteDAO extends AbstractDAO<Remanente> {

	public RemanenteDAO() {
		setClasz(Remanente.class);
	}

	public Remanente getByProducto(int id) {
		Criteria criteria =  getCurrentSession().createCriteria(getClasz());
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.add(Restrictions.eq("producto.id", id));
		return (Remanente) criteria.uniqueResult();
	}
	
}
