package co.edu.uniquindio.Marketplace.controller;

import java.util.ArrayList;

import co.edu.uniquindio.Marketplace.model.EstadoProducto;
import co.edu.uniquindio.Marketplace.model.Marketplace;
import co.edu.uniquindio.Marketplace.model.Producto;
import co.edu.uniquindio.Marketplace.model.Usuario;

public class CrudLoginViewController {
	ModelFactoryController modelFactoryController;
	Marketplace marketplace;

	
	public CrudLoginViewController(ModelFactoryController modelFactoryController) {
		this.modelFactoryController = modelFactoryController;
		marketplace = modelFactoryController.getMarketplace();
	}


	public Marketplace getMarketplace() {
		return marketplace;
	}
	
	public void setMarketplace(Marketplace marketplace) {
		this.marketplace = marketplace;
	}
	
	public ArrayList<Usuario> getListaUsuarios(){
		return modelFactoryController.getListaUsuarios();
	}
	
	// CRUD comunica con ModelFactoryController
	public Usuario crearUsuario(String usuario, String contrasenia) {		
		return modelFactoryController.crearUsuario(usuario, contrasenia);
	}
	
	public boolean verificarUsuario(String usuario, String contrasenia){
		return modelFactoryController.verificarUsuario(usuario, contrasenia);
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


	// Creo la copia de seguridad al instante de abrir la aplicacion 
	public void crearCopiaSeguridad() {
		modelFactoryController.crearCopiaSeguridad();
	}

}

