package co.edu.uniquindio.Marketplace.model.services;

import java.util.ArrayList;

import co.edu.uniquindio.Marketplace.exceptions.ProductoException;
import co.edu.uniquindio.Marketplace.exceptions.UsuarioException;
import co.edu.uniquindio.Marketplace.exceptions.VendedorException;
import co.edu.uniquindio.Marketplace.model.EstadoProducto;
import co.edu.uniquindio.Marketplace.model.Producto;
import co.edu.uniquindio.Marketplace.model.Usuario;
import co.edu.uniquindio.Marketplace.model.Vendedor;

public interface IMarketplaceService {
	
	// Vendedor
	public Vendedor crearVendedor(String nombre, String apellido, String cedula, String direccion) throws VendedorException;
	public boolean actualizarVendedor(String cedulaActual, String nombre, String apellido, String cedula, String direccion) throws VendedorException;
	public boolean eliminarVendedor(String cedula) throws VendedorException;
	public Vendedor getVendedor(String cedula);
	public boolean verificarVendedorExistente(String cedula);
	public ArrayList<Vendedor> getListaVendedores();
	
	// Producto
	public Producto crearProducto(Vendedor vendedor, String nombre, String precio, String categoria, EstadoProducto estado, String rutaImagen) throws ProductoException;
	public boolean actualizarProducto(Vendedor vendedor, String nombreActual, String nombre, String precio, String categoria, EstadoProducto estado, String rutaImagen) throws ProductoException;
	public boolean eliminarProducto(Vendedor vendedor, String nombreProducto) throws ProductoException;
	public Producto getProducto(Vendedor vendedor, String nombreProducto);
	public boolean verificarProductoExistente(Vendedor vendedor, String nombreProducto);
	
	// Usuario
	public Usuario crearUsuario(String usuario, String contrasenia) throws UsuarioException;
	public Usuario getUsuario(String usuario, String contrasenia);
	public boolean verificarUsuarioExistente(String nombreUsuario);
	public boolean verificarUsuario(String nombreUsuario, String contrasenia) throws UsuarioException;
}
