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
	Vendedor vendedorSeleccionado;
	
	ObservableList<Vendedor> listaVendedoresData = FXCollections.observableArrayList();
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Acá se inicializan todos los controladores CRUD
		modelFactoryController = new ModelFactoryController().getInstance();
		crudVendedorViewController = new CrudVendedorViewController(modelFactoryController);
		
		inicializarVendedorView();
	}
	
	public void inicializarVendedorView(){
		// Initialize the person table with the two columns.
		columnaNombreVendedor.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		columnaApellidoVendedor.setCellValueFactory(new PropertyValueFactory<>("apellido"));
		columnaCedulaVendedor.setCellValueFactory(new PropertyValueFactory<>("cedula"));
		columnaDireccionVendedor.setCellValueFactory(new PropertyValueFactory<>("direccion"));
		
		
		
		// Acción para mostrar informacion de un empleado
		tablaVendedores.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->{
			vendedorSeleccionado = newSelection;
			mostrarInformacionVendedor(vendedorSeleccionado);
		});
	}
	
    @FXML
    void accionBtnAgregarVendedor(ActionEvent event) {

    }
    
    private void mostrarInformacionVendedor(Vendedor vendedorSeleccionado) {
		if(vendedorSeleccionado != null){
//			txtnom // 1:24:30 Clase 04-10-2021
		}
		
	}

    
    
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		// Add observable list data to the table
		tablaVendedores.setItems(modelFactoryController.getClienteData());
	}
	
	public Marketplace getMarketplace(){
		return marketplace;
	}
    
    
}
