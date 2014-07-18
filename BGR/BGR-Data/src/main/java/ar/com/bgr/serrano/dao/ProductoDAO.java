/**
 * 
 */
package ar.com.bgr.serrano.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.com.bgr.serrano.model.Producto;
import ar.com.bgr.serrano.model.ProductoProveedor;

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
public class ProductoDAO extends AbstractDAO<Producto> {

	public ProductoDAO(){
		setClasz(Producto.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Producto> getProductosByCategoria(Integer categoriaId){
		Criteria criteria =  getCurrentSession().createCriteria(getClasz());
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.add(Restrictions.eq("categoria.id", categoriaId));
		return  criteria.list();
	}
	
	public Producto saveOrUpdateAndDelete(Producto producto){		
		Producto persisted = getById(producto.getId());		
		removeRelation(producto, persisted);		
		getCurrentSession().merge(producto);
		getCurrentSession().saveOrUpdate(persisted);		
		return producto;
	}

	public void removeAllRelations(int id){
		Producto persisted =   getById(id);		
		for(ProductoProveedor pp : persisted.getProductoProveedor()){
			Query query = getCurrentSession().createQuery("delete Producto_proveedor where proveedor_id = :proveedor_id and producto_id = :producto_id");
			query.setParameter("proveedor_id", pp.getProveedor().getId());
			query.setParameter("producto_id", pp.getProducto().getId());
			query.executeUpdate();
		}
		getCurrentSession().saveOrUpdate(persisted);
	}	
	
	private void removeRelation(Producto producto, Producto persisted) {
		for(ProductoProveedor pp : persisted.getProductoProveedor()){
			
			Boolean found = Boolean.FALSE;
			for(ProductoProveedor ppnp : producto.getProductoProveedor()){
				if(ppnp.getProveedor().getId() == pp.getProveedor().getId()){
					found = true;
				}
			}
			if(!found){				
				Query query = getCurrentSession().createQuery("delete Producto_proveedor where proveedor_id = :proveedor_id and producto_id = :producto_id");
				query.setParameter("proveedor_id", pp.getProveedor().getId());
				query.setParameter("producto_id", pp.getProducto().getId());
				query.executeUpdate();
			}
		}
	}
	

}
