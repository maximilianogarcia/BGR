package ar.com.bgr.serrano.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.com.bgr.serrano.model.Contacto;
import ar.com.bgr.serrano.model.Sucursal;

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
public class ContactoDao extends AbstractDAO<Contacto> {

	public ContactoDao() {
		setClasz(Contacto.class);
	}

	public Set<Contacto> listBySucursal(int id) {

		Sucursal sucursal = (Sucursal) getSessionFactory().getCurrentSession()
				.get(Sucursal.class, id);

		return sucursal.getContactos();
	}

	@SuppressWarnings("unchecked")
	public List<Contacto> listByEoi(int id) {
		Criteria criteria = getSessionFactory().getCurrentSession()
				.createCriteria(getClasz());
		criteria.add(Restrictions.eq("eoi.id", id));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		return (List<Contacto>) criteria.list();
	}

	public boolean removeFromSucursal(int contactoId, int sucursalId) {

		Sucursal sucursal = (Sucursal) getSessionFactory().getCurrentSession().get(Sucursal.class, sucursalId);
		Set<Contacto> contactos = sucursal.getContactos();
		Contacto toremove = null;
		for (Contacto contacto : contactos) {
			if(contacto.getId() == contactoId){
				toremove= contacto;
				break;
			}
		}
		if(toremove != null){
			sucursal.getContactos().remove(toremove);
			getSessionFactory().getCurrentSession().save(sucursal);
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<Contacto> listOthersByEoi(int proveedorId, int sucursalId) {
	//refactorizar esta cosa
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("select c.* from contacto c join contacto_sucursal cs on (cs.CONTACTO_ID = c.id ) where c.EOI_ID =");
		stringBuilder.append(proveedorId);
		stringBuilder.append(" AND cs.SUCURSAL_ID !=");
		stringBuilder.append(sucursalId);
		stringBuilder.append(" and c.id not in( ");
		stringBuilder.append(" select c.id from contacto c join contacto_sucursal cs on (cs.CONTACTO_ID = c.id ) where c.EOI_ID =");
		stringBuilder.append(proveedorId);
		stringBuilder.append(" AND cs.SUCURSAL_ID =");
		stringBuilder.append(sucursalId);
		stringBuilder.append(")");
		stringBuilder.append(" union select c.* from contacto c left join contacto_sucursal cs on (cs.CONTACTO_ID != c.id ) where c.EOI_ID =");
		stringBuilder.append(proveedorId);
		stringBuilder.append(" and c.id not in (select cs.CONTACTO_ID from db_desa.contacto_sucursal cs ) ");
		stringBuilder.append(";");
		String query = stringBuilder.toString() ;
		System.out.println(query);
		
		return (List<Contacto>) getSessionFactory().getCurrentSession().createSQLQuery(query).addEntity(Contacto.class).list();
	}
}
