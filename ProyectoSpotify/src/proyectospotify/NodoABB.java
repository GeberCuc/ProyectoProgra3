
package proyectospotify;



public class NodoABB {
    
    Archivomp3 Cancion;
    
    NodoABB Izq,Der;
     int Repetido;
   
    
    public NodoABB(Archivomp3 Musica){
      this.Cancion=Musica;
      this.Izq=null;
      this.Der=null;
     this.Repetido=1;
    }
    
    
    
    
}
