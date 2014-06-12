package ar.com.bgr.serrano.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.com.bgr.serrano.dao.exception.EntityDontMatchException;
import ar.com.bgr.serrano.model.Eoi;

@Repository
public class EOIDao extends AbstractDAO<Eoi>{

	@Autowired
	private SessionFactory sessionFactory;

	public static final String PROVEEDOR = "PROVEEDOR", CLIENTE="CLIENTE";
	public EOIDao() {
		setClasz(Eoi.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Eoi> listProveedores(){
		Criteria criteria =  getCurrentSession().createCriteria(getClasz());
		criteria.add(Restrictions.eq("type", PROVEEDOR));
		return  (List<Eoi>)criteria.list();
	}
	public Eoi getProveedorById(int id){
		Eoi proveedor = super.getById(id);
		validateProveedor(proveedor);
		return  proveedor;
	}
	
	public void removeProveedor(int id){
		Eoi proveedor = super.getById(id);
		validateProveedor(proveedor);
		super.remove(id);
	}

	public Eoi saveOrUpdateProveedor(Eoi proveedor){
		if(proveedor.getId() != 0){
			validateProveedor(proveedor);
		}
		return super.saveOrUpdate(proveedor);
	
	}

	private void validateProveedor(Eoi proveedor) {
		if(!PROVEEDOR.equals(proveedor.getType())){
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("El id solicitado: ");
			stringBuilder.append(proveedor.getId());
			stringBuilder.append(" no corresponde a un proveedor valido");
			throw new EntityDontMatchException(stringBuilder.toString());
		}
	}

}
