package co.edu.uniquindio.Marketplace.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Prestamo implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private int numero;
	private String fechaPrestamo;
	private String fechaEntrega;
	private String numeroObjectoPrestado;
	private int valorPrestado;
	private String estado;
	private Empleado empleadoAsociado;
	private Cliente clienteAsociado;
	ArrayList<Objeto> listaObjetosAsociados = new ArrayList<>();


	public Prestamo()
	{

	}


	public Prestamo(int numero, String fechaPrestamo, String fechaEntrega, String numeroObjectoPrestado,
			int valorPrestado, String estado) {
		super();
		this.numero = numero;
		this.fechaPrestamo = fechaPrestamo;
		this.fechaEntrega = fechaEntrega;
		this.numeroObjectoPrestado = numeroObjectoPrestado;
		this.valorPrestado = valorPrestado;
		this.estado = estado;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getFechaPrestamo() {
		return fechaPrestamo;
	}

	public void setFechaPrestamo(String fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}

	public String getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(String fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public String getNumeroObjectoPrestado() {
		return numeroObjectoPrestado;
	}

	public void setNumeroObjectoPrestado(String numeroObjectoPrestado) {
		this.numeroObjectoPrestado = numeroObjectoPrestado;
	}

	public int getValorPrestado() {
		return valorPrestado;
	}

	public void setValorPrestado(int valorPrestado) {
		this.valorPrestado = valorPrestado;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Empleado getEmpleadoAsociado() {
		return empleadoAsociado;
	}

	public void setEmpleadoAsociado(Empleado empleadoAsociado) {
		this.empleadoAsociado = empleadoAsociado;
	}

	public Cliente getClienteAsociado() {
		return clienteAsociado;
	}

	public void setClienteAsociado(Cliente clienteAsociado) {
		this.clienteAsociado = clienteAsociado;
	}


	public ArrayList<Objeto> getListaObjetosAsociados() {
		return listaObjetosAsociados;
	}


	public void setListaObjetosAsociados(ArrayList<Objeto> listaObjetosAsociados) {
		this.listaObjetosAsociados = listaObjetosAsociados;
	}




}
