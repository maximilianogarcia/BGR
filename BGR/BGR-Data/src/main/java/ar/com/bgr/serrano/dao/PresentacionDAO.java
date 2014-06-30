package ar.com.bgr.serrano.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.com.bgr.serrano.model.Presentacion;

/**
 * 
 * @author matias
 *
 */
@Repository
public class PresentacionDAO extends AbstractDAO<Presentacion>{

	public PresentacionDAO() {
		setClasz(Presentacion.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Presentacion> getPresentacionesByState(Boolean active){
		Criteria criteria =  getCurrentSession().createCriteria(getClasz());
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.add(Restrictions.eq("active", active));
		return  criteria.list();
	}

	public Presentacion changeState(Integer id, String message, Boolean actualState) {
		Presentacion p  = getById(id);
		p.setMessage(message);
		saveOrUpdate(p);		
		Query query = getCurrentSession().createQuery("update Paquete SET estado = :estado WHERE presentacion_id = :id AND estado = :estadoActual");
		query.setParameter("id", id);
		query.setParameter("estadoActual", State.getState(actualState));
		query.setParameter("estado", State.getState(!actualState));
		query.executeUpdate();
		return p;
	}
	

}
