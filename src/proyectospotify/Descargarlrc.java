
package proyectospotify;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


/*

Método encargado de sincronizar de latras, se conecta a la api LRCLIB mediante una
petición HTTP, procesa el JSON generado y genera el archivos .lrc
*/

public class Descargarlrc{

    /**
     * Busca y descarga automaticamente el archivo .lrc usando el objeto Archivomp3.
     * @param cancion Objeto con los metadatos de la canción actual.
     * retorna verdadero si la letra fue encontrada y descargada.
     */
    
   public static boolean descargarLrcAutomatico(Archivomp3 cancion){
        if(cancion==null||cancion.getNombre()==null||cancion.getArtista()==null){
           
            return false;
        }

        try{
            String titulo=cancion.getNombre();
            String artista=cancion.getArtista();
            
            String rutaMp3=cancion.getAudio();
            
            
            titulo=titulo.replaceFirst("^\\d+[\\.\\s\\-]*", "").trim();
            
            //codifica los parametros en para que los espacios y acentos sean validos 
            String query=URLEncoder.encode(titulo + " " + artista, StandardCharsets.UTF_8.toString());
            String urlApi="https://lrclib.net/api/search?q=" + query;

            //abre la conexión
            URL url=new URL(urlApi);
            HttpURLConnection conexion=(HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("GET");
            //Nos identificamos para evitar bloqueos 
            conexion.setRequestProperty("User-Agent", "ProyectoSpotifyJavaFX (Contacto: yo@gmail.com)");
            
            //si nos devuelve un codigo diferente de 200 cancelamos 
            if(conexion.getResponseCode()!= 200){
                
                return false; 
            }
            
            //leemos y convertimos el archivo tipo web a arreglo json
            InputStreamReader lector=new InputStreamReader(conexion.getInputStream(), StandardCharsets.UTF_8);
            JsonArray resultados=JsonParser.parseReader(lector).getAsJsonArray();
            
            //Si no ecuentra nada terminamos
            if(resultados.size()==0){
               
                
                return false;
            }
            
            //verifar si coincide 
            for(int i=0;i<resultados.size();i++){
                JsonObject cancionJson=resultados.get(i).getAsJsonObject();
                
                
                //validar que no sea nulo y si contenga los letra con marcas de tiempo
                if(cancionJson.has("syncedLyrics")&&!cancionJson.get("syncedLyrics").isJsonNull()){
                    
                    
                    String contenidoLrc=cancionJson.get("syncedLyrics").getAsString();
                    
                    //obtenemos el nombre del mp3 original y cambiamos la extencio a.lrc asignandolo al archivo nuevo
                    String rutaLrc=rutaMp3.replaceAll("(?i)\\.mp3$",".lrc");
                    File archivoLrc=new File(rutaLrc);

                    
                    try(FileWriter escritor=new FileWriter(archivoLrc, StandardCharsets.UTF_8)){
                        escritor.write(contenidoLrc);
                    }
                    return true;//éxito
                }
            }
            
           
            return false;//éxito a medias, se encontro la letra pero sin sincronización

        }catch(Exception e){
            
            return false; 
        }
    }
}
