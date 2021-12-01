package co.edu.uniquindio.Marketplace.controller.dinamico;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import co.edu.uniquindio.Marketplace.model.EstadoProducto;
import co.edu.uniquindio.Marketplace.model.Producto;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ProductoController implements Initializable{
	
    @FXML private AnchorPane anchorPane;
    @FXML private ImageView imagenProducto;
    @FXML private Label nombreProducto;
    @FXML private Label precioProducto;
    @FXML private Label fechaPublicadoProducto;
    @FXML private Label estadoProducto;
    
    @FXML private Label cantidadLikes;
    @FXML private Button btnLikeProducto;
    @FXML private Button btnComentariosProducto;

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Platform.runLater(()->{
			
		});	
	}
	
	public void establecerDatos(Producto producto, String fechaPublicado){
		
		nombreProducto.setText(producto.getNombre());
	    precioProducto.setText(producto.getPrecio());
	    // NO PASA EL VALOR DEL ESTADOPRODUCTO A STRING ARREGLARRRRRR!!!!!!!
//	    estadoProducto.setText(""+producto.getEstado(););
	    
	    
	 // Muestro la imagen en el ImageView
			try{ 				
				File archivoImagen = new File(producto.getRutaImagen());
				BufferedImage bf;	
	        	
				if(archivoImagen != null){
	 	    		// Leo la imagen para luego mostrarla en el ImageView
	 				bf = ImageIO.read(archivoImagen);
	 				
	 	    		Image imagen = SwingFXUtils.toFXImage(bf, null);
	 	    		imagenProducto.setImage(imagen);
				}	        			    	    	
				
			} catch(IOException e){
				
				imagenProducto.setImage(null);
				System.out.println("Imagen No encontrada. Ruta: "+producto.getRutaImagen());
//		 		e.printStackTrace();
 		}
	}

	
	@FXML
    void accionBtnComentariosProducto(ActionEvent event) {

    }

    @FXML
    void accionBtnLikeProducto(ActionEvent event) {

    }
    
    
    
    public void mostrarMensaje(String titulo, String header, String contenido, AlertType alertType){
    	Alert alert = new Alert(alertType);
    	alert.setTitle(titulo);
    	alert.setHeaderText(header);
    	alert.setContentText(contenido);
    	alert.showAndWait();
    }
    
    
    
    
    
}
