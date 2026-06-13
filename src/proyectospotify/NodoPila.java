
package proyectospotify;


public class NodoPila {

    public Archivomp3 getCancion() {
        return Cancion;
    }

    public void setCancion(Archivomp3 Cancion) {
        this.Cancion = Cancion;
    }

    public NodoPila getSiguiente() {
        return Siguiente;
    }

    public void setSiguiente(NodoPila Siguiente) {
        this.Siguiente = Siguiente;
    }

    //nodo para el metodopila 
private Archivomp3 Cancion;    
NodoPila Siguiente;



public NodoPila(Archivomp3 Cancion){
    this.Cancion=Cancion;
    this.Siguiente=null;
}    




}
