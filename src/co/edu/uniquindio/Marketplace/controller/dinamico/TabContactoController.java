package co.edu.uniquindio.Marketplace.controller.dinamico;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;

public class TabContactoController implements Initializable{
    @FXML private Tab tabContactoDinamica;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tabContactoDinamica.setClosable(true);
		
	}
	
	public void setInformacionContacto(){
		
	}
    
    
}
