
package proyectospotify;

import java.io.BufferedReader;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
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
import javafx.stage.Popup;
import javafx.util.Duration;
import static javafx.util.Duration.minutes;







public class PrincipalController implements Initializable {
    

//imagens
private Image PlayImagen=new Image("/IconosFX/play.png");
private Image PauseImagen=new Image("/IconosFX/pause.png");
private ImageView Icono=new ImageView();
@FXML
private ImageView Imgbotm;  

//globales
@FXML
private Popup popupStats=new Popup();
Archivomp3 TOPUNO;
String tm;
EstadisticasTXT txt=new EstadisticasTXT();
private Timeline Animacion;
private ParallelTransition transicion;
private TranslateTransition Mov;
private FadeTransition Aparece;

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
boolean valido=false;
private javafx.beans.value.ChangeListener<javafx.util.Duration> progresoListener;

private MetodoPila Historial=new MetodoPila();
private Archivomp3 ultimo=null;
private double tim=0.0;
private ObservableList<Playlist> playlists=FXCollections.observableArrayList();
private ObservableList<Archivomp3>DatosBiblioteca=FXCollections.observableArrayList();
private List<Karaoke>Versos;
private javafx.collections.transformation.FilteredList<Archivomp3> BibliotecaCentral;
private ObservableList<Archivomp3>DatosPlaylistActual=FXCollections.observableArrayList();
//hashs
 HashMap<String,ArrayList<Archivomp3>> Generos=new HashMap<>(); 
 HashMap<String,ArrayList<Archivomp3>> Arti= new HashMap<>();

 

@FXML
private Circle Carga;
 
 
//Vbox
@FXML
private HBox ContenedorK;

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
@FXML 
private Pane PanelVersos;

//TextFields

@FXML
private TextField BuscadorPB;
@FXML
private TextField BuscadorPB1;
@FXML
private TextField BusquedaInicio;

//Labels
@FXML 
private Label LVersos;
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
private Label TiempoAVL1,TiempoABB1;
@FXML 
private Label TiempoF,TiempoR;
@FXML
private Label PlaylistANombre;


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
@FXML
private ScrollPane ScrollT;
@FXML
private ScrollPane ScrollH;
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
private ToggleButton Activador;
@FXML
private Button CrearPT;
@FXML
private Button BtD;

//ComboBox
@FXML
private ComboBox BoxBus;
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
private HBox Top;
@FXML
private HBox hisC;



 @FXML
    public void MostrarInicio(){

        PanelInicio.setVisible(true);
        PanelInicio.setManaged(true);
        BuscarTop();

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
         TiempoAVL.setText("Tiempo AVL: 0 ns");
         TiempoABB.setText("Tiempo ABB: 0 ns");
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
       
       
       
       limpiadito(ContenedorAL);
      Generos.clear();
       
        
      ArrayList<Archivomp3>canciones=Import.getAVL().PostOrden(); 
      for(Archivomp3 can:canciones){
          
          String genero=can.getGenero();
           if(genero==null||genero.isEmpty()){
               
           genero="Desconocido";
           }
       if(!Generos.containsKey(genero)){
           Generos.put(genero,new ArrayList<>());
       }
      Generos.get(genero).add(can);    
      }
      for(String genero:Generos.keySet()){
          
          ArrayList<Archivomp3> lista=Generos.get(genero);
          
          VBox tarjetas=Crear(genero,lista);
          
          ContenedorAL.getChildren().add(tarjetas);
      }
     
   }
  
   
   
   private void limpiadito(HBox Contenedor){
       
       
        for(javafx.scene.Node nodo : Contenedor.getChildren()){
        if(nodo instanceof VBox){
            VBox tarjetaVieja=(VBox)nodo;
            if(!tarjetaVieja.getChildren().isEmpty()&&tarjetaVieja.getChildren().get(0)instanceof ImageView){
                ImageView iv=(ImageView)tarjetaVieja.getChildren().get(0);
                iv.setImage(null);
            }
        }
    } 
    Contenedor.getChildren().clear();
   }
   
  
   
   public void BusquedaGenero(){
       Busquedas(Generos,ContenedorAL,"genero");
   }
   
