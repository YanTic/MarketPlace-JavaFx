package co.edu.uniquindio.Marketplace.model;

import java.io.Serializable;
import java.util.ArrayList;

import co.edu.uniquindio.Marketplace.exceptions.ProductoException;
import co.edu.uniquindio.Marketplace.exceptions.UsuarioException;
import co.edu.uniquindio.Marketplace.exceptions.VendedorException;
import co.edu.uniquindio.Marketplace.model.services.IMarketplaceService;

public class Marketplace implements Serializable, IMarketplaceService{

	private static final long serialVersionUID = 1L;

	ArrayList<Vendedor> listaVendedores = new ArrayList<>();
	ArrayList<Usuario> listaUsuarios = new ArrayList<>();

	public Marketplace() { }
	
	/*
	 *  CONTINUAR CON LOS METODOS PARA MODEL FACTORY CONTROLLER DE PRODUCTO 
	 * */

	
	// -------------- METODOS PARA MODEL FACTORY CONTROLLER DE VENDEDOR --------------
	
	
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
		// Aquí se propaga una excepcion, para que el ModelFactoryController la capture
			throw new VendedorException("El empleado con cédula: "+cedula+ " NO se ha podido crear. Ya existe");			
		}
		
		// Recordar: Que esto se retorna al ModelFactoryController, luego al CRUD, luego al
		// MarketPlaceViewController en el metodo "crearVendedor" para mostrar la alerta si
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
	
	
	
	
	
	// -------------- METODOS PARA MODEL FACTORY CONTROLLER DE PRODUCTO --------------
	
	@Override
	public Producto crearProducto(Vendedor vendedor, String nombre, String precio, String categoria, 
								  EstadoProducto estado, String rutaImagen) throws ProductoException{
		Producto nuevoProducto = null;
		boolean flagProductoExiste = false;
		
		flagProductoExiste = verificarProductoExistente(vendedor, nombre);
		
		// Esto es para no crear un producto que ya existe
		if(flagProductoExiste != true){
			nuevoProducto = new Producto();
			nuevoProducto.setNombre(nombre);
			nuevoProducto.setPrecio(precio);
			nuevoProducto.setCategoria(categoria);
			nuevoProducto.setEstado(estado);
			nuevoProducto.setRutaImagen(rutaImagen);
			
			vendedor.getListaProductos().add(nuevoProducto);
		}
		else{
		// Aquí se propaga una excepcion, para que el ModelFactoryController la capture
			throw new ProductoException("El producto \""+nombre+ "\" NO se ha podido crear. Ya existe");			
		}
		
		// Recordar: Que esto se retorna al ModelFactoryController, luego al CRUD, luego al
		// MarketPlaceViewController en el metodo "crearProducto" para mostrar la alerta si
		// el producto ha sido creado o no, y por supuesto agregar a la ObservableList (Para 
		// agregarlo a la tabla)
		return nuevoProducto;
	}
	
	@Override
	public boolean actualizarProducto(Vendedor vendedor, String nombreActual, String nombre, 
									  String precio, String categoria, EstadoProducto estado,
									  String rutaImagen) 
									  throws ProductoException {
		Boolean flagActualizado = false;
		Producto producto = getProducto(vendedor, nombreActual);
		

		if(producto != null){
			producto.setNombre(nombre);
			producto.setPrecio(precio);
			producto.setCategoria(categoria);
			producto.setEstado(estado);
			producto.setRutaImagen(rutaImagen);
			
			flagActualizado = true;
		}
		else{
			throw new ProductoException("El producto: "+nombreActual+ " NO se ha podido actualizar. No encontrado");
		}
		
		return flagActualizado;
	}
	

	@Override
	public boolean eliminarProducto(Vendedor vendedor, String nombreProducto) throws ProductoException {
		Boolean flagEliminado = false;
		Producto producto = getProducto(vendedor, nombreProducto);

		if(producto != null) {
			vendedor.getListaProductos().remove(producto);
			flagEliminado = true;
		}
		else{
			throw new ProductoException("El producto: "+nombreProducto+ " NO se ha podido eliminar. Ya Eliminado");
		}

		return flagEliminado;
	}

	
	@Override
	public Producto getProducto(Vendedor vendedor, String nombreProducto) {
		Producto productoEncontrado = null;

		for (Producto producto : vendedor.getListaProductos()) {
			if(producto.getNombre().equals(nombreProducto)) {
				productoEncontrado = producto;
				break;
			}
		}

		return productoEncontrado;
	}
	
	
	@Override
	public boolean verificarProductoExistente(Vendedor vendedor, String nombreProducto) {
		Boolean flagProductoExistente = false;
		
		// Esto compara el nombre de cada producto para verificar si se encuentra en la lista
		// de productos del vendedor seleccionado
		for(Producto producto: vendedor.getListaProductos()){
			if(producto.getNombre().equalsIgnoreCase(nombreProducto)){
				flagProductoExistente = true;
				break;
			}
		}
		
		return flagProductoExistente;
	}
	
	
	//////////////////////////////////////////////////////////////////////////////////
	/*
	 * Este metodo crea una publicacion cada vez que se crea/actualiza/elimina un producto 
	 * */
	public boolean crearPublicacion(Vendedor vendedor, Producto producto, String numeroLikes, 
								    String fechaPublicado) {
		Boolean flagPublicacionCreada = false;
		
		Publicacion nuevaPublicacion = new Publicacion();
		nuevaPublicacion.setProducto(producto);
		nuevaPublicacion.setNumeroLikes(0);
		nuevaPublicacion.setComentarios(null);
		nuevaPublicacion.setFechaPublicado(fechaPublicado);
		
//		else{
//		// Aquí se propaga una excepcion, para que el ModelFactoryController la capture
//			throw new ProductoException("El producto \""+nombre+ "\" NO se ha podido crear. Ya existe");			
//		}
		
		
		
		return flagPublicacionCreada;
	}
	
	
	
	
	
	
	
	
	
	
	
	// -------------- METODOS PARA MODEL FACTORY CONTROLLER DE USUARIO --------------
	
	@Override
	public Usuario crearUsuario(String usuario, String contrasenia) throws UsuarioException {
		Usuario nuevoUsuario = null;
		boolean flagUsuarioExiste = false;
		
		flagUsuarioExiste = verificarUsuarioExistente(usuario);
		
		// Esto es para no crear un Usuario que ya existe
		if(flagUsuarioExiste != true){
			nuevoUsuario = new Usuario();
			nuevoUsuario.setUsuario(usuario);
			nuevoUsuario.setContrasenia(contrasenia);
			getListaUsuarios().add(nuevoUsuario);
		}
		else{
		// 	Aquí se propaga una excepcion, para que el ModelFactoryController la capture
			throw new UsuarioException("El usuario: "+usuario+ " NO se ha podido crear. Ya existe");			
		}
		
		return nuevoUsuario;
	}

	@Override
	public Usuario getUsuario(String nombreUsuario, String contrasenia) {
		Usuario usuarioEncontrado = null;

		for (Usuario usuario : listaUsuarios) {
			if(usuario.getUsuario().equals(nombreUsuario)) {
				usuarioEncontrado = usuario;
				break;
			}
		}

		return usuarioEncontrado;
	}
	
	@Override
	public boolean verificarUsuarioExistente(String nombreUsuario) {
		boolean flagUsuarioExistente = false;
		
		// Esto compara la cedula de cada vendedor para verificar si se encuentra en la lista
		for(Usuario usuario: listaUsuarios){
			if(usuario.getUsuario().equalsIgnoreCase(nombreUsuario)){
				flagUsuarioExistente = true;
				break;
			}
		}
		
		return flagUsuarioExistente;
	}
	
	
	/*
	 * ESTE METODO ES DE SEGURIDAD. VERIFICA SI LA CONTRASEÑA Y EL USUARIO SON CORRECTOS
	 * PARA PODER ENTRAR A LA APLICACION
	 * */
	@Override
	public boolean verificarUsuario(String nombreUsuario, String contrasenia) throws UsuarioException {
		boolean flagUsuarioExistente = false;
		
		// Esto compara el nombre de usuario y su contrasenia para verificar 
		// si se encuentra en la lista
		for(Usuario usuario: listaUsuarios){
			if(usuario.getUsuario().equalsIgnoreCase(nombreUsuario) &&
			   usuario.getContrasenia().equals(contrasenia)){
				flagUsuarioExistente = true;
				break;
			}
		}
		
		if(!flagUsuarioExistente){
			throw new UsuarioException("Usuario o Contrasenia no coinciden");
		}
		
		return flagUsuarioExistente;
	}
	
	
	
	


	// --- Setters & Getters ---
	public ArrayList<Vendedor> getListaVendedores() {
		return listaVendedores;
	}
	public void setListaVendedores(ArrayList<Vendedor> listaVendedores) {
		this.listaVendedores = listaVendedores;
	}
	public ArrayList<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}
	public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public ArrayList<Producto> getListaProductos(Vendedor vendedorSeleccionado) {
		return vendedorSeleccionado.getListaProductos();
	}


}
