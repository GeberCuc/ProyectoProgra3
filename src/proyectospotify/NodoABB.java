
package proyectospotify;


//nodo para el árbol binario
public class NodoABB {
   
    //Variable mp3
    Archivomp3 Cancion;
    // Punteros de direccion 
    NodoABB Izq,Der;
     int Repetido;
   
   //constructor inicializa las variables y recibe un mp3
    public NodoABB(Archivomp3 Musica){
      this.Cancion=Musica;
      this.Izq=null;
      this.Der=null;
     this.Repetido=0;
    }
    
    
    
    
}
