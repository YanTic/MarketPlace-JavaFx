package co.edu.uniquindio.Marketplace;

import java.io.IOException;

import co.edu.uniquindio.Marketplace.controller.CrudVendedorViewController;
import co.edu.uniquindio.Marketplace.controller.MarketplaceViewController;
import co.edu.uniquindio.Marketplace.controller.ModelFactoryController;
import co.edu.uniquindio.Marketplace.model.Marketplace;
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


	public MainApp() {
		modelFactoryController = new ModelFactoryController();
		marketplace = modelFactoryController.getMarketplace();
	}


	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("MarketPlace");
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
			loader.setLocation(MainApp.class.getResource("view/MarketplaceView.fxml"));
			AnchorPane marketplaceOverview = (AnchorPane) loader.load();
			// Set person overview into the center of root layout.
			rootLayout.setCenter(marketplaceOverview);


			// Give the controller access to the main app.
			MarketplaceViewController controller = loader.getController();
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
