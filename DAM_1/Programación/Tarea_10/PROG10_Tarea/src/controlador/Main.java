package controlador;
 
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
 
/**
 * FXML Controller class
 *
 * @author FColls
 */
//Clase Main hereda de la Clase Application
public class Main extends Application {
 
     /*
    Sobreescritura del metodo start
    El método start() toma un solo parámetro del tipo Stage. 
    El escenario es donde se muestran todas las partes visuales de la aplicación JavaFX.
    */
    @Override
    public void start(Stage primaryStage) {
        
        try {
            /*
            Establecer ruta archivo FXML
            */
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/vista/CalculadoraVista.fxml"));
             
            Pane ventana = (Pane) loader.load();
             
            /*
            La escena JavaFX contiene todos los componentes visuales de la GUI de JavaFX en su interior. 
            */
            Scene scene = new Scene(ventana);
             
           /*
            primaryStage, representa la ventana principal de su aplicación JavaFX.
            */
            primaryStage.setTitle("Calculadora JavaFX");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
 
    public static void main(String[] args) {
        launch(args);
    }
 
}