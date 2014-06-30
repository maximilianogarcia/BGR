package ar.com.bgr.serrano.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
@SuppressWarnings("unchecked")
public abstract class AbstractDAO<T> {
	
	@Autowired
    private SessionFactory sessionFactory;
	private Class<T> clasz;
	
	public AbstractDAO(Class<T> clasz) {
		this.clasz = clasz;
	}
	
	public AbstractDAO() {
	}
	
	public List<T> list(){
		Criteria criteria =  getCurrentSession().createCriteria(getClasz());
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return  criteria.list();
	}

	public T getById(int id){
		return  (T) getCurrentSession().get(getClasz(), id);
	}	

	public T getByName(String name){
		Criteria criteria =  getCurrentSession().createCriteria(getClasz());
		return (T) criteria.add(Restrictions.eq("name", name));
	}

	public T saveOrUpdate(T entity){
		getCurrentSession().saveOrUpdate(entity);
		return entity;
	}
	
	public void remove(int id){
		T entity=   (T) getCurrentSession().get(getClasz(), id);
		getCurrentSession().delete(entity);
	}

	public Class<?> getClasz() {
		return clasz;
	}

	public void setClasz(Class<T> clasz) {
		this.clasz = clasz;
	}

	protected final  Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public  void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	public enum State {
		DISPONIBLE, DESACTIVADO, MIXED;
		
		private static Map<Boolean, State> stateMap;				
	    public static State getState(Boolean state) {
	        if (stateMap == null)       
	        initMap();	        
	        return stateMap.get(state);
	    }
	    
		private static void initMap() {
			stateMap = new HashMap<Boolean, State>();
	        stateMap.put(Boolean.TRUE, State.DISPONIBLE);
	        stateMap.put(Boolean.FALSE, State.DESACTIVADO);	
		}
	}
}
