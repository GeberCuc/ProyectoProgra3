package proyectospotify;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;



public class CargarCanciones {
    
    
    public CargarCanciones(){}
    
    
    
    public String Canciones(String Ruta,MetodosABB Arbol,MetodosAVL ArbolAvl){
        StringBuilder mensaje =new StringBuilder();
    try{
        
        File Carpeta=new File(Ruta);
        
        if(!Carpeta.exists()){
            
            return "No existe";
        }
        
        if(!Carpeta.isDirectory()){
            
            return"No es una carpeta";
        }
        
        File[] Archivo=Carpeta.listFiles();
        
        if(Archivo==null||Archivo.length==0){
            
            return "Carpeta vacia";
        }
        
        
        File carpetaImagenes=new File("imagenes");
        
            if(!carpetaImagenes.exists()){
                carpetaImagenes.mkdir();
            }
        
        for(File Archivos: Archivo ){
            if(Archivos.isFile()){
                
                String NombreArchivo=Archivos.getName();
                
                if(NombreArchivo.toLowerCase().endsWith(".mp3")){
                    
                    
                    try{
                        Mp3File mp3=new Mp3File(Archivos.getAbsolutePath());
                        
                        String Nombre=NombreArchivo.replace(".mp3","");
                        
                        String Artista="Desconocido";
                        
                        String Album="Desconocido";
                        
                        String Genero="Desconocido";
                        
                        String Imagen="";
                        
                        int año=0;
                        
                        if(mp3.hasId3v2Tag()){
                            
                            
                            
                            ID3v2 data=mp3.getId3v2Tag();
                            
                            if(data.getTitle()!=null){
                                
                                Nombre=data.getTitle();
                            }
                            
                            if(data.getArtist()!=null){
                                Artista=data.getArtist();
                                
                            }
                            
                            if(data.getAlbum()!=null){
                                Album=data.getAlbum();
                            }
                            
                            if(data.getGenreDescription()!=null){
                                
                                Genero=data.getGenreDescription();
                                
                            }
                            
                           byte [] ImagenBT=data.getAlbumImage();
                           
                           if(ImagenBT!=null){
                               
                               
                               String NombreImagen=Nombre.replaceAll("[^a-zA-Z0-9]","_")+".jpg";
                               
                               String ruta="imagenes/"+NombreImagen;
                               
                                try (FileOutputStream Im = new FileOutputStream(ruta)) {
                                    Im.write(ImagenBT);
                                }
                               
                               Imagen=ruta;
                           }
                           
                           if(data.getYear()!=null){
                               try{
                                   
                                   año=Integer.parseInt(data.getYear());
                               }catch(NumberFormatException e){
                                   año=0;
                               }
                           }
                        }else if(mp3.hasId3v1Tag()){
                            
                            ID3v1 data=mp3.getId3v1Tag();
                            
                            if(data.getTitle()!=null){
                                
                                Nombre=data.getTitle();
                            }
                            
                            if(data.getArtist()!=null){
                                Artista=data.getArtist();
                                
                            }
                            
                            if(data.getAlbum()!=null){
                                Album=data.getAlbum();
                            }
                            
                            if(data.getGenreDescription()!=null){
                                
                                Genero=data.getGenreDescription();
                                
                            }
                            if(data.getYear()!=null){
                               try{
                                   
                                   año=Integer.parseInt(data.getYear());
                               }catch(NumberFormatException e){
                                   año=0;
                               }
                           }
                              
                        }
                        
                       long Segundos=mp3.getLengthInSeconds();
                        
                       long Minutos=Segundos/60;
                       
                       long restante=Segundos%60;
                       
                       String Duracion=Minutos+":"+String.format("%02d", restante);
                       
                       
                       double Tamaño=Archivos.length()/(1024.0*1024.0);
                       
                       String TamañoTX=String.format("%.2f MB",Tamaño);
                       
                       
                        Archivomp3 Cancion=new Archivomp3(Nombre,Artista,Album,Genero,Imagen,Archivos.getAbsolutePath(),Duracion,TamañoTX,año);
                       
                        Arbol.Insertar(Cancion);
                        ArbolAvl.Insertar(Cancion);
                        
                        
                        
                        mensaje.append(Nombre).append(": Cargado Correctamente.\n");
                        
                        
                        
                    }catch(InvalidDataException | UnsupportedTagException | IOException e){
                        
                        mensaje.append("Error en el archivo: ").append(Archivos.getName()).append("\n");
                      
                    }
                    
                    
                }
                
            }
            
        }
        
    }catch(Exception e){
        mensaje.append("Error al Cargar Archivos");
        
    }
    
    if(mensaje.length()==0){
    return "No se encontraron archivos mp3";
}
  return mensaje.toString();
}
  
    
}