   public void BusquedaArtista(){
       Busquedas(Arti,Artistas,"artistas");
   }
   
   
  @FXML
  public void Busquedas(HashMap<String,ArrayList<Archivomp3>> cositas,HBox contenedor,String metodo){
   String Busqueda=BusquedaInicio.getText().trim();
   
  limpiadito(contenedor);
   
   if(Busqueda.isEmpty()){
       for(String cos:cositas.keySet()){
           ArrayList<Archivomp3>contenido=cositas.get(cos);
           // operador ternario god 
           VBox jetica=metodo.equals("genero")?Crear(cos,contenido): TarjetaA(cos,contenido);
           contenedor.getChildren().add(jetica);
       }
       return;
   }
   
   
   for(Map.Entry<String, ArrayList<Archivomp3>> calve:cositas.entrySet()){
   
       String Indice=calve.getKey().toLowerCase();
       if(Indice.contains(Busqueda)){
           ArrayList<Archivomp3> canciones=calve.getValue();
           
           if(canciones!=null){
               VBox tar=metodo.equals("genero")?Crear(calve.getKey(),canciones):TarjetaA(calve.getKey(),canciones);
               contenedor.getChildren().add(tar);
           
           }
           
       }
       
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
       
       limpiadito(Recomendados);
       
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
           
           
           ReproducirCancion(Cancion);
           
       });
       

