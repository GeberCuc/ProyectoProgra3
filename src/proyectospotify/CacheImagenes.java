
package proyectospotify;

import java.io.File;
import java.util.HashMap;
import javafx.scene.image.Image;

public class CacheImagenes{

    //elimina las imagenes de la ram al scrollear en listview
    private static final HashMap<String,Image>cache=new HashMap<>();
    
    private static Image imgDefault=null;
    private static Image imgCorazon=null;
    private static Image imgCorazonCheck=null;

    public static Image ObtenerImagen(String ruta){
        try{
            if(ruta==null||ruta.isEmpty()){

                return ObtenerDefault();
            }
            //reusar la imagen si todavia no ha sido limpiado por el recolector
            if(cache.containsKey(ruta)){
                Image enCache=cache.get(ruta);
                if(enCache!=null){
                return enCache;    
                }
                
            }



            File archivo=new File(ruta);
            Image img;
            
            if(archivo.exists()){
                img=new Image(archivo.toURI().toString(),200,200,true,true,true);
                cache.put(ruta, img);

            }else{

                img=ObtenerDefault();
            }

            return img;

        }catch(Exception e){
            return ObtenerDefault();
        }
    }






    public static Image ObtenerDefault(){
        
        if(imgDefault==null){
            
            imgDefault=new Image(CacheImagenes.class.getResourceAsStream("/resources/imagenes/disco-vinilo.png"),200,200,true,true);
        }
        return imgDefault;
    }






    public static Image ObtenerCorazon(){
    
        if(imgCorazon==null){
            
            imgCorazon= new Image(CacheImagenes.class.getResourceAsStream("/IconosFX/favorite.png"),20,20,true,true);
        }
        return imgCorazon;
    }






    public static Image ObtenerCorazonCheck(){
    if (imgCorazonCheck==null) {
            imgCorazonCheck =new Image(CacheImagenes.class.getResourceAsStream("/IconosFX/addfavorite.png"),20,20,true,true);
        }
        return imgCorazonCheck;
    }

    public static void Limpiar(){

        cache.clear();
        System.gc();
}
}


