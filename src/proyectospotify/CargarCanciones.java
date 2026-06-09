package proyectospotify;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import java.io.File;
import java.io.FileOutputStream;




public class CargarCanciones{
    
    int i=0;
    private static final String RUTA_IMAGEN_DEFECTO="/resources/imagenes/disco-vinilo.png";
    public CargarCanciones(){}
    
    
    

    
    
 public String Canciones(String Ruta,MetodosABB Arbol,MetodosAVL ArbolAvl,Playlist Play){
    i=0;

    try{
        File Carpeta=new File(Ruta);

        if(!Carpeta.exists()){
            return "No existe la ruta";
        
        }
        if(!Carpeta.isDirectory()){
            
            return "No es una carpeta";
        }

        File[]ArchivosLista= Carpeta.listFiles();

        if(ArchivosLista==null||ArchivosLista.length==0){
            return "Carpeta vacia";
            
            
        }

        File carpetaImagenes=new File("imagenes");

        if(!carpetaImagenes.exists()){
            carpetaImagenes.mkdir();
        }

        Subcarpetas(Carpeta,carpetaImagenes,Arbol, ArbolAvl,Play);
        
        return "Canciones Cargadas: " + i;

    } catch(Exception e){
        return "Error: " + e.getMessage();
    }
}

 
 
 
private void Subcarpetas(File carpeta,File CarpetaImagenes,MetodosABB arbolabb,MetodosAVL arbolavl, Playlist play){
   
    File[] Archivos=carpeta.listFiles();

    if(Archivos==null){
        return;
    }

    for(File archivo :Archivos){
        
        if(archivo.isDirectory()){
            Subcarpetas(archivo,CarpetaImagenes,arbolabb,arbolavl,play);
        } 
      
        else if(archivo.isFile() && archivo.getName().toLowerCase().endsWith(".mp3")){
            
            Mp3File mp3=null;
            byte[] Imagenbyte=null;
            
            try{
                
                mp3=new Mp3File(archivo.getAbsolutePath());

                String nombre=archivo.getName().replace(".mp3","");
                String artista="Desconocido";
                String album="Desconocido";
                String genero="Desconocido";
                String rutaImagen=RUTA_IMAGEN_DEFECTO;
                int anio=0;

  
                if(mp3.hasId3v2Tag()){
                    ID3v2 data=mp3.getId3v2Tag();
                    if(data.getTitle()!=null) {
                        nombre=data.getTitle();
                    }
                    if(data.getArtist()!=null) {
                        artista=data.getArtist();
                    }
                    if(data.getAlbum()!=null){
                        album=data.getAlbum();
                    }
                    if(data.getGenreDescription()!=null){
                        genero=data.getGenreDescription();
                    }

                    if(data.getYear()!=null){
                        try{
                            anio=Integer.parseInt(data.getYear());
                        } catch(Exception e){
                            anio=0;
                        }
                    }

                    Imagenbyte=data.getAlbumImage();
                    
                    if(Imagenbyte!=null && Imagenbyte.length > 0){
                        String rutaE=data.getAlbumImageMimeType();
                        String extencion=".jpg";
                        if(rutaE!=null){
                            if(rutaE.contains(".png")){
                                extencion=".png";
                            }else if(rutaE.contains("gif")){
                                extencion=".gif";
                            }
                        }
                        
                        String nom=nombre.replaceAll("[^a-zA-Z0-9]", "_")+extencion;
                        File Img=new File(CarpetaImagenes,nom);

                        try(FileOutputStream Ar=new FileOutputStream(Img)){
                            Ar.write(Imagenbyte);
                        }
                        rutaImagen=Img.getAbsolutePath();
                    }
                } 
     
                else if(mp3.hasId3v1Tag()){
                    ID3v1 datos=mp3.getId3v1Tag();
                    if(datos.getTitle()!=null){
                        nombre=datos.getTitle();
                    }
                    if(datos.getArtist()!=null){
                        artista=datos.getArtist();
                    }
                    if(datos.getAlbum()!=null){
                        album=datos.getAlbum();
                    }
                }

              
                long Segundos=mp3.getLengthInSeconds();
                if(Segundos<=0){
                    Segundos=0;
                }
                
                String duracion;
                long horas=Segundos/3600;
                long minutos=(Segundos%3600)/60;
                long segs=Segundos%60;

                if(horas>0){
                    duracion=String.format("%d:%02d:%02d",horas,minutos,segs);
                }else{
                    duracion=String.format("%d:%02d",minutos,segs);
                }

                String tam=String.format("%.2f MB",archivo.length()/(1024.0*1024.0));

                Archivomp3 nuevo=new Archivomp3(nombre,artista,album,genero,rutaImagen,archivo.getAbsolutePath(),duracion,tam,anio);

                arbolabb.Insertar(nuevo);
                arbolavl.Insertar(nuevo);
                play.InsertarFin(nuevo);

                i++;

            }catch(Exception e){ 
                System.out.println("Error en: "+archivo.getName()+" - "+e.getMessage());
            }finally{
                mp3=null;
                Imagenbyte=null;
            }
        }
    }
}
}
