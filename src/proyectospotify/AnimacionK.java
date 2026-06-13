
package proyectospotify;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class AnimacionK{

    
    /*
    Metodo encargada de la animacion del modo karaoke recibe como parametro el label
    que se tiene que animar y un string que es el verso extraido del lrc
    */
    public static void animarEntradaTexto(Label label, String nuevoTexto) {
 
        label.setText(nuevoTexto);
        label.setTranslateY(30);
        label.setOpacity(0.0);
        
       //Función necesaria para realizar el movimiento de la animacion con una duracion de 400 milisegundos 
        TranslateTransition mover=new TranslateTransition(Duration.millis(400),label);
        mover.setToY(0);
        
        //Crea una animacion de desvanecimineto para la aparicion del label
        FadeTransition aparecer=new FadeTransition(Duration.millis(400),label);
        aparecer.setToValue(1.0);
        
        //Ajecuta ambas animaciones al mismo tiempo para darle 
        ParallelTransition transicion=new ParallelTransition(mover,aparecer);
        transicion.play();
    }
}