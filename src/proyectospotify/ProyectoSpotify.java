package proyectospotify;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ProyectoSpotify extends Application {

    @Override
    public void start(Stage stage)throws Exception {
        

        Font fuente = Font.loadFont(getClass().getResourceAsStream("/resources/Montserrat-Regular.ttf"), 13);
   
        FXMLLoader loader=new FXMLLoader(
                getClass().getResource("Principal.fxml")
        );

        Parent root=loader.load();
        Scene scene=new Scene(root);

        stage.setTitle("Selenio");
        
       scene.getStylesheets().add(getClass().getResource("/Estilos/fuente.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

