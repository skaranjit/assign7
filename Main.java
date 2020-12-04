package assign7;


import assign7.ast.*;
import assign7.lexer.* ;
import assign7.parser.* ;
import assign7.unparser.*;
import assign7.typechecker.*;
import assign7.intercode.*;


public class Main {
    public static void main (String[] args) {
        Lexer lexer = new Lexer();
        Parser parser = new Parser(lexer);
        TypeChecker typeCheck = new TypeChecker(parser);
        InterCodeGen inter= new InterCodeGen(typeCheck);
        Unparser unparser = new Unparser(inter);

    }
}
