package co.edu.uniquindio.Marketplace.model;

public class Usuario {

	private String usuario;
	private String contrasenia;

	public Usuario(String usuario, String contrasenia) {
		super();
		this.usuario = usuario;
		this.contrasenia = contrasenia;
	}

	public Usuario() {
		super();
	}


	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}


	@Override
	public String toString() {
		return "Usuario [usuario=" + usuario + ", contrasenia=" + contrasenia + "]";
	}


}
