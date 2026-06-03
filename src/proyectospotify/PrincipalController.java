
package proyectospotify;

import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.DirectoryChooser;
import javafx.util.Duration;







public class PrincipalController implements Initializable {
    

//imagens
private Image PlayImagen=new Image("/IconosFX/play.png");
private Image PauseImagen=new Image("/IconosFX/pause.png");
private ImageView Icono=new ImageView();
@FXML
private ImageView Imgbotm;  

//globales

private Timeline Animacion;
private double fase=0;
private double VolumenI=0.5;
private BooleanProperty CrearP=new SimpleBooleanProperty(false);
private boolean EliminadoB=false;
private boolean reproduciendo=false;
private boolean modoCola=false;
private boolean modoCircular=false;
private boolean modoAleatorio=false;
private NodoDoble Actual=null;
private Playlist PlaylistActual;
private MediaPlayer reproductor;
private Archivomp3 CancionActual;
ImportarFx Import=new ImportarFx();
private MetodoCola ColaReproduccion=new MetodoCola();
private Playlist favoritos= new Playlist("Favoritos");

private MetodoPila Historial=new MetodoPila();
private Archivomp3 ultimo=null;

private ObservableList<Playlist> playlists=FXCollections.observableArrayList();
private ObservableList<Archivomp3>DatosBiblioteca=FXCollections.observableArrayList();

private javafx.collections.transformation.FilteredList<Archivomp3> BibliotecaCentral;

 //Paneles
@FXML
private AnchorPane PanelInicio;
@FXML
private AnchorPane PanelBuscar;
@FXML
private AnchorPane PanelBiblioteca;
@FXML
private AnchorPane PanelPlaylist;
@FXML
private StackPane  PanePortada;
@FXML
private StackPane Stak;

//TextFields

@FXML
private TextField BuscadorPB;
@FXML
private TextField BuscadorPB1;


//Labels
@FXML
private Label Estado;
@FXML
private Label Can;
@FXML
private Label Art;
@FXML
private Label Alb;
@FXML
private Label Gen;
@FXML
private Label Anio;
@FXML
private Label TxtV;
@FXML
private Label CantidadM ;
@FXML
private Label DuracionP ;
@FXML
private Label AutorP;
@FXML
private Label TiempoAVL;
@FXML
private Label TiempoABB;
@FXML 
private Label TiempoF,TiempoR;


//slider
@FXML
private Slider Progreso;
@FXML
private Slider Volumen;

//Listas
@FXML
private ListView<Playlist> ListaPlaylist;
@FXML
private ListView<Archivomp3> ListaCanciones;
@FXML
private ListView<Archivomp3>ListaVBIblioteca;
@FXML
private ListView<Archivomp3>ListBusquedaParcial;

@FXML
private ListView<Archivomp3>Listahistorial;


//Scroll
@FXML
private ScrollPane ScrollG;
@FXML
private ScrollPane ScrollA;
//Botones
@FXML
private Button Play;
@FXML
private Button Siguiente;
@FXML
private Button Anterior;
@FXML
private Button ReproducirP;
@FXML
private Button AniadirP;
@FXML
private Button EliminarE;
@FXML
private Button CrearPT;

//ComboBox

@FXML
private ComboBox orden;

//HBox
@FXML
private HBox ContenedorAL;
@FXML
private HBox Recomendados;
@FXML
private HBox Artistas;




 @FXML
    public void MostrarInicio(){

        PanelInicio.setVisible(true);
        PanelInicio.setManaged(true);

        PanelBuscar.setVisible(false);
        PanelBuscar.setManaged(false);

        PanelBiblioteca.setVisible(false);
        PanelBiblioteca.setManaged(false);

        PanelPlaylist.setVisible(false);
        PanelPlaylist.setManaged(false);
    }



 @FXML
    public void MostrarBuscar(){

        PanelInicio.setVisible(false);
        PanelInicio.setManaged(false);

        PanelBuscar.setVisible(true);
        PanelBuscar.setManaged(true);

        PanelBiblioteca.setVisible(false);
        PanelBiblioteca.setManaged(false);

        PanelPlaylist.setVisible(false);
        PanelPlaylist.setManaged(false);
    }

    @FXML
    public void MostrarBiblioteca(){

        PanelInicio.setVisible(false);
        PanelInicio.setManaged(false);

        PanelBuscar.setVisible(false);
        PanelBuscar.setManaged(false);

        PanelBiblioteca.setVisible(true);
        PanelBiblioteca.setManaged(true);

        PanelPlaylist.setVisible(false);
        PanelPlaylist.setManaged(false);
    }

