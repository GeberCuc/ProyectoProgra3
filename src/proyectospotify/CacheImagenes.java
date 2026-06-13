
package proyectospotify;

import java.io.File;
import java.util.HashMap;
import javafx.scene.image.Image;

public class CacheImagenes{

    /*
    Clase encargada de gestionar las portadas e iconos del sistema, evitando 
    duplicados mediante un hash map implementado un caché capaz de reutilizar instacias
    existente para optimizar el uso de la memoria ram.
    
    */
   
    
     //variables globales y unicas, su funcion ahorra memoria evitando cargar la misma imagen varias veces
    private static final HashMap<String,Image>cache=new HashMap<>();
    private static Image imgDefault=null;
    private static Image imgCorazon=null;
    private static Image imgCorazonCheck=null;

    /**Método encargado de reutilizar imagenes existente en el cache
      en caso de no existir la carga desde la unidad de almacenamiento
      @param ruta la dirección absoluta de la imagen en el sistema 
      @return regresa una objeto de tipo Image para ser renderizado en la interfaz
    */   
    public static Image ObtenerImagen(String ruta){
        try{
            //si la ruta es invalida asigna el predeterminado un disco de vinilo 
            if(ruta==null||ruta.isEmpty()){

                return ObtenerDefault();
            }
            
            //si la imagen existe y todavia existe en el cache la reutiliza 
            if(cache.containsKey(ruta)){
                Image enCache=cache.get(ruta);
                if(enCache!=null){
                return enCache;    
                }
                
            }
          
            File archivo=new File(ruta);
            Image img;
            
            
            
            if(archivo.exists()){
                
                /*constructor para la imagen recibe como parametros; 
                1:ancho , 2: alto, 3: preservación del tamaño origanal
                4: aplica una especie de filtro al redimencionar evita la perdida de calidad
                5:carga la imagen en otro hilo evitando congelar la interfaz
                
                */
                img=new Image(archivo.toURI().toString(),200,200,true,true,true);
                cache.put(ruta, img);//almacenamos la imagen 

            }else{

                img=ObtenerDefault();
            }

            return img;

        }catch(Exception e){
            return ObtenerDefault();
        }
    }






    //devuelve la variable unica de la portada el disco
    public static Image ObtenerDefault(){
        
        if(imgDefault==null){
            
            imgDefault=new Image(CacheImagenes.class.getResourceAsStream("/resources/imagenes/disco-vinilo.png"),200,200,true,true);
        }
        return imgDefault;
    }





//devuelve la variable estatica el corazon vacio
    public static Image ObtenerCorazon(){
    
        if(imgCorazon==null){
            
            imgCorazon= new Image(CacheImagenes.class.getResourceAsStream("/IconosFX/favorite.png"),20,20,true,true);
        }
        return imgCorazon;
    }





//devuelve la variable estatica de corazon relleno
    public static Image ObtenerCorazonCheck(){
    if (imgCorazonCheck==null) {
            imgCorazonCheck =new Image(CacheImagenes.class.getResourceAsStream("/IconosFX/addfavorite.png"),20,20,true,true);
        }
        return imgCorazonCheck;
    }

    //vacia el hash eliminando todas sus instancias para que el recolector los limpie
    public static void Limpiar(){

        cache.clear();
        
}
    // el tamaño 
    public static int getCache(){
            
            return cache.size();
    }
}


