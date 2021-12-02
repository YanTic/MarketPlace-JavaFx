package co.edu.uniquindio.Marketplace.persistencia;

import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import co.edu.uniquindio.Marketplace.exceptions.UsuarioException;
import co.edu.uniquindio.Marketplace.model.EstadoProducto;
import co.edu.uniquindio.Marketplace.model.Marketplace;
import co.edu.uniquindio.Marketplace.model.Producto;
import co.edu.uniquindio.Marketplace.model.Publicacion;
import co.edu.uniquindio.Marketplace.model.Usuario;
import co.edu.uniquindio.Marketplace.model.Vendedor;


public class Persistencia {
	
//	public static final String RUTA_ARCHIVO_EMPLEADOS = "src/resources/archivoEmpleados.txt";
//	public static final String RUTA_ARCHIVO_OBJETOS = "src/resources/archivoObjetos.txt";

	public static final String RUTA_ARCHIVO_VENDEDORES = "src/resources/archivoVendedores.txt";
	public static final String RUTA_ARCHIVO_USUARIOS = "src/resources/archivoUsuarios.txt";
	public static final String RUTA_ARCHIVO_PRODUCTOS = "src/resources/archivoProductos.txt";
	public static final String RUTA_ARCHIVO_CONTACTOS = "src/resources/archivoContactos.txt";
	public static final String RUTA_ARCHIVO_PUBLICACIONES = "src/resources/archivoPublicaciones.txt";
	public static final String RUTA_ARCHIVO_LOG = "src/resources/MarketplaceLog.txt";
	public static final String RUTA_ARCHIVO_MODELO_MARKETPLACE_BINARIO = "src/resources/model.dat";
	public static final String RUTA_ARCHIVO_MODELO_MARKETPLACE_XML = "src/resources/model.xml";
	public static final String RUTA_ARCHIVO_IMAGEN_PRODUCTOS = "src/resources/imagenesProducto/";

	// RUTAS DE SEGURIDAD	(Con Rutas Absolutas)
	public static final String RUTA_ARCHIVO_SEGURIDAD_MODELO_MARKETPLACE_XML = "C:/td/persistencia/model.xml";
	public static final String RUTA_ARCHIVO_SEGURIDAD_MODELO_MARKETPLACE_BINARIO = "C:/td/persistencia/model.dat";
	public static final String RUTA_ARCHIVO_SEGURIDAD_LOG = "C:/td/persistencia/log/MarketplaceLog.txt";
	public static final String RUTA_ARCHIVO_SEGURIDAD_VENDEDORES = "C:/td/persistencia/archivos/archivoVendedores.txt";
	public static final String RUTA_ARCHIVO_SEGURIDAD_USUARIOS = "C:/td/persistencia/archivos/archivoUsuarios.txt";
	public static final String RUTA_ARCHIVO_SEGURIDAD_PRODUCTOS = "C:/td/persistencia/archivos/archivoProductos.txt";
	public static final String RUTA_ARCHIVO_SEGURIDAD_CONTACTOS = "C:/td/persistencia/archivos/archivoContactos.txt";
	public static final String RUTA_ARCHIVO_SEGURIDAD_PUBLICACIONES = "C:/td/persistencia/archivos/archivoPublicaciones.txt";
	