    @FXML
    public void MostrarPlaylis(){

        PanelInicio.setVisible(false);
        PanelInicio.setManaged(false);

        PanelBuscar.setVisible(false);
        PanelBuscar.setManaged(false);

        PanelBiblioteca.setVisible(false);
        PanelBiblioteca.setManaged(false);

        PanelPlaylist.setVisible(true);
        PanelPlaylist.setManaged(true);
    }

    
    
    
    
   public void Favoritos(){
       
     favoritos.Vaciar();
//pendiente
    Playlist canciones=Import.getAVL().InOrden();

    NodoDoble nodito=canciones.getInicio();
    
    while(nodito!=null){

        if(nodito.getCancion().isFav()){
            if(favoritos.BuscarCancion(nodito.getCancion())==null)
            favoritos.InsertarFin(nodito.getCancion());
        }
        nodito=nodito.Siguiente;
    }
       if(!ListaPlaylist.getItems().contains(favoritos)){
           
           ListaPlaylist.getItems().add(favoritos);
    }
       ListaPlaylist.refresh();
       
   }
    
  
   
   
   @FXML
   public void Buscar(){
       
      String Buscado=BuscadorPB1.getText();
    
     if(Buscado.isEmpty()){
     
         BibliotecaCentral.setPredicate(valido-> true);
         return;
     }
      
     Archivomp3 Resultado=Import.getAVL().Buscar(Buscado);
     
      long tiempo=Import.getAVL().Tiempo(Buscado);
      long tiempoABB=Import.getABB().tiempo(Buscado);
 
       if(Resultado!=null){
           BibliotecaCentral.setPredicate(Cancion-> Cancion.getAudio().equals(Resultado.getAudio()));
           TiempoAVL.setText("Encontrado AVL: "+tiempo+" ns");
           TiempoABB.setText("Encontrado ABB: "+tiempoABB+" ns");
       }else{
           
           BibliotecaCentral.setPredicate(Cancion->false);
           TiempoAVL.setText("No encontrado AVL: "+tiempo+" ns");
           TiempoABB.setText("No encontrado ABB: "+tiempoABB+" ns");
       }
     
   }
        
   @FXML
   public void GenerosM(){
       
       
       
       for(javafx.scene.Node nodo : ContenedorAL.getChildren()){
        if(nodo instanceof VBox){
            VBox tarjetaVieja=(VBox)nodo;
            if (!tarjetaVieja.getChildren().isEmpty()&&tarjetaVieja.getChildren().get(0)instanceof ImageView){
                ImageView iv=(ImageView)tarjetaVieja.getChildren().get(0);
                iv.setImage(null);
            }
        }
    }
       
       
       ContenedorAL.getChildren().clear();
       
       
       HashMap<String,ArrayList<Archivomp3>> Generos=new HashMap<>();   
      ArrayList<Archivomp3>canciones=Import.getAVL().PostOrden(); 
      for(Archivomp3 can:canciones){
          
          String genero=can.getGenero();
           if(genero==null||genero.isEmpty()){
               
           genero="Desconocido";
           }
       if(!Generos.containsKey(genero)){
           Generos.put(genero, new ArrayList<>());
       }
      Generos.get(genero).add(can);    
      }
      for(String genero:Generos.keySet()){
          
          ArrayList<Archivomp3> lista=Generos.get(genero);
          
          VBox tarjetas=Crear(genero,lista);
          
          ContenedorAL.getChildren().add(tarjetas);
      }
     
   }
   
    
   public VBox Crear(String genero,ArrayList<Archivomp3> canciones){


       VBox tarjeta=new VBox(10);
       
       tarjeta.getStyleClass().addAll("music");
       
       ImageView imagen=new ImageView();
       
       imagen.setFitHeight(85);
       imagen.setFitWidth(85);
       
       
       if(!canciones.isEmpty()){
           
           imagen.setImage(CacheImagenes.ObtenerImagen(canciones.get(0).getImagen()));
           
       }
       Label generoT=new Label(genero);
       Label Cantidad= new Label(canciones.size()+" Canciones");
       
       generoT.getStyleClass().add("letrita");
       Cantidad.getStyleClass().add("letrita");
       
       tarjeta.getChildren().addAll(imagen,generoT,Cantidad);

       tarjeta.setOnMouseClicked(eventito->{
           
           
           ListaCanciones.getItems().setAll(canciones);
           PlaylistActual=new Playlist(genero);
           
           for(Archivomp3 musica:canciones){
               
               PlaylistActual.InsertarFin(musica);
               
           }
           
           MostrarPlaylis();
           
       });
   return tarjeta; 
   }

