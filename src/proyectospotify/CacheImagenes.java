
package proyectospotify;

import java.io.File;
import java.util.HashMap;
import javafx.scene.image.Image;

public class CacheImagenes{

    private static HashMap<String,Image>cache=new HashMap<>();



    public static Image ObtenerImagen(String ruta){

        try{

            if(ruta==null||ruta.isEmpty()){

                return ObtenerDefault();
            }



            if(cache.containsKey(ruta)){

                return cache.get(ruta);
            }



            File archivo=new File(ruta);
            Image img;
            if(archivo.exists()){

                img=new Image(archivo.toURI().toString(),80,80,true,true,true);

            }else{

                img=ObtenerDefault();
            }

            cache.put(ruta,img);

            return img;

        }catch(Exception e){
            
            return ObtenerDefault();
        }
    }






    public static Image ObtenerDefault(){

        String rutaDefault="Predeterminado";

        if(cache.containsKey(rutaDefault)){

            return cache.get(rutaDefault);
        }

        Image img=new Image(CacheImagenes.class.getResourceAsStream("/resources/imagenes/disco-vinilo.png"),80,80,true,true);

        cache.put(rutaDefault, img);

        return img;
    }






    public static Image ObtenerCorazon(){

        String key="Corazon";

        if(cache.containsKey(key)){

            return cache.get(key);
        }

        Image img=new Image(CacheImagenes.class.getResourceAsStream("/IconosFX/favorite.png"),20,20,true,true);
        cache.put(key, img);
        
        return img;
    }






    public static Image ObtenerCorazonCheck(){

        String key="CorazonCheck";

        if(cache.containsKey(key)){
            
            return cache.get(key);
        }

        Image img=new Image(CacheImagenes.class.getResourceAsStream( "/IconosFX/addfavorite.png"),20,20,true,true);

        cache.put(key, img);

        return img;
    }
}
