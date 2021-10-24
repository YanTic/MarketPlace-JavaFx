package co.edu.uniquindio.Marketplace.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.Marketplace.MainApp;
import co.edu.uniquindio.Marketplace.model.EstadoProducto;
import co.edu.uniquindio.Marketplace.model.Marketplace;
import co.edu.uniquindio.Marketplace.model.Usuario;
import co.edu.uniquindio.Marketplace.model.Vendedor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;

public class LoginViewController implements Initializable {
	
    @FXML private TextField txtUsuario;
    @FXML private TextField txtContrasenia;
    @FXML private Button btnIngresar;
    @FXML private Button btnRegistrarse;
    
    
    // Referencia a la MainApp.
    private MainApp mainApp;
	
    Marketplace marketplace;
	ModelFactoryController modelFactoryController;
	CrudLoginViewController crudLoginViewController;
	
	// Aunque no trabaje con estos CRUD, los inicializa
	CrudVendedorViewController crudVendedorViewController;
	CrudProductoViewController crudProductoViewController;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Acá se inicializan todos los controladores CRUD
		modelFactoryController = new ModelFactoryController().getInstance();
		crudLoginViewController = new CrudLoginViewController(modelFactoryController);
		crudVendedorViewController = new CrudVendedorViewController(modelFactoryController);
		crudProductoViewController = new CrudProductoViewController(modelFactoryController);
		
		// Inicializo los datos de cada controlador CRUD (Como tablas...)
		inicializarLoginView();
    }
    	
    
//    	-------------- METODOS PARA VENDEDOR VIEW CONTROLLER --------------
    public void inicializarLoginView(){
    	
    	
    }
    
    @FXML
    void accionBtnIngresar(ActionEvent event) {
    	inicioSesion();
    }
    
    public void inicioSesion(){
    	// Captura los datos
		String usuario = txtUsuario.getText();
		String contrasenia = txtContrasenia.getText();
		
		// Valida los datos
		if(datosValidos(usuario, contrasenia)){
			// Comparo los datos del usuario 
			if(crudLoginViewController.verificarUsuario(usuario, contrasenia)){
				mostrarMensaje("Notifacion", "Login Correcto", "Bienvenido "+usuario+ "!", AlertType.INFORMATION);
				
				// Llamo al MarketplaceViewController y cambio la view (el fxml)				
				try {
//					FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MarketplaceView.fxml"));
//					FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/co/edu/uniquindio/Marketplace/view/MarketplaceView.fxml"));
//					FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MarketPlaceView.fxml"));
					FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/MarketplaceView.fxml"));
					Parent root = (Parent) loader.load();

					mainApp.getRootLayout().setCenter(root);
					
					// Creo el controlador
					MarketplaceViewController marketplaceViewController = loader.getController();
					marketplaceViewController.establecerValores(marketplace, 
																modelFactoryController, 
																crudLoginViewController, 
																crudVendedorViewController, 
																crudProductoViewController);
					
					
					// Esta manera es para agregarle animacion de cambio de scene, pero tambien
					// Se puede usar el "mainApp.getRootLayout().setCenter(root);"
//					Scene scene = btnIngresar.getScene();
//					StackPane loginView = (StackPane) scene.getRoot();
//					loginView.getChildren().add(root);
					
					// Animaciones ------ Efectos
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
				
				
			}
			else{
				mostrarMensaje("Notifacion", "Usuario NO Encontado ó Contrasenia es Incorrecta", "Datos ingresados NO validos", AlertType.ERROR);
			}
		}
		else{
			mostrarMensaje("Notifacion", "Usuario NO Encontado", "Datos ingresados NO validos", AlertType.ERROR);
		}
    }
    


    @FXML
    void accionBtnRegistrarse(ActionEvent event) {
//    	crearNuevoUsuario();
    }
    
//  
//  private void crearNuevoUsuario() {
//  	// Captura los datos
//		String usuario = txtUsuario.getText();
//		String contrasenia = txtContrasenia.getText();
//		
//		// Valida los datos
//		if(datosValidos(usuario, contrasenia)){
//			Usuario usuario = null;
//			
//			usuario = crudUsuarioViewController.crearVendedor(nombre, apellido, cedula, direccion);
//			
//			if(vendedor != null){
//				listaVendedoresData.add(vendedor);
//				mostrarMensaje("Notifacion", "Vendedor Creado", "El vendedor ha sido creado con exito!", AlertType.INFORMATION);
//				
//				// Limpio los textfield
//				accionBtnNuevoVendedor(new ActionEvent());
//			}
//			else{
//				mostrarMensaje("Notifacion", "Vendedor NO Creado", "El vendedor NO ha sido creado", AlertType.ERROR);
//			}
//		}
//		else{
//			mostrarMensaje("Notifacion", "Vendedor NO Creado", "Datos ingresados NO validos", AlertType.ERROR);
//		}
//	}
    
    
    
    public void mostrarMensaje(String titulo, String header, String contenido, AlertType alertType){
    	Alert alert = new Alert(alertType);
    	alert.setTitle(titulo);
    	alert.setHeaderText(header);
    	alert.setContentText(contenido);
    	alert.showAndWait();
    }
    
    
    /*
     * Este metodo valida los datos de un --- Usuario ---
     * */
    private boolean datosValidos(String usuario, String contrasenia){
    	String mensaje = "";
    	
    	if(usuario == null || usuario.equals(""))
    		mensaje += "Usuario no valido\n";
    	
    	if(contrasenia == null || contrasenia.equals(""))
    		mensaje += "Contrasenia no valida\n";
    	
    	if(mensaje.equals("")){
    		return true;    		
    	}
    	else{
    		mostrarMensaje("Notificacion", "Datos no validos", mensaje, AlertType.WARNING);
    		return false;    		
    	}
    	
    } 
    

    public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
}
