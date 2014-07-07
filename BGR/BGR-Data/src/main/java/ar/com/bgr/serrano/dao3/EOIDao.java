package ar.com.bgr.serrano.dao3;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.com.bgr.serrano.dao.exception.EntityDontMatchException;
import ar.com.bgr.serrano.model.Eoi;

@Repository
public class EOIDAO extends AbstractDAO<Eoi>{

	@Autowired
	private SessionFactory sessionFactory;

	public static final String PROVEEDOR = "PROVEEDOR", CLIENTE="CLIENTE";
	public EOIDAO() {
		setClasz(Eoi.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Eoi> listProveedores(){
		Criteria criteria =  getCurrentSession().createCriteria(getClasz());
		criteria.add(Restrictions.eq("type", PROVEEDOR));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.add(Restrictions.eq("active", true));
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
		proveedor.setActive(false);
		getCurrentSession().update(proveedor);
	//	super.remove(id);
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

	public void remove(int id){
		Eoi entity=   (Eoi) getCurrentSession().get(getClasz(), id);
		entity.setActive(false);
		getCurrentSession().update(entity);
	}	
	
	
	
	
	
	
	
	
	
	
	
	@SuppressWarnings("unchecked")
	public List<Eoi> listClientes(){
		Criteria criteria =  getCurrentSession().createCriteria(getClasz());
		criteria.add(Restrictions.eq("type", CLIENTE));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.add(Restrictions.eq("active", true));
		return  (List<Eoi>)criteria.list();
	}
	public Eoi getClienteById(int id){
		Eoi cliente = super.getById(id);
		validateCliente(cliente);
		return  cliente;
	}
	
	public void removeCliente(int id){
		Eoi cliente = super.getById(id);
		validateCliente(cliente);
		cliente.setActive(false);
		getCurrentSession().update(cliente);
	//	super.remove(id);
	}

	public Eoi saveOrUpdateCliente(Eoi cliente){
		if(cliente.getId() != 0){
			validateCliente(cliente);
		}
		return super.saveOrUpdate(cliente);
	
	}

	private void validateCliente(Eoi cliente) {
		if(!CLIENTE.equals(cliente.getType())){
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("El id solicitado: ");
			stringBuilder.append(cliente.getId());
			stringBuilder.append(" no corresponde a un cliente valido");
			throw new EntityDontMatchException(stringBuilder.toString());
		}
	}
	
	
	
	
}
