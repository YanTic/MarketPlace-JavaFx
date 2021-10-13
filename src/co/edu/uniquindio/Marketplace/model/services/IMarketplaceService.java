package co.edu.uniquindio.Marketplace.model.services;

import java.util.ArrayList;

import co.edu.uniquindio.Marketplace.exceptions.VendedorException;
import co.edu.uniquindio.Marketplace.model.Vendedor;

public interface IMarketplaceService {
	
	public Vendedor crearVendedor(String nombre, String apellido, String cedula, String direccion) throws VendedorException;
	public boolean actualizarVendedor(String cedulaActual, String nombre, String apellido, String cedula, String direccion) throws VendedorException;
	public Vendedor getVendedor(String cedula);
	public boolean eliminarVendedor(String cedula) throws VendedorException;
	public boolean verificarVendedorExistente(String cedula);
	public ArrayList<Vendedor> getListaVendedores();
}
