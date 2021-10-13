package co.edu.uniquindio.Marketplace.controller;

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
import co.edu.uniquindio.Marketplace.exceptions.VendedorException;
import co.edu.uniquindio.Marketplace.model.Marketplace;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ModelFactoryController implements IModelFactoryService{
	Marketplace marketplace;


	//------------------------------  Singleton ------------------------------------------------
	// Clase estatica oculta. Tan solo se instanciara el singleton una vez
	private static class SingletonHolder {
		// El constructor de Singleton puede ser llamado desde aqu� al ser protected
		private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
	}

	// M�todo para obtener la instancia de nuestra clase
	public static ModelFactoryController getInstance() {
		return SingletonHolder.eINSTANCE;
	}
	
// 	Esta lista observable, solo le crea en el controlador principal, para mostrar los datos
//  al usuario. Tambien se puede crear una lista observable desde ac�. Pero segun nuestra 
//  estructura no es as�.
//	private ObservableList<Vendedor> listaVendedoresData = FXCollections.observableArrayList();


	public ModelFactoryController(){
		inicializarDatos();
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


/*
//		Inicializar la lista de Empleados
		Empleado empleado = new Empleado();
		empleado.setNombre("Jorge");
		empleado.setApellido("Arias");
		empleado.setCedula("8913");
		empresa.getListaEmpleados().add(empleado);


		empleado = new Empleado();
		empleado.setNombre("Jorge");
		empleado.setApellido("Arias");
		empleado.setCedula("8913");
		empresa.getListaEmpleados().add(empleado);


//		Inicializar la lista de Objetos
		Objeto objeto = new Objeto();
		objeto.setCodigo(1);
		objeto.setNombre("Pala");
		objeto.setDescripcion("Pala de acero");
		objeto.setEstado("Disponible");
		empresa.getListaObjetos().add(objeto);

		objeto = new Objeto();
		objeto.setCodigo(2);
		objeto.setNombre("Linterna");
		objeto.setDescripcion("Sin pilas");
		objeto.setEstado("Disponible");
		empresa.getListaObjetos().add(objeto);

		objeto = new Objeto();
		objeto.setCodigo(3);
		objeto.setNombre("Palin");
		objeto.setDescripcion("en buen estado");
		objeto.setEstado("Disponible");
		empresa.getListaObjetos().add(objeto);

*/
//		listaVendedoresData.addAll(getMarketplace().getListaVendedores());
	}

//	public ObservableList<Vendedor> getListaVendedoresData() {
//		return listaVendedoresData;
//	}
	
//	public ArrayList<Vendedor> getListaVendedores(){
//		return marketplace.getListaVendedores();
//	}

	public Marketplace getMarketplace() {
		return marketplace;
	}

	public void setMarketplace(Marketplace empresa) {
		this.marketplace = empresa;
	}

	// Aqu� aprovechamos para usar excepciones, El ModelFactoryController captura
	// esas excepciones, de resto el banco propaga las excepciones y sus clases enlazadas
	@Override
	public Vendedor crearVendedor(String nombre, String apellido, String cedula, String direccion) {
		Vendedor vendedor = null;	
		
		// 26:02 min
		try {
			vendedor = marketplace.crearVendedor(nombre, apellido, cedula, direccion);
		} catch (VendedorException e) {
//			e.printStackTrace();
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
			e.getMessage();
		}
		
		return flagVendedorActualizado;
	}

	@Override
	public Vendedor getVendedor(String cedula) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean eliminarVendedor(String cedula) {
		boolean flagVendedorEliminado = false;
		
		try{
			flagVendedorEliminado = marketplace.eliminarVendedor(cedula);			
		}
		catch(VendedorException e){
			e.getMessage();
		}
		
		return flagVendedorEliminado;
		
	}

	@Override
	public ArrayList<Vendedor> getListaVendedores() {
		return marketplace.getListaVendedores();
	}	








//------------------------------------------------------CLIENTE-------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------
//
//
//	public void agregarCliente(String nombres, String apellidos, String cedula, String descripcion) {
//
////		Cliente cliente = new Cliente();
////		cliente.setNombres(nombres);
////		cliente.setApellidos(apellidos);
////		cliente.setCedula(cedula);
////		cliente.setDescripcion(descripcion);
////		listaClientes.add(cliente);
//	}
//
//
//	public boolean eliminarCliente(String cedulaCliente) {
//
//		//Metodo Iterator, util para recorrer un arrayList
//		Cliente cliente = null;
//		cliente = obtenerCliente(cedulaCliente);
//
//		if(cliente != null)
//		{
//			getListaClientes().remove(cliente);
//		return true;
//		}
//		else
//		{
//			return false;
//		}
//
//	}
//
//	public Cliente obtenerCliente(String cedulaCliente) {
//
//		Cliente cliente = null;
////		iteratorCliente = getListaClientes().iterator();
////
////		while (iteratorCliente.hasNext()){
////			cliente = iteratorCliente.next();
////
////			if(cliente.getCedula().equalsIgnoreCase(cedulaCliente))
////			{
////				return cliente;
////			}
////		}
//		return cliente;
//	}
//
////------------------------------------------------------VENDEDOR-------------------------------------------------------------------------
////-------------------------------------------------------------------------------------------------------------------------------
////-------------------------------------------------------------------------------------------------------------------------------
//
//	public void agregarVendedor(String nombres, String apellidos, String cedula, String descripcion) {
//
////		Vendedor vendedor = new Vendedor();
////		vendedor.setNombres(nombres);
////		vendedor.setApellidos(apellidos);
////		vendedor.setCedula(cedula);
////		vendedor.setDescripcion(descripcion);
////
////		listaVendores.add(vendedor);
//
//	}
//
//
//	public boolean eliminarVendedor(String cedulaVendedor) {
//
//		//Metodo Iterator, util para recorrer un arrayList
////		Vendedor vendedor = null;
////
////		vendedor = obtenerVendedor(cedulaVendedor);
////
////		if(vendedor != null)
////		{
////			getListaVendores().remove(vendedor);
////		return true;
////		}
////		else
////		{
////			return false;
////		}
//		return false;
//	}
//
//
//	public Vendedor obtenerVendedor(String cedulaVendedor) {
//
//		Vendedor vendedor = null;
////		iteratorVendedor = getListaVendores().iterator();
////
////		while (iteratorVendedor.hasNext()){
////			vendedor = iteratorVendedor.next();
////
////			if(vendedor.getCedula().equalsIgnoreCase(cedulaVendedor))
////			{
////				return vendedor;
////			}
////		}
//		return vendedor;
//	}
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