	public static final String RUTA_ARCHIVO_SEGURIDAD_RESPALDO = "C:/td/persistencia/respaldo/Marketplace_";
	
	
	public static void cargarDatosArchivos(Marketplace marketplace) throws FileNotFoundException, IOException {
		
		
		// Cargar archivo de vendedores (Recordar: Los vendedores contienen sus productos,
		// por lo tanto, esto tambien carga los productos de cada vendedor)
		ArrayList<Vendedor> vendedoresCargados = cargarVendedores();
		
		if(vendedoresCargados.size() > 0)
			marketplace.getListaVendedores().addAll(vendedoresCargados);

		
		// Cargar usuarios
		ArrayList<Usuario> usuariosCargados = cargarUsuarios();
		
		if(usuariosCargados.size() > 0)
			marketplace.getListaUsuarios().addAll(usuariosCargados);
		
		
		
		
		
		
		
//		//cargar archivos empleados
//		ArrayList<Empleado> empleadosCargados = cargarEmpleados();
//		
//		if(empleadosCargados.size() > 0)
//			banco.getListaEmpleados().addAll(empleadosCargados);
//		
		
		
		
		//cargar archivo objetos
		
		//cargar archivo empleados
		
		//cargar archivo prestamo
		
	}
	
	
	




	/**
	 * Guarda en un archivo de texto todos la información de las personas almacenadas en el ArrayList
	 * @param objetos
	 * @param ruta
	 * @throws IOException
	 */
	public static void guardarVendedores(ArrayList<Vendedor> listaVendedores) throws IOException {
		// TODO Auto-generated method stub
		String contenidoVendedores = "";
		String contenidoProductos = "";
		String contenidoContactos = "";
		String contenidoPublicaciones = "";
		
		for(Vendedor vendedor :listaVendedores) 
		{
			contenidoVendedores += vendedor.getNombre()+ "@@"+ vendedor.getApellido()+ "@@"+ 
					     		   vendedor.getCedula()+ "@@"+ vendedor.getDireccion()+ "\n";
			contenidoProductos += guardarProductos(vendedor);
			contenidoContactos += guardarContactos(vendedor);
			contenidoPublicaciones += guardarPublicaciones(vendedor); 
			
		}
		
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_VENDEDORES, contenidoVendedores, false);
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_PRODUCTOS, contenidoProductos, false);
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_CONTACTOS, contenidoContactos, false);
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_PUBLICACIONES, contenidoPublicaciones, false);
		
	}
	
	public static String guardarProductos(Vendedor vendedor) throws IOException {
		String contenido = "";
		
		System.out.println("GUARDANDO PRODUCTOS");
		
		for(Producto producto : vendedor.getListaProductos()) 
		{
			contenido += vendedor.getNombre()+ "@@"+ producto.getNombre()+ "@@"+ 
					     producto.getPrecio()+ "@@"+ producto.getCategoria()+ "@@"+
					     producto.getEstado()+ "@@"+ producto.getRutaImagen()+ "\n";
		}
		
		return contenido;
	}
	
	public static String guardarContactos(Vendedor vendedor) throws IOException {
		String contenido = "";
		
		System.out.println("GUARDANDO CONTACTOS");
		
		for(Vendedor contacto : vendedor.getListaContactos()) 
		{
			contenido += vendedor.getNombre()+ "@@"+ contacto.getNombre()+ "@@"+ 
						 contacto.getCedula()+ "\n";
		}
		
		return contenido;
	}
	
	public static String guardarPublicaciones(Vendedor vendedor) throws IOException {
		String contenido = "";
		
		System.out.println("GUARDANDO PUBLICACIONES");
		
		for(Publicacion publicacion : vendedor.getListaPublicaciones()) 
		{
			contenido += vendedor.getNombre()+ "@@"+ publicacion.getNombreProducto()+ "@@"+ 
					     publicacion.getPrecioProducto()+ "@@"+ publicacion.getRutaImagenProducto()+ "@@"+
					     publicacion.getEstadoProducto()+ "@@"+ publicacion.getFechaPublicado()+ "@@"+
					     publicacion.getCantidadLikes()+ "\n";
		}
		
		return contenido;
	}
	
	
	public static void guardarUsuarios(ArrayList<Usuario> listaUsuarios) throws IOException {
		// TODO Auto-generated method stub
		String contenido = "";
		
		for(Usuario usuario : listaUsuarios) 
		{
			contenido += usuario.getUsuario()+ "@@"+ usuario.getContrasenia()+"\n";
		}
		
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_USUARIOS, contenido, false);
		
	}
	
	
