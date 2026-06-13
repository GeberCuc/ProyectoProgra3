
package proyectospotify;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;


public class MetodosAVL {
    
    
    NodoAVL Raiz;
    
    
    public MetodosAVL(){
    }
    
    
    private int Niveles(NodoAVL Nivel){
        
        if(Nivel==null){
            return 0;
        }
        return Nivel.altura;
    }
    
    
    private int Balance(NodoAVL Nodo){
        
        if(Nodo==null){
            
            return 0;
            
        }
        
        return Niveles(Nodo.Izq)-Niveles(Nodo.Der);
    }
    
    
    private NodoAVL RotacionD(NodoAVL NodoRO){
        if(NodoRO==null||NodoRO.Izq==null){
            return NodoRO;
        }
        
        NodoAVL NuevaR=NodoRO.Izq;
        NodoAVL Solin=NuevaR.Der;
        
        NuevaR.Der=NodoRO;
        NodoRO.Izq=Solin;
        
        NodoRO.altura=Math.max(Niveles(NodoRO.Izq),Niveles(NodoRO.Der))+1;
        NuevaR.altura=Math.max(Niveles(NuevaR.Izq),Niveles(NuevaR.Der))+1;
        
        
        return NuevaR;
    }
    
    
    
    
    private NodoAVL RotacionI(NodoAVL NodoRO){
        
        if(NodoRO==null||NodoRO.Der==null){
            return NodoRO;
        }
        
        NodoAVL NuevaR= NodoRO.Der;
        NodoAVL Solin=NuevaR.Izq;
        
        NuevaR.Izq=NodoRO;
        NodoRO.Der=Solin;
        
        NodoRO.altura=Math.max(Niveles(NodoRO.Izq),Niveles(NodoRO.Der))+1;
        NuevaR.altura=Math.max(Niveles(NuevaR.Izq),Niveles(NuevaR.Der))+1;
        
        return NuevaR;
    }
    
    
    
    public void Insertar(Archivomp3 Cancion){
        
        Raiz=InsertarP(Raiz,Cancion);
    }
    
    
    
    private NodoAVL InsertarP(NodoAVL Actual,Archivomp3 Cancion){
        
        if(Actual==null){
            
            return new NodoAVL(Cancion);
        }
        
            if(Cancion.getNombre().compareToIgnoreCase(Actual.Cancion.getNombre())<0){
                Actual.Izq=InsertarP(Actual.Izq,Cancion);     
                }else if(Cancion.getNombre().compareToIgnoreCase(Actual.Cancion.getNombre())>0){
                    
                    Actual.Der=InsertarP(Actual.Der,Cancion);
                    
                }else{
                    Actual.repetido++;
                    
                    return Actual;
                    
                }
            
            Actual.altura=1+Math.max(Niveles(Actual.Izq), Niveles(Actual.Der));
            
            int FactorB=Balance(Actual);
            if(FactorB>1&&Actual.Izq!=null){
        
        if(Cancion.getNombre().compareToIgnoreCase(Actual.Izq.Cancion.getNombre())<0){
            return RotacionD(Actual);
        }
        
        if(Cancion.getNombre().compareToIgnoreCase(Actual.Izq.Cancion.getNombre())>0){
            Actual.Izq=RotacionI(Actual.Izq);
            return RotacionD(Actual);
        }
    }
    
            
 
    if(FactorB<-1&&Actual.Der!=null){
        
        if(Cancion.getNombre().compareToIgnoreCase(Actual.Der.Cancion.getNombre())>0){
            return RotacionI(Actual);
        }
      
        if(Cancion.getNombre().compareToIgnoreCase(Actual.Der.Cancion.getNombre())<0){
            Actual.Der=RotacionD(Actual.Der);
            return RotacionI(Actual);
        }
    }
        
        return Actual;
    }
    

public int RepetidosTotal(){
    
    
    return  Repetidos(Raiz);
    
}

private int Repetidos(NodoAVL actual) {
    if(actual==null){
        return 0;
    }
    
    return actual.getRepetido()+Repetidos(actual.Izq)+Repetidos(actual.Der);
}
    
