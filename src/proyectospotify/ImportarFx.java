
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
    
    if(play.getInicio()==null){
      
        return "Canciones: 0     Duracion total: 00:00";
    }


    StringBuilder informacion=new StringBuilder();

    informacion.append("Canciones: ").append(play.getSize()).append("     ");

   

    int total=0;
    int contadorNodos=0;

    NodoDoble Actual=play.getInicio();
    
    while(Actual!=null){
        contadorNodos++;
        Archivomp3 ar=Actual.getCancion();
        
        if(ar==null){
          
        } else {
            
            try{
                String duracionTexto = ar.getDuracion().trim();
                String[] partes=duracionTexto.split(":");
                
                int horas=0;
                int minutos=0;
                int segundos=0;

                if(partes.length==3){
                    horas=Integer.parseInt(partes[0].trim());
                    minutos=Integer.parseInt(partes[1].trim());
                    segundos=Integer.parseInt(partes[2].trim());
                }else if(partes.length==2){
                    minutos=Integer.parseInt(partes[0].trim());
                    segundos=Integer.parseInt(partes[1].trim());
                }
                
                total+=(horas*3600)+(minutos*60)+segundos;
                
            }catch(Exception e){
               
            }
        }
        
        Actual=Actual.Siguiente;
    }

   

    int horasFinal=total/3600;
    int minutosFinal=(total%3600)/60;
    int segundosFinal=total%60;
    
    informacion.append("Duracion total: ");
    if(horasFinal>0){
        informacion.append(horasFinal).append(":").append(String.format("%02d",minutosFinal)).append(":").append(String.format("%02d",segundosFinal));
    }else{
        informacion.append(String.format("%02d",minutosFinal)).append(":").append(String.format("%02d",segundosFinal));
    }

    
    return informacion.toString();
}
}