//	public static void guardarEmpleados(ArrayList<Empleado> listaEmpleados) throws IOException {
//		
//		// TODO Auto-generated method stub
//		String contenido = "";
//		
//		for(Empleado empleado:listaEmpleados) 
//		{
//			contenido+= empleado.getNombre()+","+empleado.getApellido()+","+empleado.getCedula()+","+empleado.getFechaNacimiento()+"\n";
//		}
//		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_EMPLEADOS, contenido, false);
//	}
	
	
	
//	----------------------LOADS------------------------
	
	/**
	 * 
	 * @param tipoPersona
	 * @param ruta
	 * @return un Arraylist de personas con los datos obtenidos del archivo de texto indicado
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static ArrayList<Vendedor> cargarVendedores() throws FileNotFoundException, IOException 
	{
		ArrayList<Vendedor> vendedores = new ArrayList<Vendedor>();
		
		ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_VENDEDORES);
		String linea="";
		
		for (int i = 0; i < contenido.size(); i++)
		{
			linea = contenido.get(i); // Pedro@@Perez@@1095@@Quimbaya
			Vendedor vendedor = new Vendedor();
			vendedor.setNombre(linea.split("@@")[0]);
			vendedor.setApellido(linea.split("@@")[1]);
			vendedor.setCedula(linea.split("@@")[2]);
			vendedor.setDireccion(linea.split("@@")[3]);
			
//			cliente.setCorreo(linea.split(",")[4]);
//			cliente.setFechaNacimiento(linea.split(",")[5]);
//			cliente.setTelefono(linea.split(",")[6]);
			
			vendedor.getListaProductos().addAll(cargarProductos(vendedor));
			vendedor.getListaContactos().addAll(cargarContactos(vendedor));
			vendedor.getListaPublicaciones().addAll(cargarPublicaciones(vendedor));
//			vendedor.setListaProductos(cargarProductos(vendedor));
			
			vendedores.add(vendedor);
		}
		return vendedores;
	}
	
	
	/*
	 * Metodo que carga los producto de cada vendedor, este es llamando cuando se
	 * cargan los vendedores
	 * */
	public static ArrayList<Producto> cargarProductos(Vendedor vendedor) throws FileNotFoundException, IOException 
	{
		ArrayList<Producto> productos = new ArrayList<Producto>();
		
		ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_PRODUCTOS);
		String linea="";
		
		for (int i = 0; i < contenido.size(); i++)
		{
			linea = contenido.get(i);	// Juan@@Helado Pelapop@@5000@@Postre@@Publicado
			
			// Verifico si le estoy añadiendo los productos al vendedor correspondiente
			if(linea.split("@@")[0].equals(vendedor.getNombre())){
				Producto producto = new Producto();
				
				// No se carga el "linea.split("@@")[0]" porque es el nombre del vendedor
				producto.setNombre(linea.split("@@")[1]);
				producto.setPrecio(linea.split("@@")[2]);
				producto.setCategoria(linea.split("@@")[3]);
				producto.setEstado(EstadoProducto.valueOf(linea.split("@@")[4]));
				producto.setRutaImagen(linea.split("@@")[5]);
				
				vendedor.getListaProductos().add(producto);	
			}
		
		}
		return productos;
	}
	
	public static ArrayList<Vendedor> cargarContactos(Vendedor vendedor) throws FileNotFoundException, IOException 
	{
		ArrayList<Vendedor> contactos = new ArrayList<Vendedor>();
		
		ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_CONTACTOS);
		String linea="";
		
		for (int i = 0; i < contenido.size(); i++)
		{
			linea = contenido.get(i);	// Juan@@Julian@@123123
			
			// Verifico si le estoy añadiendo los productos al vendedor correspondiente
			if(linea.split("@@")[0].equals(vendedor.getNombre())){
				Vendedor contacto = new Vendedor();
				
				// No se carga el "linea.split("@@")[0]" porque es el nombre del vendedor
				contacto.setNombre(linea.split("@@")[1]);
				contacto.setCedula(linea.split("@@")[2]);
				
				vendedor.getListaContactos().add(contacto);	
			}
		}
		
		return contactos;
	}
	
	public static ArrayList<Publicacion> cargarPublicaciones(Vendedor vendedor) throws FileNotFoundException, IOException 
	{
		ArrayList<Publicacion> publicaciones = new ArrayList<Publicacion>();
		
		ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_PUBLICACIONES);
		String linea="";
		
		for (int i = 0; i < contenido.size(); i++)
		{
			linea = contenido.get(i);	// Juan@@Helado Pelapop@@5000@@src/view/imagen@@Publicado@@11_10_2021@@3
			
			// Verifico si le estoy añadiendo los productos al vendedor correspondiente
			if(linea.split("@@")[0].equals(vendedor.getNombre())){
				
//				contenido += vendedor.getNombre()+ "@@"+ publicacion.getNombreProducto()+ "@@"+ 
//					     publicacion.getPrecioProducto()+ "@@"+ publicacion.getRutaImagenProducto()+ "@@"+
//					     publicacion.getEstadoProducto()+ "@@"+ publicacion.getFechaPublicado()+ "@@"+
//					     publicacion.getCantidadLikes()+ "\n"; 
				
				Publicacion publicacion = new Publicacion();
				
				// No se carga el "linea.split("@@")[0]" porque es el nombre del vendedor
				publicacion.setNombreProducto(linea.split("@@")[1]);
				publicacion.setPrecioProducto(linea.split("@@")[2]);
				publicacion.setRutaImagenProducto(linea.split("@@")[3]);
				publicacion.setEstadoProducto(linea.split("@@")[4]);
				publicacion.setFechaPublicado(linea.split("@@")[5]);
				publicacion.setCantidadLikes(Integer.parseInt(linea.split("@@")[6]));
				
				vendedor.getListaPublicaciones().add(publicacion);
			}
			
		}
		return publicaciones; // La verdad no se para que retorno si esto está vacio, directamente la publicacion se le agrega a la lista del vendedor
	}
	
	
	public static ArrayList<Usuario> cargarUsuarios() throws FileNotFoundException, IOException 
	{
		ArrayList<Usuario> usuarios =new ArrayList<Usuario>();
		
		ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_USUARIOS);
		String linea="";
		
		for (int i = 0; i < contenido.size(); i++)
		{
			linea = contenido.get(i);// admin@@admin123
			Usuario usuario = new Usuario();
			usuario.setUsuario(linea.split("@@")[0]);
			usuario.setContrasenia(linea.split("@@")[1]);

			usuarios.add(usuario);
		}
		return usuarios;
	}
	
	
