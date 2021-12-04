package co.edu.uniquindio.Marketplace.controller.dinamico;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import co.edu.uniquindio.Marketplace.MainApp;
import co.edu.uniquindio.Marketplace.controller.CrudVendedorViewController;
import co.edu.uniquindio.Marketplace.model.Publicacion;
import co.edu.uniquindio.Marketplace.model.Vendedor;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

public class TabContactoController implements Initializable{
	
    @FXML private Tab tabContactoDinamica;
    @FXML private Label labelContactoNombre;
    @FXML private ScrollPane scrollpaneProductos;
    @FXML private GridPane gridpaneProductos;
    @FXML private TableView<Vendedor> tablaContactos;
    @FXML private TableColumn<Vendedor, String> columnaNombreContacto;

    
    CrudVendedorViewController crudVendedorViewController;
        
    ObservableList<Vendedor> listaContactosData = FXCollections.observableArrayList();
    Vendedor contactoSeleccionado; //Responsable del tab

    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Platform.runLater(()->{
			tabContactoDinamica.setClosable(true);
			tabContactoDinamica.setText("Contacto "+contactoSeleccionado.getNombre());
			
			cargarInformacionTabContacto();
			
		});		
	}
	
	public void setInformacionContacto(CrudVendedorViewController crudVendedorViewController, Vendedor contactoSeleccionado){
		this.crudVendedorViewController = crudVendedorViewController;
		this.contactoSeleccionado = contactoSeleccionado;
	}
    

	 /*
     * Este metodo crea un tabulador con la informacion (nombre, productos, contactos...)
     * del vendedor seleccionado en la tabla
     * */
    public void cargarInformacionTabContacto(){
    	if(contactoSeleccionado != null){
    		
    		labelContactoNombre.setText(contactoSeleccionado.getNombre());
    		cargarPublicacionesVendedor();
    		
    		
    		// Inicializa los vendederos en la tabla con sus columnas.
    		columnaNombreContacto.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    		
    		
    		// Añade los datos de la lista observable a la tabla
    		// Esa lista se obtiene del modelFactoryController, que se obtiene desde un CRUD
    		tablaContactos.getItems().clear();	// Limpio la tabla porque se usan diferentes productos que pertenecen a otros vendedores
    		tablaContactos.setItems(getContactosData(contactoSeleccionado));    		
    		
    	}
    	else{
    		mostrarMensaje("Notifacion", "Tab No abierta", "La tab no puede ser abierta si no selecciona un vendedor", AlertType.ERROR);
    	}
    }
    
    
    public void cargarPublicacionesVendedor(){
    	ArrayList<Publicacion> publicaciones = getListaPublicacionesDesdeContactos(contactoSeleccionado);
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
    
    
    
    
    
    
    
    public void mostrarMensaje(String titulo, String header, String contenido, AlertType alertType){
    	Alert alert = new Alert(alertType);
    	alert.setTitle(titulo);
    	alert.setHeaderText(header);
    	alert.setContentText(contenido);
    	alert.showAndWait();
    }
    
    
    public ArrayList<Publicacion> getListaPublicacionesDesdeContactos(Vendedor contactoSeleccionado){
    	return crudVendedorViewController.getListaPublicacionesDesdeContactos(contactoSeleccionado);
    }
    
    public ObservableList<Vendedor> getContactosData(Vendedor contactoSeleccionado){
		listaContactosData.addAll(crudVendedorViewController.getListaContactosDesdeContactos(contactoSeleccionado)) ;
		return listaContactosData;
	}
	
}
