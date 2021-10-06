package co.edu.uniquindio.Marketplace.model;

import java.io.Serializable;

public class Objeto implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String descripcion;
	private int codigo;
	private String estado;
	private Prestamo prestamoAsociado;

	public Objeto() {

	}



	public Objeto(String nombre, String descripcion, int codigo, String estado) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.codigo = codigo;
		this.estado = estado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Prestamo getPrestamoAsociado() {
		return prestamoAsociado;
	}

	public void setPrestamoAsociado(Prestamo prestamoAsociado) {
		this.prestamoAsociado = prestamoAsociado;
	}
}
