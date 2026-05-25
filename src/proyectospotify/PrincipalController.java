
package proyectospotify;

import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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
 MetodosAVL Arbol2= new MetodosAVL();

private double VolumenI=0.5;
private BooleanProperty CrearP=new SimpleBooleanProperty(false);
private boolean EliminadoB=false;
private boolean reproduciendo=false;
private boolean modoCola=false;
private NodoDoble Actual=null;
private Playlist PlaylistActual;
private MediaPlayer reproductor;
private Archivomp3 CancionActual;
ImportarFx Import=new ImportarFx();
private MetodoCola ColaReproduccion=new MetodoCola();
private Playlist favoritos= new Playlist("Favoritos");
private MetodoPila Historial=new MetodoPila();
private ObservableList<Playlist> playlists=FXCollections.observableArrayList();
private ObservableList<Archivomp3>DatosBiblioteca=FXCollections.observableArrayList();
 
 //Paneles
@FXML
private AnchorPane PanelInicio;
@FXML
private AnchorPane PanelBuscar;
@FXML
private AnchorPane PanelBiblioteca;
@FXML
private AnchorPane PanelPlaylist;


//TextFields

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
private ListView<Archivomp3>ListaAlbum;

//Scroll

@FXML
private ScrollPane ScrollG;
//Botones
@FXML
private Button Play;
@FXML
private Button Pausa;
@FXML
private Button Stop;
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

