package ar.com.escuelita.chicken.persistencia.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="Deposito")
public class DepositoModel extends Modelo {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="stockHuevos")
	private long stockHuevos;
	
	@Column(name="stockMaximo")
	private long stockMaximo;
	
	@Column(name="borrado")
	private boolean borrado;
	
	@OneToMany(mappedBy="deposito",fetch=FetchType.EAGER)
	private List<MovimientoModel> listaMovimientos = new ArrayList<>();	
	
	public DepositoModel () {
		
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getStockHuevos() {
		return stockHuevos;
	}

	public void setStockHuevos(long stockHuevos) {
		this.stockHuevos = stockHuevos;
	}

	public long getStockMaximo() {
		return stockMaximo;
	}

	public void setStockMaximo(long stockMaximo) {
		this.stockMaximo = stockMaximo;
	}
	
	public boolean isBorrado() {
		return borrado;
	}

	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}

	public List<MovimientoModel> getListaMovimientos() {
		return listaMovimientos;
	}

	public void setListaMovimientos(List<MovimientoModel> listaMovimientos) {
		this.listaMovimientos = listaMovimientos;
	}
	
}
