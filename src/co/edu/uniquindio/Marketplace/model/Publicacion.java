package co.edu.uniquindio.Marketplace.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Publicacion implements Serializable {

	private static final long serialVersionUID = 1L;
	private Producto producto;
	private int numeroLikes;
	private String fechaPublicado;
	private ArrayList<String> comentarios;
	
	public Publicacion() {
		
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getNumeroLikes() {
		return numeroLikes;
	}

	public void setNumeroLikes(int numeroLikes) {
		this.numeroLikes = numeroLikes;
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
