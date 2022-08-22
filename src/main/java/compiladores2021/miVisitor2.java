package compiladores2021;
import org.antlr.v4.runtime.tree.ParseTree;

import compiladores2021.compiladorParser.DeclaracionContext;

public class miVisitor2<ParseTree> extends compiladorBaseVisitor<ParseTree>{
    

    @Override
    public ParseTree visitDeclaracion(DeclaracionContext ctx) {
        System.out.println("HIJOS DE DECLA: "+ctx.getChildCount());
        System.out.println("HIJOS DE MASVAR: "+ctx.masvar().getChildCount());
        System.out.println("HIJOS DE DEF: "+ctx.definicion().getChildCount());
        System.out.println("HIJOS DE asdasdF: "+ctx.PCOMA().getText());
        return super.visitDeclaracion(ctx);
    }
}
