package ar.com.bgr.serrano.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.com.bgr.serrano.model.Presentacion;
import ar.com.bgr.serrano.model.Stock;

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
		p.setActive(!actualState);
		p.setMessage(message);
		saveOrUpdate(p);		
		Query query = getCurrentSession().createQuery("update Paquete SET estado = :estado WHERE presentacion_id = :id AND estado = :estadoActual");
		query.setParameter("id", id);
		query.setParameter("estadoActual", State.getState(actualState).toString());
		query.setParameter("estado", State.getState(!actualState).toString());
		query.executeUpdate();
		return p;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Stock> getStocks(){
		Criteria criteria = getCurrentSession().createCriteria(Stock.class);
		criteria.add(Restrictions.eq("estado", "DISPONIBLE"));
		return criteria.list();		
	}

	@SuppressWarnings("unchecked")
	public List<Stock> getStocksByCategory(Integer id){
		Criteria criteria = getCurrentSession().createCriteria(Stock.class);
		criteria.add(Restrictions.eq("estado", "DISPONIBLE"));
		criteria.add(Restrictions.eq("categoria", id));
		return criteria.list();		
	}
	

	@SuppressWarnings("unchecked")
	public List<Object[]> getStocksByProduct(Integer id){
		Criteria criteria = getCurrentSession().createCriteria(Stock.class);
		criteria.add(Restrictions.eq("estado", "DISPONIBLE"));
		criteria.add(Restrictions.eq("productoId", id));
		return criteria.list();		
	    

	}
}
