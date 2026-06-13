
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

/*
Método para personalizar las celdas de las listview
optimiza la renderizacion de las celdas mediante la reutilización
de los nodos graficos

*/

public class CancionCell extends ListCell<Archivomp3>{
    //vartiable de control para limitar el scroll en cambio de celda de la playlist
    private long Tiempo=0;
//componentes graficos 
    private final CheckBox check=new CheckBox();
    private final ImageView imagen=new ImageView();

    private final Label nombre=new Label();
    private final Label artista=new Label();
    private final Label album=new Label();
    private final Label tiempo=new Label();

    private final ToggleButton fav=new ToggleButton();
    private final ImageView iconoFav= new ImageView();

    //contenedores para los componente graficos
    private final VBox texto=new VBox(nombre,artista,album, tiempo);
    private final HBox contenido=new HBox(10,check,fav, imagen, texto );

    //variables globales del controllador
   private final BooleanProperty CrearP;//controla la visibilidad de los checkbox
    private final Runnable actualizarFavoritos;//controla la visibilidad del corazon 
   //imagenes unicas para evitar sobrecargar la memoria
    private static final Image corazon=CacheImagenes.ObtenerCorazon();
    private static final Image corazonCheck=CacheImagenes.ObtenerCorazonCheck();

/* interfaz se encangade de interactual con la estructura da datos pasados como parametros 
 para reordenar sus elementos   
    */
    public interface Mover{
      /*
        Realiza el translado de un elemnto de una  posicion a otra
        origen es el origen kjaja
        el destino es su parada final 
        retorna verdadero si la operacion se realizo con exito
        */  
        boolean saltodelTigre(int origen,int destino);
    }
//contructor de las celdas  defino el estolo el tamaño y los detectores de eventos
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
        
        //actualiza el estado de seleccion del objeto mp3 al marcar o desmarcar
        check.setOnAction(evento->{
            Archivomp3 itemActual=getItem();
            if(itemActual!=null){
                itemActual.setEstado(check.isSelected());
            }
        });
        //alterna el estado favorito de la canción y actuliza el icono
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
        
        
        // eventos para la función de arrastrar una canción
        this.setOnDragDetected(evento->{
            if(getItem()==null){
                return;//ignora las celdas vacías
                
            }
            
            Dragboard movimiento=this.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent contenido1=new ClipboardContent();
            
            
            //Envía el índice de la selda como texto al evento
            
            contenido1.putString(String.valueOf(getIndex()));
            movimiento.setContent(contenido1);
            
            //atenúa las celdas que esta siendo arrastrado 
            this.setOpacity(0.5);
            evento.consume();
        });
        
        // Gestiona el movimiento y el auto scroll dinámico
        this.setOnDragOver(evento->{
           if(evento.getGestureSource()!=this&&evento.getDragboard().hasString()){
               
               evento.acceptTransferModes(TransferMode.MOVE);
               javafx.scene.control.ListView<Archivomp3>lista=this.getListView();
               
               if(lista!=null){
                   
                   long tiempoActual=System.currentTimeMillis();
                   //añade un delay de 250ms para evitar que el auto scorll sea muy rapido
                   if(tiempoActual-Tiempo>250){
                       // calcula las cordenadas del cursor
                       double mouseEnListaY=lista.sceneToLocal(evento.getSceneX(),evento.getSceneY()).getY();
                       double alturaLista=lista.getHeight();
                       
                       //calcula la direccion desplaza hacia ariibna si supera los 30 
                       if(mouseEnListaY<30){
                           int i=getIndex()-1;
                           //calcula la direccion tambien en este caso la zona de abajo 
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
        
        //suelta el elemnto recuperando la posición incial y ejecuta la lógica para reordenar
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
        
        //finaliza el arrastre y restaura las propiedades visuales de las celdas involucradas
        this.setOnDragDone(evento->{
            
            this.setOpacity(1.0);
            evento.consume();
            
        });
        
    }


//se ejecuta de forma automatica para llenar las celtas o limpiarlas
    @Override
    protected void updateItem(Archivomp3 item,boolean empty){

       super.updateItem(item,empty); 
        // si la celda esta vacío o el objeto es nulo, limpia el contenido
        if(empty||item==null){
            
            setGraphic(null);
            setText(null);
            
            return;
        }

       //sincroniza los componentes con los estados del mp3
        check.setSelected(item.isEstado());
        iconoFav.setImage(item.isFav() ?corazonCheck: corazon);
        imagen.setImage(CacheImagenes.ObtenerImagen(item.getImagen()));
        
        nombre.setText(item.getNombre());
        artista.setText(item.getArtista());
        album.setText(item.getAlbum());
        tiempo.setText(item.getDuracion());
        //añade al contendero y renderiza 
        setGraphic(contenido);
    }







}