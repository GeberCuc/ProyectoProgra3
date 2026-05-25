package proyectospotify;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import java.io.File;
import java.io.FileOutputStream;




public class CargarCanciones{
    
    
    public CargarCanciones(){}
    
    
    
    public String Canciones(String Ruta,MetodosABB Arbol,MetodosAVL ArbolAvl,Playlist Play){
        
        
    StringBuilder mensaje=new StringBuilder();
    
    int i=0;
    try{
        File Carpeta=new File(Ruta);
        
        if(!Carpeta.exists()){
            
            return "No existe la ruta";
        }
        if(!Carpeta.isDirectory()){
            
            return "No es una carpeta";
        }

        File[] ArchivosLista=Carpeta.listFiles();
        
        if(ArchivosLista==null||ArchivosLista.length==0){
            return "Carpeta vacia";
        }

        
        File carpetaImagenes=new File("imagenes");
        
        if(!carpetaImagenes.exists())carpetaImagenes.mkdir();
        
        

        for(File archivo: ArchivosLista){
            if(archivo.isFile()&&archivo.getName().toLowerCase().endsWith(".mp3")){
               
                try{
                    Mp3File mp3=new Mp3File(archivo.getAbsolutePath());
                    
                    
                    
                    
                    String nombre=archivo.getName().replace(".mp3","");
                    String artista="Desconocido";
                    String album="Desconocido";
                    String genero="Desconocido";
                    String rutaImagen="/resources/imagenes/disco-vinilo.jpg";
                    int anio=0;

                
                    if(mp3.hasId3v2Tag()){
                        
                        ID3v2 data=mp3.getId3v2Tag();
                        if(data.getTitle()!=null){
                            
                            nombre=data.getTitle();
                        }
                        if(data.getArtist()!=null){
                            artista=data.getArtist();
                        
                        }
                        if(data.getAlbum()!= null){
                            
                            album=data.getAlbum();
                        }
                        if(data.getGenreDescription()!=null){
                            genero=data.getGenreDescription();
                        }
                        if(data.getYear()!=null){
                            try{
                                anio=Integer.parseInt(data.getYear()); 
                            } 
                            
                            catch (Exception e){
                                anio=0;
                            }
                        }

                    
                        byte[] imagenBytes=data.getAlbumImage();
                        if(imagenBytes!=null){
             
                            String nombreLimpio=nombre.replaceAll("[^a-zA-Z0-9]","_")+".jpg";
                            File salidaImagen=new File(carpetaImagenes,nombreLimpio);
                            
                            try(FileOutputStream fos=new FileOutputStream(salidaImagen)){
                                fos.write(imagenBytes);
                            }
                            
                            rutaImagen=salidaImagen.getAbsolutePath();
                        }
                    } 
                  
                    else if(mp3.hasId3v1Tag()){
                        ID3v1 data=mp3.getId3v1Tag();
                        if(data.getTitle()!=null)nombre=data.getTitle();
                        
                        if(data.getArtist()!= null) artista=data.getArtist();
                        if(data.getAlbum()!=null) album=data.getAlbum();
                    }

                    
                    long segundos=mp3.getLengthInSeconds();
                    
                    String duracion=String.format("%d:%02d",segundos/60,segundos%60);
                    String tamano=String.format("%.2f MB",archivo.length()/(1024.0*1024.0));

                  
                    Archivomp3 nuevaCancion=new Archivomp3(nombre,artista,album,genero,rutaImagen,archivo.getAbsolutePath(),duracion,tamano,anio);
                    
                    
                    Arbol.Insertar(nuevaCancion);
                    ArbolAvl.Insertar(nuevaCancion);
                    Play.InsertarFin(nuevaCancion);
                    
                    
                    
                       

                    i++;

                } catch (Exception e){
                    mensaje.append("Error en:").append(archivo.getName()).append("\n");
                }
            }
        }
        mensaje.append("Canciones subidas: ").append(i).append("\n");
    } catch (Exception e){
        return "Error critico: "+e.getMessage();
    }

    return mensaje.length()==0?"No se encontraron mp3":mensaje.toString();
}
  
    
}
