package co.edu.uniquindio.Marketplace.controller.dinamico;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import co.edu.uniquindio.Marketplace.MainApp;
import co.edu.uniquindio.Marketplace.controller.CrudVendedorViewController;
import co.edu.uniquindio.Marketplace.model.EstadoProducto;
import co.edu.uniquindio.Marketplace.model.Producto;
import co.edu.uniquindio.Marketplace.model.Publicacion;
import co.edu.uniquindio.Marketplace.model.Usuario;
import co.edu.uniquindio.Marketplace.model.Vendedor;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

public class TabVendedorController implements Initializable{

	// Principal
	@FXML private Tab tabVendedorPrincipal;
	@FXML private TabPane tabPaneVendedor; 
		@FXML private Tab tabInformacionVendedorPrincipal;
		@FXML private Tab tabCRUDProductosVendedorPrincipal;
			
			
	
	@FXML private Label labelVendedorNombre;
	@FXML private ScrollPane scrollpaneProductos;
	@FXML private GridPane gridpaneProductos;
    @FXML private Button btnEditarProductos;
    
    // CRUD PRODUCTOS
    @FXML private TextField txtNombreProducto;
    @FXML private TextField txtPrecioProducto;
    @FXML private TextField txtCategoriaProducto;
    @FXML private ComboBox<EstadoProducto> cbEstadoProducto;
    @FXML private ImageView imagenViewProducto;
    @FXML private Button btnActualizarProducto;
    @FXML private Button btnNuevoProducto;
    @FXML private Button btnAgregarProducto;
    @FXML private Button btnEliminarProducto;
    @FXML private Button btnSubirImagenProducto;
    
    @FXML private TableView<Producto> tablaProductos;
    @FXML private TableColumn<Producto, String> columnaNombreProducto;
    @FXML private TableColumn<Producto, String> columnaPrecioProducto;
    @FXML private TableColumn<Producto, String> columnaCategoriaProducto;
    @FXML private TableColumn<Producto, EstadoProducto> columnaEstadoProducto;
    
    // Tab Vendedores
    @FXML private Button btnMostrarProductos;
    
    @FXML private TableView<Vendedor> tablaContactos;
    @FXML private TableView<Vendedor> tablaSugeridos; 
    @FXML private TableColumn<Vendedor, String> columnaNombreContacto;
    @FXML private TableColumn<Vendedor, String> columnaNombreSugeridos;

    
    CrudVendedorViewController crudVendedorViewController;
    
