package ar.com.escuelita.chicken.negocio.servicios.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.escuelita.chicken.base.dto.DTO;
import ar.com.escuelita.chicken.base.excepciones.NegocioExcepcion;
import ar.com.escuelita.chicken.base.excepciones.PersistenciaExcepcion;
import ar.com.escuelita.chicken.negocio.mapeos.UsuarioMapeador;
import ar.com.escuelita.chicken.negocio.servicios.IPerfilServicio;
import ar.com.escuelita.chicken.negocio.servicios.IUsuarioServicio;
import ar.com.escuelita.chicken.persistencia.dao.IGallineroDAO;
import ar.com.escuelita.chicken.persistencia.dao.IUsuarioDAO;
import ar.com.escuelita.chicken.persistencia.modelo.GallineroModel;
import ar.com.escuelita.chicken.persistencia.modelo.UsuarioModel;
import ar.com.escuelita.chicken.presentacion.dto.UsuarioDTO;
import ar.com.escuelita.chicken.presentacion.filtro.Filtro;
import ar.com.escuelita.chicken.presentacion.filtro.UsuarioFiltro;

public class UsuarioServicioImpl extends Servicio implements IUsuarioServicio {
	
	@Autowired
	UsuarioMapeador usuarioMapeador;
	
	@Autowired
	IUsuarioDAO usuarioDAO;
	
	@Autowired
	IGallineroDAO gallineroDAO;
	
	@Autowired
	IPerfilServicio perfilServicio;
	
	@Override
	public DTO buscar(long id) {
		return usuarioMapeador.map(usuarioDAO.get(id));
	}

	@Override
	public Collection<DTO> listar() {
		return usuarioMapeador.map(usuarioDAO.listar());
	}

	@Override
	public void crear(DTO dto) throws NegocioExcepcion {
		try {
			usuarioDAO.guardar((UsuarioModel)usuarioMapeador.map(dto, null));
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage());
		}
	}

	@Override
	public void modificar(DTO dto) throws NegocioExcepcion {
		UsuarioModel usuarioModel = (UsuarioModel)usuarioMapeador.map(dto, null);
		UsuarioDTO usuarioDTO = (UsuarioDTO) dto;
		usuarioModel.setId(Long.parseLong(usuarioDTO.getId()));		
		try {
			usuarioDAO.modificar(usuarioModel);
		} catch (PersistenciaExcepcion e) {
			throw new NegocioExcepcion(e.getMessage());
		}
	}

	@Override
	public void borrar(DTO dto) {
		usuarioDAO.borrar(Long.parseLong(((UsuarioDTO)dto).getId()));
		for(GallineroModel gallinero : gallineroDAO.listar()) {
			if(gallinero.getUsuario()!=null){
				if(gallinero.getUsuario().getId() == Long.parseLong(((UsuarioDTO)dto).getId())) {
					gallinero.setUsuario(null);
					gallineroDAO.modificar(gallinero);
				}
			}
		}
	}

	@Override
	public Collection<DTO> listar(Filtro filtro) {
		UsuarioFiltro usuarioFiltro = (UsuarioFiltro) filtro;
		Collection<UsuarioModel> listaUsuarioModel = usuarioDAO.listar(usuarioFiltro);
		Collection<DTO> listaUsuarioDto = usuarioMapeador.map(listaUsuarioModel);
		
		return listaUsuarioDto;
	}
	
	@Override
	public List<HashMap<String, String>> getTotalesProduccion(UsuarioFiltro usuarioFiltro){
		return usuarioDAO.getProduccionTotal(usuarioFiltro);
	}
}
