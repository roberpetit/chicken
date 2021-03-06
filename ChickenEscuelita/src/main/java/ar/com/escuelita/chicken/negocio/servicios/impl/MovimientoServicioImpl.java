package ar.com.escuelita.chicken.negocio.servicios.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.escuelita.chicken.base.dto.DTO;
import ar.com.escuelita.chicken.negocio.mapeos.MovimientoMapeador;
import ar.com.escuelita.chicken.negocio.servicios.IMovimientoServicio;
import ar.com.escuelita.chicken.persistencia.dao.IDepositoDAO;
import ar.com.escuelita.chicken.persistencia.dao.IMovimientoDAO;
import ar.com.escuelita.chicken.persistencia.modelo.DepositoModel;
import ar.com.escuelita.chicken.persistencia.modelo.MovimientoModel;
import ar.com.escuelita.chicken.presentacion.dto.MovimientoDTO;
import ar.com.escuelita.chicken.presentacion.filtro.Filtro;
import ar.com.escuelita.chicken.presentacion.filtro.MovimientoFiltro;

public class MovimientoServicioImpl extends Servicio implements IMovimientoServicio {
	
	@Autowired
	MovimientoMapeador movimientoMapeador;
	
	@Autowired
	IMovimientoDAO movimientoDAO;
	
	@Autowired
	IDepositoDAO depositoDAO;
	
	@Override
	public DTO buscar(long id) {
		return movimientoMapeador.map(movimientoDAO.get(id));
	}

	@Override
	public Collection<DTO> listar() {
		return movimientoMapeador.map(movimientoDAO.listar());
	}

	@Override
	public void crear(DTO dto) {
		movimientoDAO.guardar((MovimientoModel)movimientoMapeador.map(dto, null));
		DepositoModel nuevoDeposito = depositoDAO.get(Long.parseLong(((MovimientoDTO)dto).getDepositoId()));
		nuevoDeposito.setStockHuevos(nuevoDeposito.getStockHuevos()+((MovimientoDTO)dto).getCantidad());
		depositoDAO.modificar(nuevoDeposito);
	}

	@Override
	public void modificar(DTO dto) {
		MovimientoModel movimientoModel = (MovimientoModel)movimientoMapeador.map(dto, null);
		MovimientoDTO movimientoDTO = (MovimientoDTO) dto;
		movimientoModel.setId(Long.parseLong(movimientoDTO.getId()));
		movimientoDAO.modificar(movimientoModel);
	}

	@Override
	public void borrar(DTO dto) {
		movimientoDAO.borrar(Long.parseLong(((MovimientoDTO)dto).getId()));		
	}

	@Override
	public Collection<DTO> listar(Filtro filtro) {
		MovimientoFiltro movimientoFiltro = (MovimientoFiltro) filtro;
		Collection<MovimientoModel> listaMovimientoModel = movimientoDAO.listar(movimientoFiltro);
		Collection<DTO> listaMovimientoDto = movimientoMapeador.map(listaMovimientoModel);
		
		return listaMovimientoDto;
	}
	
	

}
