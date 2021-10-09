package co.edu.uniquindio.Marketplace.controller;

import java.net.URL;

import java.util.ResourceBundle;

import co.edu.uniquindio.Marketplace.MainApp;
import co.edu.uniquindio.Marketplace.model.Marketplace;
import co.edu.uniquindio.Marketplace.model.Vendedor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
		
//	Añade los datos de la lista observable a la tabla
// 	Esa lista se obtiene del modelFactoryController, que se obtiene desde un CRUD
		tablaVendedores.setItems(getVendedoresData());
		
		// Acción de la tabla para mostrar informacion de un empleado
		tablaVendedores.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->{
			vendedorSeleccionado = newSelection;
			mostrarInformacionVendedor(vendedorSeleccionado);
		});
	}
	
    @FXML
    void accionBtnAgregarVendedor(ActionEvent event) {

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
