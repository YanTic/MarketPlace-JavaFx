package co.edu.uniquindio.Marketplace.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Marketplace implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	ArrayList<Vendedor> listaVendedores = new ArrayList<>();
	ArrayList<Producto> listaProductos = new ArrayList<>();

	public Marketplace() {

	}


	public void agregarVendedor(String nombre, String apellido, String direccion, String cedula) {
		Vendedor nuevoVendedor = new Vendedor();
		nuevoVendedor.setNombre(nombre);
		nuevoVendedor.setApellido(apellido);
		nuevoVendedor.setCedula(cedula);
		nuevoVendedor.setDireccion(direccion);
		getListaVendedores().add(nuevoVendedor);
	}

	public void actualizarVendedor(String nombre, String apellido, String direccion, String cedula) {
		Vendedor vendedor = obtenerVendedor(cedula);

		if(vendedor != null){
			vendedor.setNombre(nombre);
			vendedor.setApellido(apellido);
			vendedor.setCedula(cedula);
			vendedor.setDireccion(direccion);
		}
	}


	public Boolean eliminarVendedor(String cedulaVendedor) {

		Boolean flagEliminado = false;
		Vendedor vendedor = obtenerVendedor(cedulaVendedor);

		if(vendedor != null) {
			getListaVendedores().remove(vendedor);
			flagEliminado = true;
		}

		return flagEliminado;
	}

	public Vendedor obtenerVendedor(String cedulaVendedor) {

		Vendedor vendedorEncontrado = null;

		for (Vendedor vendedor : listaVendedores) {
			if(vendedor.getCedula().equals(cedulaVendedor)) {
				vendedorEncontrado = vendedor;
				break;
			}
		}

		return vendedorEncontrado;
	}
	
	
	// --- Setters & Getters ---
	public ArrayList<Producto> getListaProductos() {
		return listaProductos;
	}
	public void setListaProductos(ArrayList<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}
	public ArrayList<Vendedor> getListaVendedores() {
		return listaVendedores;
	}
	public void setListaVendedores(ArrayList<Vendedor> listaVendedores) {
		this.listaVendedores = listaVendedores;
	}



}
