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
	public ArrayList<Usuario>  getListaUsuarios();
	public ArrayList<Producto> getListaProductos(Vendedor vendedorSeleccionado);
	
	// Producto
	public Producto crearProducto(Vendedor vendedor, String nombre, String precio, String categoria, EstadoProducto estado);
	public boolean actualizarProducto(Vendedor vendedor, String nombreActual, String nombre, String precio, String categoria, EstadoProducto estado);
	public boolean eliminarProducto(Vendedor vendedor, String nombre);
	
	// Usuario
	public Usuario crearUsuario(String usuario, String contrasenia);
	public boolean verificarUsuario(String usuario, String contrasenia);
}
