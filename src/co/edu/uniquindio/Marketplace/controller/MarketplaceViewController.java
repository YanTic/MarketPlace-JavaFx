package co.edu.uniquindio.Marketplace.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import co.edu.uniquindio.Marketplace.MainApp;
import co.edu.uniquindio.Marketplace.model.Marketplace;
import co.edu.uniquindio.Marketplace.model.Vendedor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/*
 * ---- CONTROLADOR PRINCIPAL ----
 * */

public class MarketplaceViewController implements Initializable{
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
    
	// Reference to the main application.
	private MainApp mainApp;
	
    Marketplace marketplace;
	ModelFactoryController modelFactoryController;
	CrudVendedorViewController crudVendedorViewController;
	
	ObservableList<Vendedor> listaVendedoresData = FXCollections.observableArrayList();
	Vendedor vendedorSeleccionado;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Acá se inicializan todos los controladores CRUD
		modelFactoryController = new ModelFactoryController().getInstance();
		crudVendedorViewController = new CrudVendedorViewController(modelFactoryController);
		
		// Inicializo los datos de cada controlador CRUD (Como tablas...)
		inicializarVendedorView();
	}
	
	public void inicializarVendedorView(){
		// Initialize the person table with the two columns.
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
				mostrarMensaje("Notifacion", "Vendedor No Creado", "El vendedor no ha sido creado", AlertType.ERROR);
			}
		}
		else{
			mostrarMensaje("Notifacion", "Vendedor No Creado", "Datos ingresados no validos", AlertType.ERROR);
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
    			}
    			else{
    				mostrarMensaje("Notifacion", "Vendedor No Eliminado", "El vendedor no ha sido eliminado", AlertType.ERROR);
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
					
					// Limpio los textfield
					accionBtnNuevoVendedor(new ActionEvent());
				}
				else{
					mostrarMensaje("Notifacion", "Vendedor NO Actualizado", "El vendedor NO ha sido actualizado", AlertType.ERROR);
				}
			}
			else{
				mostrarMensaje("Notifacion", "Vendedor NO Actualizado", "Datos ingresados no validos", AlertType.ERROR);
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
    		mostrarMensaje("Notificacion", "Datos no valido", mensaje, AlertType.WARNING);
    		return false;    		
    	}
    	
    }
    
    
    
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
	
	public Marketplace getMarketplace(){
		return marketplace;
	}
	
	
    
    
}
