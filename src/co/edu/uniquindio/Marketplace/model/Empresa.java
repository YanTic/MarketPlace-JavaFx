package co.edu.uniquindio.Marketplace.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Empresa implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	ArrayList<Prestamo> listaPrestamos = new ArrayList<>();
	ArrayList<Cliente> listaClientes = new ArrayList<>();
	ArrayList<Empleado> listaEmpleados = new ArrayList<>();
	ArrayList<Objeto> listaObjetos = new ArrayList<>();



	public Empresa() {

	}



	public ArrayList<Prestamo> getListaPrestamos() {
		return listaPrestamos;
	}
	public void setListaPrestamos(ArrayList<Prestamo> listaPrestamos) {
		this.listaPrestamos = listaPrestamos;
	}
	public ArrayList<Cliente> getListaClientes() {
		return listaClientes;
	}
	public void setListaClientes(ArrayList<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}
	public ArrayList<Empleado> getListaEmpleados() {
		return listaEmpleados;
	}
	public void setListaEmpleados(ArrayList<Empleado> listaEmpleados) {
		this.listaEmpleados = listaEmpleados;
	}
	public ArrayList<Objeto> getListaObjetos() {
		return listaObjetos;
	}
	public void setListaObjetos(ArrayList<Objeto> listaObjetos) {
		this.listaObjetos = listaObjetos;
	}



	public void agregarCliente(String nombre, String apellido, String cedula, String direccion)
	{
		Cliente nuevoCliente = new Cliente();
		nuevoCliente.setNombre(nombre);
		nuevoCliente.setApellido(apellido);
		nuevoCliente.setCedula(cedula);
		nuevoCliente.setDireccion(direccion);
		getListaClientes().add(nuevoCliente);
	}

	public void actualizarCliente(String nombre, String apellido, String cedula, String direccion)
	{
		Cliente cliente = obtenerCliente(cedula);

		if(cliente != null)
		{
			cliente.setNombre(nombre);
			cliente.setApellido(apellido);
			cliente.setCedula(cedula);
			cliente.setDireccion(direccion);
		}
	}


	public Boolean eliminarcliente(String cedulaCliente) {

		Boolean flagEliminado = false;
		Cliente cliente = obtenerCliente(cedulaCliente);

		if(cliente != null)
		{
			getListaClientes().remove(cliente);
			flagEliminado = true;
		}

		return flagEliminado;
	}

	public Cliente obtenerCliente(String cedulaCliente) {

		Cliente clienteEncontrado = null;

		for (Cliente cliente : listaClientes)
		{
			if(cliente.getCedula().equals(cedulaCliente))
			{
				clienteEncontrado = cliente;
				break;
			}
		}

		return clienteEncontrado;
	}


	public void agregarEmpleado(String nombre, String apellido, String cedula, String direccion) {

		Empleado empleado = new Empleado();
		empleado.setNombre(nombre);
		empleado.setApellido(apellido);
		empleado.setCedula(cedula);
		empleado.setDireccion(direccion);
		getListaEmpleados().add(empleado);
	}









}
