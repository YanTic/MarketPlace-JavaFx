package co.edu.uniquindio.Marketplace.model;

import java.io.Serializable;
import java.util.ArrayList;

import co.edu.uniquindio.Marketplace.exceptions.VendedorException;
import co.edu.uniquindio.Marketplace.model.services.IMarketplaceService;

public class Marketplace implements Serializable, IMarketplaceService{

	/**
	 * 	ESTOY EN 29:00 MIN
	 */
	private static final long serialVersionUID = 1L;

	ArrayList<Vendedor> listaVendedores = new ArrayList<>();
	ArrayList<Producto> listaProductos = new ArrayList<>();

	public Marketplace() {

	}

	
	@Override
	public Vendedor crearVendedor(String nombre, String apellido, String cedula, String direccion) throws VendedorException {
		Vendedor nuevoVendedor = null;
		boolean flagVendedorExiste = false;
		
		flagVendedorExiste = verificarVendedorExistente(cedula);
		
		// Esto es para no crear un vendedor que ya existe
		if(flagVendedorExiste != true){
			nuevoVendedor = new Vendedor();
			nuevoVendedor.setNombre(nombre);
			nuevoVendedor.setApellido(apellido);
			nuevoVendedor.setCedula(cedula);
			nuevoVendedor.setDireccion(direccion);
			getListaVendedores().add(nuevoVendedor);
		}
		else{
		// Aquí se propago una excepcion, para que el ModelFactoryController la capture
			throw new VendedorException("El empleado con cédula: "+cedula+ " NO se ha podido crear. Ya existe");			
		}
		
		// Recordar: Que esto se retorna al ModelFactoryController, luego al CRUD, luego al
		// MarketPlaceViewController en el metodo "crearVendedor" para mostra la alerta si
		// el vendedor ha sido creado o no, y por supuesto agregar a la ObservableList (Para 
		// agregarlo a la tabla)
		return nuevoVendedor;
	}
	
	@Override
	public boolean actualizarVendedor(String cedulaActual, String nombre, String apellido, String cedula, String direccion) throws VendedorException {
		Boolean flagActualizado = false;
		Vendedor vendedor = getVendedor(cedulaActual);
		

		if(vendedor != null){
			vendedor.setNombre(nombre);
			vendedor.setApellido(apellido);
			vendedor.setCedula(cedula);
			vendedor.setDireccion(direccion);
			
			flagActualizado = true;
		}
		else{
			throw new VendedorException("El empleado con cédula: "+cedulaActual+ " NO se ha podido actualizar. No encontrado");
		}
		
		return flagActualizado;
	}
	
	

	@Override
	public boolean eliminarVendedor(String cedulaVendedor) throws VendedorException {

		Boolean flagEliminado = false;
		Vendedor vendedor = getVendedor(cedulaVendedor);

		if(vendedor != null) {
			getListaVendedores().remove(vendedor);
			flagEliminado = true;
		}
		else{
			throw new VendedorException("El empleado con cédula: "+cedulaVendedor+ " NO se ha podido eliminar. Ya Eliminado");
		}

		return flagEliminado;
	}

	@Override
	public Vendedor getVendedor(String cedulaVendedor) {

		Vendedor vendedorEncontrado = null;

		for (Vendedor vendedor : listaVendedores) {
			if(vendedor.getCedula().equals(cedulaVendedor)) {
				vendedorEncontrado = vendedor;
				break;
			}
		}

		return vendedorEncontrado;
	}
	
	@Override
	public boolean verificarVendedorExistente(String cedula){
		Boolean flagVendedorExistente = false;
		
		// Esto compara la cedula de cada vendedor para verificar si se encuentra en la lista
		for(Vendedor vendedor: listaVendedores){
			if(vendedor.getCedula().equalsIgnoreCase(cedula)){
				flagVendedorExistente = true;
				break;
			}
		}
		
		return flagVendedorExistente;
		
	}
	
	
	// --- Setters & Getters ---
	public ArrayList<Producto> getListaProductos() {
		return listaProductos;
	}
	public void setListaProductos(ArrayList<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}
	public ArrayList<Vendedor> getListaVendedores() {
		return listaVendedores;
	}
	public void setListaVendedores(ArrayList<Vendedor> listaVendedores) {
		this.listaVendedores = listaVendedores;
	}

}
