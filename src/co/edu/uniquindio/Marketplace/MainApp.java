package co.edu.uniquindio.Marketplace;

import java.io.IOException;

import co.edu.uniquindio.Marketplace.controller.CrudClienteViewController;
import co.edu.uniquindio.Marketplace.controller.GestionPrestamoControl;
import co.edu.uniquindio.Marketplace.model.Empresa;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;

	GestionPrestamoControl gestionPrestamoControl ;
	Empresa empresa;


	public MainApp() {
		gestionPrestamoControl = new GestionPrestamoControl();
		empresa = gestionPrestamoControl.getEmpresa();
	}


	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Prestamo de Objetos");
		initRootLayout();
		showPersonOverview();
	}

	/**
	 * Initializes the root layout.
	 */
	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Shows the person overview inside the root layout.
	 */
	public void showPersonOverview() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/PrestamoObjetosView.fxml"));
			AnchorPane prestamoObjetoOverview = (AnchorPane) loader.load();
			// Set person overview into the center of root layout.
			rootLayout.setCenter(prestamoObjetoOverview);


			// Give the controller access to the main app.
			CrudClienteViewController controller = loader.getController();
			controller.setMainApp(this);


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
	public static void main(String[] args) {
		launch(args);
	}



}
