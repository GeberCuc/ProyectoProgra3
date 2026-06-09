
package proyectospotify;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class AnimacionK{

    public static void animarEntradaTexto(Label label, String nuevoTexto) {
 
        label.setText(nuevoTexto);
        
        label.setTranslateY(30);
        label.setOpacity(0.0);
        
     
        TranslateTransition mover =new TranslateTransition(Duration.millis(400),label);
        mover.setToY(0);
        FadeTransition aparecer=new FadeTransition(Duration.millis(400),label);
        aparecer.setToValue(1.0);
        
   
        ParallelTransition transicion=new ParallelTransition(mover,aparecer);
        transicion.play();
    }
}