       return tarjetica;
   }
   
   
   
   private String TiempoTotal(double segundos){
    
       
       int totalSegundos=(int)segundos;
    
    
       int h=totalSegundos/3600;
       int m=(totalSegundos%3600)/60;
       int s=totalSegundos%60;
       
       if (h>0){
           return String.format("%dh %02dm %02ds",h,m,s);
       }else if(m>0){
           
           return String.format("%dm %02ds",m,s);
       }else{
           
           return s+"s";
       }
}
   
   public void BuscarTop(){
    
       
    List<String> busqueda=new ArrayList<>();
    List<String> TOP=new ArrayList<>();
    List<Double> TiempoRe=new ArrayList<>(); 

    try(BufferedReader leer=new BufferedReader(new FileReader("C:/Users/dxnie/OneDrive/Desktop/ProyectoSpotify/registros.txt"))){
        
        leer.readLine();
        String linea;

        while((linea=leer.readLine())!=null){ 
            if(linea.trim().isEmpty()){
                continue;
            }

            String[] info=linea.split(";");

            if(info.length<3){
                continue;
            }
            
            String nombre=info[0];
            int reproduccion=Integer.parseInt(info[1]);
            double tiempoS=Double.parseDouble(info[2]); 

            if(reproduccion >2){
               
                long tiempoString=Math.round(tiempoS * 100);
                String dataTiempo=String.format("%012d",tiempoString);
                
                TOP.add(dataTiempo+"ñ"+nombre+"ñ"+reproduccion);
            }
        }

        TOP.sort(Comparator.reverseOrder());

        
        for (String fila :TOP){
            
            String[] partes=fila.split("ñ",3); 
            
            if(partes.length>=3){
                String tiempoFormateado=partes[0];
                String nombreLimpio=partes[1];
               

                double tiempoReal=Long.parseLong(tiempoFormateado)/100.0;

                busqueda.add(nombreLimpio);   
                TiempoRe.add(tiempoReal); 
            }
        }

        
        TOPUNO=null;
        double SegundosTop1=0;
     
        for(int i=0;i<busqueda.size();i++){
            
            Archivomp3 sies=Import.getAVL().Buscar(busqueda.get(i));
            if(sies!=null){
                TOPUNO=sies;
                SegundosTop1=TiempoRe.get(i);
                break;
                
            }
        }
        
        
        if(TOPUNO!=null){
            
            tm=TiempoTotal(SegundosTop1);
        }else{
            tm="Sin datos";
        }
        limpiadito(Top);
        
      
        int tarjetasPintadas=0;
        for(int i=0;i<busqueda.size()&&tarjetasPintadas<15;i++){
            Archivomp3 pista=Import.getAVL().Buscar(busqueda.get(i));
            if(pista!=null){
               
                String TiempoT=TiempoTotal(TiempoRe.get(i));
                VBox t=TarjetaTop(pista,TiempoT);  
                Top.getChildren().add(t);
                tarjetasPintadas++;
            }
        }
         
    } catch(IOException|NumberFormatException e){
        
    }
       


   }
   
   
   
   public VBox TarjetaTop(Archivomp3 pista,String tiempoT){
       
       VBox jetica=new VBox();
       
       jetica.getStyleClass().addAll("music");
      
        
       jetica.setPrefWidth(100);
       jetica.setPrefHeight(100);
       
       ImageView portada= new ImageView();
       
       portada.setFitHeight(100);
       portada.setFitWidth(100);
       
       
       if(pista.getImagen()!=null){
           
           portada.setImage(CacheImagenes.ObtenerImagen(pista.getImagen()));
           
       }
       Label letrica=new Label(pista.getNombre());
       Label letrica2=new Label(tiempoT);
       letrica.getStyleClass().addAll("letrita");
       letrica2.getStyleClass().addAll("letrita");
       jetica.getChildren().addAll(portada,letrica,letrica2);
       
       jetica.setOnMouseClicked(evento->{
        
           ReproducirCancion(pista);
           
       });
       
       return jetica; 
   }
   
   
   
   
   
  
   
   
   public void ArtistasI(){
       
       limpiadito(Artistas);
       Arti.clear();
     
       ArrayList<Archivomp3> info=Import.getAVL().PostOrden();
       
       
       for(Archivomp3 art:info){
           String Artis=art.getArtista();
           if(Artis==null||Artis.isEmpty()){
               Artis="Desconocido";
           }
           if(!Arti.containsKey(Artis)){
               Arti.put(Artis,new ArrayList());
               
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
   private double Formateo(String duracuion){
         
         
   if(duracuion==null||duracuion.trim().isEmpty()) {
        return 0;
    }
    
    try{
        
        if(duracuion.contains(":")){
            
            String[] partes=duracuion.split(":");
            
            if(partes.length==3){
                double horas=Double.parseDouble(partes[0]);
                double minutos=Double.parseDouble(partes[1]);
                double segundos=Double.parseDouble(partes[2]);
                
                return (horas*3600)+(minutos*60)+segundos;
            }else{
          
                double minutos=Double.parseDouble(partes[0]);
                double segundos=Double.parseDouble(partes[1]);
                
                return (minutos*60)+segundos;
            }
        }
        
      
        return Double.parseDouble(duracuion);
        
    }catch(NumberFormatException e){
       
        return 0;
    }
    
}   
    
    

   public String  time(String tipo){
         
         String ti="Sin datos";
    
    ArrayList<Archivomp3> Duracion=Import.getAVL().InordeP();
   if(!Duracion.isEmpty()){
 
    Archivomp3 Mayor=Duracion.get(0);
    
    double top=Formateo(Mayor.getDuracion());
    
    for(Archivomp3 actual:Duracion){
        double t=Formateo(actual.getDuracion());
        
        if(t>top){
            Mayor=actual;
            top=t;
        }
    }
    
    ti=tipo.equals("stats")?"Nombre: "+Mayor.getNombre()+" Duración: "+Mayor.getDuracion() :" Duración: "+Mayor.getDuracion();
   }
     return ti;    
     }
     

@FXML
public void desplegable() {

   if (popupStats.isShowing()) {
        popupStats.hide();
        return;
    }

   if(Import.getAVL().Raiz==null){
    
    return;
   }
    
   int R=Import.getAVL().RepetidosTotal();
    VBox stats=new VBox(10);
    
    
     Label tiempo=new Label();
    
    if(TOPUNO==null){
        tiempo.setText("Top 1: Sin datos");
        
    }else{
    
     tiempo.setText("Top 1: "+TOPUNO.getNombre()+", Tiempo: "+tm);
    }
    
    String duracel=time("stats");
   Label Largo=new Label(duracel);
    Largo.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
    Label titulo=new Label("Estadísticas");
    titulo.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
    
   
    tiempo.setStyle("-fx-text-fill: white;");
    
    Label repetidos=new Label("Repetidos: "+R);
    repetidos.setStyle("-fx-text-fill: white;");
    
    
    stats.setStyle( "-fx-background-color: #0b1426;"+
    "-fx-background-radius: 15;"+
    "-fx-padding: 12;"+
    "-fx-border-radius: 15;"+
    "-fx-border-color: #1e2d4a;"+
    "-fx-border-width: 1;"+
    "-fx-effect: dropshadow(gaussian, rgba(11,20,38,0.6), 12, 0.2, 0, 3);");

    stats.getChildren().addAll(titulo,tiempo,repetidos,Largo);

    popupStats.getContent().clear();
    popupStats.getContent().add(stats);
    
    popupStats.setAutoHide(true);

    Bounds b =BtD.localToScreen(BtD.getBoundsInLocal());

    popupStats.show(BtD.getScene().getWindow(),b.getMinX()-80,b.getMaxY()-2);
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
            ActualizarBiblioteca();
        
       
        
   
        
        GenerosM();
        RecomendaG();
        ArtistasI();
        BuscarTop();
        BusquedaP();
        Alert alerta=new Alert(Alert.AlertType.INFORMATION);
       
        alerta.setHeaderText(null);
        alerta.setContentText("Carpeta "+Carpeta.getName()+" Cargada con exito");
        alerta.showAndWait();
        }else{
          
            Alert alertaError=new Alert(Alert.AlertType.WARNING);
            alertaError.setHeaderText(null);
            alertaError.setContentText("La playlist '"+Carpeta.getName()+"' ya existe en la biblioteca.");
            alertaError.showAndWait();
        }
    }
}





private void AnimacionS(StackPane H,Archivomp3 si){
  

    if(si==null||si.getNombre()==null){
        return;
    }
    PanePortada.getStyleClass().clear();
    PanePortada.getStyleClass().addAll("foto","portada");
    int ColorR=Math.abs(si.getNombre().hashCode())%3;
    
    if(Animacion!=null){
    Animacion.stop();
    
    }

    DropShadow sombra=new DropShadow();
    sombra.setSpread(0.05);
    sombra.setOffsetY(15);
    H.setEffect(sombra);
    
   

final Color color;
    
    switch(ColorR){
       
        case 0->{
            PanePortada.getStyleClass().add("portada-verde");
            Can.setStyle("-fx-text-fill: #1DB954; -fx-font-weight: bold;");
            color=Color.rgb(29,185,84);
        }
            
        case 1->{
            PanePortada.getStyleClass().add("portada-azul");
            Can.setStyle("-fx-text-fill: #00D2FF; -fx-font-weight: bold;");
            color=Color.rgb(0,210,255);
        }
            
        case 2->{ 
            PanePortada.getStyleClass().add("portada-magenta");
            Can.setStyle("-fx-text-fill: #E91E63; -fx-font-weight: bold;");
            color=Color.rgb(233,30,99);
        }
        default -> color=Color.BLACK;
        
    }
    
    
    Animacion=new Timeline(new KeyFrame(Duration.millis(16),evento->{
                 fase+=0.006;
                 double opacidadSombra=0.50+0.15 * Math.sin(fase);
                 Color Opacidad=Color.rgb((int)(color.getRed()*255),(int)(color.getGreen()*255),(int)(color.getBlue()*255),opacidadSombra);
                 
                 sombra.setColor(Opacidad);sombra.setRadius(160+40*Math.sin(fase));
                 sombra.setOffsetY(20+8*Math.sin(fase));
                 
             }));
    Animacion.setCycleCount(Timeline.INDEFINITE);
    Animacion.play();
}







public void TiempoT(Archivomp3 pista){
    if(reproductor!=null&&pista!=null){
        
        
        double tiempoA=reproductor.getCurrentTime().toSeconds();
        double tiempoE=tiempoA-tim;
        if(tiempoE>2.0){
         
            txt.Registro(pista.getNombre(),tiempoE);
            
        }
        tim=tiempoA;
    }
    
    
}
@FXML
private void EliminarEnPlaylist(){
    
    boolean eliminado=false;
    
   if(PlaylistActual!=null){
       
       NodoDoble r=PlaylistActual.getInicio();
       while(r!=null){
           NodoDoble continua=r.getSiguiente();
           if(r.getCancion().isEstado()){
               if(Actual==r){
                   Actual=continua;
               }
               
               r.getCancion().setEstado(false);
               DatosPlaylistActual.remove(r.getCancion());
               PlaylistActual.EliminarCancion(r.getCancion().getNombre());
               eliminado=true;
           }
           r=continua;
       }
   }

   if(eliminado){
       
       Estado.setText("Eliminado Correctamente");
       chek();
   }else{
       
       Estado.setText("Seleccione una Canción");
   }
       
       
   
}

private void Portada(){
   
   limpiadito(ContenedorK);
  
    if(valido&&CancionActual!=null){
        VBox tarjetaPortada=new VBox();
        tarjetaPortada.getStyleClass().add("tarjeta-portada");
        
        Image img=CacheImagenes.ObtenerImagen(CancionActual.getImagen());
        ImageView port=new ImageView(img);
        
        port.setFitWidth(180);  
        port.setFitHeight(180);
        port.setPreserveRatio(true);
        port.setSmooth(true);      
        
        javafx.scene.shape.Rectangle clip=new javafx.scene.shape.Rectangle(180,180);
        clip.setArcWidth(15);
        clip.setArcHeight(15);
        port.setClip(clip);

        Label nom=new Label(CancionActual.getNombre());
        nom.getStyleClass().add("texto-portada");
        
        tarjetaPortada.getChildren().addAll(port,nom);
        ContenedorK.setAlignment(javafx.geometry.Pos.CENTER);
      
        ContenedorK.getChildren().add(tarjetaPortada);
    }
}

@FXML
private void KaraokeVerso(){ 
    valido=!valido;
   boolean Sevesignodeinterrogacion=PanelVersos.isVisible();
    
    PanelVersos.setVisible(!Sevesignodeinterrogacion);
    PanelVersos.setManaged(!Sevesignodeinterrogacion);
   
    
    if(!Sevesignodeinterrogacion){
        if(Versos==null||Versos.isEmpty()){
            LVersos.setText("[Buscando letra o instrumental...]");
        }else{
            LVersos.setText("[Cargando letra...] ");
        }
    }
    Portada();
}


private List<Karaoke>cargarLetra(File archivoLrc){
   
   List<Karaoke>listaLetras=new ArrayList<>();
    if(archivoLrc==null||!archivoLrc.exists()){
        System.out.println("El archivo LRC no existe o es nulo.");
        return listaLetras;
    }

    try(BufferedReader leer=new BufferedReader(new FileReader(archivoLrc))){
        String linea;
        while ((linea=leer.readLine())!=null){
            linea=linea.trim();
            
            if(linea.startsWith("[")&&linea.contains("]")){
                int fin=linea.indexOf("]");
                String tiempoStr=linea.substring(1,fin);
                String verso=linea.substring(fin+1).trim();
                
                if(tiempoStr.matches("\\d+.*")){ 
                    String[] partes=tiempoStr.split(":");
                    if(partes.length>=2){
                        double minutos=Double.parseDouble(partes[0]);
                        
                        
                        String segundosStr=partes[1].replace(',','.');
                        double segundos=Double.parseDouble(segundosStr);
                        
                        double tiempoTotalSegundos=(minutos* 60)+segundos;
                        
                        listaLetras.add(new Karaoke(tiempoTotalSegundos,verso));
                    }
                }
            }
        }
    }catch(Exception e){
      
    }
    return listaLetras;
}


 @FXML
 public void ReproducirCancion(Archivomp3 Cancion){

 
    try{
           Archivomp3 yaquefuncione=CancionActual;
        if(reproductor!=null){
            
               reproductor.stop();
               if(progresoListener!=null){
                   
                   reproductor.currentTimeProperty().removeListener(progresoListener);
               }
               
               
               TiempoT(yaquefuncione);
               reproductor.setOnEndOfMedia(null);
               reproductor.setOnReady(null);
               
               this.reproductor.dispose();
               this.Versos=null;
               this.reproductor=null;
               
        }
        CancionActual=Cancion;
        
      
        String rutaLrc=Cancion.getAudio().replaceAll("(?i)\\.mp3$", ".lrc");
        File archivoLrc=new File(rutaLrc);
        
        if (!archivoLrc.exists()) {
            
            Carga.setFill(javafx.scene.paint.Color.web("#ffb86c")); 
            
            new Thread(()->{
                
                boolean exito = Descargarlrc.descargarLrcAutomatico(Cancion);
                
                Platform.runLater(()->{
            if(exito){
                this.Versos = cargarLetra(archivoLrc);
                
                
                Carga.setFill(javafx.scene.paint.Color.web("#50fa7b"));
                
                if(PanelVersos.isVisible() && Versos != null && !Versos.isEmpty()){
                    LVersos.setText("Letra sincronizada con éxito");
                }
            }else{
                Carga.setFill(javafx.scene.paint.Color.web("#ff5555"));
                
                if(PanelVersos.isVisible()){
                    LVersos.setText("No se encontró letra disponible");
                }
            }
        });
    }).start();
        }else{
            this.Versos = cargarLetra(archivoLrc);
            
            Carga.setFill(javafx.scene.paint.Color.web("#50fa7b")); 
            
        }
      
        
        
        Media Musica=new Media(new File(Cancion.getAudio()).toURI().toString());

   
        reproductor=new MediaPlayer(Musica);
            


         if(PlaylistActual!=null){
             PlaylistANombre.setText("Reproduciendo Playlist: "+PlaylistActual.getNombre());
         
         }
        reproductor.setOnReady(()->{

           reproductor.setVolume(VolumenI);
           
           
            double total=reproductor.getTotalDuration().toSeconds();
            
            Progreso.setMax(total);
            
            reproductor.play();
             
            tim=0.0;
            
            int minutos=(int)total/60;
            int segundos=(int)total%60;
            
            TiempoR.setText(String.format("%d:%02d",minutos,segundos));
            
            AnimacionS(Stak,CancionActual);
            reproduciendo=true;
  
            Icono.setImage(PauseImagen);
        });
        
        
        
        progresoListener=(espiado,antes,actual)->{
            if(!Progreso.isValueChanging()){
                double Tactual=actual.toSeconds();
                Progreso.setValue(Tactual);
                
                if(Versos!=null&&!Versos.isEmpty()){
                    String versoActual="";
                    
                    for(Karaoke linea:Versos){
                       
                        if(Tactual>=linea.getTiempoSegundos()){
                            versoActual=linea.getVerso();
                        }else{
                           
                            break; 
                        }
                    }
                    
                    
                    if (PanelVersos.isVisible()&&!versoActual.isEmpty()&&!versoActual.equals(LVersos.getText())){
                        String finalVerso=versoActual;
                      
                        
                        Platform.runLater(()->{
                            transicion.stop();
                            
                            LVersos.setText(finalVerso);
                            LVersos.setTranslateY(25);
                            LVersos.setOpacity(0.0);
                            transicion.play();
                        });
                    }
                }
                
                int minutosA=(int)Tactual/60;
                int segundosA=(int)Tactual%60;
                TiempoF.setText(String.format("%d:%02d", minutosA, segundosA));
            }    
        };
        
       
        reproductor.currentTimeProperty().addListener(progresoListener);
    
       
        
        if(ultimo==null||!ultimo.getAudio().equals(Cancion.getAudio())){
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

                       AnimacionS(Stak,Cancion);
                   }catch (Exception e){
                   }

        
            
        });
             
      
            reproductor.setOnEndOfMedia(()->{
                TiempoT(CancionActual);
                if(modoCola){
                    
                    AvanzarCola();
                }else if(modoCircular){
                    Siguiente();
                
                }else{
                    Siguiente();
                }
            
            });
            
            Histtt();
            HistT();

    }catch(Exception e){
        Estado.setText("Error al reproducir");
        System.out.println(e);
    }
}

 
 @FXML
 public void AvanzarCola(){
    
Archivomp3 siguiente =ColaReproduccion.Salida();

    if(siguiente!=null){
        ReproducirCancion(siguiente);
    } else{
   
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
                 AnimacionS(Stak,CancionActual);

                ReproducirCancion(CancionActual);

            }else{
                
                tim=reproductor.getCurrentTime().toSeconds();
                reproductor.play();
                AnimacionS(Stak,CancionActual);
            }

            Icono.setImage(PauseImagen);
            reproduciendo=true;

        }else{

              TiempoT(CancionActual);
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
        }else{
            break;
        }
        
    }
    if(actual!=null){
        Actual=actual;
        Archivomp3 ale=Actual.getCancion();
        
        modoCola=false;
        ReproducirCancion(ale);
        
        ListaCanciones.getSelectionModel().select(ale);
    }
    
   
    }catch(Exception e){
        Estado.setText("Error en el modo aleatorio");
    }
}




 public void HistT(){
       
     
     limpiadito(hisC);        
               
     
       
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
        for(int i=0;i<lista.size();i++){
            if(lista.get(i)!=null){
            
            VBox Tarjeta=TarjetaH(lista.get(i));
            hisC.getChildren().add(Tarjeta);
            }
        }
      
       
   }
   
   public VBox TarjetaH(Archivomp3 Cancion){
       
       VBox tarjetica= new VBox(12);
       
      
       tarjetica.getStyleClass().addAll("music");
        
       tarjetica.setPrefWidth(80);
       tarjetica.setPrefHeight(80);
       
       ImageView portada= new ImageView();
       
       portada.setFitHeight(75);
       portada.setFitWidth(75);
       
       
       if(Cancion.getImagen()!=null){
           
           portada.setImage(CacheImagenes.ObtenerImagen(Cancion.getImagen()));
           
       }
       Label letrica=new Label(Cancion.getNombre());
       Label letrica2=new Label(Cancion.getArtista());
       letrica.getStyleClass().addAll("letrita");
       letrica2.getStyleClass().addAll("letrita");
       tarjetica.getChildren().addAll(portada,letrica,letrica2);
       
       
       
       tarjetica.setOnMouseClicked(evento->{
           
           
           ReproducirCancion(Cancion);
           
       });
       

       return tarjetica;
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

     
          TiempoT(CancionActual);
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
            if(Import.getAVL().Buscar(p.getNombre())==null){
                Import.getAVL().Insertar(p);
            }
             nodo=nodo.Siguiente;
        }
    }

    DatosBiblioteca.setAll( Import.getAVL().InordeP());         
    
}


