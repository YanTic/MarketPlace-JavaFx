package co.edu.uniquindio.Marketplace.controller;

import co.edu.uniquindio.Marketplace.MainApp;
import co.edu.uniquindio.Marketplace.model.Marketplace;
import co.edu.uniquindio.Marketplace.model.Vendedor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CrudVendedorViewController {	
	ModelFactoryController modelFactoryController;
	Marketplace marketplace;

	
	public CrudVendedorViewController(ModelFactoryController modelFactoryController) {
		this.modelFactoryController = modelFactoryController;
		marketplace = modelFactoryController.getMarketplace();
	}

	public Marketplace getMarketplace() {
		return marketplace;
	}


	public void setMarketplace(Marketplace marketplace) {
		this.marketplace = marketplace;
	}
	
}
