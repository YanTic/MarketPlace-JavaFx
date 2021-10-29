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
	
	public ArrayList<Producto> getListaProductos(){
		return modelFactoryController.getListaProductos();
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
	public Producto crearProducto(String nombre, String precio, String categoria, EstadoProducto estado) {		
		return modelFactoryController.crearProducto(nombre, precio, categoria, estado);
	}
	
	
	
}
