
package proyectospotify;


public class MetodoPila {
    
    NodoPila peek;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    private int size;
    
    public MetodoPila(){
        
    }
    
    
    public void Push(Archivomp3 Dato){
        
        NodoPila nuevo= new NodoPila(Dato);
        
        
        nuevo.Siguiente=peek;
        
        peek=nuevo;
        size++;
    }
    
    
    public Archivomp3 Pop(){
        
       if(peek==null){

        return null;
    }

    Archivomp3 dato=peek.getCancion();

    peek=peek.Siguiente;

    size--;

    return dato;
        
    }
    
    
    public Archivomp3 Peek(){

    if(peek == null){
       return null;
    }

    return peek.getCancion();
}
    
    
    public boolean vacio(){
        
        return peek==null;
    }
    
}
