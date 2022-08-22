package compiladores2021;

import java.util.ArrayList;
import java.util.HashMap;


import org.antlr.v4.runtime.tree.TerminalNode;
import compiladores2021.TablaSimbolos.Variable;
import compiladores2021.TablaSimbolos.Funcion;
import compiladores2021.TablaSimbolos.Id;
import compiladores2021.TablaSimbolos.TablaSimbolo;
import compiladores2021.TablaSimbolos.TipoDato;
import compiladores2021.compiladorParser.BloqueContext;
import compiladores2021.compiladorParser.DeclaracionContext;
import compiladores2021.compiladorParser.DeclaracionFuncionContext;
import compiladores2021.compiladorParser.DefFuncionContext;
import compiladores2021.compiladorParser.DefinicionContext;
import compiladores2021.compiladorParser.FactoresContext;
import compiladores2021.compiladorParser.I_forContext;
import compiladores2021.compiladorParser.I_ifContext;
import compiladores2021.compiladorParser.I_whileContext;

import compiladores2021.compiladorParser.LadoAContext;
import compiladores2021.compiladorParser.LadoBContext;
import compiladores2021.compiladorParser.LlamadaFuncionContext;
import compiladores2021.compiladorParser.MasvarContext;
import compiladores2021.compiladorParser.OalContext;
import compiladores2021.compiladorParser.ParametroContext;
import compiladores2021.compiladorParser.SecvarContext;


public class miListener extends compiladorBaseListener {
//El compiladorBaseListener tieene funcionaes que son enterIntrucciones()
// exitInstrucciones() que son entra y salida de la regla.
//Hay informacion que no tenemos apenas entrramos a unregla que solo la tenemos
//en el exit por eso tenemos para cada regla gramtical tenemos un enter y un exit.

//Tambien tenemos enterEveryRUle() y exitEveryRule()

//Cuando llegmaos a la hojas tenemos visitTerminal() y visitErrorNode()

    private Integer token=0;
    private Integer dlc=0;
    private Integer vars=0;
    ArrayList<Id> ids;  //List para guardar las variables de masvar es decir cuando es int a ,d, c ..
    ArrayList<Id> parametros;
    HashMap<String,ArrayList> parametros_por_funcion=new HashMap<>();;
    private TipoDato tipo;
    private TablaSimbolo tabla;