   public void RecomendaG(){
       for(javafx.scene.Node nodo :Recomendados.getChildren()){
        if(nodo instanceof VBox){
            VBox tarjetaVieja=(VBox)nodo;
            if (!tarjetaVieja.getChildren().isEmpty()&&tarjetaVieja.getChildren().get(0)instanceof ImageView){
                ImageView iv=(ImageView)tarjetaVieja.getChildren().get(0);
                iv.setImage(null);
            }
        }
    }
       Recomendados.getChildren().clear();
       
       Playlist Canciones=Import.getAVL().InOrden();
       
       if(Canciones.vacio()){
           return;
       }
       
       NodoDoble si=Canciones.getInicio();
       int tam=0;
       while(si!=null&&tam<10){
           
           Archivomp3 Musica=si.getCancion();
           
           VBox tarjeta=TarjetaR(Musica);
           
           Recomendados.getChildren().add(tarjeta);
           
           si=si.Siguiente;
           tam++;
       }
       
   }
   
   public VBox TarjetaR(Archivomp3 Cancion){
       
       VBox tarjetica= new VBox(12);
       
      
       tarjetica.getStyleClass().addAll("music");
        
       tarjetica.setPrefWidth(80);
       tarjetica.setPrefHeight(180);
       
       ImageView portada= new ImageView();
       
       portada.setFitHeight(100);
       portada.setFitWidth(100);
       
       
       if(Cancion.getImagen()!=null){
           
           portada.setImage(CacheImagenes.ObtenerImagen(Cancion.getImagen()));
           
       }
       Label letrica=new Label(Cancion.getNombre());
       Label letrica2=new Label(Cancion.getArtista());
       letrica.getStyleClass().addAll("letrita");
       letrica2.getStyleClass().addAll("letrita");
       tarjetica.getChildren().addAll(portada,letrica,letrica2);
       
       
       
       tarjetica.setOnMouseClicked(evento->{
           
           CancionActual=Cancion;
           ReproducirCancion(Cancion);
           
       });
       

       return tarjetica;
   }
   
   
   public void ArtistasI(){
       
       for(javafx.scene.Node nodo:Artistas.getChildren()){
        if(nodo instanceof VBox){
            VBox tarjetaVieja=(VBox)nodo;
            if (!tarjetaVieja.getChildren().isEmpty()&&tarjetaVieja.getChildren().get(0)instanceof ImageView){
                ImageView iv=(ImageView)tarjetaVieja.getChildren().get(0);
                iv.setImage(null);
            }
        }
    }
       
       
       
       
       Artistas.getChildren().clear();
       HashMap<String,ArrayList<Archivomp3>> Arti= new HashMap<>();
       
       ArrayList<Archivomp3> info=Import.getAVL().PostOrden();
       
       
       for(Archivomp3 art:info){
           String Artis=art.getArtista();
           if(Artis==null||Artis.isEmpty()){
               Artis="Desconocido";
           }
           if(!Arti.containsKey(Artis)){
               Arti.put(Artis, new ArrayList());
               
           }
           Arti.get(Artis).add(art);
       }
       
       for(String art:Arti.keySet()){
           
           ArrayList<Archivomp3> lista=Arti.get(art);
           VBox tarjetaA=TarjetaA(art,lista);
           
           Artistas.getChildren().add(tarjetaA);
       }
      
       
   
       }
   
