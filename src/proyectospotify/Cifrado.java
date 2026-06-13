
package proyectospotify;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Cifrado {
    
    
    
    public Cifrado(){
        
        
    }
    /*
    Cifrado por sustitucion basado en la tabla periodica y cada
    elemento apunta a una letra del alafaBETO, permite exportar e importar playlist  
    */
    
    // arreglo de los elementos alafabeto cifrado
    private static final String[]TABLA={
    "H","He","Li","Be","B","C","N","O","F","Ne","Na","Mg","Al","Si","P","S","Cl","Ar","K","Ca","Sc","Ti","V","Cr","Mn","Fe","Co","Ni","Cu","Zn",
    "Ga","Ge","As","Se","Br","Kr","Rb","Sr","Y","Zr","Nb","Mo","Tc","Ru","Rh","Pd","Ag","Cd","In","Sn","Sb","Te","I","Xe","Cs","Ba","La","Ce","Pr","Nd",
    "Pm","Sm","Eu","Gd","Tb","Dy","Ho","Er","Tm","Yb","Lu","Hf","Ta","W","Re","Os","Ir","Pt","Au","Hg","Tl","Pb","Bi","Po","At","Rn","Fr","Ra","Ac","Th",
    "Pa","U","Np","Pu","Am","Cm","Bk","Cf","Es","Fm", "Md","No","Lr","Rf","Db","Sg","Bh","Hs","Mt","Ds", "Rg","Cn","Nh","Fl","Mc","Lv","Ts","Og"
    };
   //alfabeto real
   private static final String BETO=" abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"+",.-_'()[]{}!?;:+*/\\&@#%\"=<>|"
           +"áéíóúÁÉÍÓÚñÑüÜ¿¡"+"$~`^©®°";

    
   
   /*
   cifra un texto plano sustituyendolo cada caracter por un elemento quimico 
   recibe como parametro el texto original y retorna el string cifrado
   */
    public static String Cifrar(String texto){

    StringBuilder resultado=new StringBuilder();

    for(int i=0;i<texto.length();i++){

        char c=texto.charAt(i);
        //buscamos el índice del caracter en el alfabeto plano
        int pos=BETO.indexOf(c);
        //validamos si existe el carcter y que su posicion no supere la cantidad de elementos
        if(pos!=-1&&pos<TABLA.length){
            resultado.append(TABLA[pos]);
        }else{
            //elemtos desconocidos
            resultado.append("?");
        }

        if(i<texto.length()-1){
            resultado.append("-");
        }
    }

    return resultado.toString();
}

    /*
    
    Recontruye el texto original a partir de la caneda de elemntos quimicos
    tiene como parametro un string el cual esta cifrado
    retorna la  texto orignal 
   
    */
public static String descifrar(String textoCifrado){

    StringBuilder resultado=new StringBuilder();
    //Rompemos la cadena usando el guion qu habiamos añadido
    String[] simbolos=textoCifrado.split("-");

    for(String simbolo:simbolos){

        int pos=-1;
        
        //bucamos dentro de la tabla para recupera el indice
        for(int i=0;i<TABLA.length;i++){

            if(TABLA[i].equals(simbolo)){
                pos=i;
                break;
            }
        }
        // volvemos al texto original
        if(pos!=-1&&pos<BETO.length()){
            resultado.append(BETO.charAt(pos));
        }else{
            resultado.append("?");
        }
    }

    return resultado.toString();
}
/*
Extrae los nombres de cada mp3, cifrandolos y guardandolo en un txt
recibe como parametro la playlist y la ruta del txt
*/
public static void exportarPlaylist(Playlist Exportar,String Txt){

    try(FileWriter writer=new FileWriter(Txt)){

        NodoDoble actual=Exportar.getInicio();

        while(actual!=null){
            
            
            String identificador=actual.getCancion().getNombre();
            String lineaCifrada=Cifrar(identificador);
            writer.write(lineaCifrada+"\n");

            actual=actual.getSiguiente();
        }

    }catch(IOException e){

        System.out.println("Error al exportar: "+e.getMessage());
    }
}


/*
Normaliza el archivo txt, decifra cada linea, busca en el avl
y retornamos una playlist

*/
public static Playlist importarPlaylist(MetodosAVL biblioteca,String nombre){
    
    Playlist nuevaPlaylist =new Playlist();

    try(BufferedReader reader=new BufferedReader(new FileReader(nombre))){
        String linea;

        while((linea=reader.readLine())!=null){
            String titulo =descifrar(linea);
            Archivomp3 cancionEncontrada=biblioteca.Buscar(titulo);

            if(cancionEncontrada!=null){
                
                nuevaPlaylist.InsertarFin(cancionEncontrada);
            }else{
                return null;
            }
        }
    }catch(IOException e){
        
    }
    return nuevaPlaylist;
}
}
    

    

