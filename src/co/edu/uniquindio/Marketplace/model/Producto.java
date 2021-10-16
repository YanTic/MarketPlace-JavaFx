package co.edu.uniquindio.Marketplace.model;

public class Producto {
	private String nombre;
	private String categoria;
	private String usuario;
	private String contrasena;
	private String precio;
	private EstadoProducto estado;

	public Producto() { }
	
	public Producto(String nombre, String categoria, String usuario, String contrasena,
					String precio, EstadoProducto estado) {
		this.nombre = nombre;
		this.categoria = categoria;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.precio = precio;
		this.estado = estado;
	}
	
	public Producto(String nombre, String precio, String categoria, EstadoProducto estado) {
		this.nombre = nombre;
		this.precio = precio;
		this.categoria = categoria;
		this.estado = estado;
}
	
	
	
	// --- Setters & Getters ---
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public EstadoProducto getEstado() {
		return estado;
	}

	public void setEstado(EstadoProducto estado) {
		this.estado = estado;
	}

}
