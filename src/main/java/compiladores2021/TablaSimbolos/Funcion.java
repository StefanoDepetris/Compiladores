package compiladores2021.TablaSimbolos;
import java.util.ArrayList;


public  class Funcion extends Id{

    private ArrayList<TipoDato> args=new ArrayList<TipoDato>();

    public  Funcion(String nombreDeFuncion, TipoDato dato){
        super(nombreDeFuncion,dato);

    }
    public void agregarParametro(TipoDato tipo){
        args.add(tipo);
    }
    public ArrayList<TipoDato> getArgs(){
        return args;
    }
  

}