package co.edu.uniquindio.Marketplace.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Vendedor implements Serializable {

	private String nombre;
	private String apellido;
	private String direccion;
	private String cedula;
	
	private static final long serialVersionUID = 1L;
	ArrayList<Producto> 	 listaProductos     = new ArrayList<>();
	ArrayList<Vendedor> 	 listaContactos     = new ArrayList<>();
	ArrayList<Publicacion>   listaPublicaciones = new ArrayList<>();

	public Vendedor() {}

	public Vendedor(String nombre, String apellido, String direccion, String cedula) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.cedula = cedula;
	}

	
	// --- Setters & Getters ---
	public ArrayList<Producto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(ArrayList<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}

	public ArrayList<Vendedor> getListaContactos() {
		return listaContactos;
	}

	public void setListaContactos(ArrayList<Vendedor> listaContactos) {
		this.listaContactos = listaContactos;
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public ArrayList<Publicacion> getListaPublicaciones() {
		return listaPublicaciones;
	}

	public void setListaPublicaciones(ArrayList<Publicacion> listaPublicaciones) {
		this.listaPublicaciones = listaPublicaciones;
	}
	
	


}
