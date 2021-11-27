package co.edu.uniquindio.Marketplace.model;

import java.io.Serializable;

import javafx.scene.image.ImageView;

public class Producto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String precio;
	private String categoria;
	private EstadoProducto estado;
	private ImageView imagen;

	public Producto() { }
	
	public Producto(String nombre, String precio, String categoria, EstadoProducto estado, ImageView imagen) {
		this.nombre = nombre;
		this.precio = precio;
		this.categoria = categoria;
		this.estado = estado;
		this.imagen = imagen;
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

	public ImageView getImagen() {
		return imagen;
	}

	public void setImagen(ImageView imagen) {
		this.imagen = imagen;
	}
	
	

}
