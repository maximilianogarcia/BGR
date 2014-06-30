package ar.com.bgr.serrano.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import ar.com.bgr.serrano.model.Paquete;
import ar.com.bgr.serrano.utils.PaqueteFromQuery;

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
public class PaqueteDAO extends AbstractDAO<Paquete> {
	
	public PaqueteDAO(){
		super();
		setClasz(Paquete.class);
	}

	@SuppressWarnings({ "unchecked" })
	public List<PaqueteFromQuery> getPaquetesByProducto(Integer id) {

		String select = "select pa.id,pa.presentacion_id,	pa.codigo,pa.estado,p.cantidad,	p.unidad_de_medida.id ";
        String from = "from  Presentacion p, Paquete pa, Producto pr ";
		String where ="where  pa.presentacion_id = p.id " +
					  "and  p.producto.id = pr.id " +
					  "and  pr.id = :producto_id AND pa.estado= :estado ";

		Query query = getCurrentSession().createQuery(select+from+where)
		.setParameter("producto_id", id)
		.setParameter("estado", State.DISPONIBLE.toString());

		List<Object[]> rows = query.list();
		List<PaqueteFromQuery> m = new ArrayList<PaqueteFromQuery>();
		for (Object[] row : rows) {
			m.add(new PaqueteFromQuery((Integer) row[0], (Integer) row[1], (String) row[2], (String) row[3], (Double) row[4], (Integer) row[5] ));
		}
		return m;
	}

	public void markMixed(int id) {
		Query query = getCurrentSession().createQuery("update Paquete set estado = :estado where id = :id");
		query.setParameter("id",id);
		query.setParameter("estado",State.MIXED.toString());
		query.executeUpdate();
	}

}
