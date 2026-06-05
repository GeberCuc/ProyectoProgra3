
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
