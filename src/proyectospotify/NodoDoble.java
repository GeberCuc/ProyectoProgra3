
package proyectospotify;


public class NodoDoble {

    public Archivomp3 getCancion() {
        return Cancion;
    }

    public void setCancion(Archivomp3 Cancion) {
        this.Cancion = Cancion;
    }

    public NodoDoble getSiguiente() {
        return Siguiente;
    }

    public void setSiguiente(NodoDoble Siguiente) {
        this.Siguiente = Siguiente;
    }

    public NodoDoble getAnterior() {
        return Anterior;
    }

    public void setAnterior(NodoDoble Anterior) {
        this.Anterior = Anterior;
    }

    
    private Archivomp3 Cancion;
   NodoDoble Siguiente,Anterior;
   
   //Nodo para la lista doble 
   public NodoDoble(Archivomp3 Cancion){
       this.Cancion=Cancion;
       this.Siguiente=null;
       this.Anterior=null;
   }
   
}
