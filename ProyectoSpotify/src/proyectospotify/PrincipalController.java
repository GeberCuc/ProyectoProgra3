
package proyectospotify;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;







public class PrincipalController implements Initializable {

    
    ImportarFx Import=  new ImportarFx();
    
@FXML
private AnchorPane PanelInicio;
@FXML
private AnchorPane PanelBuscar;
@FXML
private AnchorPane PanelBiblioteca;
@FXML
private AnchorPane PanelPlaylist;
@FXML
    private ListView<String> ListaCanciones2;


 @FXML
    public void MostrarInicio(){

        PanelInicio.setVisible(true);
        PanelInicio.setManaged(true);

        PanelBuscar.setVisible(false);
        PanelBuscar.setManaged(false);

        PanelBiblioteca.setVisible(false);
        PanelBiblioteca.setManaged(false);

        PanelPlaylist.setVisible(false);
        PanelPlaylist.setManaged(false);
    }



 @FXML
    public void MostrarBuscar(){

        PanelInicio.setVisible(false);
        PanelInicio.setManaged(false);

        PanelBuscar.setVisible(true);
        PanelBuscar.setManaged(true);

        PanelBiblioteca.setVisible(false);
        PanelBiblioteca.setManaged(false);

        PanelPlaylist.setVisible(false);
        PanelPlaylist.setManaged(false);
    }

    @FXML
    public void MostrarBiblioteca(){

        PanelInicio.setVisible(false);
        PanelInicio.setManaged(false);

        PanelBuscar.setVisible(false);
        PanelBuscar.setManaged(false);

        PanelBiblioteca.setVisible(true);
        PanelBiblioteca.setManaged(true);

        PanelPlaylist.setVisible(false);
        PanelPlaylist.setManaged(false);
    }

    @FXML
    public void MostrarPlaylis(){

        PanelInicio.setVisible(false);
        PanelInicio.setManaged(false);

        PanelBuscar.setVisible(false);
        PanelBuscar.setManaged(false);

        PanelBiblioteca.setVisible(false);
        PanelBiblioteca.setManaged(false);

        PanelPlaylist.setVisible(true);
        PanelPlaylist.setManaged(true);
    }

    
    
    
    @FXML
    public void ImportarMusica(){
    
        
    DirectoryChooser Selector=new DirectoryChooser();

    Selector.setTitle("Seleccionar Carpeta");

    File Carpeta=Selector.showDialog(null);

    
    if(Carpeta!=null){
        
        String Resultado=Import.Importar(Carpeta.getAbsolutePath());

        Alert alerta=new Alert( Alert.AlertType.INFORMATION );

        alerta.setHeaderText("Importación Finalizada");

        alerta.setContentText(Resultado);

        alerta.showAndWait();
    }
}
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     MostrarInicio();
  
   
    }    
    
}
