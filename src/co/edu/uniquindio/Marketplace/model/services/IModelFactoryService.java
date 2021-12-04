package co.edu.uniquindio.Marketplace.model.services;

import java.util.ArrayList;

import co.edu.uniquindio.Marketplace.model.EstadoProducto;
import co.edu.uniquindio.Marketplace.model.Producto;
import co.edu.uniquindio.Marketplace.model.Publicacion;
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
	public ArrayList<Vendedor> getListaContactos(Vendedor vendedorSeleccionado);
	public ArrayList<Publicacion> getListaPublicaciones(Vendedor vendedorSeleccionado);
	public ArrayList<Publicacion> getListaPublicacionesDesdeContactos(Vendedor contactoSeleccionado);
	public ArrayList<Vendedor> getListaContactosDesdeContactos(Vendedor contactoSeleccionado);
	
	// Producto
	public Producto crearProducto(Vendedor vendedor, String nombre, String precio, String categoria, EstadoProducto estado, String rutaImagen);
	public boolean actualizarProducto(Vendedor vendedor, String nombreActual, String nombre, String precio, String categoria, EstadoProducto estado, String rutaImagen);
	public boolean eliminarProducto(Vendedor vendedor, String nombre);
	
	// Publicacion
	public Publicacion crearPublicacion(Vendedor vendedor, String nombreProducto);
	public boolean eliminarPublicacion(Vendedor vendedor, String nombre); 
	public boolean actualizarPublicacion(Vendedor vendedor, String viejoNombreProducto, String nuevoNombreProducto);
	
	// Usuario
	public Usuario crearUsuario(String usuario, String contrasenia);
	public boolean verificarUsuario(String usuario, String contrasenia);
}
