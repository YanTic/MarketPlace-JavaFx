package co.edu.uniquindio.Marketplace.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import co.edu.uniquindio.Marketplace.MainApp;
import co.edu.uniquindio.Marketplace.controller.dinamico.TabVendedorController;
import co.edu.uniquindio.Marketplace.model.Marketplace;
import co.edu.uniquindio.Marketplace.model.Vendedor;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/*
 * ---- CONTROLADOR PRINCIPAL ----
 * */

public class MarketplaceViewController implements Initializable{
	
	// User    
	@FXML private Button btnUsuario;
	@FXML private Button btnLogout;
	
	@FXML private TabPane mainTabPane;
	@FXML private Tab tabAdministracion;
	@FXML private AnchorPane anchorPaneTabAdministracion;
	
	
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
    
	// Referencia a la MainApp.
	private MainApp mainApp;
	
    Marketplace marketplace;
	ModelFactoryController	   modelFactoryController;
	CrudVendedorViewController crudVendedorViewController;
	CrudLoginViewController	   crudLoginViewController;
	
	// Listas observable para mostrar en tablas, junto a su objeto seleccionado
	ObservableList<Vendedor> listaVendedoresData = FXCollections.observableArrayList();
	ArrayList<Vendedor> listaTabVendedoresAbiertas;
	Vendedor vendedorSeleccionado;
	String   rutaImagenProducto;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
// ------- Estos metodos ya están inicializados en el LoginViewController -------
		
		// Acá se inicializan todos los controladores CRUD
//		modelFactoryController = new ModelFactoryController().getInstance();
//		crudVendedorViewController = new CrudVendedorViewController(modelFactoryController);
//		crudProductoViewController = new CrudProductoViewController(modelFactoryController);
		
		
		
		// Inicializo los datos de cada controlador CRUD (Como tablas...)
//		inicializarVendedorView();
//		inicializarProductoView();
		
		
		// Oculto los tabs || Nota: YA NO EXISTEN 
//		mainTabPane.getTabs().remove(tabVendedorPrincipal);
//		mainTabPane.getTabs().remove(tabCRUDProductos);
		
		
		
		
		// Uso Platform.runLater, porque los metodos se están llamando antes de inicializar
		// los datos cuando se crea este controlador desde LoginViewController
		Platform.runLater(()->{
			// Inicializo los datos de cada controlador CRUD (Como tablas...)
			inicializarVendedorView();
			
			// Digo que no se han abierto tabs de ningun vendedor
			listaTabVendedoresAbiertas = new ArrayList<Vendedor>();
			
			// Establesco el Usuario que acaba de iniciara sesion
			usuarioLogeado();
			
			
			// Muestro la tab dependiendo del usuario logeado
			if(!mainApp.getUsuarioLogeado().getUsuario().equals("admin")){
				mainTabPane.getTabs().remove(tabAdministracion);
				
				for(Vendedor vendedor : getListaVendedores()){
					if(mainApp.getUsuarioLogeado().getUsuario() == vendedor.getNombre() ||
					   mainApp.getUsuarioLogeado().getUsuario().equals(vendedor.getNombre())){
						vendedorSeleccionado = vendedor;
						mostrarTabVendedor();
						break;
					}					
				}
				
//				vendedorSeleccionado = mainApp.getUsuarioLogeado().getUsuario() =
			}
			
			
			
			
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
			
			// Yo no establezco valores porque ya allá se crean desde el initialize
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
		
		// Añade los datos de la lista observable a la tabla
		// Esa lista se obtiene del modelFactoryController, que se obtiene desde un CRUD
		tablaVendedores.setItems(getVendedoresData());
		
		// Acción de la tabla para mostrar informacion de un empleado
		tablaVendedores.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->{
			vendedorSeleccionado = newSelection;
//			mainApp.setVendedorSeleccionadoGeneral(vendedorSeleccionado);
			
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
		
		tablaVendedores.getSelectionModel().clearSelection();
		
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
    		if(mostrarMensajeConfirmacion("¿Está seguro de eliminar el vendedor?")){
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
//					listaVendedoresData.add(vendedor); No lo agrego de nuevo, crearía otro vendedor
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
    	boolean tabVendedorAbierta = false;
    	
    	// Verifico si la tab del vendedor ya está abierta
    	for(Vendedor vendedor : listaTabVendedoresAbiertas){
    		if(vendedor.equals(vendedorSeleccionado)){
    			tabVendedorAbierta = true;
    			break;
    		}
    	}
    	
    	// Si no está abierta la tab del vendedor, puede abrirla
    	if(tabVendedorAbierta != true){
    		try {    			
    			FXMLLoader fxmlLoader = new FXMLLoader();
    			fxmlLoader.setLocation(MainApp.class.getResource("view/tabView/vendedorTab.fxml"));
    			mainTabPane.getTabs().add(fxmlLoader.load());
    			
    			// RECORDAR: El error anterior de que no se establecian los datos en el controlador,
    			// 			 era porque el fxmlLoader no se habia cargado antes (.load()), así que 
    			//			 se tiene que cargar antes de asignarle el controlador y los valores
    			
    			TabVendedorController tabVendedorController = fxmlLoader.getController();
    			tabVendedorController.establecerValores(crudVendedorViewController, mainApp.getUsuarioLogeado(), 
    													vendedorSeleccionado);
    			
    			listaTabVendedoresAbiertas.add(vendedorSeleccionado);
    		} catch (IOException e) {				
    			e.printStackTrace();
    		}
    	}
    	else{
    		mostrarMensaje("Notificacion", "Tab Ya Abierta", "La tab del vendedor "+vendedorSeleccionado.getNombre()+ " ya está abierta", AlertType.INFORMATION);
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
     * Este metodo asigna la clase MainApp a este controlador, que junto esta contiene
     * el ModelFactoryController y el objeto Marketplace
     * */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		// Añade los datos de la lista observable a la tabla
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
	
	public ArrayList<Vendedor> getListaVendedores(){
		return crudVendedorViewController.getListaVendedores();
	}
	
	
	public Marketplace getMarketplace(){
		return marketplace;
	}
	
	
    
    
}
