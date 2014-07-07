package ar.com.bgr.serrano.dao3;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.com.bgr.serrano.dao.exception.EntityDontMatchException;
import ar.com.bgr.serrano.model.Calificativo;

/**
 * 
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
public class CalificativoDAO extends AbstractDAO<Calificativo> {

	protected static final String CLIENTE = "CLIENTE", PROVEEDOR = "PROVEEDOR";

	public CalificativoDAO() {
		setClasz(Calificativo.class);
	}

	@SuppressWarnings("unchecked")
	public List<Calificativo> listCliente() {
		Criteria criteria = getSessionFactory().getCurrentSession()
				.createCriteria(Calificativo.class);
		criteria.add(Restrictions.eq("type", CLIENTE));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<Calificativo> listProveedor() {
		Criteria criteria = getSessionFactory().getCurrentSession()
				.createCriteria(Calificativo.class);
		criteria.add(Restrictions.eq("type", PROVEEDOR));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}

	public Calificativo create(Calificativo calificativo) {
		return (Calificativo) getSessionFactory().getCurrentSession().save(
				calificativo);
	}

	public Calificativo updateCliente(Calificativo calificativo) {
		Calificativo cali = (Calificativo) getCurrentSession().get(Calificativo.class, calificativo.getId());
		validateCliente(cali);
		cali.setName(calificativo.getName());
	    getCurrentSession().saveOrUpdate(cali);
	    return calificativo;
	}

	public Calificativo updateProveedor(Calificativo calificativo) {
		Calificativo cali = (Calificativo) getCurrentSession().get(Calificativo.class, calificativo.getId());
		validateProveedor(cali);
		cali.setName(calificativo.getName());
		getCurrentSession().saveOrUpdate(cali);
		return calificativo;
	}

	private void validateProveedor(Calificativo calificativo) {
		
		if(!PROVEEDOR.equals(calificativo.getType())){
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("El id solicitado: ");
			stringBuilder.append(calificativo.getId());
			stringBuilder.append(" no corresponde a un calificativo de proveedor valido");
			throw new EntityDontMatchException(stringBuilder.toString());
		}
	}
	private void validateCliente(Calificativo calificativo) {
		
		if(!CLIENTE.equals(calificativo.getType())){
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("El id solicitado: ");
			stringBuilder.append(calificativo.getId());
			stringBuilder.append(" no corresponde a un calificativo de cliente valido");
			throw new EntityDontMatchException(stringBuilder.toString());
		}
	}

}
