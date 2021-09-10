package application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
//import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.scene.control.TextArea;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

public class SampleController implements Initializable {
	
	InfrastructureDepartment Billboards = new InfrastructureDepartment();
	
	@FXML
    public Button BotonAgregar;
	@FXML
	MenuItem  BotonCerrar;
	@FXML
	MenuItem  BotonCargarByteCode;
       
    @FXML
    public TextArea AreaDeTexto;
    
    @FXML
    public TextField InputAncho;
    @FXML
    public TextField InputAlto;
    @FXML
    public ComboBox<String> ComboBoxEnUso;
    @FXML
    public TextField InputEmpresa;
    
    
    
    @FXML
    private void ClickBotonAgregar(ActionEvent event) {    	
    	try{
        	Billboards.AddBillboard(Double.parseDouble(InputAncho.getText()), Double.parseDouble(InputAlto.getText()), ComboBoxEnUso.getValue().equals("Si"), InputEmpresa.getText());
        	ActualizarLista();
    	}
    	catch(NumberFormatException ex){
    		JOptionPane.showMessageDialog(null, "Error al ingresar Ancho y Alto: solo ingrese valores numericos");    		  
    	}
    }
    
    
    @FXML
    private void ClickCargarByteCode(ActionEvent event) {
    	Billboards.LoadBillboardFromByteCode();
    	ActualizarLista();    	    	
    }
    
    
    @FXML
    private void ClickBotonCerrar(ActionEvent event) {    	
    	//Stage stage = (Stage) Boton.getScene().getWindow();
        //stage.close();
    	Platform.exit();    	
    }
    
    
    public void ActualizarLista() {
    	String lines = String.format("%-15s%-15s%-15s%-15s\n","Ancho","Alto","EnUso","Empresa");
    	for(Billboard b: Billboards.billboards) {
    		lines += String.format("%-15s%-15s%-15s%-15s\n",
    				b.getWidth(),
    				b.getHeight(),
    				b.isInUse(),
    				b.getBrand());
    	}
    	AreaDeTexto.setText(lines);
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {    	
    	
    	Billboards.LoadBillboard();
    	ActualizarLista();   	
    	
    	ComboBoxEnUso.getItems().add("Si");
    	ComboBoxEnUso.getItems().add("No");
    	ComboBoxEnUso.setValue("Si");    	

    }    

	
	
	
	
	
}