    // Listas observable para mostrar en tablas, junto a su objeto seleccionado
 	ObservableList<Vendedor> listaContactosData = FXCollections.observableArrayList();
 	ObservableList<Producto> listaProductosData = FXCollections.observableArrayList();
 	ObservableList<Vendedor> listaSugeridosData = FXCollections.observableArrayList();
    ArrayList<Vendedor> listaTabContactosAbiertos;
// 	Vendedor vendedorSeleccionado;
 	Usuario  usuarioLogeado;
 	Vendedor vendedorPrincipal; // El responsable del tab
 	Vendedor contactoSeleccionado;
 	Vendedor sugeridoSeleccionado;
 	Producto productoSeleccionado;
 	String   rutaImagenProductoSeleccionado;
 	String   rutaImagenNuevoProducto;
 	Boolean CRUDabierto;
    
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Platform.runLater(()->{
			tabPaneVendedor.getTabs().remove(tabCRUDProductosVendedorPrincipal);
			tabVendedorPrincipal.setText("Vendedor "+ vendedorPrincipal.getNombre());
			CRUDabierto = false;
			
			listaTabContactosAbiertos = new ArrayList<Vendedor>();
			
			cargarInformacionTabVendedor();							
		});
	}
	
	public void establecerValores(CrudVendedorViewController crudVendedorViewController,
								  Usuario usuarioLogeado,
								  Vendedor vendedorPrincipal){
//		this.mainTabPane = mainTabPane;
		this.crudVendedorViewController = crudVendedorViewController;		
		this.usuarioLogeado = usuarioLogeado;
		this.vendedorPrincipal = vendedorPrincipal;
}
	
	
	 /*
     * Este metodo crea un tabulador con la informacion (nombre, productos, contactos...)
     * del vendedor seleccionado en la tabla
     * */
    public void cargarInformacionTabVendedor(){
    	if(vendedorPrincipal != null){
    		
    		labelVendedorNombre.setText(vendedorPrincipal.getNombre());
    		cargarPublicacionesVendedor();
    		
    		
    		// Inicializa los contactos y sugeridos en sus tablas con sus columnas.
    		columnaNombreContacto.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    		columnaNombreSugeridos.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    		
    		
    		// Añade los datos de la lista observable a la tabla
    		// Esa lista se obtiene del modelFactoryController, que se obtiene desde un CRUD
    		tablaContactos.getItems().clear();	// Limpio la tabla porque se usan diferentes productos que pertenecen a otros vendedores
    		tablaContactos.setItems(getContactosData(vendedorPrincipal));
    		
    		// Acción de la tabla para mostrar informacion de un contacto
    		tablaContactos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->{
    			contactoSeleccionado = newSelection;
    			
    			// Usar doble click acá para mostrar el tab
    			
    			mostrarTabContacto();
    		});
    		
    		
    		
    		
    		
    		// Muestra un vendedor aleatorio para agregar como contacto
    		tablaSugeridos.getItems().clear();
    		tablaSugeridos.setItems(getVendedoresSugeridosData());
    		
//    		CON UN MOSTRAR MENSAJE DE CONFIRMACION Y UN METODO DE SELECCION HACER QUE EL
//    		VENDEDOR PUEDA AGREGAR AMIGOS CONTACTOS
    		
    		tablaSugeridos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->{
    			sugeridoSeleccionado = newSelection;
    			
    			agregarContacto();
    		});
    		
    	}
    	else{
    		mostrarMensaje("Notifacion", "Tab No abierta", "La tab no puede ser abierta si no selecciona un vendedor", AlertType.ERROR);
    	}
    }
    
    
    public void cargarPublicacionesVendedor(){
    	ArrayList<Publicacion> publicaciones = getListaPublicaciones(vendedorPrincipal);
    	int filas = 0;
    	
    	gridpaneProductos.getChildren().clear();
    	
    	for(int i=0; i<publicaciones.size(); i++){
    		try {
	    		FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(MainApp.class.getResource("view/tabView/producto.fxml"));
			
				gridpaneProductos.add(fxmlLoader.load(), 0, filas); // (Nodo, Columna, Filas)
				
				ProductoController productoController = fxmlLoader.getController();
				productoController.establecerDatos(publicaciones.get(i));
				
				
				filas++;
				
			} catch (IOException e) {

				e.printStackTrace();
			}
			
			
    	}
    	
    }
    
    public void mostrarTabContacto() {
    	boolean tabContactoAbierto = false;
    	
    	// Verifico si la tab del vendedor ya está abierta
    	for(Vendedor vendedor : listaTabContactosAbiertos){
    		if(vendedor.equals(contactoSeleccionado)){
    			tabContactoAbierto = true;
    			break;
    		}
    	}
    	
    	// Si no está abierta la tab del vendedor, puede abrirla
    	if(tabContactoAbierto != true){
    		try {    			
    			FXMLLoader fxmlLoader = new FXMLLoader();
    			fxmlLoader.setLocation(MainApp.class.getResource("view/tabView/contactoTab.fxml"));
    			tabPaneVendedor.getTabs().add(fxmlLoader.load());
    			
    			TabContactoController tabContactoController = fxmlLoader.getController();
    			tabContactoController.setInformacionContacto(crudVendedorViewController, contactoSeleccionado);
    			
    			listaTabContactosAbiertos.add(contactoSeleccionado);
    			
			} catch (IOException e) {				
				e.printStackTrace();
			}
    	}
    	else{
    		mostrarMensaje("Notificacion", "Tab Ya Abierta", "La tab del contacto "+contactoSeleccionado.getNombre()+ " ya está abierta", AlertType.INFORMATION);
    	}
    	
    	
    }
    
    public void agregarContacto(){
    	if(sugeridoSeleccionado != null){   
    		
    		
    		CUANDO AGREGO UN CONTACTO ESTE MENSAJE DE CONFIRMACION SE REPITA | POR QUEEEEEEEEE!!
    		if(mostrarMensajeConfirmacion("¿Desea agregar el vendedor como amigo?")){
    			Vendedor nuevoContacto = crudVendedorViewController.agregarContacto(vendedorPrincipal, sugeridoSeleccionado);
    			
    			if(nuevoContacto != null){
    				listaContactosData.add(nuevoContacto);
    				listaSugeridosData.remove(sugeridoSeleccionado);
    				
    				sugeridoSeleccionado = null;
    				
    				mostrarMensaje("Notificacion", "Contacto Agregado", "El vendedor: "+nuevoContacto.getNombre()+ " ha sido agregado como amigo", AlertType.INFORMATION);
    				
    				// Registro la accion de agregar Producto y guardo los datos
    				crudVendedorViewController.guardarDatos();    	
    				crudVendedorViewController.registrarAccion("El contacto ha sido creado con exito!. Realizado por el Usuario : "+ 
    						usuarioLogeado.getUsuario(), 1, "Agregar Contacto");
    				crudVendedorViewController.guardarDatosTXT();
    				
    				tablaSugeridos.getSelectionModel().clearSelection();
    				tablaContactos.refresh();
    				tablaSugeridos.refresh();
    				
    			}
    			else{
    				mostrarMensaje("Notificacion", "Contacto No Agregado", "El vendedor: "+nuevoContacto.getNombre()+ " No ha sido agregado como amigo", AlertType.ERROR);
    			}
    		}
    	}
    }
    
    
    
    
    public void setCerrarTabHandler(Runnable handler){
    	
    }
    
    
    public ObservableList<Vendedor> getVendedoresSugeridosData(){    	
    	listaSugeridosData.addAll(getListaVendedores());
    	listaSugeridosData.remove(vendedorPrincipal);
    	
    	for(Vendedor v : listaSugeridosData){
    		for(Vendedor c : getListaContactos(vendedorPrincipal)){
    			if(v.getNombre() == c.getNombre() || v.getNombre().equals(c.getNombre())){
    				listaSugeridosData.remove(v);			
    			}
    		}
    	}
    	
    	return listaSugeridosData;

    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	
	@FXML
    void accionBtnEditarProductos(ActionEvent event) {
		mostrarTabCRUDProductos();
    }
	
    /*
     * Este metodo muestra el tab de 'CRUD Productos', para el vendedor seleccionado
     * */
    public void mostrarTabCRUDProductos(){
    	
    	if(CRUDabierto != true){
    		tabPaneVendedor.getTabs().add(tabCRUDProductosVendedorPrincipal);
    		inicializarProductoView();
    		tablaProductos.refresh();

    		CRUDabierto = true;
    	}
    	else{
    		mostrarMensaje("Notificacion", "CRUD Productos", "La ventana CRUD productos ya está abierta", AlertType.INFORMATION);
    	}
    }
	    
    
	    
	    
    // -------------- METODOS PARA CRUD PRODUCTO VIEW CONTROLLER --------------
    
	
	public void inicializarProductoView(){
		// Asigno valores a la combo box
		cbEstadoProducto.getItems().clear();// Limpio la comboBox en caso de que se repitan sus valores
		cbEstadoProducto.getItems().addAll(EstadoProducto.values());
		
			
		// Inicializa los vendederos en la tabla con sus columnas.
		columnaNombreProducto.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		columnaPrecioProducto.setCellValueFactory(new PropertyValueFactory<>("precio"));
		columnaCategoriaProducto.setCellValueFactory(new PropertyValueFactory<>("categoria"));
		columnaEstadoProducto.setCellValueFactory(new PropertyValueFactory<>("estado"));
		
		// Limpio los textfield
		accionBtnNuevoProducto(new ActionEvent());
		
		// Añade los datos de la lista observable a la tabla
		// Esa lista se obtiene del modelFactoryController, que se obtiene desde un CRUD
		tablaProductos.getItems().clear();	// Limpio la tabla porque se usan diferentes productos que pertenecen a otros vendedores
		tablaProductos.setItems(getProductosData(vendedorPrincipal));
		
		// Acción de la tabla para mostrar informacion de un empleado
		tablaProductos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->{
			productoSeleccionado = newSelection;
			rutaImagenProductoSeleccionado = productoSeleccionado.getRutaImagen();
			mostrarInformacionProducto(productoSeleccionado);
			
		});

	}


    @FXML
    void accionBtnAgregarProducto(ActionEvent event) {
    	crearProducto();
    }

    public void crearProducto(){
    	// Captura los datos
    	Vendedor vendedor = vendedorPrincipal;
    	
		String nombre = txtNombreProducto.getText();
		String precio = txtPrecioProducto.getText();
		String categoria = txtCategoriaProducto.getText();
		EstadoProducto estado = cbEstadoProducto.getValue(); 
		String rutaImagen = rutaImagenNuevoProducto;
		
		// Valida los datos
		if(datosValidos(nombre, precio, categoria, estado, rutaImagen)){
			Producto producto = null;
			Publicacion publicacion = null;
			
			producto = crudVendedorViewController.crearProducto(vendedor, nombre, precio, categoria, estado, rutaImagen);
			publicacion = crudVendedorViewController.crearPublicacion(vendedor, nombre);
			
			
			if(producto != null && publicacion != null){
				listaProductosData.add(producto);
				mostrarMensaje("Notifacion", "Producto Creado", "El producto ha sido creado con exito!", AlertType.INFORMATION);
				mostrarMensaje("Notifacion", "Publicacion Creada", "La publicacion ha sido creada con exito!", AlertType.INFORMATION);
				
				// Registro la accion de agregar Producto y guardo los datos
				crudVendedorViewController.guardarDatos();    	
				crudVendedorViewController.registrarAccion("El producto ha sido creado con exito!. Realizado por el Usuario : "+ 
															usuarioLogeado.getUsuario(), 1, "Agregar Producto");
				crudVendedorViewController.registrarAccion("La publicacion ha sido creada con exito!. Realizado por el Usuario : "+ 
															usuarioLogeado.getUsuario(), 1, "Agregar Publicacion");
				crudVendedorViewController.guardarDatosTXT();
				
				// Limpio los textfield y Actualiza las publicaciones
				accionBtnNuevoProducto(new ActionEvent());
				cargarPublicacionesVendedor();
			}
			else{
				mostrarMensaje("Notifacion", "Producto NO Creado", "El producto NO ha sido creado", AlertType.ERROR);
			}
		}
		else{
			mostrarMensaje("Notifacion", "Producto NO Creado", "Datos ingresados NO validos", AlertType.ERROR);
		}
		
    }

    @FXML
    void accionBtnNuevoProducto(ActionEvent event) {
		// Limpio los textfield y combobox
		txtNombreProducto.clear();
		txtPrecioProducto.clear();
		txtCategoriaProducto.clear();
		cbEstadoProducto.getSelectionModel().clearSelection();
		imagenViewProducto.setImage(null);
		
		tablaProductos.getSelectionModel().clearSelection();
		
		// setPromptText a diferencia de setText, es mejor, porque la letra es transparente
		// y se elimina al tocar en el textfield, y no es como poner un texto plano y ya		
		txtNombreProducto.setPromptText("Ingrese el Nombre");
		txtPrecioProducto.setPromptText("Ingrese el Precio");
		txtCategoriaProducto.setPromptText("Ingrese la Categoria");
		cbEstadoProducto.setPromptText("Ingrese el Estado");
		rutaImagenNuevoProducto = "";
		
    }
	    
	    
		
    @FXML
    void accionBtnEliminarProducto(ActionEvent event) {
    	eliminarProducto();
    }
    
    public void eliminarProducto(){
    	Vendedor vendedor = vendedorPrincipal;
    	boolean productoEliminado = false;
    	boolean publicacionEliminada = false;
    	
    	if(productoSeleccionado != null){
    		if(mostrarMensajeConfirmacion("¿Está seguro de eliminar el producto?")){
//	    			vendedorEliminado = crudVendedorViewController.eliminarVendedor(vendedorSeleccionado.getCedula());
    			productoEliminado = crudVendedorViewController.eliminarProducto(vendedor, productoSeleccionado.getNombre());
    			publicacionEliminada = crudVendedorViewController.eliminarPublicacion(vendedor, productoSeleccionado.getNombre());
    			
    			if(productoEliminado && publicacionEliminada){
    				listaProductosData.remove(productoSeleccionado);
    				productoSeleccionado = null;
    				
    				// Se elimina el producto de la tabla y limpiamos los textfield y Actualiza las publicaciones
    				tablaProductos.getSelectionModel().clearSelection();
    				accionBtnNuevoProducto(new ActionEvent());
    				cargarPublicacionesVendedor();
    				
    				mostrarMensaje("Notifacion", "Producto Eliminado", "El producto ha sido eliminado con exito!", AlertType.INFORMATION);
    				
    				// Registro la accion de eliminar Producto y guardo los datos
    				crudVendedorViewController.guardarDatos();    	
    				crudVendedorViewController.registrarAccion("El producto ha sido eliminado con exito!. Realizado por el Usuario : "+ 
    															usuarioLogeado.getUsuario(), 1, "Eliminar Producto");
    				crudVendedorViewController.registrarAccion("La publicacion ha sido eliminada con exito!. Realizado por el Usuario : "+ 
																usuarioLogeado.getUsuario(), 1, "Eliminar Publicacion");
    				crudVendedorViewController.guardarDatosTXT();
    				
    			}
    			else{
    				mostrarMensaje("Notifacion", "Producto NO Eliminado", "El producto NO ha sido eliminado", AlertType.ERROR);
    			}
    		}
    	}
    	else{
    		mostrarMensaje("Notifacion", "Producto NO seleccionado", "Seleccionado un producto de la lista", AlertType.WARNING);
    	}
    }
	    
    @FXML
    void accionBtnActualizarProducto(ActionEvent event) {
    	actualizarProducto();
    }
	    
    public void actualizarProducto(){
    	// Capturo los datos
		Vendedor vendedor = vendedorPrincipal;
    	
		String nombreActual = productoSeleccionado.getNombre();
		String nombre = txtNombreProducto.getText();
		String precio = txtPrecioProducto.getText();
		String categoria = txtCategoriaProducto.getText();
		EstadoProducto estado = cbEstadoProducto.getValue();
		String rutaImagen = rutaImagenProductoSeleccionado;
		
		boolean productoActualizado = false;
		boolean publicacionActualizada = false;
		
		// Verifico los datos
		if(productoSeleccionado != null){
			// Valido los datos
			if(datosValidos(nombre, precio, categoria, estado, rutaImagen)){
				
//					vendedorActualizado = crudVendedorViewController.actualizarVendedor(vendedorSeleccionado.getCedula(), nombre, apellido, cedula, direccion);
				productoActualizado = crudVendedorViewController.actualizarProducto(vendedor, nombreActual, nombre, precio, categoria, estado, rutaImagen);
				publicacionActualizada = crudVendedorViewController.actualizarPublicacion(vendedor, nombreActual, nombre);
				
				if(productoActualizado && publicacionActualizada){
					tablaProductos.refresh();
					mostrarMensaje("Notifacion", "Producto Actualizado", "El producto ha sido actualizado con exito!", AlertType.INFORMATION);
					
					// Registro la accion de actualizar Producto y guardo los datos
    				crudVendedorViewController.guardarDatos();    	
    				crudVendedorViewController.registrarAccion("El producto ha sido actualizado con exito!. Realizado por el Usuario : "+ 
    															usuarioLogeado.getUsuario(), 1, "Actualizar Producto");
    				crudVendedorViewController.registrarAccion("La publicacion ha sido actualizada con exito!. Realizado por el Usuario : "+ 
																usuarioLogeado.getUsuario(), 1, "Actualizar Publicacion");
    				crudVendedorViewController.guardarDatosTXT();
					
					// Limpio los textfield y Actualiza las publicaciones
					accionBtnNuevoProducto(new ActionEvent());
					cargarPublicacionesVendedor();
				}
				else{
					mostrarMensaje("Notifacion", "Producto NO Actualizado", "El producto NO ha sido actualizado", AlertType.ERROR);
				}
			}
			else{
				mostrarMensaje("Notifacion", "Producto NO Actualizado", "Datos ingresados NO validos", AlertType.ERROR);
			}		
		}
		
		
	
    }
	    
    @FXML
    void accionBtnSubirImagenProducto(ActionEvent event) {
    	subirImagen();
    }
    
    public void subirImagen(){
		// Creo un fileChooser donde solo se pueda escoger imagenes .jpg o .png
		FileChooser fileChooser = new FileChooser();
//	        	FileChooser.ExtensionFilter ext1 = new FileChooser.ExtensionFilter("JPG files(*.jpg)","*.JPG");
    	FileChooser.ExtensionFilter ext2 = new FileChooser.ExtensionFilter("PNG files(*.png)","*.PNG");
    	fileChooser.getExtensionFilters().addAll(ext2);
    	    	
    	File archivoSeleccionado = fileChooser.showOpenDialog(null);
    	
    	try {    		        		
	    	BufferedImage bf;	
	    	
	    	if(archivoSeleccionado != null){
	    		// Leo la imagen para luego mostrarla en el ImageView
				bf = ImageIO.read(archivoSeleccionado);
				
	    		Image imagen = SwingFXUtils.toFXImage(bf, null);
	    		imagenViewProducto.setImage(imagen);    	   
	    		
	    		// Hacer una copia de la imagen porque la imagen le pertenece a la ruta especifica del usuario
	    		// Y guardo la nueva ruta para asignarsela al producto
	    		if(productoSeleccionado != null){
	    			rutaImagenProductoSeleccionado = crudVendedorViewController.copiarImagen(productoSeleccionado.getNombre(),
																		    				 archivoSeleccionado.getAbsolutePath());    	    		    	    			   
	    		} 
	    		else{ // Esto me permite guardar la imagen de un nuevo producto sin hacer seleccion en la tabla 
	    			rutaImagenNuevoProducto = crudVendedorViewController.copiarImagen(txtNombreProducto.getText(),
		    				 														  archivoSeleccionado.getAbsolutePath());
	    		}
	    	
	    	}
	    	else{
	    		mostrarMensaje("Notifacion", "Archivo NO valido", "El archivo no ha sido encontrado", AlertType.ERROR);
	    	}
    	} catch (IOException e) {
			e.printStackTrace();
		}	
//    	}
//    	else{
//    		mostrarMensaje("Notifacion", "Error Imagen", "NO se puede agregar imagen sin elegir un producto", AlertType.ERROR);
//    	}
    	
    }
    
	    
	    
	    
    /*
     * Este metodo asigna los valores del producto seleccionado de la tabla, en los textField
     * */
    private void mostrarInformacionProducto(Producto productoSeleccionado) {
		if(productoSeleccionado != null){
			txtNombreProducto.setText(productoSeleccionado.getNombre());
		    txtPrecioProducto.setText(productoSeleccionado.getPrecio());
		    txtCategoriaProducto.setText(productoSeleccionado.getCategoria());
		    cbEstadoProducto.getSelectionModel().select(productoSeleccionado.getEstado());
		    
		 // Muestro la imagen en el ImageView
 			try{ 				
 				File archivoImagen = new File(productoSeleccionado.getRutaImagen());
 				BufferedImage bf;	
 	        	
 				if(archivoImagen != null){
     	    		// Leo la imagen para luego mostrarla en el ImageView
     				bf = ImageIO.read(archivoImagen);
     				
     	    		Image imagen = SwingFXUtils.toFXImage(bf, null);
     	    		imagenViewProducto.setImage(imagen);
 				}	        			    	    	
 				
 			} catch(IOException e){
 				
 				imagenViewProducto.setImage(null);
 				System.out.println("Imagen No encontrada. Ruta: "+productoSeleccionado.getRutaImagen());
//			 		e.printStackTrace();
	 		}
		}
		
	}
	    
	    
	    
	    
	    

	    
	    
    public boolean mostrarMensajeConfirmacion(String mensaje){
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Confirmacion");
    	alert.setHeaderText(null);
    	alert.setContentText(mensaje);
    	
    	Optional<ButtonType> action = alert.showAndWait();
    	
    	if(action.get() == ButtonType.OK)
    		return true;
    	else
    		return false;
    }	

	
	public void mostrarMensaje(String titulo, String header, String contenido, AlertType alertType){
    	Alert alert = new Alert(alertType);
    	alert.setTitle(titulo);
    	alert.setHeaderText(header);
    	alert.setContentText(contenido);
    	alert.showAndWait();
    }
	
	/*
     * Este metodo valida los datos de un --- Producto ---
     * */
    private boolean datosValidos(String nombre, String precio, String categoria, EstadoProducto estado, String rutaImagen) {
    	String mensaje = "";
    	
    	if(nombre == null || nombre.equals(""))
    		mensaje += "Nombre no valido\n";
    	
    	if(precio == null || precio.equals(""))
    		mensaje += "Precio no valido\n";
    	
    	if(categoria == null || categoria.equals(""))
    		mensaje += "Categoria no valida\n";
    	
    	if(estado == null || estado.equals(""))
    		mensaje += "Estado no valida\n";
    	
    	if(rutaImagen == null || rutaImagen.equals(""))
    		mensaje += "Imagen no valida\n";
    	
    	
    	if(mensaje.equals("")){
    		return true;    		
    	}
    	else{
    		mostrarMensaje("Notificacion", "Datos no validos", mensaje, AlertType.WARNING);
    		return false;    		
    	}
    	
    } 
    
    
    public ArrayList<Producto> getListaProductos(Vendedor vendedorSeleccionado){
    	return crudVendedorViewController.getListaProductos(vendedorSeleccionado);
    }
    
    public ArrayList<Publicacion> getListaPublicaciones(Vendedor vendedorSeleccionado){
    	return crudVendedorViewController.getListaPublicaciones(vendedorSeleccionado);
    }
    
    public ArrayList<Vendedor> getListaContactos(Vendedor vendedorSeleccionado){
    	return crudVendedorViewController.getListaContactos(vendedorSeleccionado);
    }
    
    public ArrayList<Vendedor> getListaVendedores(){
		return crudVendedorViewController.getListaVendedores();
	}

    
    
    /*
	 * Esta lista se puede obtener directamente del ModelFactoryController, pero segun nuestra
	 * estructura de trabajo, el ModelFactoryController se comunica con los CRUD y estos se
	 * comunican con este controlador principal, por lo que debemos pedirle la lista al CRUD 
	 * */
	public ObservableList<Producto> getProductosData(Vendedor vendedorSeleccionado){
		listaProductosData.addAll(crudVendedorViewController.getListaProductos(vendedorSeleccionado)) ;
		return listaProductosData;
	}
    
	public ObservableList<Vendedor> getContactosData(Vendedor vendedorSeleccionado){
		listaContactosData.addAll(crudVendedorViewController.getListaContactos(vendedorSeleccionado)) ;
		return listaContactosData;
	}

}