//	private static ArrayList<Empleado> cargarEmpleados() throws FileNotFoundException, IOException {
//
//		ArrayList<Empleado> empleados =new ArrayList<Empleado>();
//		
//		ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_EMPLEADOS);
//		String linea="";
//		
//		for (int i = 0; i < contenido.size(); i++)
//		{
//			linea = contenido.get(i);
//			Empleado empleado = new Empleado();
//			empleado.setNombre(linea.split(",")[0]);
//			empleado.setApellido(linea.split(",")[1]);
//			empleado.setCedula(linea.split(",")[2]);
//			empleado.setFechaNacimiento(linea.split(",")[3]);
//			empleados.add(empleado);
//		}
//		return empleados;
//		
//		
//	}
	
	


	public static void guardaRegistroLog(String mensajeLog, int nivel, String accion)
	{		
		ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG);
	}


	public static boolean iniciarSesion(String usuario, String contrasenia) throws FileNotFoundException, IOException, UsuarioException {
		
		if(validarUsuario(usuario,contrasenia)) {
			return true;
		}else {
			throw new UsuarioException("Usuario no existe");
		}
		
	}
	
	private static boolean validarUsuario(String usuario, String contrasenia) throws FileNotFoundException, IOException 
	{
		ArrayList<Usuario> usuarios = Persistencia.cargarUsuarios(RUTA_ARCHIVO_USUARIOS);
		
		for (int indiceUsuario = 0; indiceUsuario < usuarios.size(); indiceUsuario++) 
		{
			Usuario usuarioAux = usuarios.get(indiceUsuario);
			if(usuarioAux.getUsuario().equalsIgnoreCase(usuario) && usuarioAux.getContrasenia().equalsIgnoreCase(contrasenia)) {
				return true;
			}
		}
		return false;
	}

	public static ArrayList<Usuario> cargarUsuarios(String ruta) throws FileNotFoundException, IOException {
		ArrayList<Usuario> usuarios =new ArrayList<Usuario>();
		
		ArrayList<String> contenido = ArchivoUtil.leerArchivo(ruta);
		String linea="";
		
		for (int i = 0; i < contenido.size(); i++) {
			linea = contenido.get(i);
			
			Usuario usuario = new Usuario();
			usuario.setUsuario(linea.split(",")[0]);
			usuario.setContrasenia(linea.split(",")[1]);
			
			usuarios.add(usuario);
		}
		return usuarios;
	}
	
	
