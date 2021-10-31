package co.edu.uniquindio.Marketplace.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import co.edu.uniquindio.Marketplace.model.Vendedor;
import co.edu.uniquindio.Marketplace.model.services.IModelFactoryService;
import co.edu.uniquindio.Marketplace.persistencia.Persistencia;
import co.edu.uniquindio.Marketplace.exceptions.ProductoException;
import co.edu.uniquindio.Marketplace.exceptions.UsuarioException;
import co.edu.uniquindio.Marketplace.exceptions.VendedorException;
import co.edu.uniquindio.Marketplace.model.EstadoProducto;
import co.edu.uniquindio.Marketplace.model.Marketplace;
import co.edu.uniquindio.Marketplace.model.Producto;
import co.edu.uniquindio.Marketplace.model.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/*
 * - ModelFactoryController. La clase singleton del archivo -
 * 		Aquí conectamos con los CRUD para obtener o mandar datos de
 * 		la clase principal Marketplace.
 * 
 * 		Además, aquí se capturan las excepciones que son propagadas
 * 		por la clase Marketplace (usando throws)
 * */

public class ModelFactoryController implements IModelFactoryService{
	Marketplace marketplace;


	//------------------------------  Singleton ------------------------------------------------
	// Clase estatica oculta. Tan solo se instanciara el singleton una vez
	private static class SingletonHolder {
		// El constructor de Singleton puede ser llamado desde aquí al ser protected
		private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
	}

	// Método para obtener la instancia de nuestra clase
	public static ModelFactoryController getInstance() {
		return SingletonHolder.eINSTANCE;
	}
	
// 	Esta lista observable, solo le crea en el controlador principal, para mostrar los datos
//  al usuario. Tambien se puede crear una lista observable desde acá. Pero segun nuestra 
//  estructura no es así.
//	private ObservableList<Vendedor> listaVendedoresData = FXCollections.observableArrayList();


	public ModelFactoryController(){
//		inicializarDatos();
		
		// 1. Inicializar datos y luego guardarlo en archivos
//		iniciarSalvarDatosPrueba();
		
		// 2. Cargar los datos de los archivos
//		cargarDatosDesdeArchivos();
		
		// 3. Guardar y cargar el recurso serializable binario
//		guardarResourceBinario();
//		cargarResourceBinario();
		
		// 4. Guardar y cargar el recurso serializable XML
//		guardarResourceXML();
		cargarResourceXML();
		
		// Siempre se verifica si la raiz del recurso es null
		if(marketplace == null){
			System.out.println("MARKETPLACE ES NULL");
//			inicializarDatos();
//			guardarResourceSerializable();
//			guardarResourceXML();
		}
		
		
		
		// Registrar la acción de inicio de sesion
//		Persistencia.guardaRegistroLog("Inicio de sesion del usuario: Admin", 1, "inicioSesion");
		
	}

