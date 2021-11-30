package co.edu.uniquindio.Marketplace.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import co.edu.uniquindio.Marketplace.MainApp;
import co.edu.uniquindio.Marketplace.controller.dinamico.TabVendedorController;
import co.edu.uniquindio.Marketplace.model.EstadoProducto;
import co.edu.uniquindio.Marketplace.model.Marketplace;
import co.edu.uniquindio.Marketplace.model.Producto;
import co.edu.uniquindio.Marketplace.model.Vendedor;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

/*
 * ---- CONTROLADOR PRINCIPAL ----
 * */

public class MarketplaceViewController implements Initializable{
	
	// User    
	@FXML private Button btnUsuario;
	@FXML private Button btnLogout;
	
	@FXML private TabPane mainTabPane;
	@FXML private Tab tabAdministracion;
    @FXML private Tab tabVendedorPrincipal;
	@FXML private Tab tabCRUDProductos;
	@FXML private AnchorPane anchorPaneTabAdministracion;

    @FXML private Label labelVendedorNombre;
	
    
//    // Tab Vendedores
//    @FXML private Tab tabNuevoVendedor;
//    @FXML private Label labelVendedorNombreTEST;
//    @FXML private Button btnMostrarProductosTEST;
//    @FXML private VBox vBoxProductosVendedorTEST;
	
	// CRUD VENDEDORES
	@FXML private TextField txtNombreVendedor;
    @FXML private TextField txtApellidoVendedor;
    @FXML private TextField txtCedulaVendedor;
    @FXML private TextField txtDireccionVendedor;
    @FXML private Button btnActualizarVendedor;
    @FXML private Button btnNuevoVendedor;
    @FXML private Button btnAgregarVendedor;
    @FXML private Button btnEliminarVendedor;
    @FXML private TableView<Vendedor> tablaVendedores;
    @FXML private TableColumn<Vendedor, String> columnaNombreVendedor;
    @FXML private TableColumn<Vendedor, String> columnaApellidoVendedor;
    @FXML private TableColumn<Vendedor, String> columnaCedulaVendedor;
    @FXML private TableColumn<Vendedor, String> columnaDireccionVendedor;
    
    @FXML private Button btnMostrarVendedor;
//    @FXML private VBox vBoxProductosVendedor;
    
    
//    // CRUD PRODUCTOS
//    @FXML private TextField txtNombreProducto;
//    @FXML private TextField txtPrecioProducto;
//    @FXML private TextField txtCategoriaProducto;
//    @FXML private ComboBox<EstadoProducto> cbEstadoProducto;
//    @FXML private Button btnActualizarProducto;
//    @FXML private Button btnNuevoProducto;
//    @FXML private Button btnAgregarProducto;
//    @FXML private Button btnEliminarProducto;
//    @FXML private Button btnSubirImagenProducto;
//    @FXML private ImageView imagenViewProducto;
//    
//    @FXML private TableView<Producto> tablaProductos;
//    @FXML private TableColumn<Producto, String> columnaNombreProducto;
//    @FXML private TableColumn<Producto, String> columnaPrecioProducto;
//    @FXML private TableColumn<Producto, String> columnaCategoriaProducto;
//    @FXML private TableColumn<Producto, EstadoProducto> columnaEstadoProducto;
//    
//    // Tab Vendedores
//    @FXML private Button btnMostrarProductos;
//    @FXML private TableView<Vendedor> tablaContactos;
//    @FXML private TableColumn<Vendedor, String> columnaNombreContacto;

    
	// Referencia a la MainApp.
	private MainApp mainApp;
	
    Marketplace marketplace;
	ModelFactoryController	   modelFactoryController;
	CrudVendedorViewController crudVendedorViewController;
	CrudLoginViewController	   crudLoginViewController;
	
	// Listas observable para mostrar en tablas, junto a su objeto seleccionado
	ObservableList<Vendedor> listaVendedoresData = FXCollections.observableArrayList();
	ObservableList<Vendedor> listaContactosData = FXCollections.observableArrayList();
	ObservableList<Producto> listaProductosData = FXCollections.observableArrayList();
	Vendedor vendedorSeleccionado;
	Vendedor contactoSeleccionado;
	Producto productoSeleccionado;
	String   rutaImagenProducto;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
// ------- Estos metodos ya est�n inicializados en el LoginViewController -------
		
		// Ac� se inicializan todos los controladores CRUD
//		modelFactoryController = new ModelFactoryController().getInstance();
//		crudVendedorViewController = new CrudVendedorViewController(modelFactoryController);
//		crudProductoViewController = new CrudProductoViewController(modelFactoryController);
		
		
		