@FXML
public void BusquedaP(){
    
    String Buscado=BuscadorPB.getText().trim().toLowerCase();
    
    if(Buscado.isEmpty()){
        ListBusquedaParcial.getItems().setAll(Import.getAVL().PreOrden());
        TiempoAVL1.setText("Tiempo AVL: 0 ns");
        TiempoABB1.setText("Tiempo ABB: 0 ns");
       return; 
    }
    long tiempoavl=Import.getAVL().TiempoParcial(Buscado);
    long tiempoabb=Import.getABB().TiempoParcial(Buscado);
    
    Playlist ResultadosV=Import.getAVL().BusquedaParcial(Buscado);
    
    ListBusquedaParcial.getItems().clear();
    
    NodoDoble nod=ResultadosV.getInicio();
    while(nod!=null){
        
        ListBusquedaParcial.getItems().add(nod.getCancion());
        nod=nod.Siguiente;
    }
    
    if(ResultadosV.getInicio()!=null){
        TiempoAVL1.setText("Encontrado AVL: "+tiempoavl+" ns");
        TiempoABB1.setText("Encontrado ABB: "+tiempoabb+" ns");
    } else {
        
        TiempoAVL1.setText("No encontrado AVL: "+tiempoavl+" ns");
        TiempoABB1.setText("No encontrado ABB: "+ tiempoabb+" ns");
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
         DatosPlaylistActual.add(todoNodo.getCancion());
         todoNodo=todoNodo.Siguiente;
    }
   
    if(agregado){

        Estado.setText("Canciones agregadas a "+PlaylistActual.getNombre());
        chek();

    }else{

        Estado.setText("No selecciono canciones");
    }
}

