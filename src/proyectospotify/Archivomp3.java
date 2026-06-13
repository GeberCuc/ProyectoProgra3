
package proyectospotify;

public class Archivomp3 {

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getArtista() {
        return Artista;
    }

    public void setArtista(String Artista) {
        this.Artista = Artista;
    }

    public String getAlbum() {
        return Album;
    }

    public void setAlbum(String Album) {
        this.Album = Album;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String Genero) {
        this.Genero = Genero;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String Imagen) {
        this.Imagen = Imagen;
    }

    public String getAudio() {
        return Audio;
    }

    public void setAudio(String Audio) {
        this.Audio = Audio;
    }
        public String getDuracion() {
        return Duracion;
    }

    public void setDuracion(String Duracion) {
        this.Duracion = Duracion;
    }

    public String getTamaño() {
        return Tamaño;
    }

    public void setTamaño(String Tamaño) {
        this.Tamaño = Tamaño;
    }

    public int getAño() {
        return Año;
    }

    public void setAño(int Año) {
        this.Año = Año;
    }
    
    
    public boolean isEstado() {
        return Estado;
    }

    public void setEstado(boolean Estado) {
        this.Estado = Estado;
    }
    
    
      public boolean isFav() {
        return Fav;
    }

    public void setFav(boolean Fav) {
        this.Fav = Fav;
    }
    
    
    private String Nombre;
    private String Artista;
    private String Album;
    private String Genero;
    private String Imagen;
    private String Audio;


    private String Duracion;
    private String Tamaño;
    private int Año;

    
    private boolean Estado;

  /*
    Clase encargada de iniicializar un objeto con sus respectivos metadatps para cada canción 
    
    */
    private boolean Fav;
    public Archivomp3(String Nombre,String Artista,String Album ,String Genero,String Imagen ,String Audio,String Duracion,String Tamaño,int Año){
        
        this.Nombre=Nombre;
        this.Artista=Artista;
        this.Album=Album;
        this.Genero=Genero;
        this.Imagen=Imagen;
        this.Audio=Audio;
        this.Duracion=Duracion;
        this.Tamaño=Tamaño;
        this.Año=Año;
    }
    
    
 @Override
public String toString(){

    return Nombre;
}
}