//	----------------------SAVES------------------------
	
	/**
	 * Guarda en un archivo de texto todos la información de las personas almacenadas en el ArrayList
	 * @param objetos
	 * @param ruta
	 * @throws IOException
	 */
	
	public static void guardarObjetos(ArrayList<Vendedor> listaVendedores, String ruta) throws IOException  {
		String contenido = "";
		
		for(Vendedor vendedorAux : listaVendedores) {
			contenido += vendedorAux.getNombre()+ ","+ vendedorAux.getApellido()+ ","+
						 vendedorAux.getCedula()+ vendedorAux.getDireccion() +"\n";
		}
		ArchivoUtil.guardarArchivo(ruta, contenido, true);
	}
	
	
	public static String copiarImagen(String nombreProducto, String rutaAbsolutaArchivo){
		String rutaDestino = "";  
		
		try {
			
			System.out.println("Ruta Absoluta Original: "+rutaAbsolutaArchivo);
			
			// Ejemplo: src/resources/imagenesProducto/imagen_nombreProducto.png
			rutaDestino = RUTA_ARCHIVO_IMAGEN_PRODUCTOS + "imagen_" + nombreProducto + ".png";
	
			System.out.println("Ruta Destino: "+rutaDestino);
			
			ArchivoUtil.copiarArchivo(rutaAbsolutaArchivo, rutaDestino);
			
		} catch (IOException e) {
			// TODO Auto-generated method stub
			rutaDestino = "";
			e.printStackTrace();
		} 
		
		return rutaDestino;
		
	}
	
	
	public static String getFechaSistema(){
		return ArchivoUtil.fechaSistema();
	}
	
	

	