private void EventoBuscador(TextField Bus,Runnable Buscador){
    
  Bus.setOnKeyPressed(evento->{
        
        if(evento.getCode()==KeyCode.ENTER){
            Buscador.run();
        }
        
    });
    
    Bus.textProperty().addListener((espiado,antes,actual)->{
        
        if(actual.trim().isEmpty()){
        Buscador.run();
        
        }
    });
    
}
     

private void EventoScroll(ScrollPane horizontal){
    
    horizontal.setOnScroll(evento->{
        
     double desplazamiento=evento.getDeltaY();
     double valor=horizontal.getHvalue();
     
     double velocidad=0.05;
     
        if(desplazamiento<0){
            horizontal.setHvalue(valor+velocidad);
        }else if(desplazamiento>0){
            horizontal.setHvalue(valor-velocidad);
        }
        evento.consume();
    });
    
}

private void SinVertical(ScrollPane si){
     
 si.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
 si.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
 si.setFitToHeight(true);    
 }
private void actualizar(){
    if(PlaylistActual!=null){
        DatosPlaylistActual.clear(); 

        
        NodoDoble actual=PlaylistActual.getInicio();
        while(actual!=null){
            DatosPlaylistActual.add(actual.getCancion());
            actual=actual.getSiguiente();
            
        }
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
     
     ListaCanciones.setItems(DatosPlaylistActual);
     
     Icono.setImage(PlayImagen);
     Icono.setFitWidth(30);
     Icono.setFitHeight(23);
     Play.setGraphic(Icono);
     
     Mov=new TranslateTransition(Duration.millis(220),LVersos);
     Mov.setFromY(25);
     Mov.setToY(0);

     Aparece=new FadeTransition(Duration.millis(200),LVersos);
     Aparece.setFromValue(0.0);
     Aparece.setToValue(1.0);

     transicion=new ParallelTransition(Mov,Aparece);

     
     
     ListaPlaylist.getSelectionModel().selectedItemProperty().addListener((obs,antes,actual)->{
        if(actual!=null){
            PlaylistActual=actual;
            
            DatosPlaylistActual.clear(); 
            NodoDoble nodo=actual.getInicio();

            while(nodo!=null){
                DatosPlaylistActual.add(nodo.getCancion());
                nodo=nodo.getSiguiente();
            }
        }
     });
  
   ListaVBIblioteca.setOnMouseClicked(evento ->{
   
   if(evento.getClickCount()==2){
       
       modoCola=false;
       
       Archivomp3 c=ListaVBIblioteca.getSelectionModel().getSelectedItem();
       
       if(c!=null){
           
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


            if(PlaylistActual!=null){
                Actual=PlaylistActual.BuscarCancion(Cancion);
            }

            ReproducirCancion(Cancion);
        }
       
               
           }
           
           
           });
   
   
  ListaPlaylist.setCellFactory(parametros->{
       
      
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
                 
                 
                 Nombre.setOnMouseClicked(e ->{
                     
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
                 
                 
                 Editar.setOnAction(e ->{
                     
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
   
   
   

ListaVBIblioteca.setCellFactory(parametros->new CancionCell(CrearP,()-> Favoritos(),(origen,destino)->false));
ListBusquedaParcial.setCellFactory(Parametros->new CancionCell(CrearP,()->Favoritos(),(origen,destino)->false));
ListaCanciones.setCellFactory(parametro->new CancionCell(CrearP,()->Favoritos(),(origen,destino)->{

if(PlaylistActual!=null){
    boolean movido=PlaylistActual.MoverCancion(origen, destino);
    if(movido){
        actualizar();
        ListaCanciones.getSelectionModel().select(destino-1);
        return true;
    }
}
return false;
}));

  
   
   Progreso.setOnMousePressed(e->{

    if(reproductor!=null){

          TiempoT(CancionActual);
        reproductor.seek(Duration.seconds(Progreso.getValue() ));
    }
});
    
   
   
   
   
   
Progreso.setOnMouseDragged(a->{

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
    

 
 EventoBuscador(BuscadorPB1,this::Buscar);
 EventoBuscador(BuscadorPB,this::BusquedaP);
 
 
 BoxBus.getItems().addAll("Generos","Artistas");
 BoxBus.getSelectionModel().selectFirst();
 EventoBuscador(BusquedaInicio,()->{
    
     String op=BoxBus.getValue().toString();
     
     if(op!=null){
         if(op.equals("Generos")){
             BusquedaGenero();
         }else if(op.equals("Artistas")){
             BusquedaArtista();
         }
         
     }

     
     
 });
 
 
 
 


orden.getItems().addAll("A-Z","Fecha de publicacion","Invertido");
orden.getSelectionModel().selectFirst(); 
orden.valueProperty().addListener((espiado,antes,nuevo)->{
        
       if(nuevo==null||Import.getAVL()==null){
           return;
           
       } 
        
       ListaCanciones.getItems().clear();
       
       switch(nuevo.toString()){
            case "A-Z" ->{ DatosBiblioteca.setAll(Import.getAVL().InordeP());
            
            }
            case "Fecha de publicacion" ->{
               DatosBiblioteca.setAll(Import.getAVL().PreOrden());
                
             }
                
            case "Invertido" ->{
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
 

 
 
 
 
 EventoScroll(ScrollG);
 EventoScroll(ScrollA);
 EventoScroll(ScrollT);
 EventoScroll(ScrollH);

 SinVertical(ScrollG);
 SinVertical(ScrollT);
}
    
}