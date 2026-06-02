
package proyectospotify;

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
        
        return Niveles(Nodo.Izq)- Niveles(Nodo.Der);
    }
    
    
    private NodoAVL RotacionD(NodoAVL NodoRO){
        
        NodoAVL NuevaR=NodoRO.Izq;
        
        NodoAVL Solin=NuevaR.Der;
        
        NuevaR.Der=NodoRO;
        
        NodoRO.Izq=Solin;
        
        NodoRO.altura=Math.max(Niveles(NodoRO.Izq),Niveles(NodoRO.Der))+1;
        
        NuevaR.altura=Math.max(Niveles(NuevaR.Izq),Niveles(NuevaR.Der))+1;
        
        
        return NuevaR;
    }
    
    
    
    
    private NodoAVL RotacionI(NodoAVL NodoRO){
        
        NodoAVL NuevaR= NodoRO.Der;
        
        NodoAVL Solin=NuevaR.Izq;
        
        NuevaR.Izq=Solin;
        
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
            
            if(FactorB>1&&Cancion.getNombre().compareToIgnoreCase(Actual.Cancion.getNombre())<0){
                
                
                return RotacionD(Actual);
                
            }
            
            
            if(FactorB<-1&&Cancion.getNombre().compareToIgnoreCase(Actual.Cancion.getNombre())>0){
                
                return RotacionI(Actual);
                
            }
            
            
            
            
            
            if(FactorB>1&&Cancion.getNombre().compareToIgnoreCase(Actual.Cancion.getNombre())>0){
                
                
                Actual.Izq=RotacionI(Actual.Izq);
                
                return RotacionD(Actual);
            }
            
         
            if(FactorB<-1&&Cancion.getNombre().compareToIgnoreCase(Actual.Cancion.getNombre())<0){
                
                Actual.Der=RotacionD(Actual.Der);
                
                return RotacionI(Actual);
                
            }
        
        
        
        return Actual;
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
        
        
        if(Coincidencia.contains(Buscado)){
            
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
    
    
    
    
    private NodoAVL Menor(NodoAVL Actual){
        
        NodoAVL Comparador=Actual.Izq;
        while(Actual.Izq!=null){
            
            Comparador=Actual.Izq;
        }
        return Comparador;
    }
    
    
    public void Eliminar(String Buscado){
        
        Raiz=EliminarP(Raiz,Buscado);
        
    }
    
    public NodoAVL EliminarP(NodoAVL Actual,String Nombre){
        
        if(Actual==null) return null;
        
        
        if(Nombre.compareToIgnoreCase(Actual.Cancion.getNombre())<0){
            
            return EliminarP(Actual.Izq,Nombre);
        }else if(Nombre.compareToIgnoreCase(Actual.Cancion.getNombre())>0){
            
         return EliminarP(Actual.Der,Nombre);
            
        }else{
            if(Actual.repetido>1){
            Actual.repetido--;
            return Actual;
            }
            
            if(Actual.Izq==null||Actual.Der==null){
               
                NodoAVL aux;
                
                if(Actual.Izq==null){
                    aux=Actual.Izq;
                }else{
                    
                    aux=Actual.Der;
                }
                
                if(aux==null){
                    
                    Actual=null;
                    
                }else{
                    
                    Actual=aux.Izq;
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
                
                
                Actual.Izq=RotacionI(Actual.Izq);
                
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
        
         ArrayList<Archivomp3> lista=new ArrayList<>();
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
    
    //pendiente
    public ArrayList<Archivomp3> PreOrden(){
    
    ArrayList<Archivomp3> lista=new ArrayList<>();
    PreOrdenP(Raiz, lista);

    return lista;
}



//pendiente

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



private void PostOrdenP(NodoAVL Actual, ArrayList<Archivomp3> lista){
    if(Actual != null){
        PostOrdenP(Actual.Izq, lista);
        PostOrdenP(Actual.Der, lista);
        lista.add(Actual.Cancion);
        
    }
}
    
}
