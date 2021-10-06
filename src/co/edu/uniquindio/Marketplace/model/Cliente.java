package co.edu.uniquindio.Marketplace.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Cliente extends Persona implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String direccion;
	ArrayList<Prestamo> listaPrestamosAsociados = new ArrayList<>();


	public Cliente() {

	}


	public Cliente(String cedula, String nombre, String telefono, String direccion, String apellido)
	{
		super(cedula, nombre, telefono,apellido);
		this.direccion = direccion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public ArrayList<Prestamo> getListaPrestamosAsociados() {
		return listaPrestamosAsociados;
	}


	public void setListaPrestamosAsociados(ArrayList<Prestamo> listaPrestamosAsociados) {
		this.listaPrestamosAsociados = listaPrestamosAsociados;
	}


}
