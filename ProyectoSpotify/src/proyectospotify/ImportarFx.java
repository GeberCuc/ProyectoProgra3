
package proyectospotify;

public class ImportarFx {

    public MetodosABB getABB() {
        return ABB;
    }

    public void setABB(MetodosABB ABB) {
        this.ABB = ABB;
    }

    public MetodosAVL getAVL() {
        return AVL;
    }

    public void setAVL(MetodosAVL AVL) {
        this.AVL = AVL;
    }

    public CargarCanciones getCargador() {
        return Cargador;
    }

    public void setCargador(CargarCanciones Cargador) {
        this.Cargador = Cargador;
    }

    
    
    
    private  MetodosABB ABB;
    private MetodosAVL AVL;
    private CargarCanciones Cargador;
    
    
    public ImportarFx(){
        
        ABB= new MetodosABB();
        AVL=new MetodosAVL();
        Cargador= new CargarCanciones();
        
    }
    
    
    
    public String Importar(String link){
        
        return Cargador.Canciones(link, ABB, AVL);
        
        
    }

}
