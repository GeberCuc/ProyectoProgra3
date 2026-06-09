
package proyectospotify;


public class Karaoke {

    public double getTiempoSegundos() {
        return tiempoSegundos;
    }

    public String getVerso() {
        return verso;
    }
    //2 horas buscando el error y habia paso mal el parametro el error estaba entre la compu y la silla 
    private double tiempoSegundos;
    private String verso;
    
    public Karaoke(double tiempoSegundos,String verso){
        
        this.tiempoSegundos=tiempoSegundos;
        this.verso=verso;
    }


}

