
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

    private CheckBox check=new CheckBox();
    private ImageView imagen=new ImageView();

    private Label nombre=new Label();
    private Label artista=new Label();
    private Label album=new Label();
    private Label tiempo=new Label();

    private ToggleButton fav=new ToggleButton();

    private VBox texto=new VBox(nombre,artista,album, tiempo);
    private HBox contenido=new HBox(10,check,fav, imagen, texto );

   private BooleanProperty CrearP;;
    private Runnable actualizarFavoritos;
   
    private static Image corazon=CacheImagenes.ObtenerCorazon();
    private static Image corazonCheck=CacheImagenes.ObtenerCorazonCheck();



    public CancionCell(BooleanProperty CrearP,Runnable actualizarFavoritos){

        this.CrearP=CrearP;

        this.actualizarFavoritos=actualizarFavoritos;

        fav.getStyleClass().add("boton-favorito");

        imagen.setFitWidth(50);
        imagen.setFitHeight(50);
    }



    @Override
    protected void updateItem(Archivomp3 item,boolean empty){

        super.updateItem(item,empty);

        if(empty||item==null){

            setGraphic(null);
            setText(null);

            return;
        }



       
        check.setVisible(CrearP.get());
        check.setManaged(CrearP.get());
        
        check.setSelected(item.isEstado());

        check.setOnAction(e ->{
            
            item.setEstado(check.isSelected());
        
        });



        ImageView iconoFav=new ImageView();

        iconoFav.setFitWidth(20);
        iconoFav.setFitHeight(20);

        fav.setGraphic(iconoFav);
        fav.setSelected(item.isFav());

        if(fav.isSelected()){
            iconoFav.setImage(corazonCheck);

        }else{
            iconoFav.setImage(corazon);
        }






        fav.setOnAction(e ->{

            item.setFav(fav.isSelected());

            if(fav.isSelected()){

                iconoFav.setImage(corazonCheck);

            }else{
                iconoFav.setImage(corazon);
            }

            if(actualizarFavoritos != null){

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