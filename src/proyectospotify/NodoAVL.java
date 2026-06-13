
package proyectospotify;

        


public class NodoAVL {

    public Archivomp3 getCancion() {
        return Cancion;
    }

    public void setCancion(Archivomp3 Cancion) {
        this.Cancion = Cancion;
    }

    public NodoAVL getIzq() {
        return Izq;
    }

    public void setIzq(NodoAVL Izq) {
        this.Izq = Izq;
    }

    public NodoAVL getDer() {
        return Der;
    }

    public void setDer(NodoAVL Der) {
        this.Der = Der;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getRepetido() {
        return repetido;
    }

    public void setRepetido(int repetido) {
        this.repetido = repetido;
    }
    
    //Variable mp3
    Archivomp3 Cancion;
    //punteros de dirección
    NodoAVL Izq,Der;
    //Altura del nodo en el arbol
    int altura;
    //repetidos
    int repetido;


    //Constructor para inicializar las variables 
    public NodoAVL(Archivomp3 Cancion){
        this.Cancion=Cancion;
        this.Der=null;
        this.Izq=null;
        this.altura=1;
        this.repetido=0;
        
    }
}
