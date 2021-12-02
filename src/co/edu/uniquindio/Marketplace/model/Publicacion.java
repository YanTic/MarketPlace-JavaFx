package co.edu.uniquindio.Marketplace.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Publicacion implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nombreProducto;
	private String precioProducto;
	private String rutaImagenProducto;
	private String estadoProducto;
	private String fechaPublicado;
	private int cantidadLikes;
	private ArrayList<String> comentarios;
	
	public Publicacion() {
		
	}

	public int getCantidadLikes() {
		return cantidadLikes;
	}

	public void setCantidadLikes(int cantidadLikes) {
		this.cantidadLikes = cantidadLikes;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getPrecioProducto() {
		return precioProducto;
	}

	public void setPrecioProducto(String precioProducto) {
		this.precioProducto = precioProducto;
	}

	public String getRutaImagenProducto() {
		return rutaImagenProducto;
	}

	public void setRutaImagenProducto(String rutaImagenProducto) {
		this.rutaImagenProducto = rutaImagenProducto;
	}

	public String getEstadoProducto() {
		return estadoProducto;
	}

	public void setEstadoProducto(String estadoProducto) {
		this.estadoProducto = estadoProducto;
	}

	public String getFechaPublicado() {
		return fechaPublicado;
	}

	public void setFechaPublicado(String fechaPublicado) {
		this.fechaPublicado = fechaPublicado;
	}

	public ArrayList<String> getComentarios() {
		return comentarios;
	}

	public void setComentarios(ArrayList<String> comentarios) {
		this.comentarios = comentarios;
	}

		

}