  public VBox TarjetaA(String artista,ArrayList<Archivomp3>art ){
       VBox tarjeta=new VBox(10);
       
       tarjeta.getStyleClass().addAll("music");
       
       ImageView imagen=new ImageView();
       
       imagen.setFitHeight(100);
       imagen.setFitWidth(100);
       
       if(!art.isEmpty()){
           imagen.setImage(CacheImagenes.ObtenerImagen(art.get(0).getImagen()));
           
       }
       
       Label lab=new Label(artista);
       
       lab.getStyleClass().addAll("letrita");
       
       tarjeta.getChildren().addAll(imagen,lab);
       
       tarjeta.setOnMouseClicked(evento->{
           
           ListaCanciones.getItems().setAll(art);
           PlaylistActual =new Playlist(artista);
           
           for(Archivomp3 cancion: art){
               if(cancion.getArtista().equalsIgnoreCase(artista)){
                   PlaylistActual.InsertarFin(cancion);
               }
               
           }
           MostrarPlaylis();
       });
       
       return tarjeta;
   }
   
    
 @FXML
public void CrearPlaylist(){

    
    TextInputDialog dialogo=new TextInputDialog();
    
    
    dialogo.setTitle("Nueva Playlist");
    dialogo.setHeaderText(null);
    
    
    dialogo.setContentText("Nombre:" );

    
    Optional<String>resultado=dialogo.showAndWait();

    if(resultado.isPresent()){
        
        String nom=resultado.get();

        if(!nom.isEmpty()){
            
            
            Playlist nuevo=new Playlist(nom);
            playlists.add(nuevo);
        }
    }
}
        
    
    
    
    
//busquedas artistas y genero por tablas hash 
@FXML//pendiente 
public void ImportarMusica(){

    
    DirectoryChooser Selector=new DirectoryChooser();

    
    Selector.setTitle("Seleccionar Carpeta");
    File Carpeta=Selector.showDialog(null);

    
    
    if(Carpeta!=null){
        
        Playlist NPlaylist=Import.Importar(Carpeta.getAbsolutePath(),Carpeta.getName());
        boolean duplicado=false;
        
        
        for(Playlist p: playlists){
            
            if(p.getNombre().equalsIgnoreCase(NPlaylist.getNombre())){
                duplicado=true;
                break;
            }
        }
        
        if(!duplicado){
            playlists.add(NPlaylist);
            
        }
       
        
        ActualizarBiblioteca();
        
        GenerosM();
        RecomendaG();
        ArtistasI();
        Alert alerta=new Alert(Alert.AlertType.INFORMATION);
        
        alerta.setHeaderText(null);
        alerta.setContentText("Carpeta "+Carpeta.getName()+" Cargada con exito");
        alerta.showAndWait();
    }
}





private void AnimacionS(StackPane H){
  

    if (Animacion != null) {
    Animacion.stop();

    }


    DropShadow sombra=new DropShadow();
    sombra.setSpread(0.05);
    sombra.setOffsetY(15);
    H.setEffect(sombra);
    
    int r=30;
    int g=215;
    int b=96;


    Animacion=new Timeline(new KeyFrame(Duration.millis(16),evento->{

        fase+=0.006;

        double opacidadSombra=0.50 + 0.15 * Math.sin(fase);

        sombra.setColor(Color.rgb(r,g,b,opacidadSombra));

        sombra.setRadius(160+40*Math.sin(fase));

        sombra.setOffsetY(20+8*Math.sin(fase));
    })
);


    Animacion.setCycleCount(Timeline.INDEFINITE);
    Animacion.play();
}




private void Colores(Archivomp3 si){
    
    
    if(si==null||si.getNombre()==null){
        return;
    }
    
    PanePortada.getStyleClass().clear();
    
   
    PanePortada.getStyleClass().addAll("foto","portada");
    
    int ColorR=Math.abs(si.getNombre().hashCode())%3;
    
    switch(ColorR){
        case 0:PanePortada.getStyleClass().add("portada-verde");
            Can.setStyle("-fx-text-fill: #1DB954; -fx-font-weight: bold;");
            break;
            
        case 1:
            PanePortada.getStyleClass().add("portada-azul");
            Can.setStyle("-fx-text-fill: #00D2FF; -fx-font-weight: bold;");
            break;
            
        case 2: 
            PanePortada.getStyleClass().add("portada-magenta");
            Can.setStyle("-fx-text-fill: #E91E63; -fx-font-weight: bold;");
            break;
        
    }
}



