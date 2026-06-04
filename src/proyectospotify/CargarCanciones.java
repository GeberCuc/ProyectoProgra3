package proyectospotify;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import java.io.File;
import java.io.FileOutputStream;




public class CargarCanciones{
    
    int i=0;
    public CargarCanciones(){}
    
    
 public String Canciones(String Ruta, MetodosABB Arbol,MetodosAVL ArbolAvl,Playlist Play){
    i=0;
    StringBuilder mensaje=new StringBuilder();

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

        Subcarpetas(Carpeta,carpetaImagenes,Arbol, ArbolAvl,Play, mensaje);
        
        mensaje.append("Canciones Cargadas: ").append(i).append("\n");

    } catch(Exception e){
        return "Error: " + e.getMessage();
    }

    return mensaje.length()==0 ? "No se hallaron archivos mp3":mensaje.toString();
}

 
 
 
 
private void Subcarpetas(File carpeta,File CarpetaImagenes,MetodosABB arbolabb,MetodosAVL arbolavl, Playlist play,StringBuilder mensaje){
    File[] Archivos=carpeta.listFiles();

    if(Archivos==null){
        return;
    }

    for(File archivo :Archivos){
        
        if(archivo.isDirectory()){
            Subcarpetas(archivo,CarpetaImagenes,arbolabb,arbolavl,play,mensaje);
        } 
      
        else if(archivo.isFile() && archivo.getName().toLowerCase().endsWith(".mp3")){
            try{
                Mp3File mp3=new Mp3File(archivo.getAbsolutePath());

                String nombre=archivo.getName().replace(".mp3","");
                String artista="Desconocido";
                String album="Desconocido";
                String genero="Desconocido";
                String rutaImagen="/resources/imagenes/disco-vinilo.png";
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

                    byte[] Imagenbyte=data.getAlbumImage();
                    
                
                    if(Imagenbyte!=null){
                        
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
                String duracion=String.format("%d:%02d",Segundos/60,Segundos%60);
                String tam=String.format("%.2f MB",archivo.length()/(1024.0*1024.0));

                Archivomp3 nuevo=new Archivomp3(nombre,artista,album,genero,rutaImagen,archivo.getAbsolutePath(),duracion,tam,anio);

                arbolabb.Insertar(nuevo);
                arbolavl.Insertar(nuevo);
                play.InsertarFin(nuevo);

                i++;

               
            }catch(Exception e){ 
                mensaje.append("Error en: ").append(archivo.getName()).append(" - ").append(e.getMessage()).append("\n");
            }
        }
    }
}
 
}
