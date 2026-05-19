
package proyectospotify;


public class MetodoCola {
    
    NodoCola Entrada,Salida;
    int size;
    public MetodoCola(){
        
    }
    
    
    
    
    
    
    
    public void Entrante(Archivomp3 Dato){
        
        NodoCola nuevo= new NodoCola(Dato);
        
        
        if(Entrada==null){
        
        Salida=Entrada=nuevo;
        }else{
            
         Entrada.Siguiente=nuevo;
          Entrada=nuevo;
           
        }
         size++;
    }
    
    
    public Archivomp3 Salida(){
        
       if(Salida==null){

        return null;
    }

       Archivomp3 guardado=Salida.getCancion();   
       Salida=Salida.Siguiente;
       size--;
 
    
       if(Salida==null){

           Entrada=null;
    
       }
       return guardado;
    }
    
    public Archivomp3 Frente(){

    if(Salida==null){

        return null;
    }

    return Salida.getCancion();
}
    
    
    
    
    
    
}
