package co.edu.uniquindio.Marketplace.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import co.edu.uniquindio.Marketplace.MainApp;
import co.edu.uniquindio.Marketplace.model.EstadoProducto;
import co.edu.uniquindio.Marketplace.model.Marketplace;
import co.edu.uniquindio.Marketplace.model.Producto;
import co.edu.uniquindio.Marketplace.model.Vendedor;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/*
 * ---- CONTROLADOR PRINCIPAL ----
 * */

public class MarketplaceViewController implements Initializable{
	
	// User
    @FXML private Button btnUsuario;
    @FXML private Button btnLogout;
	
	
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
    
    
    // CRUD PRODUCTOS
    @FXML private TextField txtNombreProducto;
    @FXML private TextField txtPrecioProducto;
    @FXML private TextField txtCategoriaProducto;
    @FXML private ComboBox<EstadoProducto> cbEstadoProducto;
    @FXML private Button btnActualizarProducto;
    @FXML private Button btnNuevoProducto;
    @FXML private Button btnAgregarProducto;
    @FXML private Button btnEliminarProducto;
    @FXML private TableView<Producto> tablaProductos;
    @FXML private TableColumn<Producto, String> columnaNombreProducto;
    @FXML private TableColumn<Producto, String> columnaPrecioProducto;
    @FXML private TableColumn<Producto, String> columnaCategoriaProducto;
    @FXML private TableColumn<Producto, EstadoProducto> columnaEstadoProducto;
    
    
	// Referencia a la MainApp.
	private MainApp mainApp;
	
    Marketplace marketplace;
	ModelFactoryController modelFactoryController;
	CrudVendedorViewController crudVendedorViewController;
	CrudLoginViewController	   crudLoginViewController;
	
	// Listas observable para mostrar en tablas, junto a su objeto seleccionado
	ObservableList<Vendedor> listaVendedoresData = FXCollections.observableArrayList();
	ObservableList<Producto> listaProductosData = FXCollections.observableArrayList();
	Vendedor vendedorSeleccionado;
	Producto productoSeleccionado;
    
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
		
		
		
		
		
		// Uso Platform.runLater, porque los metodos se est�n llamando antes de inicializar
		// los datos cuando se crea este controlador desde LoginViewController
		Platform.runLater(()->{
			// Inicializo los datos de cada controlador CRUD (Como tablas...)
			inicializarVendedorView();
			inicializarProductoView();
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

    }
	
    @FXML
    void accionBtnUsuario(ActionEvent event) {

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
    
    
    
    
    @FXML
    void accionBtnMostrarVendedor(ActionEvent event) {
    	mostrarTabVendedor();
    }
    
    /*
     * Este metodo crea un tabulador con la informacion (nombre, productos, contactos...)
     * del vendedor seleccionado en la tabla
     * */
    public void mostrarTabVendedor(){
    	
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
    
    
    
    
    
    
    // -------------- METODOS PARA PRODUCTO VIEW CONTROLLER --------------
    
	
	public void inicializarProductoView(){
		// Asigno valores a la combo box
		cbEstadoProducto.getItems().addAll(EstadoProducto.values());
		
			
		// Inicializa los vendederos en la tabla con sus columnas.
		columnaNombreProducto.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		columnaPrecioProducto.setCellValueFactory(new PropertyValueFactory<>("precio"));
		columnaCategoriaProducto.setCellValueFactory(new PropertyValueFactory<>("categoria"));
		columnaEstadoProducto.setCellValueFactory(new PropertyValueFactory<>("estado"));
		
		// Limpio los textfield
		accionBtnNuevoProducto(new ActionEvent());
		
		// A�ade los datos de la lista observable a la tabla
		// Esa lista se obtiene del modelFactoryController, que se obtiene desde un CRUD
		tablaProductos.setItems(getProductosData());
		
		// Acci�n de la tabla para mostrar informacion de un empleado
		tablaProductos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->{
			productoSeleccionado = newSelection;
			mostrarInformacionProducto(productoSeleccionado);
		});

	}
	
    @FXML
    void accionBtnEliminarProducto(ActionEvent event) {

    }
    
    @FXML
    void accionBtnActualizarProducto(ActionEvent event) {

    }

    @FXML
    void accionBtnAgregarProducto(ActionEvent event) {
    	// Captura los datos
		String nombre = txtNombreProducto.getText();
		String precio = txtPrecioProducto.getText();
		String categoria = txtCategoriaProducto.getText();
		EstadoProducto estado = cbEstadoProducto.getValue(); 
		
		// Valida los datos
		if(datosValidos(nombre, precio, categoria, estado)){
			Producto producto = null;
			
			producto = crudVendedorViewController.crearProducto(nombre, precio, categoria, estado);
			
			if(producto != null){
				listaProductosData.add(producto);
				mostrarMensaje("Notifacion", "Producto Creado", "El producto ha sido creado con exito!", AlertType.INFORMATION);
				
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
		
		// setPromptText a diferencia de setText, es mejor, porque la letra es transparente
		// y se elimina al tocar en el textfield, y no es como poner un texto plano y ya		
		txtNombreProducto.setPromptText("Ingrese el Nombre");
		txtPrecioProducto.setPromptText("Ingrese el Precio");
		txtCategoriaProducto.setPromptText("Ingrese la Categoria");
		cbEstadoProducto.setPromptText("Ingrese el Estado");
		
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
		}
		
	}
    
    
    
    
    
    
    
    
    
    
    
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
    
    
    /*
     * Este metodo valida los datos de un --- Producto ---
     * */
    private boolean datosValidos(String nombre, String precio, String categoria, EstadoProducto estado){
    	String mensaje = "";
    	
    	if(nombre == null || nombre.equals(""))
    		mensaje += "Nombre no valido\n";
    	
    	if(precio == null || precio.equals(""))
    		mensaje += "Precio no valido\n";
    	
    	if(categoria == null || categoria.equals(""))
    		mensaje += "Categoria no valida\n";
    	
    	if(estado == null || estado.equals(""))
    		mensaje += "Estado no valida\n";
    	
    	
    	if(mensaje.equals("")){
    		return true;    		
    	}
    	else{
    		mostrarMensaje("Notificacion", "Datos no validos", mensaje, AlertType.WARNING);
    		return false;    		
    	}
    	
    }    
    
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
	
	public ObservableList<Producto> getProductosData(){
		listaProductosData.addAll(crudVendedorViewController.getListaProductos()) ;
		return listaProductosData;
	}
	
	public Marketplace getMarketplace(){
		return marketplace;
	}
	
	
    
    
}
