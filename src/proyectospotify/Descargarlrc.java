/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

public class Descargarlrc{

    /**
     * Busca y descarga automaticamente el archivo .lrc usando tu objeto Archivomp3.
     * @param cancion Objeto con los metadatos de la canción actual.
     * @return 
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
            
            String query=URLEncoder.encode(titulo + " " + artista, StandardCharsets.UTF_8.toString());
            String urlApi="https://lrclib.net/api/search?q=" + query;

            
            URL url=new URL(urlApi);
            HttpURLConnection conexion=(HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("GET");
            conexion.setRequestProperty("User-Agent", "ProyectoSpotifyJavaFX (Contacto: yo@gmai.com)");

            if(conexion.getResponseCode()!= 200){
                
                
                
                return false; 
            }

            InputStreamReader lector=new InputStreamReader(conexion.getInputStream(), StandardCharsets.UTF_8);
            JsonArray resultados=JsonParser.parseReader(lector).getAsJsonArray();

            if(resultados.size()==0){
               
                
                return false;
            }

            for(int i=0;i<resultados.size();i++){
                JsonObject cancionJson=resultados.get(i).getAsJsonObject();
                
                
                
                if(cancionJson.has("syncedLyrics")&&!cancionJson.get("syncedLyrics").isJsonNull()){
                    
                    
                    String contenidoLrc=cancionJson.get("syncedLyrics").getAsString();

                    String rutaLrc=rutaMp3.replaceAll("(?i)\\.mp3$",".lrc");
                    
                    File archivoLrc=new File(rutaLrc);

                    try(FileWriter escritor=new FileWriter(archivoLrc, StandardCharsets.UTF_8)){
                        escritor.write(contenidoLrc);
                    }
                    return true;
                }
            }
            
           
            return false;

        }catch(Exception e){
            
            return false; 
        }
    }
}
