
package proyectospotify;

import javafx.beans.property.BooleanProperty;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;



public class CancionCell extends ListCell<Archivomp3>{

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



    public CancionCell(BooleanProperty CrearP,Runnable actualizarFavoritos){

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
    }



    @Override
    protected void updateItem(Archivomp3 item,boolean empty){

        super.updateItem(item,empty);
        if(empty||item==null){

            setGraphic(null);
            setText(null);

            return;
        }
        
        check.setOnAction(null);
        fav.setOnAction(null);
        
        check.setSelected(item.isEstado());
        check.setOnAction(ecento->item.setEstado(check.isSelected()));
        
        
        fav.setSelected(item.isFav());
        iconoFav.setImage(item.isFav() ?corazonCheck : corazon);
        
        fav.setOnAction(evento->{
            
            item.setFav(fav.isSelected());
            iconoFav.setImage(item.isFav() ?corazonCheck :corazon);
            
            if(actualizarFavoritos!=null){
                actualizarFavoritos.run();
            }
        });
        
       
        
        imagen.setImage(CacheImagenes.ObtenerImagen(item.getImagen()));
        
        nombre.setText(item.getNombre());
        artista.setText(item.getArtista());
        album.setText(item.getAlbum() );
        tiempo.setText(item.getDuracion());



        setGraphic(contenido);
    }







}