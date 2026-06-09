
package proyectospotify;

import javafx.beans.property.BooleanProperty;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;



public class CancionCell extends ListCell<Archivomp3>{
    
    private long Tiempo=0;

    private final CheckBox check=new CheckBox();
    private final ImageView imagen=new ImageView();

    private final Label nombre=new Label();
    private final Label artista=new Label();
    private final Label album=new Label();
    private final Label tiempo=new Label();

    private final ToggleButton fav=new ToggleButton();
    private final ImageView iconoFav= new ImageView();

    private final VBox texto=new VBox(nombre,artista,album, tiempo);
    private final HBox contenido=new HBox(10,check,fav, imagen, texto );

   private final BooleanProperty CrearP;;
    private final Runnable actualizarFavoritos;
   
    private static final Image corazon=CacheImagenes.ObtenerCorazon();
    private static final Image corazonCheck=CacheImagenes.ObtenerCorazonCheck();


    public interface Mover{
        
        boolean saltodelTigre(int origen,int destino);
    }

    public CancionCell(BooleanProperty CrearP,Runnable actualizarFavoritos,Mover saltodelTigre){

        this.CrearP=CrearP;

        this.actualizarFavoritos=actualizarFavoritos;

        fav.getStyleClass().add("boton-favorito");
        iconoFav.setFitWidth(20);
        iconoFav.setFitHeight(20);
        fav.setGraphic(iconoFav);

        imagen.setFitWidth(50);
        imagen.setFitHeight(50);
        check.visibleProperty().bind(CrearP);
        check.managedProperty().bind(CrearP);
        
        
        check.visibleProperty().bind(CrearP);
        check.managedProperty().bind(CrearP);
        
        check.setOnAction(evento->{
            Archivomp3 itemActual=getItem();
            if(itemActual!=null){
                itemActual.setEstado(check.isSelected());
            }
        });
        
        fav.setOnAction(evento->{
            Archivomp3 itemActual=getItem();
            if(itemActual!=null){
                itemActual.setFav(!itemActual.isFav());
                iconoFav.setImage(itemActual.isFav()? corazonCheck: corazon);
                if(actualizarFavoritos!=null){
                    actualizarFavoritos.run();
                }
            }
        });
        
        this.setOnDragDetected(evento->{
            if(getItem()==null){
                return;
                
            }
            
            Dragboard movimiento=this.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent contenido1=new ClipboardContent();
            
            contenido1.putString(String.valueOf(getIndex()));
            movimiento.setContent(contenido1);
            
            this.setOpacity(0.5);
            evento.consume();
        });
        
        this.setOnDragOver(evento->{
           if(evento.getGestureSource()!=this&&evento.getDragboard().hasString()){
               
               evento.acceptTransferModes(TransferMode.MOVE);
               javafx.scene.control.ListView<Archivomp3>lista=this.getListView();
               
               if(lista!=null){
                   
                   long tiempoActual=System.currentTimeMillis();
                   
                   if(tiempoActual-Tiempo>250){
                       
                       double mouseEnListaY=lista.sceneToLocal(evento.getSceneX(),evento.getSceneY()).getY();
                       double alturaLista=lista.getHeight();
                       
                       if(mouseEnListaY<30){
                           int i=getIndex()-1;
                           
                           if(i>=0){
                               lista.scrollTo(i);
                               Tiempo=tiempoActual;
                           }
                       }else if(mouseEnListaY>alturaLista-30){
                           int j=getIndex()+1;
                           
                           if(j<lista.getItems().size()){
                               lista.scrollTo(j);
                               Tiempo=tiempoActual;
                           }
                       }
                   }
               }
           }
           evento.consume();
        });
        
        
        this.setOnDragDropped(evento->{
            Dragboard contenido1=evento.getDragboard();
            boolean ya=false;
            
            if(contenido1.hasString()){
                int inicio=Integer.parseInt(contenido1.getString())+1;
                int fin=getIndex()+1;
                if(saltodelTigre!=null){
                   ya=saltodelTigre.saltodelTigre(inicio, fin);
                }
                
            }
            evento.setDropCompleted(ya);
            evento.consume();
        });
        
        this.setOnDragDone(evento->{
            
            this.setOpacity(1.0);
            evento.consume();
            
        });
        
    }



    @Override
    protected void updateItem(Archivomp3 item,boolean empty){

       super.updateItem(item,empty); 
        
        if(empty||item==null){
            
            setGraphic(null);
            setText(null);
            
            return;
        }

       
        check.setSelected(item.isEstado());
        iconoFav.setImage(item.isFav() ?corazonCheck: corazon);
        imagen.setImage(CacheImagenes.ObtenerImagen(item.getImagen()));
        
        nombre.setText(item.getNombre());
        artista.setText(item.getArtista());
        album.setText(item.getAlbum());
        tiempo.setText(item.getDuracion());
        
        setGraphic(contenido);
    }







}