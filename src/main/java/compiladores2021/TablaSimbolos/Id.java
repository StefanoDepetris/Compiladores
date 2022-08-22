package compiladores2021.TablaSimbolos;
public abstract class Id{
    private  String id;
    private TipoDato tipo;
    private boolean inicializado;
    private boolean usado;

    public  Id(String id, TipoDato tipo){
        this.id=id;
        this.tipo=tipo;
        inicializado=false;
        usado=false;

    }
    public void setInicicializado(){
        inicializado=true;
    }
    public boolean getInicializado(){
        return inicializado;
    }
    public void setUsado(){
        usado=true;
    }
    public boolean getUsado(){
        return usado;
    }

    public void  setId(String id){
        this.id=id;
    }
    public String getId(){
        return id;
    }
    public void setTipo(TipoDato tipo){
        this.tipo=tipo;
    }
    public TipoDato getTipo(){
        return tipo;
    }



}