		// Inicializo los datos de cada controlador CRUD (Como tablas...)
//		inicializarVendedorView();
//		inicializarProductoView();
		
		
		// Oculto los tabs
		mainTabPane.getTabs().remove(tabVendedorPrincipal);
		mainTabPane.getTabs().remove(tabCRUDProductos);
		
		
		
		
		// Uso Platform.runLater, porque los metodos se est�n llamando antes de inicializar
		// los datos cuando se crea este controlador desde LoginViewController
		Platform.runLater(()->{
			// Inicializo los datos de cada controlador CRUD (Como tablas...)
			inicializarVendedorView();
//			inicializarProductoView();
			
			// Establesco el Usuario que acaba de iniciara sesion
			usuarioLogeado();
		});
	}
	
	/*
	 * Este metodo es como Initialize de javafx, pero este metodo es llamado desde otro controlador
	 * para que tenga los mismos valores creados del que llama a es este controlador (O sea el
	 * LoginViewController) y tambien si el usuario desea cerrar sesion e iniciar otra, los datos
	 * del marketplace no se pierdan, solo se cambie el acceso a estos.
	 * */
	public void establecerValores(Marketplace marketplace, 
								  ModelFactoryController modelFactoryController,
								  CrudLoginViewController crudLoginViewController,
								  CrudVendedorViewController crudVendedorViewController){
		
	// Esto es llamado antes de llamar Initialize, es decir, durante la creacion de esta clase
	// en el LoginViewController;
		this.marketplace = marketplace;
		this.modelFactoryController = modelFactoryController;
		this.crudLoginViewController = crudLoginViewController;
		this.crudVendedorViewController = crudVendedorViewController;		
	}
	
	
	
