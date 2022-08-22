package compiladores2021;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.Context;

import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.tool.Rule;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;

import compiladores2021.compiladorParser.BloqueContext;
import compiladores2021.compiladorParser.DeclaracionContext;
import compiladores2021.compiladorParser.DeclaracionFuncionContext;
import compiladores2021.compiladorParser.DefFuncionContext;
import compiladores2021.compiladorParser.DefinicionContext;
import compiladores2021.compiladorParser.FactoresContext;
import compiladores2021.compiladorParser.I_forContext;
import compiladores2021.compiladorParser.I_ifContext;
import compiladores2021.compiladorParser.I_whileContext;
import compiladores2021.compiladorParser.InstruccionContext;
import compiladores2021.compiladorParser.InstruccionesContext;
import compiladores2021.compiladorParser.LadoBContext;
import compiladores2021.compiladorParser.LlamadaFuncionContext;
import compiladores2021.compiladorParser.MasvarContext;
import compiladores2021.compiladorParser.OalContext;
import compiladores2021.compiladorParser.ProgramaContext;
import compiladores2021.compiladorParser.SecvarContext;
import compiladores2021.compiladorParser.TermContext;

public class miVisitor<ParseTree> extends compiladorBaseVisitor<ParseTree> {
    String texto;
    int varTemp=0;
    List<ErrorNode> errores;
    HashMap<String,String> mapVarTemp=new HashMap<>();    

    public miVisitor(){
        errores=new ArrayList<>();
    }
    @Override
    public ParseTree visitPrograma(ProgramaContext ctx){
        System.out.println("VISITOR:Visitando primer nodo");
        return super.visitPrograma(ctx);
    }

    @Override
    public ParseTree visitErrorNode(ErrorNode node) {
        // TODO Auto-generated method stub
        addErrorNode(node);
        System.out.println("Errores.Size: " + errores.size());

        return super.visitErrorNode(node);
    }

    public void addErrorNode(ErrorNode node ){
        errores.add(node);
    }


    @Override
    public ParseTree visitDeclaracion(DeclaracionContext ctx) {
        System.out.println("VISITANDO DECLARACION");
        String returDeclaracion="";
        System.out.println("HIJOS DE DECLA: "+ctx.getChildCount());
        System.out.println("HIJOS DE MASVAR: "+ctx.masvar().getChildCount());
        System.out.println("HIJOS DE DEF: "+ctx.definicion().getChildCount());
        System.out.println("HIJOS DE asdasdF: "+ctx.PCOMA().getText());
    
        for(int i=0;i<ctx.getChildCount();i++){
            System.out.println("++ "+ctx.getChild(i).getText());
        }
        returDeclaracion =generadorVarTemporales(ctx,ctx.ID().getText());//Genera un Tn
        addTextoNodo(ctx,returDeclaracion);
       
        return super.visitDeclaracion(ctx);
    }

    @Override
    public ParseTree visitDefinicion(DefinicionContext ctx) {
        System.out.println("VISITANDO DEFINICION");
        if(ctx.ladoB()!=null){
            String returDeclaracion="=";
            if(ctx.ladoB().ID()!=null){
                if(mapVarTemp.get(ctx.ladoB().ID().getText())!=null){
                    returDeclaracion=returDeclaracion+mapVarTemp.get(ctx.ladoB().ID().getText());
               }
            }else if(ctx.ladoB().ENTERO()!=null){
                returDeclaracion=returDeclaracion+ctx.ladoB().ENTERO().getText();
            }
            addTextoNodo(ctx,returDeclaracion);
        }else{
            addTextoNodo(ctx,"");
        }
    
        return super.visitDefinicion(ctx);
    }

    @Override
    public ParseTree visitOal(OalContext ctx) {//ESTP ESTA MAL
        System.out.println("VISITANDO OAL");
        String returDeclaracion="";
        if(ctx.term().factores()!=null){
                if(ctx.term().factores().ID()!=null){
                    if(mapVarTemp.get(ctx.term().factores().ID().getText())!=null){
                        returDeclaracion=returDeclaracion+mapVarTemp.get(ctx.term().factores().ID().getText());
                }
        }else{
            returDeclaracion=returDeclaracion+ctx.getText();
        }
    }
        addTextoNodo(ctx,returDeclaracion);
        return super.visitOal(ctx);
    }
  
    @Override
    public ParseTree visitMasvar(MasvarContext ctx) {
        System.out.println("ENTRE A MASVAR ");
        if(ctx.ID()!=null){ //Problema acaaaaa
            String returDeclaracion=",";
            returDeclaracion=returDeclaracion+generadorVarTemporales(ctx,ctx.ID().getText());//Genera un Tn
            addTextoNodo(ctx,returDeclaracion);
        }else{
            addTextoNodo(ctx,";");
        }
     
        return super.visitMasvar(ctx);
    }

    // @Override
    // public ParseTree visitMasvar(MasvarContext ctx) {
    //     if(ctx.COMA()!=null){ //Problema acaaaaa
    //         String returDeclaracion=",";
    //         returDeclaracion=returDeclaracion+generadorVarTemporales(ctx,ctx.ID().getText());//Genera un Tn
    //         addTextoNodo(ctx,returDeclaracion);
    //     }else{
    //         addTextoNodo(ctx,";");
    //     }
     
    //     return super.visitMasvar(ctx);
    // }
    

    // @Override
    // public ParseTree visitSecvar(SecvarContext ctx) {
    //     String textaux="";

    //     if(ctx.ladoA().ID()!=null){
           
    //         if(mapVarTemp.get(ctx.ladoA().ID().getText())!=null){
    //             System.out.println(" ctx.ladoA().ID() " +ctx.ladoA().ID() + " " + mapVarTemp.get(ctx.ladoA().ID().getText()));
    //             textaux=mapVarTemp.get(ctx.ladoA().ID().getText());
    //         }
            
    //     }
    //     if(ctx.ladoB()!=null && ctx.ladoB().ID()!=null){

           
    //         textaux=textaux+ "=" + mapVarTemp.get(ctx.ladoB().ID().getText());
    //     }
    //     else if(ctx.ladoB()!=null && ctx.ladoB().oal()!=null){

    //         textaux=textaux+ "=" + ctx.ladoB().oal().getText();
    //         // oalManager(ctx.ladoB().oal());
    //     }
    //     addTextoNodo(ctx,textaux);//Porque es lo que importa de un codigo de 3 dim
    //     // TODO Auto-generated method stub


    //     return super.visitSecvar(ctx);
    // }

    private String generadorVarTemporales(RuleContext ctx,String nombre){
        String varTmpAux="";
        varTmpAux= "t"+varTemp;

        System.out.println("nombre "+nombre + " vartmp "+ varTmpAux);
        mapVarTemp.put(nombre,varTmpAux);
        varTemp++;
        return varTmpAux;
        // addTextoNodo(ctx,varTmpAux);
        
    }
    private void addTextoNodo(RuleContext ctx, String nombre) {
        if(ctx instanceof MasvarContext||ctx instanceof DefinicionContext || ctx instanceof OalContext  ){
            texto += nombre ;
        }else{
            texto +="\n" + nombre;
        }
       

    }

    @Override
    public String toString() {
        return texto;
    }



}
