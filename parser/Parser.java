package assign6.parser;
ssign7.

ssign7.
import assign6.ast.*;
ssign7.
import assign6.visitor.*;
ssign7.
import assign6.lexer.*;
ssign7.
import java.io.*;
ssign7.

ssign7.
public class Parser extends ASTVisitor
ssign7.
{
ssign7.
    public CompilationUnit cu = null;
ssign7.
    public Lexer lexer = null ;
ssign7.
    public Token look = null;
ssign7.
    public int level;
ssign7.
    String indent = "...";
ssign7.

ssign7.

ssign7.

ssign7.
    public Env top = null;
ssign7.

ssign7.
    public Parser (Lexer lexer)
ssign7.
    {
ssign7.
        this.lexer = lexer;
ssign7.
        cu = new CompilationUnit();
ssign7.
        move();
ssign7.
        visit(cu);
ssign7.
    }
ssign7.

ssign7.
    public Parser ()
ssign7.
    {
ssign7.
        cu = new CompilationUnit();
ssign7.
        move();
ssign7.
        visit(cu);
ssign7.
        level =0;
ssign7.
    }
ssign7.

ssign7.
    void move()
ssign7.
    {
ssign7.
        try
ssign7.
        {
ssign7.
            look = lexer.scan();
ssign7.
        }
ssign7.
        catch(IOException e)
ssign7.
        {
ssign7.

ssign7.
        }
ssign7.
    }
ssign7.

ssign7.

ssign7.
    void error(String s){
ssign7.
        println("Line " + lexer.line + ": " + s);
ssign7.
        exit(1);
ssign7.
    }
ssign7.

ssign7.

ssign7.
    void match(int t){
ssign7.
        try{
ssign7.
            if (look.tag == t) {
ssign7.
                println("matched: "+look.toString());
ssign7.
                move();
ssign7.

ssign7.
            }
ssign7.
            else if (look.tag == Tag.EOF)
ssign7.
                error("Syntax error: \";\" or \"}\" expected");
ssign7.
            else
ssign7.
                error("Syntax error: \"" + (char)t + "\" expected");
ssign7.
        }
ssign7.
        catch (Error e){
ssign7.

ssign7.
        }
ssign7.
    }
ssign7.

ssign7.

ssign7.
    void print(String s){
ssign7.
        System.out.print(s);
ssign7.
    }
ssign7.

ssign7.

ssign7.
    void println(String s){
ssign7.
        System.out.println(s);
ssign7.
    }
ssign7.

ssign7.

ssign7.
    void exit(int n){
ssign7.
        System.exit(n);
ssign7.
    }
ssign7.

ssign7.
    public void visit (CompilationUnit n)
ssign7.
    {
ssign7.
        n.block = new BlockStatementNode();
ssign7.
        n.block.accept(this);
ssign7.
    }
ssign7.

ssign7.

ssign7.
    public void visit (BlockStatementNode n)
ssign7.
    {
ssign7.
        match('{');
ssign7.
        n.sTable = top; // new code
ssign7.
        top = new Env(top); // new code
ssign7.
        n.decls = new Declarations();
ssign7.
        n.decls.accept(this);
ssign7.
        n.stmts = new Statements();
ssign7.
        n.stmts.accept(this);
ssign7.
        match('}');
ssign7.
	println("End of Block Statement");
ssign7.
        top = n.sTable; // new code
ssign7.
    }
ssign7.

ssign7.
    public void visit(Declarations n)
ssign7.
    {
ssign7.
        if (look.tag == Tag.BASIC)
ssign7.
        {
ssign7.
            n.decl = new DeclarationNode();
ssign7.
            n.decl.accept(this);
ssign7.
            n.decls = new Declarations();
ssign7.
            n.decls.accept(this);
ssign7.
        }
ssign7.
    }
ssign7.

ssign7.

ssign7.
    public void visit(DeclarationNode n)
ssign7.
    {
ssign7.
        n.type = new TypeNode();
ssign7.
        n.type.accept(this);
ssign7.
        n.id = new IdentifierNode();
ssign7.
        n.id.type = n.type.basic; // new code
ssign7.
        n.id.accept(this);
ssign7.
        top.put(n.id.w, n.id); // new code
ssign7.
        IdentifierNode tmp = (IdentifierNode)top.get(n.id.w); // new code
ssign7.
        println("&&&&&& tmp.type: " + tmp.type); // new code
ssign7.
        println("&&&&&& tmp.w: " + tmp.w); // new code
ssign7.

ssign7.
        match(';');
ssign7.
    }
ssign7.

ssign7.

ssign7.
    public void visit(TypeNode n)
ssign7.
    {
ssign7.
        if (look.toString().equals("int"))
ssign7.
            n.basic = Type.Int;
ssign7.
        else if (look.toString().equals("float"))
ssign7.
            n.basic = Type.Float;
ssign7.
        match(Tag.BASIC);
ssign7.
        if (look.tag == '[')
ssign7.
        {
ssign7.
            n.array = new ArrayTypeNode();
ssign7.
            n.array.accept(this);
ssign7.
        }
ssign7.
    }
ssign7.

ssign7.

ssign7.
    public void visit (ArrayTypeNode n)
ssign7.
    {
ssign7.
        match('[');
ssign7.
        ExprNode rhs_assign = null;
ssign7.

ssign7.
        if (look.tag == Tag.ID)
ssign7.
        {
ssign7.
            rhs_assign = new IdentifierNode();
ssign7.
            ((IdentifierNode)rhs_assign).accept(this);
ssign7.
        }
ssign7.
        else if (look.tag == Tag.NUM)
ssign7.
        {
ssign7.
            rhs_assign = new NumNode();
ssign7.
            ((NumNode)rhs_assign).accept(this);
ssign7.
        }
ssign7.
        else if (look.tag == Tag.REAL)
ssign7.
        {
ssign7.
            rhs_assign = new RealNode();
ssign7.
            ((RealNode)rhs_assign).accept(this);
ssign7.
        }
ssign7.
        else if (look.tag == Tag.TRUE || look.tag == Tag.FALSE)
ssign7.
        {
ssign7.
            rhs_assign = new BooleanNode();
ssign7.
            ((BooleanNode)rhs_assign).accept(this);
ssign7.
        }
ssign7.
        else if (look.tag == '(')
ssign7.
        {
ssign7.
            rhs_assign = new ParenNode();
ssign7.
            ((ParenNode)rhs_assign).accept(this);
ssign7.
        }
ssign7.
        else {
ssign7.
            n.size = (BinExprNode) parseBinExprNode(rhs_assign, 0);
ssign7.
            n.size.type = Type.Int;
ssign7.
        }
ssign7.
        //match(Tag.NUM);
ssign7.
        match(']');
ssign7.
        if (look.tag == '[')
ssign7.
        {
ssign7.
            n.type = new ArrayTypeNode();
ssign7.
            n.type.accept(this);
ssign7.
        }
ssign7.
    }
ssign7.

ssign7.

ssign7.
    public void visit (Statements n)
ssign7.
    {
ssign7.
    	println("In Statements");
ssign7.
	println("look.toString()");
ssign7.
        if (!look.toString().equals("}") && look.tag != Tag.EOF) // new line of code
ssign7.
        {
ssign7.
            switch (look.tag)
ssign7.
            {
ssign7.
	    	case Tag.BASIC:
ssign7.
			n.decls = new DeclarationNode();
ssign7.
            		n.decls.accept(this);
ssign7.
			n.stmts = new Statements();
ssign7.
			n.stmts.accept(this);
ssign7.
			break;
ssign7.
                case Tag.ID:
ssign7.
                    n.stmt = new AssignmentNode();
ssign7.
                    (n.stmt).accept(this);
ssign7.
                    n.stmts = new Statements();
ssign7.
                    n.stmts.accept(this);
ssign7.
                    break;
ssign7.
                case Tag.IF:
ssign7.
                    n.stmt = new ConditionalNode();
ssign7.
                    (n.stmt).accept(this);
ssign7.
                    n.stmts = new Statements();
ssign7.
                    n.stmts.accept(this);
ssign7.
                    break;
ssign7.
                case Tag.WHILE:
ssign7.
                    n.stmt = new WhileNode();
ssign7.
                    (n.stmt).accept(this);
ssign7.
                    n.stmts = new Statements();
ssign7.
                    n.stmts.accept(this);
ssign7.
                    break;
ssign7.
                case Tag.DO:
ssign7.
                    n.stmt = new DoWhileNode();
ssign7.
                    (n.stmt).accept(this);
ssign7.
                    n.stmts = new Statements();
ssign7.
                    n.stmts.accept(this);
ssign7.
                    break;
ssign7.
                case Tag.BREAK:
ssign7.
                    n.stmt = new BreakNode();
ssign7.
                    (n.stmt).accept(this);
ssign7.
                    n.stmts = new Statements();
ssign7.
                    n.stmts.accept(this);
ssign7.
                    break;
ssign7.
                case '{':
ssign7.
                    n.stmt = new BlockStatementNode();
ssign7.
                    (n.stmt).accept(this);
ssign7.
                     n.stmts = new Statements();
ssign7.
                     n.stmts.accept(this);
ssign7.
                    break;
ssign7.
            }
ssign7.
        }
ssign7.
    }
ssign7.

ssign7.

ssign7.
    public void visit(AssignmentNode n)
ssign7.
    {
ssign7.
        n.left = new IdentifierNode();
ssign7.
        n.left.accept(this);
ssign7.

ssign7.
        if (top.get(n.left.w)==null){
ssign7.
            error("Variable Must be Declared");
ssign7.
        }else {
ssign7.
            n.left.type = top.get(n.left.w).type;
ssign7.
        }
ssign7.

ssign7.
        IdentifierNode id = (IdentifierNode)top.get(((IdentifierNode)n.left).w); // new code
ssign7.
        println("In Parser, AssignmentNode's left type: " + id.type); // new code
ssign7.

ssign7.
        //((IdentifierNode)n.left).type = id.type; // new code
ssign7.

ssign7.
        //if (look.tag == '['){ // new code
ssign7.
        //    n.left = parseArrayAccessNode((IdentifierNode)n.left); // new code
ssign7.
        //} // new code
ssign7.

ssign7.
        match('=');
ssign7.
        ExprNode rhs_assign = null;
ssign7.
        if (look.tag == Tag.ID)
ssign7.
        {
ssign7.
            rhs_assign = new IdentifierNode();
ssign7.
            ((IdentifierNode)rhs_assign).accept(this);
ssign7.
            println(top.get(((IdentifierNode)rhs_assign).w).type.toString());
ssign7.
            rhs_assign.type = top.get(((IdentifierNode)rhs_assign).w).type;
ssign7.
            if (top.get(((IdentifierNode)rhs_assign).w)==null){
ssign7.
                error("Variable Must be Declared");
ssign7.
            }else {
ssign7.
                n.right = rhs_assign;
ssign7.
                n.right.type = rhs_assign.type;
ssign7.
            }
ssign7.
        }
ssign7.
        else if (look.tag == Tag.NUM)
ssign7.
        {
ssign7.
            rhs_assign = new NumNode();
ssign7.
            ((NumNode)rhs_assign).accept(this);
ssign7.
            rhs_assign.type = Type.Int;
ssign7.
        }
ssign7.
        else if (look.tag == Tag.REAL)
ssign7.
        {
ssign7.
            rhs_assign = new RealNode();
ssign7.
            ((RealNode)rhs_assign).accept(this);
ssign7.
            rhs_assign.type = Type.Float;
ssign7.
        }
ssign7.
        else if (look.tag == Tag.TRUE || look.tag == Tag.FALSE)
ssign7.
        {
ssign7.
            rhs_assign = new BooleanNode();
ssign7.
            ((BooleanNode)rhs_assign).accept(this);
ssign7.
            n.right.type = Type.Bool;
ssign7.
        }
ssign7.
        else if (look.tag == '(')
ssign7.
        {
ssign7.
            rhs_assign = new ParenNode();
ssign7.
            ((ParenNode)rhs_assign).accept(this);
ssign7.
            rhs_assign.type = ((ParenNode)rhs_assign).type;
ssign7.
        }
ssign7.
        if (look.tag == ';')
ssign7.
        {
ssign7.
	    
ssign7.
            n.right = rhs_assign;
ssign7.
        }
ssign7.
        else {
ssign7.
            n.right = (BinExprNode) parseBinExprNode(rhs_assign, 0);
ssign7.

ssign7.
        }
ssign7.

ssign7.
        match(';');
ssign7.
    }
ssign7.

ssign7.
    public void visit(BinExprNode n)
ssign7.
    {
ssign7.

ssign7.
    }
ssign7.

ssign7.
    int getPrecedence(int op)
ssign7.
    {
ssign7.
        switch (op)
ssign7.
        {
ssign7.
            case '*': case '/': case '%':  return 12; // multiplicative
ssign7.
            case '+': case '-':            return 11; // additive
ssign7.
            case '<': case '>':
ssign7.
            case Tag.LE: case Tag.GE:      return 9;  // relational
ssign7.
            case Tag.EQ: case Tag.NE:      return 8;  // equality
ssign7.
            case Tag.OR:                   return 3;
ssign7.
            case Tag.AND:                  return 4;
ssign7.

ssign7.
            default:
ssign7.
                return -1;
ssign7.
        }
ssign7.
    }
ssign7.

ssign7.
    ExprNode parseBinExprNode(ExprNode lhs, int precedence)
ssign7.
    {
ssign7.
        while (getPrecedence(look.tag) >= precedence)
ssign7.
        {
ssign7.
            Token token_op = look;
ssign7.
            int op = getPrecedence(look.tag);
ssign7.
            move();
ssign7.
            ExprNode rhs = null;
ssign7.
            if (look.tag == Tag.ID)
ssign7.
            {
ssign7.
                rhs = new IdentifierNode();
ssign7.
                ((IdentifierNode)rhs).accept(this);
ssign7.
            }
ssign7.
            else if (look.tag == Tag.NUM)
ssign7.
            {
ssign7.
                rhs = new NumNode();
ssign7.
                ((NumNode)rhs).accept(this);
ssign7.
            }
ssign7.
            else if (look.tag == Tag.REAL)
ssign7.
            {
ssign7.
                rhs = new RealNode();
ssign7.
                ((RealNode)rhs).accept(this);
ssign7.
            }
ssign7.
            else if (look.tag == Tag.TRUE || look.tag == Tag.FALSE)
ssign7.
            {
ssign7.
                rhs = new BooleanNode();
ssign7.
                ((BooleanNode)rhs).accept(this);
ssign7.
            }
ssign7.
            else if (look.tag == '(')
ssign7.
            {
ssign7.
                rhs = new ParenNode();
ssign7.
                ((ParenNode)rhs).accept(this);
ssign7.
            }
ssign7.
            while (getPrecedence(look.tag) > op)
ssign7.
            {
ssign7.
                rhs = parseBinExprNode(rhs, getPrecedence(look.tag));
ssign7.
            }
ssign7.
            lhs = new BinExprNode(token_op, lhs, rhs);
ssign7.
            lhs.type = rhs.type;
ssign7.
        }
ssign7.
        return lhs;
ssign7.
    }
ssign7.

ssign7.
    public void visit(BreakNode n)
ssign7.
    {
ssign7.
        match(Tag.BREAK);
ssign7.
        match(';');
ssign7.
    }
ssign7.

ssign7.
    public void visit(ConditionalNode n)
ssign7.
    {
ssign7.
        match(Tag.IF);
ssign7.
        match('(');
ssign7.
        ExprNode rhs_assign = null;
ssign7.

ssign7.
        if (look.tag == Tag.ID)
ssign7.
        {
ssign7.
            rhs_assign = new IdentifierNode();
ssign7.
            ((IdentifierNode)rhs_assign).accept(this);
ssign7.
        }
ssign7.
        else if (look.tag == Tag.NUM)
ssign7.
        {
ssign7.
            rhs_assign = new NumNode();
ssign7.
            ((NumNode)rhs_assign).accept(this);
ssign7.
        }
ssign7.
        else if (look.tag == Tag.REAL)
ssign7.
        {
ssign7.
            rhs_assign = new RealNode();
ssign7.
            ((RealNode)rhs_assign).accept(this);
ssign7.
        }
ssign7.
        else if (look.tag == Tag.TRUE || look.tag == Tag.FALSE)
ssign7.
        {
ssign7.
            rhs_assign = new BooleanNode();
ssign7.
            ((BooleanNode)rhs_assign).accept(this);
ssign7.
        }
ssign7.
        else if (look.tag == '(')
ssign7.
        {
ssign7.
            rhs_assign = new ParenNode();
ssign7.
            ((ParenNode)rhs_assign).accept(this);
ssign7.
        }
ssign7.
        if (look.tag == ')')
ssign7.
        {
ssign7.
            n.condition = rhs_assign;
ssign7.
        }
ssign7.
        else
ssign7.
            n.condition = (BinExprNode) parseBinExprNode(rhs_assign, 0);
ssign7.

ssign7.
        match(')');
ssign7.
        switch (look.tag)
ssign7.
        {
ssign7.
            case Tag.ID:
ssign7.
                n.stmt = new AssignmentNode();
ssign7.
                (n.stmt).accept(this);
ssign7.
                break;
ssign7.
            case Tag.IF:
ssign7.
                n.stmt = new ConditionalNode();
ssign7.
                (n.stmt).accept(this);
ssign7.
                break;
ssign7.
            case Tag.WHILE:
ssign7.
                n.stmt = new WhileNode();
ssign7.
                (n.stmt).accept(this);
ssign7.
                break;
ssign7.
            case Tag.DO:
ssign7.
                n.stmt = new DoWhileNode();
ssign7.
                (n.stmt).accept(this);
ssign7.
                break;
ssign7.
            case Tag.BREAK:
ssign7.
                n.stmt = new BreakNode();
ssign7.
                (n.stmt).accept(this);
ssign7.
                break;
ssign7.
            case '{':
ssign7.
                n.stmt = new BlockStatementNode();
ssign7.
                (n.stmt).accept(this);
ssign7.
                break;
ssign7.
        }
ssign7.

ssign7.
        if (look.tag == Tag.ELSE)
ssign7.
        {
ssign7.
            switch (look.tag)
ssign7.
            {
ssign7.
                case Tag.ID:
ssign7.
                    n.elseStmt = new AssignmentNode();
ssign7.
                    (n.elseStmt).accept(this);
ssign7.
                    break;
ssign7.
                case Tag.IF:
ssign7.
                    n.elseStmt = new ConditionalNode();
ssign7.
                    (n.elseStmt).accept(this);
ssign7.
                    break;
ssign7.
                case Tag.WHILE:
ssign7.
                    n.elseStmt = new WhileNode();
ssign7.
                    (n.elseStmt).accept(this);
ssign7.
                    break;
ssign7.
                case Tag.DO:
ssign7.
                    n.elseStmt = new DoWhileNode();
ssign7.
                    (n.elseStmt).accept(this);
ssign7.
                    break;
ssign7.
                case Tag.BREAK:
ssign7.
                    n.elseStmt = new BreakNode();
ssign7.
                    (n.elseStmt).accept(this);
ssign7.
                    break;
ssign7.
                case '{':
ssign7.
                    n.elseStmt = new BlockStatementNode();
ssign7.
                    (n.elseStmt).accept(this);
ssign7.
                    break;
ssign7.
            }
ssign7.
        }
ssign7.
        else n.elseStmt= null;
ssign7.
    }
ssign7.

ssign7.
    public void visit(WhileNode n)
ssign7.
    {
ssign7.
        match(Tag.WHILE);
ssign7.
        match('(');
ssign7.
        ExprNode rhs_assign = null;
ssign7.
        if (look.tag == Tag.ID)
ssign7.
        {
ssign7.
            rhs_assign = new IdentifierNode();
ssign7.
            ((IdentifierNode)rhs_assign).accept(this);
ssign7.
        }
ssign7.
        else if (look.tag == Tag.NUM)
ssign7.
        {
ssign7.
            rhs_assign = new NumNode();
ssign7.
            ((NumNode)rhs_assign).accept(this);
ssign7.
        }
ssign7.
        else if (look.tag == Tag.REAL)
ssign7.
        {
ssign7.
            rhs_assign = new RealNode();
ssign7.
            ((RealNode)rhs_assign).accept(this);
ssign7.
        }
ssign7.
        else if (look.tag == Tag.TRUE || look.tag == Tag.FALSE)
ssign7.
        {
ssign7.
            rhs_assign = new BooleanNode();
ssign7.
            ((BooleanNode)rhs_assign).accept(this);
ssign7.
        }
ssign7.
        else if (look.tag == '(')
ssign7.
        {
ssign7.
            rhs_assign = new ParenNode();
ssign7.
            ((ParenNode)rhs_assign).accept(this);
ssign7.
        }
ssign7.
        if (look.tag == ')')
ssign7.
        {
ssign7.
            n.condition = rhs_assign;
ssign7.
        }
ssign7.
        else
ssign7.
            n.condition = (BinExprNode) parseBinExprNode(rhs_assign, 0);
ssign7.

ssign7.
        match(')');
ssign7.
        n.stmt = new StatementNode();
ssign7.
        n.stmt.accept(this);
ssign7.
    }
ssign7.

ssign7.
    public void visit(BooleanNode n)
ssign7.
    {
ssign7.
        if (look.tag == Tag.TRUE)
ssign7.
        {
ssign7.
            n.bool =Word.True;
ssign7.
            match(Tag.TRUE);
ssign7.
        }
ssign7.
        else if (look.tag == Tag.FALSE)
ssign7.
        {
ssign7.
            n.bool =Word.False;
ssign7.
            match(Tag.FALSE);
ssign7.
        }
ssign7.
        n.type = Type.Bool;
ssign7.
    }
ssign7.

ssign7.
    public void visit(DoWhileNode n)
ssign7.
    {
ssign7.
        match(Tag.DO);
ssign7.
	while(look.tag != Tag.WHILE){
ssign7.
        switch (look.tag)
ssign7.
        {
ssign7.
            case Tag.ID:
ssign7.
                n.stmt = new AssignmentNode();
ssign7.
                (n.stmt).accept(this);
ssign7.
                break;
ssign7.
            case Tag.IF:
ssign7.
                n.stmt = new ConditionalNode();
ssign7.
                (n.stmt).accept(this);
ssign7.
                break;
ssign7.
            case Tag.WHILE:
ssign7.
                n.stmt = new WhileNode();
ssign7.
                ((WhileNode)n.stmt).accept(this);
ssign7.
                break;
ssign7.
            case Tag.DO:
ssign7.
                n.stmt = new DoWhileNode();
ssign7.
                (n.stmt).accept(this);
ssign7.
                break;
ssign7.
            case Tag.BREAK:
ssign7.
                n.stmt = new BreakNode();
ssign7.
                (n.stmt).accept(this);
ssign7.
                break;
ssign7.
            case '{':
ssign7.
                n.stmt = new BlockStatementNode();
ssign7.
                (n.stmt).accept(this);
ssign7.
                break;
ssign7.
        }
ssign7.
	}
ssign7.
        match(Tag.WHILE);
ssign7.
        match('(');
ssign7.
	System.out.println("Inside DoWhile While node");
ssign7.
        ExprNode rhs_assign = null;
ssign7.
        if (look.tag == Tag.ID)
ssign7.
        {
ssign7.
            rhs_assign = new IdentifierNode();
ssign7.
            ((IdentifierNode)rhs_assign).accept(this);
ssign7.
        }
ssign7.
        else if (look.tag == Tag.NUM)
ssign7.
        {
ssign7.
            rhs_assign = new NumNode();
ssign7.
            ((NumNode)rhs_assign).accept(this);
ssign7.
        }
ssign7.
        else if (look.tag == Tag.REAL)
ssign7.
        {
ssign7.
            rhs_assign = new RealNode();
ssign7.
            ((RealNode)rhs_assign).accept(this);
ssign7.
        }
ssign7.
        else if (look.tag == Tag.TRUE || look.tag == Tag.FALSE)
ssign7.
        {
ssign7.
            rhs_assign = new BooleanNode();
ssign7.
            ((BooleanNode)rhs_assign).accept(this);
ssign7.
        }else if (look.tag == '(')
ssign7.
        {
ssign7.
            rhs_assign = new ParenNode();
ssign7.
            ((ParenNode)rhs_assign).accept(this);
ssign7.
        }
ssign7.
        if (look.tag == ')')
ssign7.
        {
ssign7.
            n.condition = rhs_assign;
ssign7.
        }
ssign7.
        else
ssign7.
            n.condition = (ExprNode) parseBinExprNode(rhs_assign, 0);
ssign7.

ssign7.
        match(')');
ssign7.
        match(';');
ssign7.
    }
ssign7.

ssign7.

ssign7.
    public void visit(NumNode n)
ssign7.
    {
ssign7.
        n.value = ((Num)look).value;
ssign7.

ssign7.
        if (look.tag != Tag.NUM) // new code
ssign7.
            error("Syntax error: Integer number needed, instead of " + n.value); // new code
ssign7.

ssign7.
        match(Tag.NUM);
ssign7.
        n.type = Type.Int;
ssign7.
        n.printNode(); // new code
ssign7.
    }
ssign7.

ssign7.

ssign7.
    public void visit(RealNode n)
ssign7.
    {
ssign7.
        n.value = ((Real)look).value;
ssign7.

ssign7.
        if (look.tag != Tag.REAL) // new code
ssign7.
            error("Syntax error: Real number needed, instead of " + n.value); // new code
ssign7.

ssign7.
        match(Tag.REAL);
ssign7.
        n.type = Type.Float;
ssign7.
        n.printNode(); // new code
ssign7.
    }
ssign7.

ssign7.

ssign7.
    public void visit(IdentifierNode n)
ssign7.
    {
ssign7.
        n.id = look.toString();
ssign7.
        n.w = (Word)look; // new code
ssign7.
	if((IdentifierNode)top.get(n.w) != null){
ssign7.
		n = (IdentifierNode)top.get((Word)look);
ssign7.
		println(n.id + " Identifier from top");
ssign7.
	}
ssign7.
	
ssign7.

ssign7.
      	println("***** n.type: "+ n.type); // new code
ssign7.

ssign7.
        if (look.tag != Tag.ID) // new code
ssign7.
            error("Syntax error: Identifier or variable needed, instead of " + n.id); // new code
ssign7.

ssign7.
        match(Tag.ID);
ssign7.
        if(look.tag == '['){
ssign7.
            n.array = new ArrayIDNode();
ssign7.
            n.array.accept(this);
ssign7.
        }
ssign7.
        n.printNode(); // new code
ssign7.
    }
ssign7.

ssign7.
    public void visit (ArrayIDNode n)
ssign7.
    {
ssign7.
        match('[');
ssign7.
        ExprNode rhs_assign = null;
ssign7.
        if (look.tag == Tag.ID)
ssign7.
        {
ssign7.
            rhs_assign = new IdentifierNode();
ssign7.
            ((IdentifierNode)rhs_assign).accept(this);
ssign7.
        }
ssign7.
        else if (look.tag == Tag.NUM)
ssign7.
        {
ssign7.
            rhs_assign = new NumNode();
ssign7.
            rhs_assign.type = Type.Int;
ssign7.
            ((NumNode)rhs_assign).accept(this);
ssign7.
        }
ssign7.
        else if (look.tag == Tag.REAL)
ssign7.
        {
ssign7.
            rhs_assign = new RealNode();
ssign7.
            rhs_assign.type = Type.Float;
ssign7.
            ((RealNode)rhs_assign).accept(this);
ssign7.
        }
ssign7.
        else if (look.tag == Tag.TRUE || look.tag == Tag.FALSE)
ssign7.
        {
ssign7.
            rhs_assign = new BooleanNode();
ssign7.
            rhs_assign.type = Type.Bool;
ssign7.
            ((BooleanNode)rhs_assign).accept(this);
ssign7.
        } else if (look.tag == '(') {
ssign7.
            rhs_assign = new ParenNode();
ssign7.
            ((ParenNode)rhs_assign).accept(this);
ssign7.
        }
ssign7.
        if (look.tag == ']')
ssign7.
        {
ssign7.

ssign7.
            n.node = rhs_assign;
ssign7.
        }
ssign7.
        else
ssign7.
            n.node = (BinExprNode) parseBinExprNode(rhs_assign, 0);
ssign7.

ssign7.
        match(']');
ssign7.
        if (look.toString().equals("["))
ssign7.
        {
ssign7.
            n.array = new ArrayIDNode();
ssign7.
            n.array.accept(this);
ssign7.
        }
ssign7.
    }
ssign7.

ssign7.
    public void visit(ParenNode n)
ssign7.
    {
ssign7.
        match('(');
ssign7.
	System.out.println("Inside Parenthesis node");
ssign7.
        ExprNode rhs_assign = null;
ssign7.
        if (look.tag == Tag.ID)
ssign7.
        {
ssign7.
            rhs_assign = new IdentifierNode();
ssign7.
	    System.out.println("Inside Parenthesis Identifier node");
ssign7.
            ((IdentifierNode)rhs_assign).accept(this);
ssign7.
	    System.out.println("Type: " + rhs_assign.type);
ssign7.
        }
ssign7.
        else if (look.tag == Tag.NUM)
ssign7.
        {
ssign7.
            rhs_assign = new NumNode();
ssign7.
            ((NumNode)rhs_assign).accept(this);
ssign7.
        }
ssign7.
        else if (look.tag == Tag.REAL)
ssign7.
        {
ssign7.
            rhs_assign = new RealNode();
ssign7.
            ((RealNode)rhs_assign).accept(this);
ssign7.
        }
ssign7.
        else if (look.tag == Tag.TRUE || look.tag == Tag.FALSE)
ssign7.
        {
ssign7.
            rhs_assign = new BooleanNode();
ssign7.
            ((BooleanNode)rhs_assign).accept(this);
ssign7.
        } else if (look.tag == '(')
ssign7.
        {
ssign7.
            rhs_assign = new ParenNode();
ssign7.
            ((ParenNode)rhs_assign).accept(this);
ssign7.
	    System.out.println("Type: " + rhs_assign.type);
ssign7.
        }
ssign7.
        if (look.tag == ')')
ssign7.
        {
ssign7.
            n.node = rhs_assign;
ssign7.
        }
ssign7.
        else
ssign7.
            n.node = (BinExprNode) parseBinExprNode(rhs_assign, 0);
ssign7.

ssign7.
        match(')');
ssign7.
	
ssign7.
	  n.type = rhs_assign.type;
ssign7.
	  System.out.println("Mytype: "+ n.type);
ssign7.
    }
ssign7.

ssign7.
    Node parseArrayAccessNode(IdentifierNode id){
ssign7.
        for (int i = 0; i < level; i++) System.out.print(indent);
ssign7.
        System.out.println("parseArrayAccessNode");
ssign7.

ssign7.
        ArrayIDNode index = new ArrayIDNode();
ssign7.
        level++;
ssign7.
        index.accept(this);
ssign7.
        level--;
ssign7.

ssign7.
        return new ArrayIDNode(id, index);
ssign7.
    }
ssign7.

ssign7.
}
ssign7.
