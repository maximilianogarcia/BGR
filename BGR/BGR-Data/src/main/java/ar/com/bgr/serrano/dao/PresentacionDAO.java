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
	
	
	public List<Object[]> getStocks(){
    
    	String select = "  SELECT  "+ 
        		"	COUNT( * ) as stock,  "+
        		"	p.id as id, "+
         		"	u.name as medida, "+
         		"	p.descripcion as presentacion, "+
        		"	(p.peso_neto * COUNT( * ) )/ 1000 as kgTotal, "+
        		"	pr.name as producto, "+
        		"	lote.descripcion as lote, "+
        		"	lote.fechaDeElaboracion as elaboracion, "+
        		"	lote.fechaDeVencimiento as vencimiento";


        String from =" FROM Presentacion p                                            "+
	                 " JOIN Paquete pa ON ( pa.presentacion_id = p.id )      "+
	            	 " JOIN Producto pr on (p.producto_id = pr.id)           "+
	                 " JOIN Lote lote on (p.lote_id = lote.id)               "+
	                 " JOIN UnidadDeMedida u on (p.unidadDeMedida_id = u.id) "; 
        
        
		String where =" WHERE pa.estado= :estado" +
					  " GROUP BY pa.presentacion_id";

		Query query = getCurrentSession().createSQLQuery(select+from+where)
		.setParameter("estado", State.DISPONIBLE.toString());

    
		@SuppressWarnings("unchecked")
		List<Object[]> rows = query.list();
//		List<PaqueteFromQuery> m = new ArrayList<PaqueteFromQuery>();
//		for (Object[] row : rows) {
//			m.add(new PaqueteFromQuery((Integer) row[0], (Integer) row[1], (String) row[2], (String) row[3], (Double) row[4], (Integer) row[5] ));
//		}

		return rows;
	}

	public List<Object[]> getStocksByCategory(Integer id){
	    
		String select = "SELECT  " +
				"  COUNT( * ) as stock,                               "+
				" p.id as id,                                         "+
				" u.name as medida,                                   "+
				" p.descripcion as presentacion,                      "+
				" (p.peso_neto * COUNT( * ) )/ 1000 as kgTotal,       "+
				" pr.name as producto,                                "+
				" lote.descripcion as lote,                           "+
				" lote.fechaDeElaboracion as elaboracion,             "+
				" lote.fechaDeVencimiento as vencimiento              ";
				    			
        String from = "  FROM Presentacion p                           "+
				        "  JOIN Paquete pa ON ( pa.presentacion_id = p.id )      "+
				    	"  JOIN Producto pr on (p.producto_id = pr.id)           "+
				    	"  JOIN Categoria cat on ( pr.categoria_id = cat.id)     "+
						"  JOIN Lote lote on (p.lote_id = lote.id)               "+
						"  JOIN UnidadDeMedida u on (p.unidadDeMedida_id = u.id) ";	
					String where = "	   WHERE cat.id = :id AND pa.estado= :estado  GROUP BY pa.presentacion_id";

		Query query = getCurrentSession().createSQLQuery(select+from+where)
		.setParameter("estado", State.DISPONIBLE.toString()).setParameter("id", id);

    
		@SuppressWarnings("unchecked")
		List<Object[]> rows = query.list();
//		List<PaqueteFromQuery> m = new ArrayList<PaqueteFromQuery>();
//		for (Object[] row : rows) {
//			m.add(new PaqueteFromQuery((Integer) row[0], (Integer) row[1], (String) row[2], (String) row[3], (Double) row[4], (Integer) row[5] ));
//		}

		return rows;
	}
	

	public List<Object[]> getStocksByProduct(Integer id){
	    
		String select = "SELECT  " +
				"  COUNT( * ) as stock,                               "+
				" p.id as id,                                         "+
				" u.name as medida,                                   "+
				" p.descripcion as presentacion,                      "+
				" (p.peso_neto * COUNT( * ) )/ 1000 as kgTotal,       "+
				" pr.name as producto,                                "+
				" lote.descripcion as lote,                           "+
				" lote.fechaDeElaboracion as elaboracion,             "+
				" lote.fechaDeVencimiento as vencimiento              ";
				    			
        String from = "  FROM Presentacion p                           "+
				        "  JOIN Paquete pa ON ( pa.presentacion_id = p.id )      "+
				    	"  JOIN Producto pr on (p.producto_id = pr.id)           "+
				    	"  JOIN Categoria cat on ( pr.categoria_id = cat.id)     "+
						"  JOIN Lote lote on (p.lote_id = lote.id)               "+
						"  JOIN UnidadDeMedida u on (p.unidadDeMedida_id = u.id) ";	
					String where = "	  WHERE  p.producto_id = :id AND pa.estado= :estado  GROUP BY pa.presentacion_id";

		Query query = getCurrentSession().createSQLQuery(select+from+where)
		.setParameter("estado", State.DISPONIBLE.toString()).setParameter("id", id);

    
		@SuppressWarnings("unchecked")
		List<Object[]> rows = query.list();
//		List<PaqueteFromQuery> m = new ArrayList<PaqueteFromQuery>();
//		for (Object[] row : rows) {
//			m.add(new PaqueteFromQuery((Integer) row[0], (Integer) row[1], (String) row[2], (String) row[3], (Double) row[4], (Integer) row[5] ));
//		}

		return rows;
	}
}
