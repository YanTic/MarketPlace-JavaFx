package co.edu.uniquindio.Marketplace.controller;

import java.util.ArrayList;

import co.edu.uniquindio.Marketplace.model.EstadoProducto;
import co.edu.uniquindio.Marketplace.model.Marketplace;
import co.edu.uniquindio.Marketplace.model.Producto;
import co.edu.uniquindio.Marketplace.model.Vendedor;

public class CrudProductoViewController {
	ModelFactoryController modelFactoryController;
	Marketplace marketplace;

	
	public CrudProductoViewController(ModelFactoryController modelFactoryController) {
		this.modelFactoryController = modelFactoryController;
		marketplace = modelFactoryController.getMarketplace();
	}


	public Marketplace getMarketplace() {
		return marketplace;
	}
	
	public void setMarketplace(Marketplace marketplace) {
		this.marketplace = marketplace;
	}
	
	public ArrayList<Producto> getListaProductos(){
		return modelFactoryController.getListaProductos();
	}
	
	// CRUD comunica con ModelFactoryController
	public Producto crearProducto(String nombre, String precio, String categoria, EstadoProducto estado) {		
		return modelFactoryController.crearProducto(nombre, precio, categoria, estado);
	}

//	public boolean eliminarVendedor(String cedula) {		
//		return modelFactoryController.eliminarVendedor(cedula);
//	}
//
//	public boolean actualizarVendedor(String cedulaActual, String nombre, String apellido, String cedula, String direccion) {		
//		return modelFactoryController.actualizarVendedor(cedulaActual, nombre, apellido, cedula, direccion);
//	}	
	
}
