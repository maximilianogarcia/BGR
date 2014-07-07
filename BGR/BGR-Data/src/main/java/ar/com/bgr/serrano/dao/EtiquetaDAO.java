package ar.com.bgr.serrano.dao;

import java.util.Set;

import org.springframework.stereotype.Repository;

import ar.com.bgr.serrano.model.Eoi;
import ar.com.bgr.serrano.model.Etiqueta;

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
public class EtiquetaDAO extends AbstractDAO<Etiqueta>{
   
	public EtiquetaDAO() {
		setClasz(Etiqueta.class);
	}
	
	public Etiqueta unTag(int etiquetaId, int eoiId){

		Eoi eoi = (Eoi) getSessionFactory().getCurrentSession().get(Eoi.class, eoiId);
		Set<Etiqueta> etiquetas = eoi.getEtiquetas();
		
		Etiqueta toremove = null;
		
		for (Etiqueta etiqueta : etiquetas) {
			if(etiqueta.getId() == etiquetaId){
				toremove= etiqueta;
				break;
			}
		}
		if(toremove != null){
			eoi.getEtiquetas().remove(toremove);
			getSessionFactory().getCurrentSession().save(eoi);
			return toremove;
		}
		return null;
	}
}
