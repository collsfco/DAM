package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import jdk.nashorn.internal.runtime.ParserException;

/**
 * FXML Controller class
 *
 * @author FColls
 */
public class CalculadoraControlador implements Initializable {

    private boolean realizaOpe=false;
    private boolean nuevoNumero=false;
    
    /**
     * textPantalla, atributo asociada a la pantalla donde se muestran los datos
     */
    @FXML
    private Label textPantalla;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    /**
     * numeros, método asociados a los botones numéricos.
     * 
     * ActionEvent, para representar un acción del usuario en la interfaz, 
     * por ejemplo, pulsar un botón en la pantalla.
     * 
     * @param event nos proporciona información acerca del suceso que se ha producido, 
     * nos dice qué control ha generado el suceso.
     */
    @FXML
    private void numeros(ActionEvent event) {
        /*
        nuevoNumero, luego de realizar una operación obtenemos un resultado en pantalla, si 
        queremos realizar una nueva operacion y presionamos un número y el número de pantalla 
        es sustituido por el presionado. Si queremos reutilizar el número en pantalla para otra
        operación, solo debemos presionar la tecla de operación más los números.
        */
       if (nuevoNumero){
           textPantalla.setText("");
       }
       //textPantalla.setText > Escribimos en pantalla, valor actual  + valor del boton presionado.
       //textPantalla.getText() > Obtenemos los caracteres actuales en la pantalla.
       //((Button)event.getSource()).getText() > Obtenemos el dato del botón que ha sido presionado.
        textPantalla.setText(textPantalla.getText() + ((Button)event.getSource()).getText());
        
        realizaOpe = true; 
        nuevoNumero=false;
    }

     /**
     * operaciones, método asociados a los botones de las operaciones matemáticas
     * (+,-,*,/).
     * 
     *param event nos proporciona información acerca del suceso que se ha producido, 
     * nos dice qué control ha generado el suceso.
     */
    @FXML
    private void operaciones(ActionEvent event) {
        /*realizaOpe, se pone en true cuando se presiona un número y en false cuando
        se presiona un botón de operación así se evita repetir, o escribir 2 signos 
        de las operaciones matemáticas seguidos.*/
        
        if (realizaOpe){
        textPantalla.setText(textPantalla.getText() + ((Button)event.getSource()).getText());
        nuevoNumero=false;
        realizaOpe = false;
        }
    }

   /**
    * limpiarPantalla,método asociados a el boton CE el cual limpia la pantalla
    * @param event  nos dice qué control ha generado el suceso
    */
    @FXML
    private void limpiarPantalla(ActionEvent event) {
        textPantalla.setText("");
        realizaOpe=false;
        
    }

    /**
    * borrarNumero,método asociados a el boton ← el cual borra un caracter en la pantalla.
    * @param event  nos dice qué control ha generado el suceso
    */
    @FXML
    private void borrarNumero(ActionEvent event) {
        //Si existe un caracter en la pantalla, es decir la longitud de caracteres es diferente de 0.
        if(!(textPantalla.getText().length()==0)){
            //Obtenemos los caracteres actuales y creamos un substring al cual le quitamos el último
            //caracter y volvemos a escribir en pantalla los caracteres restantes.
            textPantalla.setText(textPantalla.getText().substring(0,textPantalla.getText().length()-1));
        }      
    }
    
    /**
     * 
     * resultado,método asociados a el boton = el cual realiza el cálculo de la operaciones.
     * @param event  nos dice qué control ha generado el suceso.
     */
    @FXML
    private void resultado(ActionEvent event) {
        
        /*Con la función eval perteneciente a la clase ScriptEngine podemos realizar
        operaciones matemáticas obteniendo los valores de un String. Esto permite
        poder realizar distintas operaciones con varios números. Los números y opereaciones
        son almacenadas en textPantalla, luego con la función eval somos capaces de resolver
        la operación matemática.
        */ 
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        try {
            Object operation = engine.eval(textPantalla.getText().replaceAll("x", "*").replaceAll("÷", "/"));
            //Los caracteres "x" y "÷", son reemplazados por "*" y "/" los cuales si son reconocibles
            //por java para realizar las operaciones matemáticas.
            textPantalla.setText("" + operation);
            nuevoNumero=true;
           
        } catch (ScriptException | ParserException e) {
            textPantalla.setText("");
        }
    }
        
}


