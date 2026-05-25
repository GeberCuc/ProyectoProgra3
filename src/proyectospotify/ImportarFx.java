
package proyectospotify;


public class ImportarFx {

    public MetodosABB getABB() {
        return ABB;
    }

    public void setABB(MetodosABB ABB) {
        this.ABB = ABB;
    }

    public MetodosAVL getAVL() {
        return AVL;
    }

    public void setAVL(MetodosAVL AVL) {
        this.AVL = AVL;
    }

    public CargarCanciones getCargador() {
        return Cargador;
    }

    public void setCargador(CargarCanciones Cargador) {
        this.Cargador = Cargador;
    }


    
    private  MetodosABB ABB;
    private MetodosAVL AVL;
    private CargarCanciones Cargador;

    

  
 
 
 
   
    
    
    
    public ImportarFx(){
        
        ABB= new MetodosABB();
        AVL=new MetodosAVL();
        Cargador= new CargarCanciones();
        
    }
    
   
    //pendiente 
   public Playlist Importar(String Ruta,String NombrePlaylist){

       
       Playlist NuevaPlaylist=new Playlist(NombrePlaylist);
 
       Cargador.Canciones(Ruta,ABB,AVL,NuevaPlaylist );

      
    return NuevaPlaylist;
}
   
   
   
   
   
   
   
   
   
 public String Info(Playlist play){

    if(play==null){

        return "Canciones: 0     Duracion total: 00:00";
        
        
    }


    StringBuilder informacion=new StringBuilder();

    informacion.append("Canciones: ").append(play.getSize()).append("     ");

    int total=0;

    NodoDoble Actual=play.getInicio();
    while(Actual!=null){

        Archivomp3 ar=Actual.getCancion();
        
        try{

            String[] partes=ar.getDuracion().split(":");

            int minutos=Integer.parseInt(partes[0]);

            int segundos=Integer.parseInt(partes[1]);
            total+=(minutos*60)+segundos;

            
            
        }catch(Exception e){

        }
        Actual=Actual.Siguiente;
    }

    int minutosFinal=total/60;
    int segundosFinal=total%60;
    informacion.append("Duracion total: ").append(minutosFinal).append(":").append(String.format("%02d", segundosFinal));

    
    return informacion.toString();
}
}
