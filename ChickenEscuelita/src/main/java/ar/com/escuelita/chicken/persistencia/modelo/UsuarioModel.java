package ar.com.escuelita.chicken.persistencia.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="Usuario")
public class UsuarioModel extends Modelo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	@Column(name="nombreUsuario")
	private String nombreUsuario;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="apellido")
	private String apellido;
	
	@Column(name="contrasenia")
	private String contrasenia;
	
	@Column(name="borrado")
	private boolean borrado;
	
	@OneToMany(mappedBy="usuario",fetch=FetchType.EAGER,cascade=CascadeType.PERSIST)
	private List<GallineroModel> listaGallineros = new ArrayList<>();
	
	@OneToMany(mappedBy="usuario",fetch=FetchType.EAGER)
	private List<VentaModel> listaVentas = new ArrayList<>();
	
	@ManyToMany(cascade=CascadeType.PERSIST,fetch=FetchType.EAGER)
	@JoinTable(name="PerfilUsuario",//bd
	joinColumns={@JoinColumn(name="idUsuario")},//bd
	inverseJoinColumns={@JoinColumn(name="idPerfil")})//bd		
	private List<PerfilModel> listaPerfiles = new ArrayList<PerfilModel>();
	
	public UsuarioModel() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public boolean isBorrado() {
		return borrado;
	}

	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}

	public List<GallineroModel> getListaGallineros() {
		return listaGallineros;
	}

	public void setListaGallineros(List<GallineroModel> listaGallineros) {
		this.listaGallineros = listaGallineros;
	}

	public List<VentaModel> getListaVentas() {
		return listaVentas;
	}

	public void setListaVentas(List<VentaModel> listaVentas) {
		this.listaVentas = listaVentas;
	}

	public List<PerfilModel> getListaPerfiles() {
		return listaPerfiles;
	}

	public void setListaPerfiles(List<PerfilModel> listaPerfiles) {
		this.listaPerfiles = listaPerfiles;
	}
}
