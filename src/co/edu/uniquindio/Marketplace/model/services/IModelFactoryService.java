package co.edu.uniquindio.Marketplace.model.services;

import java.util.ArrayList;

import co.edu.uniquindio.Marketplace.model.Vendedor;

public interface IModelFactoryService {
	
	public Vendedor crearVendedor(String nombre, String apellido, String cedula, String direccion);
	public boolean actualizarVendedor(String cedulaActual, String nombre, String apellido, String cedula, String direccion);
	public Vendedor getVendedor(String cedula);
	public boolean eliminarVendedor(String cedula);
	public ArrayList<Vendedor> getListaVendedores();
}
