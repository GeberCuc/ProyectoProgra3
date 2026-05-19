
package proyectospotify;

import java.util.ArrayList;

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

    private ArrayList<Playlist> Playlists;
 
 
    public ArrayList<Playlist> getPlaylists() {
        return Playlists;
    }
    
    

    public void setPlaylists(ArrayList<Playlist> Playlists) {
        this.Playlists = Playlists;
    }
   
    
    
    
    public ImportarFx(){
        
        ABB= new MetodosABB();
        AVL=new MetodosAVL();
        Cargador= new CargarCanciones();
        Playlists=new ArrayList<>();
    }
    
   
    
   public String Importar(String Ruta,String NombrePlaylist){

    Playlist NuevaPlaylist=new Playlist(NombrePlaylist);

    
    String Resultado=Cargador.Canciones(Ruta,ABB,AVL,NuevaPlaylist );

    
    Playlists.add(NuevaPlaylist);
 
    
    return Resultado;
}
   
   
   
   
   
   
   
   
   
 public String Info(Playlist play){

    if(play==null){

        return "Canciones: 0     Duracion total: 00:00";
        
        
    }

    ArrayList<Archivomp3>canciones=play.ObtenerCanciones();

    if(canciones==null||canciones.isEmpty()){

        
        return "Canciones: 0     Duracion total: 00:00";
    }

    StringBuilder informacion=new StringBuilder();

    informacion.append("Canciones: ").append(canciones.size()).append("     ");

    int total=0;

    for(Archivomp3 p:canciones){

        
        try{

            
            
            
            String[] partes=p.getDuracion().split(":");

            int minutos=Integer.parseInt(partes[0]);

            int segundos=Integer.parseInt(partes[1]);
            total+=(minutos*60)+segundos;

            
            
        }catch(Exception e){

            
            
        }
    }

    int minutosFinal=total/60;
    int segundosFinal=total%60;
    informacion.append("Duracion total: ").append(minutosFinal).append(":").append(String.format("%02d", segundosFinal));

    
    
    
    return informacion.toString();
}
}
