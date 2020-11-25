package assign6;
ssign7.

ssign7.

ssign7.
import assign6.ast.*;
ssign7.
import assign6.lexer.* ;
ssign7.
import assign6.parser.* ;
ssign7.
import assign6.unparser.*;
ssign7.
import assign6.typechecker.*;
ssign7.

ssign7.

ssign7.
public class Main {
ssign7.
    public static void main (String[] args) {
ssign7.
        Lexer lexer = new Lexer();
ssign7.
        Parser parser = new Parser(lexer);
ssign7.
        TypeChecker typeCheck = new TypeChecker(parser);
ssign7.
        Unparser unparser = new Unparser(typeCheck);
ssign7.

ssign7.
    }
ssign7.
}
ssign7.
