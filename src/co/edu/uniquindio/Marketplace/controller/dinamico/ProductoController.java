package co.edu.uniquindio.Marketplace.controller.dinamico;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ProductoController implements Initializable{
	
    @FXML private AnchorPane anchorPane;
    @FXML private ImageView imagenProducto;
    @FXML private Label nombreProducto;
    @FXML private Label precioProducto;
    @FXML private Label fechaPublicadoProducto;
    @FXML private Button btnLikeProducto;
    @FXML private Button btnComentariosProducto;

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}

	
	@FXML
    void accionBtnComentariosProducto(ActionEvent event) {

    }

    @FXML
    void accionBtnLikeProducto(ActionEvent event) {

    }
}
