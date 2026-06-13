
package proyectospotify;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;


public class MetodosABB {
    
    NodoABB raiz;
    
    
    public MetodosABB(){
        
        
    }
    
    public void Insertar(Archivomp3 Cancion){
        raiz=InsertarP(raiz,Cancion);
    }
    
    
    private NodoABB InsertarP(NodoABB Actual,Archivomp3 Cancion){
        
        if(Actual==null){
            return new NodoABB(Cancion);
        }
        
        
       if(Cancion.getNombre().compareToIgnoreCase(Actual.Cancion.getNombre())<0){
           
           Actual.Izq=InsertarP(Actual.Izq,Cancion);
           
       }else if(Cancion.getNombre().compareToIgnoreCase(Actual.Cancion.getNombre())>0){
           
           Actual.Der=InsertarP(Actual.Der,Cancion);
       }else{
           Actual.Repetido++;
       }
       
      return Actual;
    }
    
public long TiempoParcial(String Nombre){
    long InicioT=System.nanoTime();


    SoloTiempo(raiz,Nombre.toLowerCase());

    long FinT=System.nanoTime();
    return FinT-InicioT;
}


private void SoloTiempo(NodoABB nodo,String textoBuscado){
    if(nodo==null){
        return;
    }
    SoloTiempo(nodo.Izq,textoBuscado);

    String nombreCancion=nodo.Cancion.getNombre().toLowerCase(); 
    if (nombreCancion.contains(textoBuscado)){
       
        int coincidencia=1; 
        //System.out.println(coincidencia+nodo.Cancion.getNombre()); para pruebas de tiempo
    }
    
    SoloTiempo(nodo.Der, textoBuscado);
}
    



    public Archivomp3 Buscar(String Buscado){ 
        return BuscarP(raiz,Buscado); 
    }
    
    
    
    private Archivomp3 BuscarP(NodoABB Actual,String Nombre){
        
        if(Actual==null){
            return null;
        }
        
        if(Nombre.compareToIgnoreCase(Actual.Cancion.getNombre())==0){
            
            return Actual.Cancion;
        }
        if(Nombre.compareToIgnoreCase(Actual.Cancion.getNombre())<0){
            
            return BuscarP(Actual.Izq,Nombre);
        }else{
            
            return BuscarP(Actual.Der,Nombre);
        }
        
    }
    
    public NodoABB Menor(NodoABB Actual){
        
        while(Actual.Izq!=null){
            
            Actual=Actual.Izq;
        }
        return Actual;
    }
    
    
    public long tiempo(String Nombre){
        
        long InicioT=System.nanoTime();
        Buscar(Nombre);
        long FinT=System.nanoTime();
        return FinT -InicioT;
    }
    
    
    
    
    
    
    
    
    public void Eliminar(String Nombre){
        
    raiz=EliminarP(raiz,Nombre);    
    }
    
 private NodoABB EliminarP(NodoABB Actual,String Nombre){

    if(Actual==null){
        return null;
    }

    if(Nombre.compareToIgnoreCase(Actual.Cancion.getNombre())<0){

        Actual.Izq=EliminarP(Actual.Izq,Nombre);

    }else if(Nombre.compareToIgnoreCase(Actual.Cancion.getNombre())>0){

        Actual.Der=EliminarP(Actual.Der,Nombre);

    }else{

        if(Actual.Repetido>0){
            Actual.Repetido--;

            return Actual;
        }

        if(Actual.Izq==null&&Actual.Der==null){

            return null;
        }

        if(Actual.Izq==null){

            return Actual.Der;
        }

        if(Actual.Der==null){

            return Actual.Izq;
        }

        NodoABB Heredero=Menor(Actual.Der);

        Actual.Cancion=Heredero.Cancion;
        Actual.Repetido=Heredero.Repetido;

        Actual.Der=EliminarP(Actual.Der,Heredero.Cancion.getNombre());
    }

    return Actual;
}
   public ArrayList<Archivomp3>InOrden(){

    ArrayList<Archivomp3>lista=new ArrayList<>();
    InOrdenP(raiz,lista);
    
    return lista;
}

   
private void InOrdenP(NodoABB Actual,ArrayList<Archivomp3> lista){
    if(Actual!=null){
        InOrdenP(Actual.Izq,lista);
        lista.add(Actual.Cancion);
        InOrdenP(Actual.Der,lista);
    }
}


public ArrayList<Archivomp3> PreOrden(){
    
    ArrayList<Archivomp3> lista=new ArrayList<>();
    PreOrdenP(raiz,lista);

    return lista;
}





private void PreOrdenP(NodoABB Actual,ArrayList<Archivomp3>lista){
    if(Actual!=null){
        lista.add(Actual.Cancion);
        PreOrdenP(Actual.Izq, lista);
        PreOrdenP(Actual.Der, lista);
    }
}





public ArrayList<Archivomp3>PostOrden(){

    ArrayList<Archivomp3> lista=new ArrayList<>();
    
    return lista;
}



private void PostOrdenP(NodoABB Actual, ArrayList<Archivomp3> lista){
    if(Actual!=null){
        PostOrdenP(Actual.Izq, lista);
        PostOrdenP(Actual.Der, lista);
        lista.add(Actual.Cancion);
        
    }
}
public void ABBGrap(String archivo) {
    try (FileWriter Escritura = new FileWriter(archivo); PrintWriter escribo = new PrintWriter(Escritura)) {
        
        escribo.println("digraph ABB {");
        escribo.println("    bgcolor=\"#181818\";");
        // Quitamos el DPI alto. SVG es vectorial por defecto.
        escribo.println("    nodesep=\"0.5\";");
        escribo.println("    ranksep=\"0.5\";");
        escribo.println("    node [shape=ellipse, style=filled, fillcolor=\"#1ED760\", fontcolor=white, fontname=\"Arial\", color=\"red\", fontsize=10];");
        escribo.println("    edge [color=\"red\", arrowhead=vee];");

        if (raiz == null) {
            escribo.println("    \"Árbol Vacío\" [shape=none, fontcolor=white];");
        } else {
            Graphiz(raiz, escribo);
        }

        escribo.println("}");
        
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("ERRORD ABB: " + e);
    }
}

    private void Graphiz(NodoABB actual, PrintWriter escribir){
        if(actual==null){
            return;
        }

  
        String nombre=actual.Cancion.getNombre().replace("\"", "\\\"");

       
        escribir.println("    \""+nombre+"\" [shape=ellipse, label=\""+nombre+"\\nTamaño: "+actual.Cancion.getTamaño()+" | Repe: "+actual.Repetido+"\"];");
    
        if(actual.Izq!=null){
            String hijoIzq=actual.Izq.Cancion.getNombre().replace("\"", "\\\"");
            escribir.println("    \""+nombre+"\" -> \""+hijoIzq + "\";");
            Graphiz(actual.Izq,escribir);
        }else{
            String invisible="nullI_"+Math.abs(nombre.hashCode());
            escribir.println("    "+invisible+" [shape=point, style=invis];");
            escribir.println("    \""+nombre+"\" -> "+invisible+" [style=invis];");
        }
        
 
        if(actual.Der!=null){
            String hijoDer=actual.Der.Cancion.getNombre().replace("\"", "\\\"");
            escribir.println("    \""+nombre+"\" -> \""+hijoDer + "\";");
            Graphiz(actual.Der,escribir);
        }else{ 
            String invisible="nullD_"+Math.abs(nombre.hashCode());
            escribir.println("    "+invisible+" [shape=point, style=invis];");
            escribir.println("    \"" + nombre+"\" -> "+invisible+" [style=invis];");
        }
    }
}