    @Override public void enterPrograma(compiladorParser.ProgramaContext ctx) {
        System.out.println("Comienza compilacion" );
        tabla= new TablaSimbolo();
        tabla.addContexto();                    //Este es el contexto global es decir siempre al crear un programa tenemos un contexto..
     }
     @Override
     public void enterI_while(I_whileContext ctx) {
        System.out.println("\n @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@   WHILE   @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

        // if(ctx.instruccion()!=null){
        //     tabla.addContexto();
        // }
       
        // tabla.addContexto();
        super.enterI_while(ctx);
     }

     @Override
     public void exitI_while(I_whileContext ctx) {
        // tabla.recorrerContext();
        System.out.println("\n @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@  FIN  WHILE   @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        tabla.delContexto();
        tabla.recorrerContext();
        super.exitI_while(ctx);
     }
     @Override
     public void enterI_for(I_forContext ctx) {
        System.out.println(" \n @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@  FOR   @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

        // if( ctx.instruccion()!=null){
        //     System.out.println("Se agrego un nuevo contexto %%%%%%%%%%%%%%%%%%%%%");
        //     tabla.addContexto();
        // }       
        // tabla.addContexto();

        super.enterI_for(ctx);
     }
     @Override
     public void exitI_for(I_forContext ctx) {

        System.out.println(" \n @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ EXIT  FOR   @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        tabla.delContexto();
        tabla.recorrerContext();
        super.exitI_for(ctx);
     }
     @Override
     public void enterI_if(I_ifContext ctx) {
        tabla.recorrerContext();
        System.out.println("\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@  IF   @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    
        // tabla.addContexto();
  

        super.enterI_if(ctx);
     }

     @Override
     public void exitI_if(I_ifContext ctx) {
         System.out.println("\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@  EXIT IF   @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
         tabla.delContexto();
         tabla.recorrerContext();
         super.exitI_if(ctx);
     }
    
     @Override
     public void enterBloque(BloqueContext ctx) {
        System.out.println("\n%%%%%%%%%%%%%%% INICIO BLOQUE   %%%%%%%%%%%%%");
        if( ctx.parent.parent !=null &&!(ctx.parent.parent instanceof DefFuncionContext)){//Por que para DefFuncion para los paremetro tenemos que empezar antes el contexto
            tabla.addContexto();
        }
      
        super.enterBloque(ctx);
     }

     @Override
     public void enterDefFuncion(DefFuncionContext ctx) {
        System.out.println("\n%%%%%%%%%%%%%%% DEF FUNCION   %%%%%%%%%%%%%");
        // System.out.println("Se agrego un nuevo contexto POR FUNCION");
         tabla.addContexto();
         super.enterDefFuncion(ctx);
     }

     @Override
     public void exitParametro(ParametroContext ctx) {


        if(ctx.parent instanceof DefFuncionContext){
            while(ctx.ID()!=null){

                if(ctx.TIPO_DATO()!=null){
                    setTipo(ctx.TIPO_DATO().toString());
                    Id id=new Variable((ctx.ID()).toString(),tipo);    //Se crea el parametro
                    System.out.println("PARAMETRO NUEVO " +id.getTipo()+"  "+ id.getId());
                    if(tabla.buscarSimboloLocal(id)){
                        System.out.println("ID YA EXISTE EN CONTEXTO ACTUAL");
                    }
                    else{
                        tabla.agregarAlContextoActual(id);
                        tabla.recorrerContext();
                    }
                }
                   
    
                if(ctx.parametro()!=null ){
                    ctx.children=ctx.parametro().children;//para iterar
                    // ctx.masvar().copyFrom(ctx.masvar().masvar());
                }else if(ctx.parametro()==null){
                    break;
                }
              
               
            }
        }else if(ctx.parent instanceof DeclaracionFuncionContext){
            while(ctx.ID()!=null){

                if(ctx.TIPO_DATO()!=null){
                    setTipo(ctx.TIPO_DATO().toString());
                    Id id=new Variable((ctx.ID()).toString(),tipo);    //Se crea el parametro
                    System.out.println("Se agrego un parametro a la lista  " +id.getTipo()+"  "+ id.getId());
                    parametros.add(id);     //solo se agrega ala lista 

                    // if(tabla.buscarSimboloLocal(id)){
                    //     System.out.println("ID YA EXISTE EN CONTEXTO ACTUAL");
                    // }
                    // else{
                    //     // tabla.agregarAlContextoActual(id);
                        
                    //     // tabla.recorrerContext();
                    // }
                }
                   
    
                if(ctx.parametro()!=null ){
                    ctx.children=ctx.parametro().children;//para iterar
                    // ctx.masvar().copyFrom(ctx.masvar().masvar());
                }else if(ctx.parametro()==null){
                    break;
                }
              
               
            }
        }
         
         // TODO Auto-generated method stub
         super.exitParametro(ctx);
     }
     @Override
     public void exitDefFuncion(DefFuncionContext ctx) {                // TIPO_DATO ID PA parametro PC   instruccion
    
        
        System.out.println("\n%%%%%%%%%%%%%%%  EXIT DEF FUNCION   %%%%%%%%%%%%%");

        tabla.delContexto();
        tabla.recorrerContext();
        super.exitDefFuncion(ctx);
     }

     @Override
     public void exitLlamadaFuncion(LlamadaFuncionContext ctx) {
        System.out.println("\n%%%%%%%%%%%%%%%   LLAMADA FUNCION   %%%%%%%%%%%%%");
         //Mi idea aca es  ahcer una lista de los parametros inicializados pero una lista de que? de booelanos, de stirng, de int?
         int numero_param=0;
         ArrayList<Id> idsParametros=parametros_por_funcion.get(ctx.ID().toString());
        if(ctx.ID()!=null){
    
            if(!tabla.buscarSimbolo(ctx.ID().getText().toString())){
                System.out.println("Error no aparece el simbolo en el contexto:  "+ ctx.ID().toString());
            }else{
                 
            }

            while(ctx.parametro_Inicializado()!=null){

                if(ctx.parametro_Inicializado().ladoA()!=null){
                    
                        Id parametro=idsParametros.get(numero_param);
                        if(parametro.getTipo() ==TipoDato.INT && ! (ctx.parametro_Inicializado().ladoA().ENTERO()!=null ||ctx.parametro_Inicializado().ladoA().oal()!=null)){
                            System.out.println("Error tipo dato parametro. ");
                        }
                        try {
                            if(ctx.parametro_Inicializado().ladoA().oal().term().factores().ID()!=null){//Para el caso de que Oal tenga una id de tipo Int
                                String idOal=ctx.parametro_Inicializado().ladoA().oal().term().factores().ID().toString();
                                if(tabla.buscarSimboloReturnVar(idOal)!=null){
                                    Id idOalAux=tabla.buscarSimboloReturnVar(idOal);
                                    if(parametro.getTipo() ==TipoDato.INT && idOalAux.getTipo()!=TipoDato.INT){
                                        System.out.println("Error tipo dato parametro. ");
                                    }
    
                                }else{
                                    System.out.println("No existe el Id en la tabla de simbolos ");
                                }
                            } 
                        } catch (Exception e) {
                            //TODO: handle exception
                        }
                      
                        
                        //  if(parametro.getTipo() ==TipoDato.CHAR && (ctx.parametro_Inicializado().ladoA().iCHAR()==null)){
                        //     System.out.println("Error tipo dato parametro. ");
                        // }
                    
                        if(ctx.parametro_Inicializado().ladoA().ID()!=null){
                            //Tengo que ver si esta en la tabla de simbolos.
                            if(!tabla.buscarSimbolo(ctx.parametro_Inicializado().ladoA().ID().getText().toString())){
                                System.out.println("Error no esta el id pasado como parmetro. ");
                            }else{
                                 Id idParametro=tabla.buscarSimboloReturnVar(ctx.parametro_Inicializado().ladoA().ID().getText().toString());
                                 if(idParametro.getTipo()!=parametro.getTipo()){
                                    System.out.println("Error tipo dato parametro. ");
                                 }
                            
                            }
                        }
                            
                    numero_param++;
                }
            


                if(ctx.parametro_Inicializado().parametro_Inicializado()!=null){
                    ctx.parametro_Inicializado().children=ctx.parametro_Inicializado().parametro_Inicializado().children;
                }else{
                    break;
                }
               ;
                
              
            }
            if(numero_param!=parametros_por_funcion.get(ctx.ID().toString()).size()){
                int diferencia=parametros_por_funcion.get(ctx.ID().toString()).size()-numero_param;
                // System.out.println("dinosaurio"+parametros_por_funcion.get(ctx.ID().toString()).size()  +"   " +numero_param);

                if(diferencia<0){
                    System.out.println("Error numero de parametro: Sobra "+ diferencia*-1 );
                }
                else{
                    System.out.println("Error numero de parametro: falta "+ diferencia );
                }
               

            }else{
              
            }
        }
         super.exitLlamadaFuncion(ctx);
     }

     @Override
     public void enterDeclaracionFuncion(DeclaracionFuncionContext ctx) {
        System.out.println(" ");
        System.out.println(" ");
   
        System.out.println("@@@@@DECLARACION DE FUNCION @@@@@@@ ");
        System.out.println(" ");
        parametros=new ArrayList<>();
        // parametros_por_funcion=new HashMap<>();
        // parametros=new ArrayList<>();
        // parametros_por_funcion=new HashMap<>();

         super.enterDeclaracionFuncion(ctx);
     }
     @Override
     public void exitDeclaracionFuncion(DeclaracionFuncionContext ctx) {
        System.out.println("Funcion encontrada!!! %%%%% %%%%% %%%% ");
        System.out.println(" ");
        setTipo(ctx.TIPO_DATO().toString());

        Id id=new Funcion(ctx.ID().toString(),tipo);
        if(tabla.buscarSimbolo(id)){
            System.out.println("ID YA EXISTE EN CONTEXTO ACTUAL");
        }
        else{
            tabla.agregarAlContextoActual(id);
            tabla.recorrerContext();
        }

        while(ctx.parametro()!=null){

            if(ctx.parametro().TIPO_DATO()!=null){
                setTipo(ctx.parametro().TIPO_DATO().toString());
                Id idParametro=new Variable((ctx.parametro().ID()).toString(),tipo);    //Se crea el parametro
                System.out.println("PARAMETRO NUEVO " +idParametro.getTipo()+"  "+ idParametro.getId());
            }
               


            if(ctx.parametro().parametro()!=null ){
                ctx.parametro().children=ctx.parametro().parametro().children;//para iterar
                // ctx.masvar().copyFrom(ctx.masvar().masvar());
            }else if(ctx.parametro().parametro()==null){
                break;
            }
          
           
        }


        if(parametros.size()!=0 && parametros!=null){
            parametros_por_funcion.put(ctx.ID().toString(), parametros);
           
            System.out.println("La cantidad de parametros en esta funcion "+ ctx.ID().toString()+" es: " +  parametros_por_funcion.get(ctx.ID().toString()).size());
        }

        System.out.println("@@@@ EXIT DECLARACION DE FUNCION @@@@@@@ ");

     }

  
    
     @Override
     public void exitBloque(BloqueContext ctx) {
        //  tabla.delContexto();
         tabla.recorrerContext();
        
         System.out.println("\n%%%%%%%%%%%%%%% FIN BLOQUE   %%%%%%%%%%%%%");
         super.exitBloque(ctx);
         
     }

     @Override
     public void exitSecvar(SecvarContext ctx) {
         Id idLadoA=new Variable("",TipoDato.INT);
         if(ctx.ladoA().ID()!=null ){
            if(tabla.buscarSimbolo(ctx.ladoA().ID().toString())){
                idLadoA=tabla.buscarSimboloReturnVar(ctx.ladoA().ID().toString());
            }
            else{
                System.out.println("Error no aparece el simbolo en el contexto global "+ ctx.ladoA().ID().toString());
            }
                
         }    
         if (ctx.ladoB().ID()!=null)   {
            if(tabla.buscarSimboloLocal(ctx.ladoB().ID().toString())){
                Id idLadoB=tabla.buscarSimboloReturnVar(ctx.ladoB().ID().toString());  
                if(idLadoB.getTipo().equals(idLadoA.getTipo())==false){
                    System.out.println("$$$$$$$$$$$$$Error no son el mismo tipo ");

                }
            }
            else{
                System.out.println("Error no aparece el simbolo " + ctx.ladoB().ID().toString());
            }
         }
         try {  //Aca mira se fija en el caso de que el lado B sea una operacion aritmentica con suma resta con factores y demas.
            if(ctx.ladoB().oal().t().term().factores().ID()!=null){//en el caso de suma resta

               String id=ctx.ladoB().oal().t().term().factores().ID().toString();
               if(tabla.buscarSimbolo(id)){
                   Id idLadoB=tabla.buscarSimboloReturnVar(id);
                   if(idLadoB.getTipo().equals(idLadoA.getTipo())==false){
                        System.out.println("$$$$$$$$$$$$$Error no son el mismo tipo ");
                   }
               }
            }  
            else if(ctx.ladoB().oal().term().factores().ID()!=null){    //multiplicacion division..

                String id=ctx.ladoB().oal().term().factores().ID().toString();
                if(tabla.buscarSimbolo(id)){
                    Id idLadoB=tabla.buscarSimboloReturnVar(id);
                    if(idLadoB.getTipo().equals(idLadoA.getTipo())==false){
                        System.out.println("$$$$$$$$$$$$$Error no son el mismo tipo ");
                    }
                }

            }
         } catch (Exception e) {
         }
         
         super.exitSecvar(ctx);
     }

     @Override
     public void enterDeclaracion(DeclaracionContext ctx) {
         dlc++;
                                                                                                                    //Al HACER UN DECLARACION TENDRIA QUE  BUSCAR SI EXISTE EL ID DE LA VARIABLE QUE ESTOY CREANDO ANTES DE AGREGARLA AL CONTEXT
         System.out.println("\n############################declaracion###################################");

         System.out.println("El start de Declaracion es: -------->"+ctx.getStart().getText());                      //ESTO NOS RE INTERESA PARA VER SI EMPIEZA CON INT O FLOAT O LO QUE SEA Y DESPUES EN SECVAR VER SI EL ID COINCIDE..
         System.out.println("Inicio de declaracion el text: "+ctx.getText() +"El start es: "+ctx.getStart() +"El stop es: "+ctx.getStop());
         ids=new ArrayList<>();//Creo una array para guardar los ID porque pueden ser varios ej: int a,b,c,d
     }

     @Override
     public void exitFactores(FactoresContext ctx) {
         if(ctx.ID()!=null){
            if(!tabla.buscarSimbolo(ctx.ID().toString())){
                System.out.println("ID PERTENECIENTE OAL NO EXISTE");
              
            }
         }
         super.exitFactores(ctx);
     }

     @Override
     public void exitDeclaracion(DeclaracionContext ctx) {  //El tema de la declaracion es que pueden declararse varias al mismo tiempo pero cuantas? bueno por eso todo esto
        System.out.println("declaracion -> Tipo dato es: " + ctx.TIPO_DATO().toString());
        System.out.println("declaracion -> id es: " + ctx.ID().toString());
            setTipo(ctx.TIPO_DATO().toString());

            Id id=new Variable(ctx.ID().toString(),tipo);
            if(tabla.buscarSimboloLocal(id)){
                System.out.println("ID YA EXISTE EN CONTEXTO ACTUAL");

            }
            else{
                tabla.agregarAlContextoActual(id);
            }
            if(ctx.masvar()==null){
                System.out.println("El maxvar es nulo!");
            }
            while(ctx.masvar()!=null){
                
                if(ctx.masvar().ID()!=null){
                   
                    Id idmasvar=new Variable(ctx.masvar().ID().getText(),tipo);
                    System.out.println("MASVAR-> " + ctx.TIPO_DATO() + " " + ctx.masvar().ID().getText());
                    ids.add(idmasvar);
                
                }
               
                if(ctx.masvar().masvar()!=null ){
                    ctx.masvar().children=ctx.masvar().masvar().children;//para iterar
                    // ctx.masvar().copyFrom(ctx.masvar().masvar());
                }else if(ctx.masvar().masvar()==null){
                    break;
                }
              
            }
            
            for (Id idaux : ids) {
                System.out.println("MASVAR list-> " + idaux.getTipo() + " " + idaux.getId());
                if( !tabla.buscarSimbolo(idaux)){
                     tabla.agregarAlContextoActual(idaux);
                }
                else{
                    System.out.println("Error masvar->  El simbolo ya existe: " + idaux.getId());
                }
            }

            if(ctx.definicion()!=null){
                String idDefinicion="";
                try {
                    if(ctx.definicion().ladoB().ID()!=null)
                    {
                       idDefinicion= ctx.definicion().ladoB().ID().toString();
                    }    
                    if(tabla.buscarSimbolo(idDefinicion)){
                        Id idDefinicionAux=tabla.buscarSimboloReturnVar(idDefinicion);
                        if(idDefinicionAux.getTipo().equals(tipo)==false){
                            System.out.println("$$$$$$$$$$$$$Error no son el mismo tipo ");
                        }
                    }
                } catch (Exception e) {
                    //TODO: handle exception
                }
              
        
            }
          
            tabla.recorrerContext();

          System.out.println("\n############################Fin declaracion##########################");
         super.exitDeclaracion(ctx);
      }

      @Override
      public void enterMasvar(MasvarContext ctx) {
        System.out.println("\n############################Enter masvar##########################");
          super.enterMasvar(ctx);
      }
      @Override
      public void exitMasvar(MasvarContext ctx) {
          System.out.println("\n############################Fin masvar##########################");
          super.exitMasvar(ctx);
      }
      @Override
      public void enterDefinicion(DefinicionContext ctx) {
        System.out.println("\n############################Enter Definicion##########################");
          super.enterDefinicion(ctx);
      }
      @Override
      public void exitDefinicion(DefinicionContext ctx) {
          if(ctx.ladoB()!=null && ctx.ladoB().ID()!=null){
            if(tabla.buscarSimboloLocal(ctx.ladoB().ID().toString())){
                System.out.println("Definicion -> id es: " + ctx.ladoB().ID().toString());
            }
            else{
                System.out.println("ERROR SIMBOLO NO ENCONTRADO");
            }
        }
          super.exitDefinicion(ctx);
          System.out.println("\n############################Exit Definicion##########################");
      }

      @Override
      public void enterSecvar(SecvarContext ctx) {
        //   System.out.println("\n##########################EnterSecVar###############################");
        //  System.out.println("El texto de Secvar es-------->"+ctx.getText());
          super.enterSecvar(ctx);
      }
      @Override
      public void enterLadoA(LadoAContext ctx) {                //ESTE TAMBIEN ME IMPORTA MUCHO PORQUE ACA TENGO QUE VER SI, LA DECLARACION FUE UN INT
      }

      @Override
      public void enterLadoB(LadoBContext ctx) {
                   //ESTE ME IMPORTA PORQUE TENGO QUE VER SI EL LADO B ES UN VARIABLE Y ESTA DEFINIDA.
          super.enterLadoB(ctx);
      }
      

     @Override public void visitTerminal(TerminalNode node) {
         token++;

    }
  
            
         

        
     public void setTipo(String node){

        switch(node){
            case "void": tipo=TipoDato.VOID; 
            break;
            case "int": tipo=TipoDato.INT;
            break;
            case "char": tipo=TipoDato.CHAR;
            break;
            case "double": tipo=TipoDato.DOUBLE;
            break;
        }
     }

    @Override public void exitPrograma(compiladorParser.ProgramaContext ctx) {
        System.out.println("\nFin de compilacion" );
        System.out.println("Se encontraron: "+token +" tokens" );
        System.out.println("Se encontraron: "+dlc+" declaraciones");
        System.out.println("Se encontraron: "+vars+" variables");
     }

}