 @FXML
 public void ReproducirCancion(Archivomp3 Cancion){

     
    try{

        if(reproductor!=null){
               
            reproductor.setOnEndOfMedia(null);
            reproductor.stop();
            
            reproductor.dispose();
        }

        Media Musica=new Media(new File(Cancion.getAudio()).toURI().toString());
      
        reproductor=new MediaPlayer(Musica);
            


        reproductor.setOnReady(()->{

           reproductor.setVolume(VolumenI);
           
           
            double total=reproductor.getTotalDuration().toSeconds();
            
            Progreso.setMax(total);
            
            reproductor.play();
             
            
            int minutos=(int)total/60;
            int segundos=(int)total%60;
            
            TiempoR.setText(String.format("%d:%02d",minutos,segundos));
            
            AnimacionS(Stak);
            reproduciendo=true;
  
            Icono.setImage(PauseImagen);
        });
        
        
        
        reproductor.currentTimeProperty().addListener((espiado, antes,actual)->{
            
            if(!Progreso.isValueChanging()){
                
                double Tactual=actual.toSeconds();
                Progreso.setValue(Tactual);
                
                int minutosA=(int)Tactual/60;
                int segundosA=(int)Tactual%60;
                
                TiempoF.setText(String.format("%d:%02d",minutosA,segundosA));
        }    
        });
        
        CancionActual=Cancion;
        
       
        
        if (ultimo==null||!ultimo.getAudio().equals(Cancion.getAudio())){
            Historial.Push(Cancion);
            ultimo=Cancion;
        
        }
        
        Platform.runLater(()->{
            
                   try{
                       
                       this.Imgbotm.setImage(null);
                       String rutaImagenCancion=Cancion.getImagen(); 
                       Image ImgCancion=CacheImagenes.ObtenerImagen(rutaImagenCancion);
                       
                       this.Imgbotm.setImage(ImgCancion);
                       
                       Can.setText("Nombre: "+Cancion.getNombre());
                       Art.setText("Artista: "+Cancion.getArtista());
                       Alb.setText("Album: "+Cancion.getAlbum());
                       Gen.setText("Genero: "+Cancion.getGenero());
                       Anio.setText("Año: "+Cancion.getAño());
                       
                      


                       Bounds yaporfas=Imgbotm.getLayoutBounds();
                       
                       Rectangle yaquequedeno=new Rectangle(yaporfas.getWidth(),yaporfas.getHeight());
   
    
                       yaquequedeno.setArcWidth(30);yaquequedeno.setArcHeight(30);
                       Imgbotm.setClip(yaquequedeno);

                       Colores(Cancion);
                   } 
                   catch (Exception e){
                   }

        
            
        });
             
            reproductor.setOnEndOfMedia(()->{

    if(modoCola){

        AvanzarCola();
        
    }else if(modoCircular){
               
        Siguiente();
     
    }else{

        
        Siguiente();
    }

            });
            Histtt();

    }catch(Exception e){
        Estado.setText("Error al reproducir");
    }
}

 
 @FXML
 public void AvanzarCola(){
    
Archivomp3 siguiente =ColaReproduccion.Salida();

    if (siguiente!=null) {
        ReproducirCancion(siguiente);
    } else {
   
        modoCola=false; 
        Estado.setText("Fin de la cola de reproduccion");
       
    }
}
 
 
 

@FXML
public void Play(){

        try{
        if(CancionActual==null){
            Estado.setText("Seleccione una cancion");
            return;
        }

        
        if(!reproduciendo){

            if(reproductor==null){
                 AnimacionS(Stak);

                ReproducirCancion(CancionActual);

            }else{
                reproductor.play();
                AnimacionS(Stak);
            }

            Icono.setImage(PauseImagen);
            reproduciendo=true;

        }else{

            reproductor.pause();
            if(Animacion!=null){
                Animacion.pause();
                
            }
            Icono.setImage(PlayImagen);
            reproduciendo=false;
        }

    }catch(Exception e){
        Estado.setText("Error Play");
            System.out.println(e);
    }
}



@FXML
public void ReproducirPlaylist(){

    
    if(PlaylistActual==null){

        return;
    }

    modoCola=true;

    ColaReproduccion=new MetodoCola();

  
    NodoDoble nodito=PlaylistActual.getInicio();
        while(nodito!=null){
        ColaReproduccion.Entrante(nodito.getCancion());
        nodito=nodito.Siguiente;
        
        }

    Archivomp3 primera=ColaReproduccion.Salida();
    if(primera!=null){

        ReproducirCancion(primera);
    }
}
@FXML
public void averquepsa(){
    modoAleatorio=!modoAleatorio;
    
    if(modoAleatorio){
        Estado.setText("Modo Aleatorio Activado");
        Aleatorio();
        
    }else{
        Estado.setText("Modo Aleatorio desactivado");
    }
    
    
}

@FXML
public void Aleatorio(){
    
    try{
        if(PlaylistActual==null||PlaylistActual.vacio()){
            Estado.setText("Seleccione una Playlist");
            return;
        }
   
    int tam=PlaylistActual.getSize();
   
    Random r=new Random();
    int aleatorio=r.nextInt(tam);
   
    NodoDoble actual=PlaylistActual.getFin();
    for(int i=0;i<aleatorio;i++){
        if(actual!=null){
            actual=actual.Anterior;
        }
        
    }
    if(actual!=null){
        Actual=actual;
        CancionActual=Actual.getCancion();
        
        modoCola=false;
        ReproducirCancion(CancionActual);
        
        ListaCanciones.getSelectionModel().select(CancionActual);
    }
    
   
    }catch(Exception e){
        Estado.setText("Error en el modo aleatorio");
    }
}

@FXML

public void Histtt(){
    try{
        if(Historial==null||Historial.vacio()){
            Listahistorial.getItems().clear();
            return;
        }
        MetodoPila pla=new MetodoPila();
        ArrayList<Archivomp3> lista=new ArrayList<>();
        
        while(!Historial.vacio()){
            
            Archivomp3 cancion=Historial.Pop();
            lista.add(cancion);
            pla.Push(cancion);
        }
        while(!pla.vacio()){
            Historial.Push(pla.Pop());
            
        }
        Platform.runLater(()->{
            Listahistorial.getItems().setAll(lista);
            Listahistorial.refresh();
        });
        
    }catch(Exception e){
        
    }
    
    
}


@FXML
public void repito(){
    modoCircular=!modoCircular;
    
    if(modoCircular){
        Estado.setText("Modo indinito Activado");
    }else{
        Estado.setText("Modo Infinito Desactivado");
    }
}


@FXML
public void pause(){
    
    
    if(reproductor!=null){
        
        reproductor.pause();
        
        if(Animacion!=null){
            
            Animacion.pause();
        }
    }
}


public void Stop(){
    
    
 if(reproductor!=null){

     
     
        reproductor.stop();

        reproductor.setOnEndOfMedia(null);
        reproductor.dispose();
        reproductor=null;
        reproduciendo=false;
        
        if(Animacion!=null){
            Animacion.pause();
        }
        
        modoCola=false;
        modoCircular=false; 
        modoAleatorio=false;
        
        Icono.setImage(PlayImagen);
        Estado.setText("Reproduccion detenida");
 }
}


@FXML
public void Siguiente(){
 
  try{
       if(modoCola){
            
        AvanzarCola();   
          return;
        }

       if(PlaylistActual==null||PlaylistActual.vacio()){
            return;
        }

       if(modoAleatorio){
           Aleatorio();
           return;
       }
       
        if(Actual==null){
            
            Actual=PlaylistActual.getInicio();
        }else{
            Actual=Actual.getSiguiente();
        
            if(Actual==null){
                if(modoCircular){
                    Actual=PlaylistActual.getInicio();
                }else{
                    
                    Stop();
                    Actual=null;
                    Estado.setText("Fin de la playlist");
                    return;
                }
            }
        }
        
        if(Actual!=null){
            Archivomp3 SiguienteC=Actual.getCancion();
            CancionActual=SiguienteC;
            ReproducirCancion(SiguienteC);
            
            Platform.runLater(()->ListaCanciones.getSelectionModel().select(SiguienteC));
            
        }
        
        
    }catch(Exception e){

         Estado.setText("Error siguiente");
    }
}
    

@FXML
public void Anterior(){

    try{

        if(modoCola){
            Estado.setText("Modo Cola Activado");
            return;
        }
        if(PlaylistActual==null||PlaylistActual.vacio()){

            return;
        }

       if(Actual==null){
           Actual=PlaylistActual.getInicio();
       }else{
           Actual=Actual.getAnterior();
       if(Actual==null){
           
           Actual=PlaylistActual.getFin();
        }
       
       }
        
       if(Actual!=null){
           Archivomp3 AnteriorC=Actual.getCancion();
           CancionActual=AnteriorC;
           ReproducirCancion(AnteriorC);
           
           Platform.runLater(()->ListaCanciones.getSelectionModel().select(AnteriorC));
       }
    }catch(Exception e){

         Estado.setText("Error anterior" );
    }
}


public void ActualizarBiblioteca(){
  

    for(Playlist lista:ListaPlaylist.getItems()){

        NodoDoble nodo=lista.getInicio();
        while(nodo!=null){

            Archivomp3 p=nodo.getCancion();
            if(Import.getAVL().Buscar(p.getAudio())==null){
                Import.getAVL().Insertar(p);
            }
             nodo=nodo.Siguiente;
        }
    }

    DatosBiblioteca.setAll( Import.getAVL().InordeP());         
    
}


@FXML
public void BusquedaP(){
    
    String Buscado=BuscadorPB.getText().trim();
    
    if(Buscado.isEmpty()){
        ListBusquedaParcial.getItems().setAll(Import.getAVL().PreOrden());
       return; 
    }
    
    Playlist ResultadosV=Import.getAVL().BusquedaParcial(Buscado);
    
    ListBusquedaParcial.getItems().clear();
    
    NodoDoble nod=ResultadosV.getInicio();
    while(nod!=null){
        
        ListBusquedaParcial.getItems().add(nod.getCancion());
        nod=nod.Siguiente;
        

    }
    
    ListBusquedaParcial.refresh();
}

@FXML
public void recorreCanciones(){

    if(PlaylistActual==null){

        Estado.setText("Seleccione una playlist primero");
        
        return;
    }

    boolean agregado=false;

    for(Archivomp3 cancion:DatosBiblioteca){

        if(cancion.isEstado()){
            if(PlaylistActual.BuscarCancion(cancion)==null){

                PlaylistActual.InsertarFin(cancion);
                agregado=true;
            }
            
            cancion.setEstado(false);
        }
    }

    ListaVBIblioteca.refresh();
    
    ListaCanciones.getItems().clear();
    NodoDoble todoNodo=PlaylistActual.getInicio();
    while(todoNodo!=null){
         ListaCanciones.getItems().add(todoNodo.getCancion());
         todoNodo=todoNodo.Siguiente;
    }
   
    if(agregado){

        Estado.setText("Canciones agregadas a "+PlaylistActual.getNombre());
        chek();

    }else{

        Estado.setText("No selecciono canciones");
    }
}


     
public void chek(){
       
  CrearP.set(!CrearP.get());


   ListaCanciones.refresh();
   ListaVBIblioteca.refresh();
   }

@FXML
@Override
    public void initialize(URL url, ResourceBundle rb) {
        
      
     MostrarInicio();
     ListaPlaylist.setItems(playlists);
     
     BibliotecaCentral= new javafx.collections.transformation.FilteredList<>(DatosBiblioteca, valido->true);
     ListaVBIblioteca.setItems(BibliotecaCentral);
     
     Icono.setImage(PlayImagen);
     Icono.setFitWidth(30);
     Icono.setFitHeight(23);
     Play.setGraphic(Icono);
     
     orden.getItems().addAll("A-Z","Fecha de publicacion","Invertido");
     
     orden.getSelectionModel().selectFirst();
     
     ListaPlaylist.getSelectionModel().selectedItemProperty().addListener((obs,anterior,actual)->{

    if(actual!=null){

        PlaylistActual=actual;
        
        ListaCanciones.getItems().clear();
        NodoDoble nodo=actual.getInicio();

        while(nodo!=null){
        ListaCanciones.getItems().add(nodo.getCancion());
        nodo=nodo.Siguiente;
        }
    }
});
  
   ListaVBIblioteca.setOnMouseClicked(evento ->{
   
   if(evento.getClickCount()==2){
       
       modoCola=false;
       
       Archivomp3 c=ListaVBIblioteca.getSelectionModel().getSelectedItem();
       
       if(c!=null){
           
           CancionActual=c;
         if(PlaylistActual!=null){
             
             Actual=PlaylistActual.BuscarCancion(c);
         }
         ReproducirCancion(c);
       }
       
   }
   
  
   
   });
   
   
   ListaCanciones.setOnMouseClicked(event->{

    if(event.getClickCount()==2){

        modoCola=false;
        
        Archivomp3 Cancion=ListaCanciones.getSelectionModel().getSelectedItem();

        if(Cancion!=null){

            CancionActual=Cancion;

            if(PlaylistActual!=null){
                Actual=PlaylistActual.BuscarCancion(Cancion);
            }

            ReproducirCancion(Cancion);
        }
    }
});
   
   ListBusquedaParcial.setOnMouseClicked(evento->{
           
           if(evento.getClickCount()==2){
               
            modoCola=false;
        
        Archivomp3 Cancion=ListBusquedaParcial.getSelectionModel().getSelectedItem();

        if(Cancion!=null){

            CancionActual=Cancion;

            if(PlaylistActual!=null){
                Actual=PlaylistActual.BuscarCancion(Cancion);
            }

            ReproducirCancion(Cancion);
        }
       
               
           }
           
           
           });
   
   
  ListaPlaylist.setCellFactory(parametros-> {
       
      
      return new ListCell<Playlist>(){
             
             private CheckBox Eliminado =new CheckBox();
             private TextField Editar= new TextField();
             private Label Nombre=new Label();
             private ImageView Imagen=new ImageView();
             
             private HBox Contenedor=new HBox(Eliminado,Imagen,Editar,Nombre);
             
             
             
             
             {
                 
                 
                 Eliminado.setVisible(EliminadoB);
                 setManaged(EliminadoB);
                 
                 
                 Imagen.setFitWidth(20);
                 Imagen.setFitHeight(20);
                 try{
                     
                     Image img=new Image( getClass().getResourceAsStream("/IconosFX/playlistList.png"));
                     Imagen.setImage(img);
                 }catch(Exception e){
                     
                 }
                 
                 
                 Editar.setVisible(false);
                 Editar.setManaged(false);
                 
                 
                 Nombre.setOnMouseClicked(e -> {
                     
                     if(e.getClickCount() == 2 &&
                             getItem() != null){
                         
                         
                         Editar.setText(
                                 getItem().getNombre()
                         );
                         
                         
                         Nombre.setVisible(false);
                         Nombre.setManaged(false);
                         
                         Editar.setVisible(true);
                         Editar.setManaged(true);
                         
                         Editar.requestFocus();
                     }
                 });
                 
                 
                 Editar.setOnAction(e -> {
                     
                     if(getItem()!=null){
                         
                         
                         getItem().setNombre(Editar.getText());
                         
                         
                         Nombre.setText(Editar.getText());
                         
                         
                         Editar.setVisible(false);
                         Editar.setManaged(false);
                         
                         Nombre.setVisible(true);
                         Nombre.setManaged(true);
                     }
                 });
             }
             
             @Override
             protected void updateItem(Playlist cositas,boolean tam){
                 
                 super.updateItem(cositas,tam);
                 
                 if(tam||cositas==null){
                     
                     setGraphic(null);
                     
                 }else{
                     
                     Nombre.setText(cositas.getNombre());
                     
                     setGraphic(Contenedor);
                 }
             }
             
         }; });
   
   
   
   
   
   ListaPlaylist.getSelectionModel().selectedItemProperty().addListener((espiado,antes,actual)->{

    if(actual!=null){
        Playlist seleccionada=ListaPlaylist.getSelectionModel().getSelectedItem();

        String inf=Import.Info(seleccionada);

            CantidadM.setText(inf);    
    }
});
   
   
   

ListaVBIblioteca.setCellFactory(parametros->new CancionCell(CrearP,()-> Favoritos()));
ListaCanciones.setCellFactory(parametro->new CancionCell(CrearP,()->Favoritos()));
ListBusquedaParcial.setCellFactory(Parametros->new CancionCell(CrearP,()->Favoritos()));
  
   
   Progreso.setOnMousePressed(e->{

    if(reproductor!=null){

        reproductor.seek(Duration.seconds(Progreso.getValue() ));
    }
});
    
   
   
   
   
   
Progreso.setOnMouseDragged(a-> {

    if(reproductor!=null){

        reproductor.seek(Duration.seconds(Progreso.getValue()
        ));
    }
});


Volumen.setMin(0);
Volumen.setMax(100);
Volumen.setValue(50);
 TxtV.setText(String.format("Volumen: 50"));

 Volumen.valueProperty().addListener((espiado,antes,actual)->{
           
   TxtV.setText(String.format("Volumen: %d",actual.intValue()));

    
    VolumenI=actual.doubleValue()/100;

    if(reproductor!=null){
            
        reproductor.setVolume(VolumenI);
    }
});
    

 
 BuscadorPB1.setOnKeyPressed(eventito->{
     
     if(eventito.getCode()==KeyCode.ENTER){
        
         Buscar();
     }
     
     
     
 });
 
 BuscadorPB.setOnKeyPressed(eventito->{
     
     if(eventito.getCode()==KeyCode.ENTER){
        
         BusquedaP();
     }
 
 });

 
 orden.valueProperty().addListener((espiado,antes,nuevo)->{
        
       if(nuevo==null||Import.getAVL()==null){
           return;
           
       } 
        
       ListaCanciones.getItems().clear();
       
       switch(nuevo.toString()){
            case "A-Z" ->{ DatosBiblioteca.setAll(Import.getAVL().InordeP());
            
            }
            case "Fecha de publicacion" -> {
               DatosBiblioteca.setAll(Import.getAVL().PreOrden());
                
             }
                
            case "Invertido" -> {
               DatosBiblioteca.setAll(Import.getAVL().PostOrden());
               
             }
        }
        
    });
 
 
 
 
 Listahistorial.setOnMouseClicked(eventop->{
     
     if(eventop.getClickCount()==2){
       Archivomp3 seleccionado= Listahistorial.getSelectionModel().getSelectedItem();
         if(seleccionado!=null){
             modoCola=false;
             ReproducirCancion(seleccionado);
         }
         
     }
     
     
 });
 
 
 ScrollG.setOnScroll(evento->{
     
    double scroil=evento.getDeltaY();
    double hoz=ScrollG.getHvalue();
    
    double velocidad=0.1;
    
    if(scroil<0){
        ScrollG.setHvalue(hoz+velocidad);
    }else if(scroil>0){
        ScrollG.setHvalue(hoz-velocidad);
    }
    
    evento.consume();
 });
 

 
 ScrollA.setOnScroll(eventito->{
     
     double y=eventito.getDeltaY();
     double ho=ScrollA.getHvalue();
     
     double vel=0.1;
     
     if(y<0){
         
         ScrollA.setHvalue(ho+vel);
     }else if(y>0){
         ScrollA.setHvalue(ho-vel);
     }
     eventito.consume();
 });
     
 
 
 
 
 ScrollG.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
 ScrollG.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
 ScrollG.setFitToHeight(true);
}
    
}