//	-------------- METODOS PARA USER --------------
	
    @FXML
    void accionBtnLogout(ActionEvent event) {
    	volverAInicioSesion();
    }

    /*
     * Este metodo cambia la scene, oculta MarketplaceView y muestra
     * el LoginView.
     * */
    public void volverAInicioSesion(){
		
		// Registro la accion de log out
		crudVendedorViewController.registrarAccion("Cerrar sesion del usuario: "+mainApp.getUsuarioLogeado().getUsuario(), 1, "CerrarSesion");
		
		mostrarMensaje("Notifacion", "Cerrando Sesion", "Usuario: "+mainApp.getUsuarioLogeado().getUsuario()+ " ha cerrado sesion", AlertType.INFORMATION);

		mainApp.setUsuarioLogeado(null);
		
		// Llamo al LoginViewController y cambio la view (el fxml)				
		try {			
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/LoginView.fxml"));
			Parent root = loader.load();
			
			// Creo el controlador
			LoginViewController loginViewController = loader.getController();
			loginViewController.setMainApp(mainApp);
			
			// Yo no establezco valores porque ya all� se crean desde el initialize
//			LoginViewController.establecerValores(marketplace, 
//														modelFactoryController, 
//														crudLoginViewController, 
//														crudVendedorViewController);
		
			
			// De esta manera es para agregarle animacion de cambio de scene
			Scene scene = new Scene(root);
			mainApp.getPrimaryStage().setScene(scene);
			
			
			
			// Animaciones ------ Efectos
			
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
    
	
    @FXML
    void accionBtnUsuario(ActionEvent event) {
    	
    }
    
    public void usuarioLogeado(){
    	btnUsuario.setText(mainApp.getUsuarioLogeado().getUsuario());
    }

    
    
    
//	-------------- METODOS PARA VENDEDOR VIEW CONTROLLER --------------
	public void inicializarVendedorView(){
		// Inicializa los vendederos en la tabla con sus columnas.
		columnaNombreVendedor.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		columnaApellidoVendedor.setCellValueFactory(new PropertyValueFactory<>("apellido"));
		columnaCedulaVendedor.setCellValueFactory(new PropertyValueFactory<>("cedula"));
		columnaDireccionVendedor.setCellValueFactory(new PropertyValueFactory<>("direccion"));
		
		// Limpio los textfield
		accionBtnNuevoVendedor(new ActionEvent());
		
		// A�ade los datos de la lista observable a la tabla
		// Esa lista se obtiene del modelFactoryController, que se obtiene desde un CRUD
		tablaVendedores.setItems(getVendedoresData());
		
		// Acci�n de la tabla para mostrar informacion de un empleado
		tablaVendedores.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->{
			vendedorSeleccionado = newSelection;
			mainApp.setVendedorSeleccionadoGeneral(vendedorSeleccionado);
			
			mostrarInformacionVendedor(vendedorSeleccionado);
		});
	}

	
	@FXML
	void accionBtnNuevoVendedor(ActionEvent event) {
		// Limpio los textfield
		txtNombreVendedor.clear();
		txtApellidoVendedor.clear();
		txtCedulaVendedor.clear();
		txtDireccionVendedor.clear();
		
		// setPromptText a diferencia de setText, es mejor, porque la letra es transparente
		// y se elimina al tocar en el textfield, y no es como poner un texto plano y ya		
		txtNombreVendedor.setPromptText("Ingrese el Nombre");
		txtApellidoVendedor.setPromptText("Ingrese el Apellido");
		txtCedulaVendedor.setPromptText("Ingrese la Cedula");
		txtDireccionVendedor.setPromptText("Ingrese la Direccion");
		
	}
	
    @FXML
    void accionBtnAgregarVendedor(ActionEvent event) {
    	crearVendedor();
    }
 
    
    private void crearVendedor() {
    	// Captura los datos
		String nombre = txtNombreVendedor.getText();
		String apellido = txtApellidoVendedor.getText();
		String cedula = txtCedulaVendedor.getText();
		String direccion = txtDireccionVendedor.getText();
		
		// Valida los datos
		if(datosValidos(nombre, apellido, cedula, direccion)){
			Vendedor vendedor = null;
			
			vendedor = crudVendedorViewController.crearVendedor(nombre, apellido, cedula, direccion);
			
			if(vendedor != null){
				listaVendedoresData.add(vendedor);
				
				// Siempre tengo que guardar y registar
				
				// Me guarda el vendedor en el archivo (.dat o .xml)
				crudVendedorViewController.guardarDatos();
				// Me registrar la accion en el log (String mensajeLog, int nivel, String accion)
				crudVendedorViewController.registrarAccion("El vendedor ha sido creado con exito!. Realizado por el Usuario : "+ 
															mainApp.getUsuarioLogeado().getUsuario(), 1, "Agregar Vendedor");
				
				// Este metodo guarda y actualiza los txt archivoProductos y archivoVendedores
				crudVendedorViewController.guardarDatosTXT();
				
				
				mostrarMensaje("Notifacion", "Vendedor Creado", "El vendedor ha sido creado con exito!", AlertType.INFORMATION);
				
				// Limpio los textfield
				accionBtnNuevoVendedor(new ActionEvent());
			}
			else{
				mostrarMensaje("Notifacion", "Vendedor NO Creado", "El vendedor NO ha sido creado", AlertType.ERROR);
			}
		}
		else{
			mostrarMensaje("Notifacion", "Vendedor NO Creado", "Datos ingresados NO validos", AlertType.ERROR);
		}
	}

    
    @FXML
    void accionBtnEliminarVendedor(ActionEvent event) {
    	eliminarVendedor();
    }
    
    public void eliminarVendedor(){
    	boolean vendedorEliminado = false;
    	
    	if(vendedorSeleccionado != null){
    		if(mostrarMensajeConfirmacion("�Est� seguro de eliminar el vendedor?")){
    			vendedorEliminado = crudVendedorViewController.eliminarVendedor(vendedorSeleccionado.getCedula());
    			
    			if(vendedorEliminado){
    				listaVendedoresData.remove(vendedorSeleccionado);
    				vendedorSeleccionado = null;
    				
    				// Se elimina el vendedor de la tabla y limpiamos los textfield
    				tablaVendedores.getSelectionModel().clearSelection();
    				accionBtnNuevoVendedor(new ActionEvent());
    				mostrarMensaje("Notifacion", "Vendedor Eliminado", "El vendedor ha sido eliminado con exito!", AlertType.INFORMATION);
    				
    				// Registro la accion de eliminar Vendedor y guardo los datos
    				crudVendedorViewController.guardarDatos();    	
    				crudVendedorViewController.registrarAccion("El vendedor ha sido eliminado con exito!. Realizado por el Usuario : "+ 
																mainApp.getUsuarioLogeado().getUsuario(), 1, "Eliminar Vendedor");
    				crudVendedorViewController.guardarDatosTXT();
    			}
    			else{
    				mostrarMensaje("Notifacion", "Vendedor NO Eliminado", "El vendedor NO ha sido eliminado", AlertType.ERROR);
    			}
    		}
    	}
    	else{
    		mostrarMensaje("Notifacion", "Vendedor NO seleccionado", "Seleccionado un vendedor de la lista", AlertType.WARNING);
    	}
    }
    
    
    @FXML
    void accionBtnActualizarVendedor(ActionEvent event) {
    	actualizarVendedor();
    }
    
    public void actualizarVendedor(){
    	// Capturo los datos
    	String nombre = txtNombreVendedor.getText();
		String apellido = txtApellidoVendedor.getText();
		String cedula = txtCedulaVendedor.getText();
		String direccion = txtDireccionVendedor.getText();
		
		boolean vendedorActualizado = false;
		
		// Verifico los datos
		if(vendedorSeleccionado != null){
			// Valido los datos
			if(datosValidos(nombre, apellido, cedula, direccion)){
				
				vendedorActualizado = crudVendedorViewController.actualizarVendedor(vendedorSeleccionado.getCedula(), nombre, apellido, cedula, direccion);
				
				if(vendedorActualizado == true){
//					listaVendedoresData.add(vendedor); No lo agrego de nuevo, crear�a otro vendedor
					tablaVendedores.refresh();
					mostrarMensaje("Notifacion", "Vendedor Actualizado", "El vendedor ha sido actualizado con exito!", AlertType.INFORMATION);
					
					// Registro la accion de actualizar Vendedor y guardo los datos
    				crudVendedorViewController.guardarDatos();    	
    				crudVendedorViewController.registrarAccion("El vendedor ha sido actualizado con exito!. Realizado por el Usuario : "+ 
																mainApp.getUsuarioLogeado().getUsuario(), 1, "Actualizar Vendedor");
    				crudVendedorViewController.guardarDatosTXT();
					
					// Limpio los textfield
					accionBtnNuevoVendedor(new ActionEvent());
				}
				else{
					mostrarMensaje("Notifacion", "Vendedor NO Actualizado", "El vendedor NO ha sido actualizado", AlertType.ERROR);
				}
			}
			else{
				mostrarMensaje("Notifacion", "Vendedor NO Actualizado", "Datos ingresados NO validos", AlertType.ERROR);
			}		
		}
		
			
    }
    
    /*
     * Este metodo asigna los valores del vendedor seleccionado de la tabla, en los textField
     * */
    private void mostrarInformacionVendedor(Vendedor vendedorSeleccionado) {
		if(vendedorSeleccionado != null){
			txtNombreVendedor.setText(vendedorSeleccionado.getNombre());;
		    txtApellidoVendedor.setText(vendedorSeleccionado.getApellido());
		    txtCedulaVendedor.setText(vendedorSeleccionado.getCedula());
		    txtDireccionVendedor.setText(vendedorSeleccionado.getDireccion());
		}
		
	}
    
    
    
    
    
    
    @FXML
    void accionBtnMostrarVendedor(ActionEvent event) {
    	mostrarTabVendedor();
    }
    
    /*
     * Este metodo crea una tab dinamica del vendedor seleccionado en la tabla
     * */
    public void mostrarTabVendedor() {
    	try {    			
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(MainApp.class.getResource("view/tabView/vendedorTab.fxml"));
			TabVendedorController tabVendedorController = new TabVendedorController();
			tabVendedorController.establecerValores(mainTabPane, crudVendedorViewController, 
													mainApp.getUsuarioLogeado(), vendedorSeleccionado);
			
			mainTabPane.getTabs().add(fxmlLoader.load());
//			anchorPaneTabAdministracion.getChildren().add(fxmlLoader.load());
		} catch (IOException e) {				
			e.printStackTrace();
		}
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
//    /*
//     * Este metodo crea un tabulador con la informacion (nombre, productos, contactos...)
//     * del vendedor seleccionado en la tabla
//     * */
//    public void mostrarTabVendedor(){
//    	if(mainApp.getVendedorSeleccionadoGeneral() != null){
//    		mainTabPane.getTabs().add(tabVendedorPrincipal);
//    		labelVendedorNombre.setText(mainApp.getVendedorSeleccionadoGeneral().getNombre());
//    		cargarPublicacionesVendedor();
//    		
//    		
//    		// Inicializa los vendederos en la tabla con sus columnas.
//    		columnaNombreContacto.setCellValueFactory(new PropertyValueFactory<>("nombre"));
//    		
//    		
//    		// A�ade los datos de la lista observable a la tabla
//    		// Esa lista se obtiene del modelFactoryController, que se obtiene desde un CRUD
//    		tablaContactos.getItems().clear();	// Limpio la tabla porque se usan diferentes productos que pertenecen a otros vendedores
//    		tablaContactos.setItems(getContactosData(mainApp.getVendedorSeleccionadoGeneral()));
//    		
//    		// Acci�n de la tabla para mostrar informacion de un contacto
//    		tablaContactos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->{
//    			contactoSeleccionado = newSelection;
//    			
//    			
//    			// Usar doble click ac� para mostrar el tab y talvez un mensjae de confirmacion
////    			mostrarInformacionProducto(contactoSeleccionado); Esto ser�a como mostrar el tab del contacto
//    			try {    			
//	    			FXMLLoader fxmlLoader = new FXMLLoader();
//	    			fxmlLoader.setLocation(MainApp.class.getResource("view/tabView/contactoTab.fxml"));
//	    			TabContactoController tabContactoController = new TabContactoController();
//	    			
//					mainTabPane.getTabs().add(fxmlLoader.load());
//				} catch (IOException e) {				
//					e.printStackTrace();
//				}
//    			
//    		});
//    		
//    		
//    	}
//    	else{
//    		mostrarMensaje("Notifacion", "Tab No abierta", "La tab no puede ser abierta si no selecciona un vendedor", AlertType.ERROR);
//    	}
//    }
//    
//    
//    public void cargarPublicacionesVendedor(){
//    	vBoxProductosVendedor.getChildren().clear();
//    	
//    	for(Producto producto : crudVendedorViewController.getListaProductos(mainApp.getVendedorSeleccionadoGeneral())){
//    		HBox hbox = new HBox();
//    			hbox.setSpacing(10);
//    		
//        	Label labelNombre = new Label();
//        		labelNombre.setText(""+producto.getNombre());
//        	Label labelPrecio = new Label();
//        		labelPrecio.setText(""+producto.getPrecio());
//        	Label labelCategoria = new Label();
//        		labelCategoria.setText(""+producto.getCategoria());
//        	Label labelEstado = new Label();
//        		labelEstado.setText(""+producto.getEstado());
//        	
//        	hbox.getChildren().add(labelNombre);
//        	hbox.getChildren().add(labelPrecio);
//        	hbox.getChildren().add(labelCategoria);
//        	hbox.getChildren().add(labelEstado);
//        
//        	vBoxProductosVendedor.getChildren().add(hbox);
//    	}
//    	
//    			
//    	
//    	
////    	vBoxProductosVendedor.getChildren().add();
//    	
//    }
    
    
    
    
//
//    @FXML
//    void accionBtnMostrarProductos(ActionEvent event) {
//    	mostrarTabCRUDProductos();
//    }
//    
//    /*
//     * Este metodo muestra el tab de 'CRUD Productos', para el vendedor seleccionado
//     * */
//    public void mostrarTabCRUDProductos(){
//    	mainTabPane.getTabs().add(tabCRUDProductos);
//    	inicializarProductoView();
//    	tablaProductos.refresh();
//    }
    
    
    
	
    
    
    
    
    
//    
//    
//    // -------------- METODOS PARA PRODUCTO VIEW CONTROLLER --------------
//    
//	
//	public void inicializarProductoView(){
//		// Asigno valores a la combo box
//		cbEstadoProducto.getItems().clear();// Limpio la comboBox en caso de que se repitan sus valores
//		cbEstadoProducto.getItems().addAll(EstadoProducto.values());
//		
//			
//		// Inicializa los vendederos en la tabla con sus columnas.
//		columnaNombreProducto.setCellValueFactory(new PropertyValueFactory<>("nombre"));
//		columnaPrecioProducto.setCellValueFactory(new PropertyValueFactory<>("precio"));
//		columnaCategoriaProducto.setCellValueFactory(new PropertyValueFactory<>("categoria"));
//		columnaEstadoProducto.setCellValueFactory(new PropertyValueFactory<>("estado"));
//		
//		// Limpio los textfield
//		accionBtnNuevoProducto(new ActionEvent());
//		
//		// A�ade los datos de la lista observable a la tabla
//		// Esa lista se obtiene del modelFactoryController, que se obtiene desde un CRUD
//		tablaProductos.getItems().clear();	// Limpio la tabla porque se usan diferentes productos que pertenecen a otros vendedores
//		tablaProductos.setItems(getProductosData(mainApp.getVendedorSeleccionadoGeneral()));
//		
//		// Acci�n de la tabla para mostrar informacion de un empleado
//		tablaProductos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->{
//			productoSeleccionado = newSelection;
//			mostrarInformacionProducto(productoSeleccionado);	
//		});
//
//	}
//
//
//    @FXML
//    void accionBtnAgregarProducto(ActionEvent event) {
//    	crearProducto();
//    }
//
//    public void crearProducto(){
//    	// Captura los datos
//    	Vendedor vendedor = mainApp.getVendedorSeleccionadoGeneral();
//    	
//		String nombre = txtNombreProducto.getText();
//		String precio = txtPrecioProducto.getText();
//		String categoria = txtCategoriaProducto.getText();
//		EstadoProducto estado = cbEstadoProducto.getValue(); 
//		String rutaImagen = rutaImagenProducto;
//		
//		// Valida los datos
//		if(datosValidos(nombre, precio, categoria, estado, rutaImagen)){
//			Producto producto = null;
//			
//			producto = crudVendedorViewController.crearProducto(vendedor, nombre, precio, categoria, estado, rutaImagen);
//			
//			if(producto != null){
//				listaProductosData.add(producto);
//				mostrarMensaje("Notifacion", "Producto Creado", "El producto ha sido creado con exito!", AlertType.INFORMATION);
//				
//				// Registro la accion de agregar Producto y guardo los datos
//				crudVendedorViewController.guardarDatos();    	
//				crudVendedorViewController.registrarAccion("El producto ha sido creado con exito!. Realizado por el Usuario : "+ 
//															mainApp.getUsuarioLogeado().getUsuario(), 1, "Agregar Producto");
//				crudVendedorViewController.guardarDatosTXT();
//				
//				// Limpio los textfield
//				accionBtnNuevoProducto(new ActionEvent());
//			}
//			else{
//				mostrarMensaje("Notifacion", "Producto NO Creado", "El producto NO ha sido creado", AlertType.ERROR);
//			}
//		}
//		else{
//			mostrarMensaje("Notifacion", "Producto NO Creado", "Datos ingresados NO validos", AlertType.ERROR);
//		}
//		
//    }
//
//    @FXML
//    void accionBtnNuevoProducto(ActionEvent event) {
//		// Limpio los textfield y combobox
//		txtNombreProducto.clear();
//		txtPrecioProducto.clear();
//		txtCategoriaProducto.clear();
//		cbEstadoProducto.getSelectionModel().clearSelection();
//		imagenViewProducto.setImage(null);
//		
//		// setPromptText a diferencia de setText, es mejor, porque la letra es transparente
//		// y se elimina al tocar en el textfield, y no es como poner un texto plano y ya		
//		txtNombreProducto.setPromptText("Ingrese el Nombre");
//		txtPrecioProducto.setPromptText("Ingrese el Precio");
//		txtCategoriaProducto.setPromptText("Ingrese la Categoria");
//		cbEstadoProducto.setPromptText("Ingrese el Estado");
//		
//    }
//    
//    
//	
//    @FXML
//    void accionBtnEliminarProducto(ActionEvent event) {
//    	eliminarProducto();
//    }
//    
//    public void eliminarProducto(){
//    	Vendedor vendedor = mainApp.getVendedorSeleccionadoGeneral();
//    	boolean productoEliminado = false;
//    	
//    	if(productoSeleccionado != null){
//    		if(mostrarMensajeConfirmacion("�Est� seguro de eliminar el producto?")){
////    			vendedorEliminado = crudVendedorViewController.eliminarVendedor(vendedorSeleccionado.getCedula());
//    			productoEliminado = crudVendedorViewController.eliminarProducto(vendedor, productoSeleccionado.getNombre());
//    			
//    			if(productoEliminado){
//    				listaProductosData.remove(productoSeleccionado);
//    				productoSeleccionado = null;
//    				
//    				// Se elimina el producto de la tabla y limpiamos los textfield
//    				tablaProductos.getSelectionModel().clearSelection();
//    				accionBtnNuevoProducto(new ActionEvent());
//    				mostrarMensaje("Notifacion", "Producto Eliminado", "El producto ha sido eliminado con exito!", AlertType.INFORMATION);
//    				
//    				// Registro la accion de eliminar Producto y guardo los datos
//    				crudVendedorViewController.guardarDatos();    	
//    				crudVendedorViewController.registrarAccion("El producto ha sido eliminado con exito!. Realizado por el Usuario : "+ 
//															mainApp.getUsuarioLogeado().getUsuario(), 1, "Eliminar Producto");
//    				crudVendedorViewController.guardarDatosTXT();
//    				
//    			}
//    			else{
//    				mostrarMensaje("Notifacion", "Producto NO Eliminado", "El producto NO ha sido eliminado", AlertType.ERROR);
//    			}
//    		}
//    	}
//    	else{
//    		mostrarMensaje("Notifacion", "Producto NO seleccionado", "Seleccionado un producto de la lista", AlertType.WARNING);
//    	}
//    }
//    
//    @FXML
//    void accionBtnActualizarProducto(ActionEvent event) {
//    	actualizarProducto();
//    }
//    
//    public void actualizarProducto(){
//    	// Capturo los datos
//		Vendedor vendedor = mainApp.getVendedorSeleccionadoGeneral();
//    	
//		String nombre = txtNombreProducto.getText();
//		String precio = txtPrecioProducto.getText();
//		String categoria = txtCategoriaProducto.getText();
//		EstadoProducto estado = cbEstadoProducto.getValue();
//		String rutaImagen = rutaImagenProducto;
//		
//		boolean productoActualizado = false;
//		
//		// Verifico los datos
//		if(productoSeleccionado != null){
//			// Valido los datos
//			if(datosValidos(nombre, precio, categoria, estado, rutaImagen)){
//				
////				vendedorActualizado = crudVendedorViewController.actualizarVendedor(vendedorSeleccionado.getCedula(), nombre, apellido, cedula, direccion);
//				productoActualizado = crudVendedorViewController.actualizarProducto(vendedor, productoSeleccionado.getNombre(),nombre, precio, categoria, estado, rutaImagen);
//				
//				if(productoActualizado == true){
//					tablaProductos.refresh();
//					mostrarMensaje("Notifacion", "Producto Actualizado", "El producto ha sido actualizado con exito!", AlertType.INFORMATION);
//					
//					// Registro la accion de actualizar Producto y guardo los datos
//    				crudVendedorViewController.guardarDatos();    	
//    				crudVendedorViewController.registrarAccion("El producto ha sido actualizado con exito!. Realizado por el Usuario : "+ 
//															mainApp.getUsuarioLogeado().getUsuario(), 1, "Actualizar Producto");
//    				crudVendedorViewController.guardarDatosTXT();
//					
//					// Limpio los textfield
//					accionBtnNuevoProducto(new ActionEvent());
//				}
//				else{
//					mostrarMensaje("Notifacion", "Producto NO Actualizado", "El producto NO ha sido actualizado", AlertType.ERROR);
//				}
//			}
//			else{
//				mostrarMensaje("Notifacion", "Producto NO Actualizado", "Datos ingresados NO validos", AlertType.ERROR);
//			}		
//		}
//		
//		
//	
//    }
//    
//    @FXML
//    void accionBtnSubirImagenProducto(ActionEvent event) {
//    	
//    	if(productoSeleccionado != null){
//    		// Creo un fileChooser donde solo se pueda escoger imagenes .jpg o .png
//    		FileChooser fileChooser = new FileChooser();
////        	FileChooser.ExtensionFilter ext1 = new FileChooser.ExtensionFilter("JPG files(*.jpg)","*.JPG");
//        	FileChooser.ExtensionFilter ext2 = new FileChooser.ExtensionFilter("PNG files(*.png)","*.PNG");
//        	fileChooser.getExtensionFilters().addAll(ext2);
//        	
//        	
//        	File archivoSeleccionado = fileChooser.showOpenDialog(null);
//        	
//        	try {
//        		        		
//    	    	BufferedImage bf;	
//    	    	
//    	    	if(archivoSeleccionado != null){
//    	    		// Leo la imagen para luego mostrarla en el ImageView
//    				bf = ImageIO.read(archivoSeleccionado);
//    				
//    	    		Image imagen = SwingFXUtils.toFXImage(bf, null);
//    	    		imagenViewProducto.setImage(imagen);    	   
//    	    		
//    	    		// Hacer una copia de la imagen porque la imagen le pertenece a la ruta especifica del usuario
//    	    		// Y guardo la nueva ruta para asignarsela al producto
//    	    		rutaImagenProducto = crudVendedorViewController.copiarImagen(productoSeleccionado.getNombre(),
//    	    																	 archivoSeleccionado.getAbsolutePath());    	    		    	    		
//    	    	
//    	    	}
//    	    	else{
//    	    		mostrarMensaje("Notifacion", "Archivo NO valido", "El archivo no ha sido encontrado", AlertType.ERROR);
//    	    	}
//        	} catch (IOException e) {
//    			e.printStackTrace();
//    		}	
//    	}
//    	else{
//    		mostrarMensaje("Notifacion", "Error Imagen", "NO se puede agregar imagen sin elegir un producto", AlertType.ERROR);
//    	}
//    	
//    	
//       
//    }
    
    
    
//    /*
//     * Este metodo asigna los valores del producto seleccionado de la tabla, en los textField
//     * */
//    private void mostrarInformacionProducto(Producto productoSeleccionado) {
//		if(productoSeleccionado != null){
//			txtNombreProducto.setText(productoSeleccionado.getNombre());
//		    txtPrecioProducto.setText(productoSeleccionado.getPrecio());
//		    txtCategoriaProducto.setText(productoSeleccionado.getCategoria());
//		    cbEstadoProducto.getSelectionModel().select(productoSeleccionado.getEstado());
//		    
//		 // Muestro la imagen en el ImageView
// 			try{ 				
// 				File archivoImagen = new File(productoSeleccionado.getRutaImagen());
// 				BufferedImage bf;	
// 	        	
// 				if(archivoImagen != null){
//     	    		// Leo la imagen para luego mostrarla en el ImageView
//     				bf = ImageIO.read(archivoImagen);
//     				
//     	    		Image imagen = SwingFXUtils.toFXImage(bf, null);
//     	    		imagenViewProducto.setImage(imagen);
// 				}	        			    	    	
// 				
// 			} catch(IOException e){
// 				
// 				imagenViewProducto.setImage(null);
// 				System.out.println("Imagen No encontrada. Ruta: "+productoSeleccionado.getRutaImagen());
////		 		e.printStackTrace();
//	 		}
//		}
//		
//	}
    
    
    
    
    
    
    
    
    
    
    
    public void mostrarMensaje(String titulo, String header, String contenido, AlertType alertType){
    	Alert alert = new Alert(alertType);
    	alert.setTitle(titulo);
    	alert.setHeaderText(header);
    	alert.setContentText(contenido);
    	alert.showAndWait();
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

    /*
     * Este metodo valida los datos de un --- Vendedor ---
     * */
    private boolean datosValidos(String nombre, String apellido, String cedula, String direccion){
    	String mensaje = "";
    	
    	if(nombre == null || nombre.equals(""))
    		mensaje += "Nombre no valido\n";
    	
    	if(apellido == null || apellido.equals(""))
    		mensaje += "Apellido no valido\n";
    	
    	if(cedula == null || cedula.equals(""))
    		mensaje += "Cedula no valida\n";
    	
    	if(direccion == null || direccion.equals(""))
    		mensaje += "Direccion no valida\n";
    	
    	
    	if(mensaje.equals("")){
    		return true;    		
    	}
    	else{
    		mostrarMensaje("Notificacion", "Datos no validos", mensaje, AlertType.WARNING);
    		return false;    		
    	}
    	
    }
    
    
//    /*
//     * Este metodo valida los datos de un --- Producto ---
//     * */
//    private boolean datosValidos(String nombre, String precio, String categoria, EstadoProducto estado, String rutaImagen) {
//    	String mensaje = "";
//    	
//    	if(nombre == null || nombre.equals(""))
//    		mensaje += "Nombre no valido\n";
//    	
//    	if(precio == null || precio.equals(""))
//    		mensaje += "Precio no valido\n";
//    	
//    	if(categoria == null || categoria.equals(""))
//    		mensaje += "Categoria no valida\n";
//    	
//    	if(estado == null || estado.equals(""))
//    		mensaje += "Estado no valida\n";
//    	
//    	if(rutaImagen == null || rutaImagen.equals(""))
//    		mensaje += "Imagen no valida\n";
//    	
//    	
//    	if(mensaje.equals("")){
//    		return true;    		
//    	}
//    	else{
//    		mostrarMensaje("Notificacion", "Datos no validos", mensaje, AlertType.WARNING);
//    		return false;    		
//    	}
//    	
//    }    
    
    
    
    
    /*
     * Este metodo asigna la clase MainApp a este controlador, que junto esta contiene
     * el ModelFactoryController y el objeto Marketplace
     * */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		// A�ade los datos de la lista observable a la tabla
		// Esa lista se obtiene del modelFactoryController, que se obtiene desde un CRUD
	//	tablaVendedores.setItems(modelFactoryController.getVendedoresData());
	}
	
	
	/*
	 * Esta lista se puede obtener directamente del ModelFactoryController, pero segun nuestra
	 * estructura de trabajo, el ModelFactoryController se comunica con los CRUD y estos se
	 * comunican con este controlador principal, por lo que debemos pedirle la lista al CRUD 
	 * */
	public ObservableList<Vendedor> getVendedoresData(){
		listaVendedoresData.addAll(crudVendedorViewController.getListaVendedores()) ;
		return listaVendedoresData;
	}
	
//	public ObservableList<Producto> getProductosData(Vendedor vendedorSeleccionado){
//		listaProductosData.addAll(crudVendedorViewController.getListaProductos(vendedorSeleccionado)) ;
//		return listaProductosData;
//	}
//	
//	public ObservableList<Vendedor> getContactosData(Vendedor vendedorSeleccionado){
//		listaContactosData.addAll(crudVendedorViewController.getListaContactos(vendedorSeleccionado)) ;
//		return listaContactosData;
//	}
	
	public Marketplace getMarketplace(){
		return marketplace;
	}
	
	
    
    
}
