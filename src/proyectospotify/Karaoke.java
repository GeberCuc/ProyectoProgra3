
package proyectospotify;


public class Karaoke {

    public double getTiempoSegundos() {
        return tiempoSegundos;
    }

    public String getVerso() {
        return verso;
    }

/*
    Clase encargada de obtener el tiempo de cada cancion y su respectivo
    texto sincronizado
    recibe como parametro el tiempo y el texto a mostrar en ese lapso de tiempo 
    */    
    private double tiempoSegundos;
    private String verso;
    
    public Karaoke(double tiempoSegundos,String verso){
        
        this.tiempoSegundos=tiempoSegundos;
        this.verso=verso;
    }


}