//	public static void guardarAppendVendedores(ArrayList<Vendedor> listaVendedores) throws IOException {
//		// TODO Auto-generated method stub
//		String contenidoVendedores = "";
//		String contenidoProductos = "";
//		
//		for(Vendedor vendedor :listaVendedores) 
//		{
//			contenidoVendedores += vendedor.getNombre()+ "@@"+ vendedor.getApellido()+ "@@"+ 
//					     		   vendedor.getCedula()+ "@@"+ vendedor.getDireccion()+ "\n";
//			contenidoProductos += guardarProductos(vendedor);			
//		}
//		
//		// La diferencia con el otro metodo, es que este no lo sobreescribre, lo añade usando append
//		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_VENDEDORES, contenidoVendedores, true);
//		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_PRODUCTOS, contenidoProductos, true);
//		
//	}
//	
//	public static void guardarAppendUsuarios(ArrayList<Usuario> listaUsuarios) throws IOException {
//		// TODO Auto-generated method stub
//		String contenido = "";
//		
//		for(Usuario usuario : listaUsuarios) 
//		{
//			contenido += usuario.getUsuario()+ "@@"+ usuario.getContrasenia()+"\n";
//		}
//		
//		// La diferencia con el otro metodo, es que este no lo sobreescribre, lo añade usando append
//		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_USUARIOS, contenido, true);
//		
//	}
	
	

	
	
	
	//------------------------------------SERIALIZACIÓN  y XML
	
	
	public static Marketplace cargarRecursoMarketplaceBinario() {
		
		Marketplace marketplace = null;
		
		try {
			marketplace = (Marketplace) ArchivoUtil.cargarRecursoSerializado(RUTA_ARCHIVO_MODELO_MARKETPLACE_BINARIO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return marketplace;
	}
	
	public static void guardarRecursoMarketplaceBinario(Marketplace marketplace) {
		
		try {
			ArchivoUtil.salvarRecursoSerializado(RUTA_ARCHIVO_MODELO_MARKETPLACE_BINARIO, marketplace);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static Marketplace cargarRecursoMarketplaceXML() {
		
		Marketplace marketplace = null;
		
		try {
			marketplace = (Marketplace) ArchivoUtil.cargarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_MARKETPLACE_XML);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return marketplace;

	}

	
	
	public static void guardarRecursoMarketplaceXML(Marketplace marketplace) {
		
		try {
			ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_MARKETPLACE_XML, marketplace);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	
	
	//------------------------------------ COPIA DE SEGURIDAD ------------------------------------
	
	public static void guardarCopiaSeguridadXML(Marketplace marketplace){
		
		try {
			ArchivoUtil.copiarArchivo(RUTA_ARCHIVO_MODELO_MARKETPLACE_XML, RUTA_ARCHIVO_SEGURIDAD_MODELO_MARKETPLACE_XML);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void guardarCopiaSeguridadBinario(Marketplace marketplace) {
		
		try {
			ArchivoUtil.copiarArchivo(RUTA_ARCHIVO_MODELO_MARKETPLACE_BINARIO, RUTA_ARCHIVO_SEGURIDAD_MODELO_MARKETPLACE_BINARIO);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void guardarCopiaSeguridadLog(){
		
		try {
			ArchivoUtil.copiarArchivo(RUTA_ARCHIVO_LOG, RUTA_ARCHIVO_SEGURIDAD_LOG);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void guardarCopiaSeguridadRespaldo(){
		String rutaDestino = RUTA_ARCHIVO_SEGURIDAD_RESPALDO+ "" +ArchivoUtil.fechaSistema()+ ".xml";
		
		try {
			ArchivoUtil.copiarArchivo(RUTA_ARCHIVO_MODELO_MARKETPLACE_XML, rutaDestino);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void guardarCopiaSeguridadArchivos(){
		
		try {
			ArchivoUtil.copiarArchivo(RUTA_ARCHIVO_VENDEDORES, RUTA_ARCHIVO_SEGURIDAD_VENDEDORES);
			ArchivoUtil.copiarArchivo(RUTA_ARCHIVO_PRODUCTOS, RUTA_ARCHIVO_SEGURIDAD_PRODUCTOS);
			ArchivoUtil.copiarArchivo(RUTA_ARCHIVO_CONTACTOS, RUTA_ARCHIVO_SEGURIDAD_CONTACTOS);
			ArchivoUtil.copiarArchivo(RUTA_ARCHIVO_PUBLICACIONES, RUTA_ARCHIVO_SEGURIDAD_PUBLICACIONES);
			ArchivoUtil.copiarArchivo(RUTA_ARCHIVO_USUARIOS, RUTA_ARCHIVO_SEGURIDAD_USUARIOS);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