	private void inicializarDatos() {

//		Inicializar la clase principal
		marketplace = new Marketplace();

//		Inicializar la lista de vendedors
		Vendedor vendedor = new Vendedor();
		vendedor.setNombre("Juan");
		vendedor.setApellido("Arias");
		vendedor.setCedula("1094");
		vendedor.setDireccion("Armenia");
		marketplace.getListaVendedores().add(vendedor);

		vendedor = new Vendedor();
		vendedor.setNombre("Pedro");
		vendedor.setApellido("Perez");
		vendedor.setCedula("1095");
		vendedor.setDireccion("Quimbaya");
		marketplace.getListaVendedores().add(vendedor);

		vendedor = new Vendedor();
		vendedor.setNombre("Jose");
		vendedor.setApellido("Restrepo");
		vendedor.setCedula("1096");
		vendedor.setDireccion("Salento");
		marketplace.getListaVendedores().add(vendedor);


//		Inicilizar la lista de productos
		Producto producto = new Producto();
		producto.setNombre("Helado Pelapop");
		producto.setPrecio("5000");
		producto.setCategoria("Postre");
		producto.setEstado(EstadoProducto.Publicado);
		marketplace.getListaVendedores().get(0).getListaProductos().add(producto);
//		marketplace.getListaProductos().add(producto);
		
		producto = new Producto();
		producto.setNombre("Galleta");
		producto.setPrecio("2000");
		producto.setCategoria("Pasaboca");
		producto.setEstado(EstadoProducto.Vendido);
		marketplace.getListaVendedores().get(0).getListaProductos().add(producto);
		
		producto = new Producto();
		producto.setNombre("Servilletas");
		producto.setPrecio("3500");
		producto.setCategoria("Utiles de Cocina");
		producto.setEstado(EstadoProducto.Cancelado);
		marketplace.getListaVendedores().get(0).getListaProductos().add(producto);
		
		
//		Inicializar la lista de Usuarios
		Usuario usuario = new Usuario();
		usuario.setUsuario("admin");
		usuario.setContrasenia("admin123");
		marketplace.getListaUsuarios().add(usuario);
		
		usuario = new Usuario();
		usuario.setUsuario("Juan Carlos");
		usuario.setContrasenia("carlitos1234");
		marketplace.getListaUsuarios().add(usuario);


//		listaVendedoresData.addAll(getMarketplace().getListaVendedores());
	}
	
	
	private void iniciarSalvarDatosPrueba(){
		inicializarDatos();
		
		try {
			Persistencia.guardarUsuarios(marketplace.getListaUsuarios());
			Persistencia.guardarVendedores(marketplace.getListaVendedores());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	private void cargarDatosDesdeArchivos(){
		marketplace = new Marketplace();
		
		try {
			Persistencia.cargarDatosArchivos(marketplace);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void cargarResourceBinario(){
		marketplace = Persistencia.cargarRecursoMarketplaceBinario();
	}
	
	public void guardarResourceBinario(){
		Persistencia.guardarRecursoMarketplaceBinario(marketplace);
	}
	
	public void cargarResourceXML(){
		marketplace = Persistencia.cargarRecursoMarketplaceXML();
	}
	
	public void guardarResourceXML(){
		Persistencia.guardarRecursoMarketplaceXML(marketplace);
	}
	
	
	// Registrar las acciones que provengan de los CRUD en un archivo Log
	public void registrarAccionesSistema(String mensajeLog, int nivel, String accion){
		Persistencia.guardaRegistroLog(mensajeLog, nivel, accion);
	}
	
	// Actualiza y guarda los datos en los txt "archivoUsuarios" "archivoVendedores" "archivoProductos"
	public void guardarDatosTXT(){
		try {	
			Persistencia.guardarUsuarios(marketplace.getListaUsuarios());
			Persistencia.guardarVendedores(marketplace.getListaVendedores());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	// Creo una copia de mi archivo xml en la carpeta c:/td/persistencia
	public void crearCopiaSeguridad() {
		
		
		
		Persistencia.guardarCopiaSeguridadBinario(marketplace);
		Persistencia.guardarCopiaSeguridadXML(marketplace);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// ------------------------------- CRUD -------------------------------
	
	
	
	// -------------- METODOS PARA CRUD VENDEDOR VIEW CONTROLLER --------------
	

	// Aquí aprovechamos para usar excepciones, El ModelFactoryController captura
	// esas excepciones, de resto el banco propaga las excepciones y sus clases enlazadas
	@Override
	public Vendedor crearVendedor(String nombre, String apellido, String cedula, String direccion) {
		Vendedor vendedor = null;	
		
		// 26:02 min
		try {
			vendedor = marketplace.crearVendedor(nombre, apellido, cedula, direccion);
		} catch (VendedorException e) {
//			e.printStackTrace();
			Persistencia.guardaRegistroLog(e.getMessage(), 2, "Vendedor Exception");
			e.getMessage();
		}
		
		return vendedor;
	}

	@Override
	public boolean actualizarVendedor(String cedulaActual, String nombre, String apellido, String cedula, String direccion) {
		boolean flagVendedorActualizado = false;
		
		try{
			flagVendedorActualizado = marketplace.actualizarVendedor(cedulaActual, nombre, apellido, cedula, direccion);
		}
		catch(VendedorException e){
			Persistencia.guardaRegistroLog(e.getMessage(), 2, "Vendedor Exception");
			e.getMessage();
		}
		
		return flagVendedorActualizado;
	}

	@Override
	public boolean eliminarVendedor(String cedula) {
		boolean flagVendedorEliminado = false;
		
		try{
			flagVendedorEliminado = marketplace.eliminarVendedor(cedula);			
		}
		catch(VendedorException e){
			Persistencia.guardaRegistroLog(e.getMessage(), 2, "Vendedor Exception");
			e.getMessage();
		}
		
		return flagVendedorEliminado;
		
	}
	
	
	// -------------- METODOS PARA CRUD PRODUCTO VIEW CONTROLLER --------------
	
	@Override
	public Producto crearProducto(Vendedor vendedor, String nombre, String precio, String categoria, EstadoProducto estado) {
		Producto producto = null;
		
		try{
			producto = marketplace.crearProducto(vendedor, nombre, precio, categoria, estado);			
		}
		catch(ProductoException e){
			Persistencia.guardaRegistroLog(e.getMessage(), 2, "Producto Exception");
			e.getMessage();
		}
		
		return producto;
		
	}
	
	@Override
	public boolean eliminarProducto(Vendedor vendedor, String nombre) {
		boolean flagProductoEliminado = false;
		
		try {
			flagProductoEliminado = marketplace.eliminarProducto(vendedor, nombre);
		} catch (ProductoException e) {
			Persistencia.guardaRegistroLog(e.getMessage(), 2, "Producto Exception");
			e.getMessage();
		}
		
		return flagProductoEliminado;
	}
	
	@Override
	public boolean actualizarProducto(Vendedor vendedor, String nombreActual, String nombre, 
								      String precio, String categoria, EstadoProducto estado) {
		boolean flagVendedorActualizado = false;
		
		try {
			flagVendedorActualizado = marketplace.actualizarProducto(vendedor, nombreActual, nombre, precio, categoria, estado);
		} catch (ProductoException e) {
			Persistencia.guardaRegistroLog(e.getMessage(), 2, "Producto Exception");
			e.getMessage();
		}
		
		return flagVendedorActualizado;
	}

	
	
	
	
	
	// -------------- METODOS PARA CRUD LOGIN VIEW CONTROLLER --------------
	
	@Override
	public Usuario crearUsuario(String usuario, String contrasenia) {
		Usuario nuevoUsuario = null;
		
		try{
			nuevoUsuario = marketplace.crearUsuario(usuario, contrasenia);
		} 
		catch(UsuarioException e){
			Persistencia.guardaRegistroLog(e.getMessage(), 2, "Usuario Exception");
			e.getMessage();
		}
		
		return nuevoUsuario;
		
	}
	
	public boolean verificarUsuario(String usuario, String contrasenia) {
		boolean flagUsuarioExiste = false;
		
		try {
			flagUsuarioExiste = marketplace.verificarUsuario(usuario, contrasenia);
		} 
		catch (UsuarioException e) {
			Persistencia.guardaRegistroLog(e.getMessage(), 2, "Usuario Exception");
			e.getMessage();
		}
		
		
		return flagUsuarioExiste;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public Marketplace getMarketplace() {
		return marketplace;
	}

	public void setMarketplace(Marketplace empresa) {
		this.marketplace = empresa;
	}

	@Override
	public ArrayList<Vendedor> getListaVendedores() {
		return marketplace.getListaVendedores();
	}

	@Override
	public ArrayList<Usuario> getListaUsuarios() {
		return marketplace.getListaUsuarios();
	}

	@Override
	public ArrayList<Producto> getListaProductos(Vendedor vendedorSeleccionado) {
		return marketplace.getListaVendedores().get(marketplace.getListaVendedores().
						   indexOf(vendedorSeleccionado)).getListaProductos();
	}

	


	









	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//
////-----------------------------------------------------PRODUCTO--------------------------------------------------------------------------
////-------------------------------------------------------------------------------------------------------------------------------
////-------------------------------------------------------------------------------------------------------------------------------
//
//
//
//
//	public void agregarProducto(String nombre, String codigo, String descripcion) {
//
//		Producto producto = new Producto();
//		producto.setNombre(nombre);
//		producto.setCodigo(codigo);
//		producto.setDescripcion(descripcion);
//
//		listaProdcutos.put(producto, producto.getCodigo());
//	}
//
//
//	public boolean eliminarProducto(String codigoProducto) {
//
//		//Metodo Iterator, util para recorrer un arrayList
//		Producto producto = null;
//		producto = obtenerProducto(codigoProducto);
//
//		if(producto != null)
//		{
//			getListaProdcutos().remove(producto,codigoProducto);
//		return true;
//		}
//		else
//		{
//			return false;
//		}
//	}
//
//
//	public Producto obtenerProducto(String codigoProducto) {
//
//		Producto producto = null;
//
////		for( Iterator it = getListaProdcutos().keySet().iterator(); it.hasNext();) {
////
////			producto = (Producto)it.next();
////
////			if(producto.getCodigo().equals(codigoProducto))
////			{
////				return producto;
////			}
////		}
////
////		iteratorProducto= getListaProdcutos().keySet().iterator();
////
////		while(iteratorProducto.hasNext()){
////			producto = iteratorProducto.next();
////			if(producto.getCodigo().equals(codigoProducto))
////			{
////				return producto;
////			}
////		}
//		return producto;
//	}
//



//	public List<Cliente> getListaClientes() {
//		return listaClientes;
//	}
//
//	public void setListaClientes(List<Cliente> listaClientes) {
//		this.listaClientes = listaClientes;
//	}
//
//	public Set<Vendedor> getListaVendores() {
//		return listaVendores;
//	}
//
//	public void setListaVendores(Set<Vendedor> listaVendores) {
//		this.listaVendores = listaVendores;
//	}
//
//	public HashMap<Producto, String> getListaProdcutos() {
//		return listaProdcutos;
//	}
//
//	public void setListaProdcutos(HashMap<Producto, String> listaProdcutos) {
//		this.listaProdcutos = listaProdcutos;
//	}
//
//	public TreeSet getListaProductosAsociadosClientes() {
//		return listaProductosAsociadosClientes;
//	}
//
//	public void setListaProductosAsociadosClientes(TreeSet listaProductosAsociadosClientes) {
//		this.listaProductosAsociadosClientes = listaProductosAsociadosClientes;
//	}
//
//	public TreeSet getListaProductosAsociadosVendedor() {
//		return listaProductosAsociadosVendedor;
//	}
//
//	public void setListaProductosAsociadosVendedor(TreeSet listaProductosAsociadosVendedor) {
//		this.listaProductosAsociadosVendedor = listaProductosAsociadosVendedor;
//	}






//	public List<Vendedor> getListaVendores() {
//		return listaVendores;
//	}
//
//	public void setListaVendores(List<Vendedor> listaVendores) {
//		this.listaVendores = listaVendores;
//	}
//
//	public List<Producto> getListaProdcutos() {
//		return listaProdcutos;
//	}
//
//	public void setListaProdcutos(List<Producto> listaProdcutos) {
//		this.listaProdcutos = listaProdcutos;
//	}







}
