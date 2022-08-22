package compiladores2021.TablaSimbolos;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

 public class TablaSimbolo{
     
    private List<HashMap<String,Id>> tabla;
    private static  TablaSimbolo TablaSimbolo;
    public TablaSimbolo(){
        tabla= new  ArrayList<HashMap<String,Id>>();//String es el nombre de la variable y Id() TENDRIA QUE SER UNA LinkedList
    }
    public static TablaSimbolo getSingletonInstance() {
        TablaSimbolo= new TablaSimbolo();
        return TablaSimbolo;  
    }

    public void addContexto(){
         HashMap<String,Id> map1 = new HashMap<String,Id>();
        // map1.put(id.getId(),id);
        tabla.add(map1);

    }

    public void delContexto(){
        int ultimoValor;
        if(tabla.size()>0){
             ultimoValor= tabla.size()-1;
             tabla.remove(ultimoValor);
             System.out.println("Se elimino el contexto!!!"); 
        }
        else{
           System.out.println("No tiene ningun nodo la Lista  de contextos");
        }
       
    }
    public boolean agregarAlContextoActual(Id id){
        if(tabla.size()>0){
            
           // System.out.println("Se puede agregar a la tabla porque hay un contexto creado");
            System.out.println("Se agrego al contexto exitosamente ");
            tabla.get(tabla.size()-1).put(id.getId(),id);
            return true;
        }
       else{
        System.out.println("No hay un contexto creado");
        return false;
       }

        
    }
    
    public boolean buscarSimbolo(Id id){

        for(HashMap<String,Id> mapa: tabla){ //Con esto recorro la lista
    
            for (HashMap.Entry<String, Id> entry : mapa.entrySet()) {                    //CAMBIAR POR CONSTAIN Y EL IF DE ARRIBA NO VA..
                if(entry.getKey().equals(id.getId())){
                    
                    return true;
                }
            }
        }
       
        return false;
    }

    public boolean buscarSimbolo(String id){

        for(HashMap<String,Id> mapa: tabla){ //Con esto recorro la lista
    
            for (HashMap.Entry<String, Id> entry : mapa.entrySet()) {                    //CAMBIAR POR CONSTAIN Y EL IF DE ARRIBA NO VA..
                if(entry.getKey().equals(id)){
                    return true;
                }
            }
        }
        return false;
    }

    public Id buscarSimboloReturnVar(String id){

        for(HashMap<String,Id> mapa: tabla){ //Con esto recorro la lista
    
            for (HashMap.Entry<String, Id> entry : mapa.entrySet()) {                    //CAMBIAR POR CONSTAIN Y EL IF DE ARRIBA NO VA..
                if(entry.getKey().equals(id)){
                    return  entry.getValue();
                }
            }
        }
       
        return null;
    }
    
    public boolean buscarSimboloLocal(Id id){
            HashMap<String,Id> maaux= tabla.get(tabla.size()-1);
           // maaux.containsKey()
           System.out.println("id.getId(): "+id.getId()); 
           
           if(maaux.containsKey(id.getId())){
               return true;
           }
           else{
          
            return false;
           }
           
    }
    public boolean buscarSimboloLocal(String id){
        HashMap<String,Id> maaux= tabla.get(tabla.size()-1);
       // maaux.containsKey()

       
       if(maaux.containsKey(id)){
            
           return true;
       }
       else{
      
        return false;
       }
       
}
    public void  recorrerContext(){
        System.out.println("    LA LISTA DE SIMBOLOS ES:"); 
        HashMap<String,Id> maaux= tabla.get(tabla.size()-1);
        for (Map.Entry<String, Id> entry : maaux.entrySet()) {
                System.out.println("        "+entry.getKey());
        }
    }
    


    public static void main(String[] args)
    {
        



    }
 }



        //  tabla.delContexto();
        //  if(tabla.buscarSimboloLocal(id)==true){
        //      System.out.println("Se encontro el simbolo");
        //  }
        //  else{
        //      System.out.println("No se encontro el simbolo");
        //  }
    




