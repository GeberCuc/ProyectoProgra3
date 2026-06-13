
package proyectospotify;

public class Playlist {

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    private String Nombre;
    private NodoDoble Inicio,Fin;

    private int Size;

    
    
    
       public String getNombre(){

        return Nombre;
    }

    
    public int getSize(){

        return Size;
    }

    
    
    public NodoDoble getInicio(){

        return Inicio;
    }

    
    
    public NodoDoble getFin(){
        return Fin;
    }
    
    
    
    public Playlist(String Nombre){
        this.Nombre=Nombre;
        Inicio=null;
        Fin=null;
        Size=0;
    }

    
    
    public Playlist(){
        
    }
    public boolean vacio(){
        return Inicio==null;
    }
    
    /*
    Método para insertar al final en la lista doble
    recibiendo como paremetro el mp3
    */
    
    public boolean InsertarFin(Archivomp3 Cancion){

        try{
            NodoDoble Nuevo=new NodoDoble(Cancion);

            
            if(vacio()){

                Inicio=Nuevo;

                Fin=Nuevo;
            }

            
            else{
                Fin.setSiguiente(Nuevo);

                Nuevo.setAnterior(Fin);
                Fin=Nuevo;
            }

            Size++;

            return true;

        }catch(Exception e){

            return false;
        }
    }

    
    /*
    Método que permite insertar al inicio de la lista,
    */
    public boolean InsertarInicio(Archivomp3 Cancion){

        try{
            NodoDoble Nuevo=new NodoDoble(Cancion);

            
            if(vacio()){

                Inicio= Nuevo;

                Fin=Nuevo;
            }

            
            else{
                Nuevo.setSiguiente(Inicio);
                Inicio.setAnterior(Nuevo);

                Inicio=Nuevo;
            }

            
            Size++;

            return true;

        }catch(Exception e){

            return false;
        }
    }

    
    

    /*
    insertar en la posicion deseada por el usuario, no se uso
    */
    public boolean InsertarPosicion( Archivomp3 Cancion,int Posicion){


        if(Posicion<1||Posicion>Size+1){

            return false;
        }

        
        if(Posicion==1){

            return InsertarInicio(Cancion);
        }

        
        if(Posicion==Size+1){

            return InsertarFin(Cancion);
        }
        
        try{

            NodoDoble Actual=Inicio;

            int Contador=1;

            
            while(Contador<Posicion-1){

                Actual=Actual.getSiguiente();
                Contador++;
            }

            
            NodoDoble Nuevo=new NodoDoble(Cancion);

            
            Nuevo.setSiguiente(Actual.getSiguiente());

            Nuevo.setAnterior(Actual);

            
            Actual.getSiguiente().setAnterior(Nuevo);

            Actual.setSiguiente(Nuevo);

            
            Size++;

            return true;

        }catch(Exception e){

            return false;
        }
    }

    /*
    
    Método para eliminar dentro de la playlist
    recibe como parametro el archivo mp3 
    
    */
    
    public boolean EliminarCancion(String NombreCancion){

        
        if(vacio()){
            return false;
        }
        
        NodoDoble Actual=Inicio;

        
        while(Actual!=null){

            
            if(Actual.getCancion().getNombre().equalsIgnoreCase(NombreCancion)){

                
                
                if(Inicio==Fin){

                    Inicio=null;

                    Fin=null;
                }

                
                
                else if(Actual==Inicio){

                    Inicio=Inicio.getSiguiente();

                    Inicio.setAnterior(null);
                }

                
              
                else if(Actual==Fin){

                    Fin=Fin.getAnterior();

                    Fin.setSiguiente(null);
                }

                
           
                else{
                    Actual.getAnterior().setSiguiente(Actual.getSiguiente());

                    Actual.getSiguiente() .setAnterior(Actual.getAnterior() );
                }

                
                Actual.setAnterior(null);

                Actual.setSiguiente(null);

                Size--;

                return true;
            }

            
            Actual =
                    Actual.getSiguiente();
        }

        
        return false;
    }

    
    

    /*
    Buscar canción dentro de la lista
    */
    public Archivomp3 BuscarCancion(String NombreCancion){
        NodoDoble Actual=Inicio;

        
        while(Actual!=null){

            if(Actual.getCancion().getNombre() .equalsIgnoreCase(NombreCancion)){

                return Actual.getCancion();
            }

            
            Actual=Actual.getSiguiente();
        }

        
        return null;
    }

    
    
 /*
    Permite arrastrar una cancion de un sitio a otro, mediante la obtencion de su posición actual y si posicion final
    
    */
    
    public boolean MoverCancion(int PosicionOrigen,int PosicionDestino){

        
        if(PosicionOrigen<1||PosicionOrigen>Size){

            return false;
        }

        
        if(PosicionDestino<1|| PosicionDestino>Size){

            return false;
        }

        
        if(PosicionOrigen==PosicionDestino){

            return false;
        }

        
        NodoDoble Actual=Inicio;
        int Contador=1;
        while(Contador<PosicionOrigen){

            Actual= Actual.getSiguiente();

            Contador++;
        }

        
        Archivomp3 CancionMover=Actual.getCancion();

        if (PosicionOrigen<PosicionDestino) {
        PosicionDestino--;
        }
        
        EliminarCancion(CancionMover.getNombre());
        InsertarPosicion(CancionMover,PosicionDestino);

        
        return true;
    }

    
    //Buscar el mp3 completo
    
    public NodoDoble BuscarCancion(Archivomp3 buscado){
        
        NodoDoble Actual=Inicio;
        
        while(Actual!=null){
            if(Actual.getCancion().equals(buscado)){
                return Actual;
            }
            Actual=Actual.Siguiente;
        }
        return null;
    }
    
    



    
    
//tiempo de busqueda
    public long TiempoBusqueda(String NombreCancion){

        
        long InicioTiempo=System.nanoTime();

        
        BuscarCancion(NombreCancion );

        long FinTiempo= System.nanoTime();

        return FinTiempo-InicioTiempo;
    }

    
 
 public void Vaciar(){

    Inicio=null;
    Fin=null;
    Size=0;
}
 

    @Override
    public String toString(){

        return Nombre+ " (" + Size + " canciones)";
    }
}