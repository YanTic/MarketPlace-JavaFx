package co.edu.uniquindio.Marketplace.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Empleado extends Persona implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private double salario;
	private String cargo;
	private String direccion;

	ArrayList<Prestamo> listaPrestamosAsociados = new ArrayList<>();


	public Empleado() {

	}


	public Empleado(String cedula, String nombre, String telefono, double salario, String cargo, String apellido)
	{
		super(cedula, nombre, telefono, apellido);
		this.salario = salario;
		this.cargo = cargo;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
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
