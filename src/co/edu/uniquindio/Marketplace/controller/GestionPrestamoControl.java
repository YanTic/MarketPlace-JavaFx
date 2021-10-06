package co.edu.uniquindio.Marketplace.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import co.edu.uniquindio.Marketplace.model.Cliente;
import co.edu.uniquindio.Marketplace.model.Empleado;
import co.edu.uniquindio.Marketplace.model.Empresa;
import co.edu.uniquindio.Marketplace.model.Objeto;
import co.edu.uniquindio.Marketplace.model.Persona;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GestionPrestamoControl
{
	Empresa empresa;


	//------------------------------  Singleton ------------------------------------------------
	// Clase estatica oculta. Tan solo se instanciara el singleton una vez
	private static class SingletonHolder {
		// El constructor de Singleton puede ser llamado desde aqu� al ser protected
		private final static GestionPrestamoControl eINSTANCE = new GestionPrestamoControl();
	}

	// M�todo para obtener la instancia de nuestra clase
	public static GestionPrestamoControl getInstance() {
		return SingletonHolder.eINSTANCE;
	}
	private ObservableList<Cliente> listClienteData = FXCollections.observableArrayList();


	public GestionPrestamoControl(){
		inicializarDatos();
	}

	private void inicializarDatos() {

//		Inicilaizar la clase principal
		empresa = new Empresa();

//		Inicializar la lista de clientes
		Cliente cliente = new Cliente();
		cliente.setNombre("Juan");
		cliente.setApellido("Arias");
		cliente.setCedula("1094");
		cliente.setDireccion("Armenia");
		empresa.getListaClientes().add(cliente);

		cliente = new Cliente();
		cliente.setNombre("Pedro");
		cliente.setApellido("Perez");
		cliente.setCedula("1095");
		cliente.setDireccion("Quimbaya");
		empresa.getListaClientes().add(cliente);

		cliente = new Cliente();
		cliente.setNombre("Jose");
		cliente.setApellido("Restrepo");
		cliente.setCedula("1096");
		cliente.setDireccion("Salento");
		empresa.getListaClientes().add(cliente);



//		Inicializar la lista de Empleados
		Empleado vendedor = new Empleado();
		vendedor.setNombre("Jorge");
		vendedor.setApellido("Arias");
		vendedor.setCedula("8913");
		empresa.getListaEmpleados().add(vendedor);


		vendedor = new Empleado();
		vendedor.setNombre("Jorge");
		vendedor.setApellido("Arias");
		vendedor.setCedula("8913");
		empresa.getListaEmpleados().add(vendedor);


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


		listClienteData.addAll(getEmpresa().getListaClientes());
	}

	public ObservableList<Cliente> getClienteData() {
		return listClienteData;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public void eliminarCliente(Cliente clienteSeleccionado) {
		getEmpresa().eliminarcliente(clienteSeleccionado.getCedula());
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
