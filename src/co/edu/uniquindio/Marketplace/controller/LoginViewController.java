package co.edu.uniquindio.Marketplace.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.Marketplace.MainApp;
import co.edu.uniquindio.Marketplace.model.Marketplace;
import co.edu.uniquindio.Marketplace.model.Usuario;
import co.edu.uniquindio.Marketplace.persistencia.Persistencia;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class LoginViewController implements Initializable {
	
    @FXML private AnchorPane panelIniciarSesion;
    @FXML private AnchorPane panelRegistrarUsuario;
    @FXML private TextField txtUsuario;
    @FXML private TextField txtContrasenia;
    @FXML private TextField txtNuevoUsuario;
    @FXML private TextField txtNuevaContrasenia;
    @FXML private TextField txtConfirmarNuevaContrasenia;
    @FXML private Button btnIngresar;
    @FXML private Button btnRegistrarse;
    @FXML private Button btnRegresar;
    @FXML private Button btnCrearCuenta;
    
    
    // Referencia a la MainApp.
    private MainApp mainApp;
	
    Marketplace marketplace;
	ModelFactoryController modelFactoryController;
	CrudLoginViewController crudLoginViewController;
	
	// Aunque no trabaje con estos CRUD, los inicializa
	CrudVendedorViewController crudVendedorViewController;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Acá se inicializan todos los controladores CRUD
		modelFactoryController = ModelFactoryController.getInstance();
		crudLoginViewController = new CrudLoginViewController(modelFactoryController);
		crudVendedorViewController = new CrudVendedorViewController(modelFactoryController);
    }
    	
    
//    	-------------- METODOS PARA LOGIN VIEW CONTROLLER --------------
    
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
				
				Usuario usuarioLogeado = new Usuario();
				usuarioLogeado.setUsuario(usuario);
				usuarioLogeado.setContrasenia(contrasenia);
				
				mainApp.setUsuarioLogeado(usuarioLogeado);
				
				// ESTO NO SE PUEDE, LA PERSISTENCIA SOLO SE LLAMA DESDE EL MODELFACTORY
				// Registrar la acción de inicio de sesion
				// Persistencia.guardaRegistroLog("Inicio de sesion del usuario: "+usuario, 1, "inicioSesion");
				
				// Registro la accion de inicio de sesion
				crudLoginViewController.registrarAccion("Inicio de sesion del usuario: "+usuario, 1, "inicioSesion");
				
				
				// Llamo al MarketplaceViewController y cambio la view (el fxml)				
				try {			
					FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/MarketplaceView.fxml"));
					Parent root = loader.load();

//					mainApp.getRootLayout().setCenter(root);
					
					// Creo el controlador
					MarketplaceViewController marketplaceViewController = loader.getController();
					marketplaceViewController.setMainApp(mainApp);
					marketplaceViewController.establecerValores(marketplace, 
																modelFactoryController, 
																crudLoginViewController, 
																crudVendedorViewController);
				
					
					// Esta manera es para agregarle animacion de cambio de scene, pero tambien
					// Se puede usar el "mainApp.getRootLayout().setCenter(root);"
//					Scene scene = btnIngresar.getScene();
					Scene scene = new Scene(root);
//					AnchorPane loginView = (AnchorPane) scene.getRoot();
//					loginView.getChildren().add(root);
					mainApp.getPrimaryStage().setScene(scene);
					
					// Animaciones ------ Efectos
					
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
				
				
			}
			else{
				mostrarMensaje("Notifacion", "Usuario NO Encontado o Contrasenia es Incorrecta", "Datos ingresados NO validos", AlertType.ERROR);
			}
		}
		else{
			mostrarMensaje("Notifacion", "Usuario NO Encontado", "Datos ingresados NO validos", AlertType.ERROR);
		}
    }
    
    /*
     * Muestra la ventana para que el usuario pueda Registrarse
     * */
    @FXML
    void accionBtnRegistrarse(ActionEvent event) {
    	panelRegistrarUsuario.setVisible(true);
    	panelIniciarSesion.setVisible(false);
    }
    
    /*
     * Muestra la ventana para que el usuario pueda Iniciar Sesion
     * */
    @FXML
    void accionBtnRegresar(ActionEvent event) {
    	panelRegistrarUsuario.setVisible(false);
    	panelIniciarSesion.setVisible(true);
    }
    
    @FXML 
    void accionBtnCrearCuenta(ActionEvent event) {
    	crearNuevoUsuario();
    }
    
    private void crearNuevoUsuario() {
		// Captura los datos
    	String nuevoUsuario = txtNuevoUsuario.getText();
		String nuevaContrasenia = txtNuevaContrasenia.getText();
		String confirmarNuevaContrasenia = txtConfirmarNuevaContrasenia.getText();
		
			
		// Valida los datos
		if(datosValidos(nuevoUsuario, nuevaContrasenia, confirmarNuevaContrasenia)){
			Usuario usuario = null;
			
			usuario = crudLoginViewController.crearUsuario(nuevoUsuario, nuevaContrasenia);
			
			if(usuario != null){				
				mostrarMensaje("Notifacion", "Usuario Creado", "Usuario creado con exito! Bienvenido "+ 
								nuevoUsuario+ "!", AlertType.INFORMATION);
				
				// Guardo y registro la accion de crear usuario
				crudLoginViewController.guardarDatos();
				crudLoginViewController.guardarDatosTXT();
				crudLoginViewController.registrarAccion("El usuario ha sido creado con exito!", 1, "Crear Nuevo Usuario");
				
				
				// Limpio los textfield
				txtNuevoUsuario.clear();
			    txtNuevaContrasenia.clear();
			    txtConfirmarNuevaContrasenia.clear();
			}
			else{
				mostrarMensaje("Notifacion", "Usuario NO Creado", "El Usuario NO ha sido creado", AlertType.ERROR);
			}
		}
		else{
			mostrarMensaje("Notifacion", "Usuario NO Creado", "Datos ingresados NO validos", AlertType.ERROR);
		}
	}
    
    
    
    
    
    
    
    
    
    public void mostrarMensaje(String titulo, String header, String contenido, AlertType alertType){
    	Alert alert = new Alert(alertType);
    	alert.setTitle(titulo);
    	alert.setHeaderText(header);
    	alert.setContentText(contenido);
    	alert.showAndWait();
    }
    
    
    /*
     * Este metodo valida los datos de --- Inicio de Sesion ---
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
    
    /*
     * Este metodo valida los datos de un --- Registro de Usuario ---
     * */
    private boolean datosValidos(String nuevoUsuario, String nuevaContrasenia, String confirmarNuevaContrasenia){
    	String mensaje = "";
    	
    	if(nuevoUsuario == null || nuevoUsuario.equals(""))
    		mensaje += "Usuario no valido\n";
    	
    	if(nuevaContrasenia == null || nuevaContrasenia.equals(""))
    		mensaje += "Contrasenia no valida\n";
    	
    	if(confirmarNuevaContrasenia == null || confirmarNuevaContrasenia.equals(""))
    		mensaje += "Contrasenia no valida\n";
    	
    	// Verifico si las dos contraseñas No son iguales
    	if(!nuevaContrasenia.equals(confirmarNuevaContrasenia)){
    		mensaje += "Las contraseñas no son iguales\n"; 
    	}
    	
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
