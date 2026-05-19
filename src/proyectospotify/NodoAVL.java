
package proyectospotify;

        


public class NodoAVL {
    

    Archivomp3 Cancion;
    NodoAVL Izq,Der;
    int altura;
    int repetido;


    
    public NodoAVL(Archivomp3 Cancion){
        this.Cancion=Cancion;
        this.Der=null;
        this.Izq=null;
        this.altura=1;
        this.repetido=1;
        
    }
}
