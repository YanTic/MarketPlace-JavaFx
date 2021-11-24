package co.edu.uniquindio.Marketplace;

import java.io.IOException;

import co.edu.uniquindio.Marketplace.controller.CrudVendedorViewController;
import co.edu.uniquindio.Marketplace.controller.LoginViewController;
import co.edu.uniquindio.Marketplace.controller.MarketplaceViewController;
import co.edu.uniquindio.Marketplace.controller.ModelFactoryController;
import co.edu.uniquindio.Marketplace.model.Marketplace;
import co.edu.uniquindio.Marketplace.model.Usuario;
import co.edu.uniquindio.Marketplace.model.Vendedor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;

	ModelFactoryController modelFactoryController;
	Marketplace marketplace;
	Usuario usuarioLogeado;
	Vendedor vendedorSeleccionadoGeneral;


	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("MarketPlace");
		this.vendedorSeleccionadoGeneral = null;
		
		// Put an icon
		// Image icon = new Image("icon.png");
		// primaryStage.getIcons().add(icon);
		
		initRootLayout();
	}
	
	/**
	 * Initializes the Root Layout.
	 */
	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/LoginView.fxml"));
			AnchorPane loginOverview = (AnchorPane) loader.load();
			
			// Give the controller access to the main app.
			// Le doy el acceso al controlodar de la main app
			LoginViewController controller = loader.getController();
			controller.setMainApp(this);
			
			// Show the scene containing the root layout.
			Scene scene = new Scene(loginOverview);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Returns the main stage.
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	
	public BorderPane getRootLayout() {
		return rootLayout;
	}
	

	public Vendedor getVendedorSeleccionadoGeneral() {
		return vendedorSeleccionadoGeneral;
	}

	public void setVendedorSeleccionadoGeneral(Vendedor vendedorSeleccionadoGeneral) {
		this.vendedorSeleccionadoGeneral = vendedorSeleccionadoGeneral;
	}
	
	public Usuario getUsuarioLogeado() {
		return usuarioLogeado;
	}

	public void setUsuarioLogeado(Usuario usuarioLogeado) {
		this.usuarioLogeado = usuarioLogeado;
	}
	
	

	public static void main(String[] args) {
		launch(args);
	}

}
