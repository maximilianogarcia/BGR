/**
 * 
 */
package ar.com.bgr.serrano.dao3;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.com.bgr.serrano.model.Lote;
import ar.com.bgr.serrano.model.Producto;

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
public class LoteDAO extends AbstractDAO<Lote>{

	public LoteDAO() {
		setClasz(Lote.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Lote> getLotesByProducto(Producto producto){
		Criteria criteria =  getCurrentSession().createCriteria(getClasz());
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.add(Restrictions.eq("productoId", producto.getId()));
		return  criteria.list();
	}
	
	
	public Lote desactivar(Integer id){
		Query query = getCurrentSession().createQuery("update Lote where id = :id");
		query.setParameter("id", id);
		query.executeUpdate();
		return getById(id);
	}
	
}
