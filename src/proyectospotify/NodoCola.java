
package proyectospotify;


public class NodoCola {

    public NodoCola getSiguiente() {
        return Siguiente;
    }

    public void setSiguiente(NodoCola Siguiente) {
        this.Siguiente = Siguiente;
    }

    public Archivomp3 getCancion() {
        return Cancion;
    }

    public void setCancion(Archivomp3 Cancion) {
        this.Cancion = Cancion;
    }
    
    
     private Archivomp3 Cancion;
    NodoCola Siguiente;
  
    
    
    
    
    public NodoCola( Archivomp3 Cancion){
        this.Cancion=Cancion;
        this.Siguiente=null;
        
    }
}