    public Playlist BusquedaParcial(String Buscado){
        
        Playlist Resultados=new Playlist("Busqueda");
        
        BusquedaParcialPriv(Raiz,Buscado,Resultados);
        
        
        return Resultados;
    }
    
    
    private void BusquedaParcialPriv(NodoAVL Actual,String Buscado,Playlist Resultado){
        

        if(Actual==null){
            
            return;
        }
        
        BusquedaParcialPriv(Actual.Izq,Buscado,Resultado);
        
        String Coincidencia=Actual.Cancion.getNombre().toLowerCase();
        
        
        if(Coincidencia.contains(Buscado.toLowerCase())){
            
            Resultado.InsertarFin(Actual.Cancion);
        }
        
        
        BusquedaParcialPriv(Actual.Der,Buscado,Resultado);
        
    }
    
    
    
    public Archivomp3 Buscar(String Nombre){
        
  
      return BuscarP(Raiz,Nombre);  
    }
    
    
    
    private Archivomp3 BuscarP(NodoAVL Actual,String Nombre){
        
        
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
    
    
    
    
    public long Tiempo(String Nombre){
        
        long InicioT=System.nanoTime();

        Buscar(Nombre);

        long FinT=System.nanoTime();

        return FinT -InicioT;
        
    }
    
    public long TiempoParcial(String Nombre){
        
        long InicioT=System.nanoTime();

        BusquedaParcial(Nombre);

        long FinT=System.nanoTime();

        return FinT -InicioT;
        
    }
    
    
    
    
    private NodoAVL Menor(NodoAVL Actual){
        
  
        while(Actual.Izq!=null){
            
            Actual=Actual.Izq;
        }
        return Actual;
    }
    
    
    public void Eliminar(String Buscado){
        
        Raiz=EliminarP(Raiz,Buscado);
        
    }
    
    private NodoAVL EliminarP(NodoAVL Actual,String Nombre){
        
        if(Actual==null) return null;
        
        
        if(Nombre.compareToIgnoreCase(Actual.Cancion.getNombre())<0){
            
            Actual.Izq=EliminarP(Actual.Izq,Nombre);
        }else if(Nombre.compareToIgnoreCase(Actual.Cancion.getNombre())>0){
            
         Actual.Der=EliminarP(Actual.Der,Nombre);
            
        }else{
            if(Actual.repetido>0){
            Actual.repetido--;
            return Actual;
            }
            
            if(Actual.Izq==null||Actual.Der==null){
               
                NodoAVL aux;
                
                if(Actual.Izq!=null){
                    aux=Actual.Izq;
                }else{
                    
                    aux=Actual.Der;
                }
                
                if(aux==null){
                    
                    Actual=null;
                    
                }else{
                    Actual=aux;
                }
                
            }else{
                
                NodoAVL menor=Menor(Actual.Der);
                
                Actual.Cancion=menor.Cancion;
                
                Actual.repetido=menor.repetido;
                
                Actual.Der=EliminarP(Actual.Der,menor.Cancion.getNombre());
                
                
            }
            
        } if(Actual==null){
            
            return null;
        }
        
         Actual.altura=1+Math.max(Niveles(Actual.Izq), Niveles(Actual.Der));
            
            int FactorB=Balance(Actual);
            
            if(FactorB>1&&Balance(Actual.Izq)>=0){
                
                
                return RotacionD(Actual);
                
            }
     
            if(FactorB>1&&Balance(Actual.Izq)<0){
                
                
                Actual.Izq=RotacionI(Actual.Izq);
                
                return RotacionD(Actual);
                
            }
            if(FactorB<-1&&Balance(Actual.Der)<=0){
                return RotacionI(Actual);
            }
            
         
            if(FactorB<-1&&Balance(Actual.Der)>0){
                
                Actual.Der=RotacionD(Actual.Der);
                return RotacionI(Actual);
                
            }
        
        return Actual;
    }
    
    
    
    
    public Playlist InOrden(){
        
        Playlist Lista= new Playlist();
        
        InOrdenP(Raiz,Lista);
     
        return Lista;
    }
    
 
    
    public ArrayList<Archivomp3> InordeP(){
        
         ArrayList<Archivomp3>lista=new ArrayList<>();
         InordenP(Raiz,lista);
         
         return lista;
    }
    
    
    private void InordenP(NodoAVL Actual,ArrayList<Archivomp3>Lista){
        
        if(Actual!=null){
           InordenP(Actual.Izq,Lista);
            Lista.add(Actual.Cancion);
            InordenP(Actual.Der,Lista);
        }
        
    }
    private void InOrdenP(NodoAVL Actual,Playlist Lista){
        
        if(Actual!=null){
       
            InOrdenP(Actual.Izq,Lista);
            Lista.InsertarFin(Actual.Cancion);
            InOrdenP(Actual.Der,Lista);
            
        }
     
    }
    
  
    public ArrayList<Archivomp3> PreOrden(){
    
    ArrayList<Archivomp3> lista=new ArrayList<>();
    PreOrdenP(Raiz, lista);

    return lista;
}





private void PreOrdenP(NodoAVL Actual,ArrayList<Archivomp3>lista){
    if(Actual!=null){
        lista.add(Actual.Cancion);
        PreOrdenP(Actual.Izq, lista);
        PreOrdenP(Actual.Der, lista);
    }
}





public ArrayList<Archivomp3>PostOrden(){

    ArrayList<Archivomp3> lista=new ArrayList<>();
    PostOrdenP(Raiz, lista);
    
    
    
    return lista;
}



private void PostOrdenP(NodoAVL Actual,ArrayList<Archivomp3>lista){
    if(Actual!=null){
        PostOrdenP(Actual.Izq,lista);
        PostOrdenP(Actual.Der, lista);
        lista.add(Actual.Cancion);
        
    }
}

public int size() {
    return Suma(Raiz);
}


private int Suma(NodoAVL actual){
    
    if(actual==null){
        return 0;
    }

    return 1+Suma(actual.Izq)+Suma(actual.Der);
}

public void DibujarArbol(String archivo) {
    try (FileWriter Escritura = new FileWriter(archivo); PrintWriter escribo = new PrintWriter(Escritura)) {
        
        escribo.println("digraph AVL {");
        escribo.println("    bgcolor=\"#181818\";");
        // Quitamos el DPI alto. SVG es vectorial por defecto.
        escribo.println("    nodesep=\"0.5\";");
        escribo.println("    ranksep=\"0.5\";");
        escribo.println("    node [shape=ellipse, style=filled, fillcolor=\"#1ED760\", fontcolor=white, fontname=\"Arial\", color=\"red\", fontsize=10];");
        escribo.println("    edge [color=\"red\", arrowhead=vee];");

        if (Raiz == null) {
            escribo.println("    \"Árbol Vacío\" [shape=none, fontcolor=white];");
        } else {
            Graphiz(Raiz, escribo);
        }

        escribo.println("}");
        
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("ERRORD: " + e);
    }
}


  private void Graphiz(NodoAVL actual,PrintWriter escribir){

   if(actual==null){
        System.out.println("vacio");
        return;
    }

    String nombre=actual.Cancion.getNombre().replace("\"","\\\"");

    
    escribir.println("    \""+nombre+"\" "+ "[shape=ellipse, label=\""+nombre+"\\nTamaño: "+actual.Cancion.getTamaño()+" | Repe: "+actual.repetido+"\"];");
    
    if(actual.Izq!=null){

        String hijoIzq=actual.Izq.Cancion.getNombre().replace("\"","\\\"");

        escribir.println("    \""+nombre+"\" -> \""+hijoIzq+"\";");
        
        Graphiz(actual.Izq, escribir);

    }
    else{

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
        escribir.println("    \""+nombre+"\" -> "+invisible+" [style=invis];");
    }
  
}
  
}
