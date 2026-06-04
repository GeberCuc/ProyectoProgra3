
package proyectospotify;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.List;


public class EstadisticasTXT {

    
    
    public EstadisticasTXT(){
        
        
    }
    
   public void Registro(String nombre,double tiempo){
       
       
       if(tiempo<=0){
           return;
       }
       
       File txt=new File("registros.txt");
       try{
           
          
           if(!txt.exists()){
               txt.createNewFile();
           }
           
           List<String> datos=Files.readAllLines(txt.toPath());
          
           if(datos.isEmpty()){
               datos.add("Nombre;Reproducciones;Tiempo");
           
           }
           
           
           boolean hallado=false;
           
           for(int i=1;i<datos.size();i++){
               
               String []info=datos.get(i).split(";");
              
               if(info[0].equalsIgnoreCase(nombre)){
                   
                   int reproducciones=Integer.parseInt(info[1]);
                   double tiempoT=Double.parseDouble(info[2]);
                  
                   reproducciones++;
                   tiempoT+=tiempo;
                   
                  String tiem=String.format(java.util.Locale.US,"%.2f",tiempoT);

                datos.set(i,nombre+";"+reproducciones+";"+tiem);
                   hallado=true;
                   break;
               }
               
               
           }
           
          
              if(!hallado){
                  String tiemp=String.format(java.util.Locale.US,"%.2f",tiempo);

            datos.add(nombre+";"+1+";"+tiemp
            );
                  
                  
               }     
           Files.write(txt.toPath(),datos);
           
       }catch(IOException e){
           System.out.println("e");
           
       }
       
       
   }
    
    
    
}
