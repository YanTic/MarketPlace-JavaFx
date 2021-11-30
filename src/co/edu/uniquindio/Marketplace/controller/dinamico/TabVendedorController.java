package co.edu.uniquindio.Marketplace.controller.dinamico;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import co.edu.uniquindio.Marketplace.MainApp;
import co.edu.uniquindio.Marketplace.controller.CrudVendedorViewController;
import co.edu.uniquindio.Marketplace.model.EstadoProducto;
import co.edu.uniquindio.Marketplace.model.Producto;
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
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

public class TabVendedorController implements Initializable{

	// Principal
	private TabPane mainTabPane;
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
// 	Vendedor vendedorSeleccionado;
 	Usuario  usuarioLogeado;
 	Vendedor vendedorPrincipal; // El responsable del tab
 	Vendedor contactoSeleccionado;
 	Producto productoSeleccionado;
 	String   rutaImagenProducto;
    
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Platform.runLater(()->{
			tabPaneVendedor.getTabs().remove(tabCRUDProductosVendedorPrincipal);
			
			cargarInformacionTabVendedor();
		});
	}
	
	public void establecerValores(TabPane mainTabPane,
								  CrudVendedorViewController crudVendedorViewController,
								  Usuario usuarioLogeado,
								  Vendedor vendedorPrincipal){
		this.mainTabPane = mainTabPane;
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
//    		mainTabPane.getTabs().add(tabVendedorPrincipal);
    		labelVendedorNombre.setText(vendedorPrincipal.getNombre());
    		cargarPublicacionesVendedor();
    		
    		
    		// Inicializa los vendederos en la tabla con sus columnas.
    		columnaNombreContacto.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    		
    		
    		// Añade los datos de la lista observable a la tabla
    		// Esa lista se obtiene del modelFactoryController, que se obtiene desde un CRUD
    		tablaContactos.getItems().clear();	// Limpio la tabla porque se usan diferentes productos que pertenecen a otros vendedores
    		tablaContactos.setItems(getContactosData(vendedorPrincipal));
    		
    		// Acción de la tabla para mostrar informacion de un contacto
    		tablaContactos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->{
    			contactoSeleccionado = newSelection;
    			
    			
    			// Usar doble click acá para mostrar el tab y talvez un mensjae de confirmacion
//    			mostrarInformacionProducto(contactoSeleccionado); Esto sería como mostrar el tab del contacto
    			try {    			
	    			FXMLLoader fxmlLoader = new FXMLLoader();
	    			fxmlLoader.setLocation(MainApp.class.getResource("view/tabView/contactoTab.fxml"));
	    			TabContactoController tabContactoController = new TabContactoController();
	    			
					tabPaneVendedor.getTabs().add(fxmlLoader.load());
				} catch (IOException e) {				
					e.printStackTrace();
				}
    			
    		});
    		
    		
    	}
    	else{
    		mostrarMensaje("Notifacion", "Tab No abierta", "La tab no puede ser abierta si no selecciona un vendedor", AlertType.ERROR);
    	}
    }
    
    
    public void cargarPublicacionesVendedor(){
    	
    	
    }
    
	
	@FXML
    void accionBtnEditarProductos(ActionEvent event) {
		mostrarTabCRUDProductos();
    }
	
    /*
     * Este metodo muestra el tab de 'CRUD Productos', para el vendedor seleccionado
     * */
    public void mostrarTabCRUDProductos(){
    	
//    	mainTabPane.getTabs().add(tabCRUDProductos);
    	tabPaneVendedor.getTabs().add(tabCRUDProductosVendedorPrincipal);
    	inicializarProductoView();
    	tablaProductos.refresh();
    }
	    
	    
	    
	    
	    
	    
	    
	    
    // -------------- METODOS PARA PRODUCTO VIEW CONTROLLER --------------
    
	
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
		String rutaImagen = rutaImagenProducto;
		
		// Valida los datos
		if(datosValidos(nombre, precio, categoria, estado, rutaImagen)){
			Producto producto = null;
			
			producto = crudVendedorViewController.crearProducto(vendedor, nombre, precio, categoria, estado, rutaImagen);
			
			if(producto != null){
				listaProductosData.add(producto);
				mostrarMensaje("Notifacion", "Producto Creado", "El producto ha sido creado con exito!", AlertType.INFORMATION);
				
				// Registro la accion de agregar Producto y guardo los datos
				crudVendedorViewController.guardarDatos();    	
				crudVendedorViewController.registrarAccion("El producto ha sido creado con exito!. Realizado por el Usuario : "+ 
															usuarioLogeado.getUsuario(), 1, "Agregar Producto");
				crudVendedorViewController.guardarDatosTXT();
				
				// Limpio los textfield
				accionBtnNuevoProducto(new ActionEvent());
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
		
		// setPromptText a diferencia de setText, es mejor, porque la letra es transparente
		// y se elimina al tocar en el textfield, y no es como poner un texto plano y ya		
		txtNombreProducto.setPromptText("Ingrese el Nombre");
		txtPrecioProducto.setPromptText("Ingrese el Precio");
		txtCategoriaProducto.setPromptText("Ingrese la Categoria");
		cbEstadoProducto.setPromptText("Ingrese el Estado");
		
    }
	    
	    
		
    @FXML
    void accionBtnEliminarProducto(ActionEvent event) {
    	eliminarProducto();
    }
    
    public void eliminarProducto(){
    	Vendedor vendedor = vendedorPrincipal;
    	boolean productoEliminado = false;
    	
    	if(productoSeleccionado != null){
    		if(mostrarMensajeConfirmacion("¿Está seguro de eliminar el producto?")){
//	    			vendedorEliminado = crudVendedorViewController.eliminarVendedor(vendedorSeleccionado.getCedula());
    			productoEliminado = crudVendedorViewController.eliminarProducto(vendedor, productoSeleccionado.getNombre());
    			
    			if(productoEliminado){
    				listaProductosData.remove(productoSeleccionado);
    				productoSeleccionado = null;
    				
    				// Se elimina el producto de la tabla y limpiamos los textfield
    				tablaProductos.getSelectionModel().clearSelection();
    				accionBtnNuevoProducto(new ActionEvent());
    				mostrarMensaje("Notifacion", "Producto Eliminado", "El producto ha sido eliminado con exito!", AlertType.INFORMATION);
    				
    				// Registro la accion de eliminar Producto y guardo los datos
    				crudVendedorViewController.guardarDatos();    	
    				crudVendedorViewController.registrarAccion("El producto ha sido eliminado con exito!. Realizado por el Usuario : "+ 
    															usuarioLogeado.getUsuario(), 1, "Eliminar Producto");
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
    	
		String nombre = txtNombreProducto.getText();
		String precio = txtPrecioProducto.getText();
		String categoria = txtCategoriaProducto.getText();
		EstadoProducto estado = cbEstadoProducto.getValue();
		String rutaImagen = rutaImagenProducto;
		
		boolean productoActualizado = false;
		
		// Verifico los datos
		if(productoSeleccionado != null){
			// Valido los datos
			if(datosValidos(nombre, precio, categoria, estado, rutaImagen)){
				
//					vendedorActualizado = crudVendedorViewController.actualizarVendedor(vendedorSeleccionado.getCedula(), nombre, apellido, cedula, direccion);
				productoActualizado = crudVendedorViewController.actualizarProducto(vendedor, productoSeleccionado.getNombre(),nombre, precio, categoria, estado, rutaImagen);
				
				if(productoActualizado == true){
					tablaProductos.refresh();
					mostrarMensaje("Notifacion", "Producto Actualizado", "El producto ha sido actualizado con exito!", AlertType.INFORMATION);
					
					// Registro la accion de actualizar Producto y guardo los datos
    				crudVendedorViewController.guardarDatos();    	
    				crudVendedorViewController.registrarAccion("El producto ha sido actualizado con exito!. Realizado por el Usuario : "+ 
    															usuarioLogeado.getUsuario(), 1, "Actualizar Producto");
    				crudVendedorViewController.guardarDatosTXT();
					
					// Limpio los textfield
					accionBtnNuevoProducto(new ActionEvent());
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
    	
    	if(productoSeleccionado != null){
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
    	    		rutaImagenProducto = crudVendedorViewController.copiarImagen(productoSeleccionado.getNombre(),
    	    																	 archivoSeleccionado.getAbsolutePath());    	    		    	    		
    	    	
    	    	}
    	    	else{
    	    		mostrarMensaje("Notifacion", "Archivo NO valido", "El archivo no ha sido encontrado", AlertType.ERROR);
    	    	}
        	} catch (IOException e) {
    			e.printStackTrace();
    		}	
    	}
    	else{
    		mostrarMensaje("Notifacion", "Error Imagen", "NO se puede agregar imagen sin elegir un producto", AlertType.ERROR);
    	}
    	
    	
       
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
