package ar.com.bgr.serrano.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.bgr.serrano.dao.CategoriaDAO;
import ar.com.bgr.serrano.dao.MixDAO;
import ar.com.bgr.serrano.dao.PaqueteDAO;
import ar.com.bgr.serrano.dao.ProductoDAO;
import ar.com.bgr.serrano.dao.RemanenteDAO;
import ar.com.bgr.serrano.dao.UnidadDeMedidaDAO;
import ar.com.bgr.serrano.model.Categoria;
import ar.com.bgr.serrano.model.Mix;
import ar.com.bgr.serrano.model.Paquete;
import ar.com.bgr.serrano.model.Producto;
import ar.com.bgr.serrano.model.Remanente;
import ar.com.bgr.serrano.model.UnidadDeMedida;
import ar.com.bgr.serrano.utils.MixImpresentation;
import ar.com.bgr.serrano.utils.PaqueteFromQuery;
import ar.com.bgr.serrano.utils.PaqueteMix;

@Service
@Transactional
public class MixService {

	@Autowired
	MixDAO dao;
	@Autowired
	CategoriaDAO categoriaDAO;
	@Autowired
	UnidadDeMedidaDAO unidadDeMedidaDAO;	
	@Autowired
	ProductoDAO productoDAO;
	@Autowired
	RemanenteDAO remanenteDAO;
	@Autowired
	PaqueteDAO paqueteDAO;
	
	public Mix save(Mix mix) {
		return dao.saveOrUpdate(mix);
	}

	public List<Mix> list() {
		return dao.list();
	}

	public void remove(int id) {
		dao.remove(id);		
	}
	
	public Mix getById(int id) {
		return dao.getById(id);
	}

	public Mix saveMix(MixImpresentation mixImpresentation) {
		Mix newMix = new Mix();
		Categoria categoria = categoriaDAO.getById(mixImpresentation
				.getCategoria());

		UnidadDeMedida unidad_de_medida = unidadDeMedidaDAO
				.getById(mixImpresentation.getUnidad_de_medida());

		newMix.setCategoria(categoria);
		newMix.setUnidad_de_medida(unidad_de_medida);
		newMix.setName(mixImpresentation.getNombre());
		newMix.setVencimiento(mixImpresentation.getVencimiento());

		Boolean ok = true;
		Integer index = 0;

		ArrayList<PaqueteMix> array = new ArrayList<PaqueteMix>();
		for (PaqueteMix pm : mixImpresentation.getPaquetes_mix()) {
			array.add(pm);
		}

		PaqueteMix paqueteMixInicial = array.get(index);

		Double cantidad_original = Math.floor(paqueteMixInicial.getCantidad()
				/ paqueteMixInicial.getCant_elegida());
		index++;

		PaqueteMix paqueteMix;

		while (ok && array.size() > index) {

			paqueteMix = array.get(index);
			Double cantidad = Math.floor(paqueteMix.getCantidad()
					/ paqueteMix.getCant_elegida());
			ok = cantidad_original == cantidad;
			index++;
		}

		if (!ok) {
			// SEND ERROR
			// response_error = new Response();
			// response_error.setContent("Las cantidades elegidas son incompatibles");
			// response_error.setStatusCode(408);
			//
			// return response_error;
			return null;

		}

		for (PaqueteMix pmix : mixImpresentation.getPaquetes_mix()) {

			// calculo el remanente
			int cant = (pmix.getCantidad() % pmix.getCant_elegida());

			// get producto
			Producto producto = productoDAO.getById(pmix.getId());

			Remanente remanente = remanenteDAO.getByProducto(producto.getId());

			if (remanente == null) {
				remanente = new Remanente();
				remanente.setProducto(producto);
				remanente.setCantidad((double) 0);
			}

			remanente.setUnidad_de_medida(newMix.getUnidad_de_medida());
			remanente.setCantidad(remanente.getCantidad() + cant);

			for (PaqueteFromQuery paquete : pmix.getPaquetes()) {
				paqueteDAO.markMixed(paquete.getId());
			}

		}

		/*
		 * 
		 * GUARDAR LOS PAQUETES NUEVOS
		 */

		// primero salvar el mix
		newMix = dao.saveOrUpdate(newMix);
		// Asocio los paquetes al mix
		for (int i = 1; i <= cantidad_original; i++) {
			Paquete paq = new Paquete();
			paq.setEstado("DISPONIBLE");
			paq.setMix_id(newMix.getId());
			paq.setCodigo("MIXED");
			paqueteDAO.saveOrUpdate(paq);
		}

		return newMix;
		
	}
}
