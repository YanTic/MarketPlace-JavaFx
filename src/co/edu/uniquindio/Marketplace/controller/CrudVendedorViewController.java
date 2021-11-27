package co.edu.uniquindio.Marketplace.controller;

import java.util.ArrayList;

import co.edu.uniquindio.Marketplace.MainApp;
import co.edu.uniquindio.Marketplace.model.EstadoProducto;
import co.edu.uniquindio.Marketplace.model.Marketplace;
import co.edu.uniquindio.Marketplace.model.Producto;
import co.edu.uniquindio.Marketplace.model.Vendedor;

public class CrudVendedorViewController {	
	ModelFactoryController modelFactoryController;
	Marketplace marketplace;

	
	public CrudVendedorViewController(ModelFactoryController modelFactoryController) {
		this.modelFactoryController = modelFactoryController;
		marketplace = modelFactoryController.getMarketplace();
	}

	public Marketplace getMarketplace() {
		return marketplace;
	}


	public void setMarketplace(Marketplace marketplace) {
		this.marketplace = marketplace;
	}
	
	public ArrayList<Vendedor> getListaVendedores(){
		return modelFactoryController.getListaVendedores();
	}
	
	public ArrayList<Producto> getListaProductos(Vendedor vendedorSeleccionado) {
		return modelFactoryController.getListaProductos(vendedorSeleccionado); 
	}
	
	public ArrayList<Vendedor> getListaContactos(Vendedor vendedorSeleccionado) {
		return modelFactoryController.getListaContactos(vendedorSeleccionado); 
	}
	
	

	
	// CRUD comunica con ModelFactoryController  ( Vendedor )
	public Vendedor crearVendedor(String nombre, String apellido, String cedula, String direccion) {		
		return modelFactoryController.crearVendedor(nombre, apellido, cedula, direccion);
	}

	public boolean eliminarVendedor(String cedula) {		
		return modelFactoryController.eliminarVendedor(cedula);
	}

	public boolean actualizarVendedor(String cedulaActual, String nombre, String apellido, String cedula, String direccion) {		
		return modelFactoryController.actualizarVendedor(cedulaActual, nombre, apellido, cedula, direccion);
	}
	
	
	// CRUD comunica con ModelFactoryController  ( Producto )
	public Producto crearProducto(Vendedor vendedor, String nombre, String precio, String categoria, EstadoProducto estado, String rutaImagen) {		
		return modelFactoryController.crearProducto(vendedor, nombre, precio, categoria, estado, rutaImagen);
	}
	
	public boolean eliminarProducto(Vendedor vendedor, String nombre) {		
		return modelFactoryController.eliminarProducto(vendedor, nombre);
	}

	public boolean actualizarProducto(Vendedor vendedor, String nombreActual, String nombre, String precio, String categoria, EstadoProducto estado, String rutaImagen) {		
		return modelFactoryController.actualizarProducto(vendedor, nombreActual, nombre, precio, categoria, estado, rutaImagen);
	}
	
	
	
	// Persistencia Metodos
	public void guardarDatos() {
		modelFactoryController.guardarResourceXML();
	}

	public void registrarAccion(String mensajeLog, int nivel, String accion) {
		modelFactoryController.registrarAccionesSistema(mensajeLog, nivel, accion);;
	}

	public void guardarDatosTXT() {
		modelFactoryController.guardarDatosTXT();
	}
	
	public String copiarImagen(String nombreProducto, String rutaImagen){
		return modelFactoryController.copiarImagen(nombreProducto, rutaImagen);
	}


}
