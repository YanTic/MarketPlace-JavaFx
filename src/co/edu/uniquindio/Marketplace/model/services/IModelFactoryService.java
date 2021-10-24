package co.edu.uniquindio.Marketplace.model.services;

import java.util.ArrayList;

import co.edu.uniquindio.Marketplace.model.EstadoProducto;
import co.edu.uniquindio.Marketplace.model.Producto;
import co.edu.uniquindio.Marketplace.model.Usuario;
import co.edu.uniquindio.Marketplace.model.Vendedor;

public interface IModelFactoryService {
	
	// Vendedor
	public Vendedor crearVendedor(String nombre, String apellido, String cedula, String direccion);
	public boolean actualizarVendedor(String cedulaActual, String nombre, String apellido, String cedula, String direccion);
	public boolean eliminarVendedor(String cedula);
	public ArrayList<Vendedor> getListaVendedores();
	public ArrayList<Producto> getListaProductos();
	public ArrayList<Usuario>  getListaUsuarios();
	
	// Producto
	public Producto crearProducto(String nombre, String precio, String categoria, EstadoProducto estado);
	
	// Usuario
	public Usuario crearUsuario(String usuario, String contrasenia);
}
