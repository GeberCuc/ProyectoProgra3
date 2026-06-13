
package proyectospotify;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.List;


public class EstadisticasTXT {

    
    /*
    Clase encargada de registrar y actualizar las estadisticas de reproduccion
    guardandolos en un txt 
    
    */
    public EstadisticasTXT(){
        
        
    }
    
    
    /*
    Método que registra cada reproduccion si es nueva en caso de no solo aumenta
    el contador de reproducción, recibe como parametro nombre de la cancion y 
    el tiempo reproducido
    */
    
   public void Registro(String nombre,double tiempo){
       
       //si no hubo tiempo de reproducción cancelamos
       if(tiempo<=0){
           return;
       }
       //creamos el txt con la cabecera y nos aseguramos que si exista 
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
           //busqueda linea por linea 
           for(int i=1;i<datos.size();i++){
               //usamos split para separa cada texto
               String []info=datos.get(i).split(";");
              
               //comparamos datos y extraemos 
               if(info[0].equalsIgnoreCase(nombre)){
                   
                   int reproducciones=Integer.parseInt(info[1]);
                   double tiempoT=Double.parseDouble(info[2]);
                  
                   reproducciones++;
                   tiempoT+=tiempo;
                   
                  String tiem=String.format(java.util.Locale.US,"%.2f",tiempoT);
                  // sobrescribe la linea modificada en el mismo indice
                datos.set(i,nombre+";"+reproducciones+";"+tiem);
                   hallado=true;
                   break;
               }
               
               
           }
           
           //si no se encontro coincidencia simplemente agrega una nueva linea 
           if(!hallado){
                  String tiemp=String.format(java.util.Locale.US,"%.2f",tiempo);

            datos.add(nombre+";"+1+";"+tiemp
            );
                  
                  
               }    
           //Rescribe todo el txt pero con los datos actualizados
           Files.write(txt.toPath(),datos);
           
       }catch(IOException e){
           
           
       }
       
       
   }
    
    
    
}
