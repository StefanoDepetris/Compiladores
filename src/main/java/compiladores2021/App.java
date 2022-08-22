package compiladores2021;


import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.CommonTokenStream;

public class App {  
    public static void main(String[] args) throws Exception {

        // create a CharStream that reads from file
        CharStream input = CharStreams.fromFileName("src/entrada.txt");

        // create a lexer that feeds off of input CharStream
        compiladorLexer lexer = new compiladorLexer(input); 
        
        // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        
        // create a parser that feeds off the tokens buffer
        compiladorParser parser = new compiladorParser(tokens);
                 
        // create Listener
        compiladorBaseListener escucha = new miListener();


        // Conecto el objeto con Listeners al parser
        parser.addParseListener(escucha);

        // Solicito al parser que comience indicando una regla gramatical
        // En este caso la regla es el simbolo inicial
        
        // parser.programa();   


        ParseTree tree=parser.programa();
        miVisitor2<ParseTree> visitor=new miVisitor2<>();
        visitor.visit(tree);
        System.out.println(visitor);


        //ParseTree tree=parser.programa();
        //miVisitor<ParseTree> visitor=new miVisitor<>();  
        //visitor.visit(tree);
        // ParseTree tree =  parser.s();
        // Conectamos el visitor
        // Caminante visitor = new Caminante();
        // visitor.visit(tree);
        // System.out.println(visitor);
        // System.out.println(visitor.getErrorNodes());
        // Imprime el arbol obtenido
        // System.out.println(tree.toStringTree(parser));
       // System.out.println(escucha);
       
    }
}
