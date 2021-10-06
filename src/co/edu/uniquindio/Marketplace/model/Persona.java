package co.edu.uniquindio.Marketplace.model;

import java.io.Serializable;
import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty; import javafx.beans.property.SimpleObjectProperty; import javafx.beans.property.SimpleStringProperty; import javafx.beans.property.StringProperty;

public abstract class Persona implements Serializable {


	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private StringProperty nombre;
	private StringProperty apellido;
	private StringProperty cedula;
	private StringProperty telefono;



	public Persona() {

	}


	public Persona(String cedula, String nombre, String telefono, String apellido) {
		super();
		this.cedula = new SimpleStringProperty(cedula);
		this.nombre =  new SimpleStringProperty(nombre);
		this.telefono =  new SimpleStringProperty(telefono);
		this.apellido =  new SimpleStringProperty(apellido);
	}


	public String getCedula() {
		return cedula.get();
	}

	public void setCedula(String cedula) {
//		this.cedula.set(cedula);
		this.cedula = new SimpleStringProperty(cedula);
	}

	public String getNombre() {
		return nombre.get();
	}

	public void setNombre(String nombre) {
//		this.nombre.set(nombre);
		this.nombre =  new SimpleStringProperty(nombre);
	}

	public String getTelefono() {
		return telefono.get();
	}

	public void setTelefono(String telefono) {
//		this.telefono.set(telefono);
		this.telefono =  new SimpleStringProperty(telefono);
	}


	public String getApellido() {
		return apellido.get();
	}


	public void setApellido(String apellido) {
//
		this.apellido =  new SimpleStringProperty(apellido);
	}



	public StringProperty getNombreProperty() {
		return nombre;
	}


//	public void setNombre(StringProperty nombre) {
//		this.nombre = nombre;
//	}


	public StringProperty getApellidoProperty() {
		return apellido;
	}


//	public void setApellido(StringProperty apellido) {
//		this.apellido = apellido;
//	}


	public StringProperty getCedulaProperty() {
		return cedula;
	}


//	public void setCedula(StringProperty cedula) {
//		this.cedula = cedula;
//	}


	public StringProperty getTelefonoProperty() {
		return telefono;
	}


//	public void setTelefono(StringProperty telefono) {
//		this.telefono = telefono;
//	}





}