//HBox
@FXML
private HBox ContenedorAL;


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
         //pendiente
         ListaVBIblioteca.getItems().setAll(Arbol2.PostOrden());
     return;
     }
      
     Archivomp3 Resultado=Arbol2.Buscar(Buscado);
     
      long tiempo=Arbol2.Tiempo(Buscado);
      
       ListaVBIblioteca.getItems().clear();
      
       if(Resultado!=null){
           
           ListaVBIblioteca.getItems().add(Resultado);
           
           TiempoAVL.setText("Encontrado: "+tiempo+" ns");
       }else{
           
           TiempoAVL.setText("No encontrado: "+tiempo+" ns");
       }
     
   }
        
   @FXML
   public void GenerosM(){
       
       ContenedorAL.getChildren().clear();
       
       HashMap<String,ArrayList<Archivomp3>> Generos=new HashMap<>();
       //pendiente
      ArrayList<Archivomp3>canciones=Arbol2.PostOrden();
      
      
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
       
       
       
       
       tarjeta.getChildren().addAll(imagen,generoT,Cantidad);

       tarjeta.setOnMouseClicked(eventito->{
           
           
           ListaCanciones.getItems().setAll(canciones);
           ListaAlbum.getItems().setAll(canciones);
           PlaylistActual=new Playlist(genero);
           
           for(Archivomp3 musica:canciones){
               
               PlaylistActual.InsertarFin(musica);
               
           }
           
           
           
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
       
        Alert alerta=new Alert(Alert.AlertType.INFORMATION);
        
        alerta.setHeaderText(null);
        alerta.setContentText("Carpeta "+Carpeta.getName()+"Cargada con exito");
        alerta.showAndWait();
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
           
            Progreso.setMax(reproductor.getTotalDuration().toSeconds());reproductor.play();
            
            reproduciendo=true;
  
            Icono.setImage(PauseImagen);
        });
        
        
        
        reproductor.currentTimeProperty().addListener((espiado, antes,actual)->{
            
            if(!Progreso.isValueChanging()){
                
                Progreso.setValue(actual.toSeconds()); 
        }    
        });
        
        CancionActual=Cancion;
        
        Historial.Push(Cancion);
        
        Platform.runLater(()->{
            
                   try{
    
                       String rutaImagenCancion=Cancion.getImagen(); 
                       File archivoImagen=new File(rutaImagenCancion);

        
                       if(rutaImagenCancion!=null&&!rutaImagenCancion.isEmpty()&&archivoImagen.exists()){
            
                    
                           Image imgCancion=new Image(archivoImagen.toURI().toString());
                    
                           this.Imgbotm.setImage(imgCancion);
                    
                    
                
                       }else{
                
                    
                           InputStream pre=getClass().getResourceAsStream("/resources/imagenes/disco-vinilo.jpg");
                   
                           if(pre!=null){
                        
                               this.Imgbotm.setImage(new Image(pre));
                    
                           }
                    
                  
                    
                           Rectangle clip=new Rectangle( this.Imgbotm.getFitWidth(),this.Imgbotm.getFitHeight());
        
                           clip.setArcWidth(30);  
                           clip.setArcHeight(30);
                    
                           this.Imgbotm.setClip(clip);
                    
               
                       }
                   } 
                   catch (Exception e){
                   }

        
            Can.setText("Nombre: "+Cancion.getNombre());
            Art.setText("Artista: "+Cancion.getArtista());
            Alb.setText("Album: "+Cancion.getAlbum());
            Gen.setText("Genero: "+Cancion.getGenero());
            Anio.setText("Año: "+Cancion.getAño());
        });
             
            reproductor.setOnEndOfMedia(()->{

    if(modoCola){

        AvanzarCola();
        

    }else{

        Siguiente();
    }

            });

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

                ReproducirCancion(CancionActual);

            }else{
                reproductor.play(); 
            }

            Icono.setImage(PauseImagen);
            reproduciendo=true;

        }else{

            reproductor.pause();
            Icono.setImage(PlayImagen);
            reproduciendo=false;
        }

    }catch(Exception e){
        Estado.setText("Error Play");
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
public void pause(){
    
    
    if(reproductor!=null){
        
        
        reproductor.pause();
        
    }
}


public void Stop(){
    
    
 if(reproductor!=null){

     
     
        reproductor.stop();

        reproductor=null;

        
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

        if(Actual==null){
            Actual=PlaylistActual.getInicio();
        }else{
            Actual=Actual.getSiguiente();
        
            if(Actual==null){
                Actual=PlaylistActual.getInicio();
            }
        }
        
        if(Actual!=null){
            Archivomp3 SiguienteC=Actual.getCancion();
            CancionActual=SiguienteC;
            ReproducirCancion(SiguienteC);
            
            ListaCanciones.getSelectionModel().select(SiguienteC);
        }
        
        
    }catch(Exception e){

         Estado.setText("Error siguiente");
    }
}
    

@FXML
public void Anterior(){

    try{

        if(modoCola){

            if(Historial!=null&&!Historial.vacio()){
            Historial.Pop();
            Archivomp3 anterior=Historial.Pop();
            if(anterior!=null){
                ReproducirCancion(anterior);
                
            }
            
            }
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
           
           ListaCanciones.getSelectionModel().select(AnteriorC);
       }
    }catch(Exception e){

         Estado.setText("Error anterior" );
    }
}


public void ActualizarBiblioteca(){

    Arbol2=new MetodosAVL();

    for(Playlist lista:ListaPlaylist.getItems()){

        NodoDoble nodo=lista.getInicio();
        while(nodo!=null){

            Archivomp3 p=nodo.getCancion();
            if(Arbol2.Buscar(p.getAudio())==null){
                Arbol2.Insertar(p);
            }
             nodo=nodo.Siguiente;
        }
    }

    DatosBiblioteca.setAll( Arbol2.PostOrden());         
    ListaVBIblioteca.getItems().setAll(Arbol2.PreOrden());
}


@FXML
public void recorreCanciones(){

    if(PlaylistActual==null){

        Estado.setText("Seleccione una playlist primero");
        
        return;
    }

    boolean agregado=false;

    for(Archivomp3 cancion: ListaVBIblioteca.getItems()){

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

    Icono.setImage(PlayImagen);
    Icono.setFitWidth(30);
    Icono.setFitHeight(23);
    Play.setGraphic(Icono);

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
   
   if(evento.getClickCount()==1){
       
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
  
  
  
   
   Progreso.setOnMousePressed(e -> {

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
 
 
 

 ScrollG.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
 ScrollG.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
 ScrollG.setFitToHeight(true);
}
    
}