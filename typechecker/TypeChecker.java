package assign6.typechecker;
ssign7.

ssign7.
import assign6.ast.*;
ssign7.
import assign6.visitor.*;
ssign7.
import assign6.parser.*;
ssign7.
import assign6.lexer.*;
ssign7.
import java.io.*;
ssign7.

ssign7.
public class TypeChecker extends ASTVisitor
ssign7.
{
ssign7.
    public Parser parser = null;
ssign7.
    public CompilationUnit cu = null;
ssign7.
    public boolean whileLoop = false;
ssign7.
    public boolean doLoop = false;
ssign7.

ssign7.
    int level = 0;
ssign7.
    String indent = "...";
ssign7.

ssign7.
    public Env top = null;
ssign7.
//    public Type lhsExp = null;
ssign7.
//    public Type rhsExp = null;
ssign7.
//    public boolean hasbeenInitialized = false;
ssign7.
//    public String[] initilizedTokens = new String[1000];
ssign7.

ssign7.

ssign7.
    public TypeChecker(Parser parser)
ssign7.
    {
ssign7.
            this.parser = parser;
ssign7.
            cu = parser.cu;
ssign7.
            //visit(this.parser.cu);
ssign7.
            visit(cu);
ssign7.
    }
ssign7.
    public TypeChecker()
ssign7.
    {
ssign7.
            //this.parser = new Parser();
ssign7.
            visit(this.parser.cu);
ssign7.
    }
ssign7.

ssign7.
    void error(String s)
ssign7.
    {
ssign7.
        println(s);
ssign7.
        exit(1);
ssign7.
    }
ssign7.

ssign7.
    void exit(int n)
ssign7.
    {
ssign7.
        System.exit(n);
ssign7.
    }
ssign7.

ssign7.
    void print(String s)
ssign7.
    {
ssign7.
        System.out.print(s);
ssign7.
    }
ssign7.

ssign7.
    void println(String s)
ssign7.
    {
ssign7.
        System.out.println(s);
ssign7.
    }
ssign7.

ssign7.
    void printSpace()
ssign7.
    {
ssign7.
        System.out.print(" ");
ssign7.
    }
ssign7.
//    void error (String s) throws Error{
ssign7.
//	throw new Error ("near line " + this.parser.lexer.line + ": " + s);
ssign7.
//    }
ssign7.

ssign7.
//    public Type getType(IdentifierNode a){
ssign7.
//        String x = a.id;
ssign7.
//	if(!(top.table.containsKey(x)))
ssign7.
//	{
ssign7.
//		 error("Variable " + x +" has not been declared.");
ssign7.
//	}
ssign7.
//	return top.table.get(x);
ssign7.
//    }
ssign7.
    public void visit (CompilationUnit n)
ssign7.
    {
ssign7.
        System.out.println("\nTypechecker starts");
ssign7.
//        top = new Env();
ssign7.
//        top = n.symbolTable;
ssign7.
//        visit(n.block);
ssign7.
        n.block.accept(this);
ssign7.
    }
ssign7.

ssign7.
    public void visit (BlockStatementNode n)
ssign7.
    {
ssign7.
        System.out.println("visiting Block");
ssign7.
// 	top = new Env(top);
ssign7.
// 	top = n.sTable.prev;
ssign7.
        n.decls.accept(this);
ssign7.
        n.stmts.accept(this);
ssign7.
    }
ssign7.

ssign7.
    public void visit(Declarations n)
ssign7.
    {
ssign7.
        System.out.println("visiting Declarations");
ssign7.
        if(n.decls != null)
ssign7.
        {
ssign7.
            n.decl.accept(this);
ssign7.
            n.decls.accept(this);
ssign7.
        }
ssign7.
    }
ssign7.

ssign7.
    public void visit(DeclarationNode n)
ssign7.
    {
ssign7.
        System.out.println("visiting DeclarationNode");
ssign7.
        n.type.accept(this);
ssign7.
        n.id.accept(this);
ssign7.
    }
ssign7.

ssign7.
    public void visit(TypeNode n)
ssign7.
    {
ssign7.
        System.out.println("visiting TypeNode"+n.basic);
ssign7.
        if(n.array != null)
ssign7.
        {
ssign7.
            n.array.accept(this);
ssign7.
        }
ssign7.
    }
ssign7.

ssign7.
    public void visit (Statements n)
ssign7.
    {
ssign7.
        System.out.println("Visiting Statements");
ssign7.
       if (n.stmts != null)
ssign7.
       {
ssign7.
       	   if(n.stmt != null) n.stmt.accept(this);
ssign7.
	   if(n.decls != null) n.decls.accept(this);
ssign7.
            n.stmts.accept(this);
ssign7.
        }
ssign7.
    }
ssign7.

ssign7.
    public void visit(ParenNode n) {
ssign7.
        System.out.println("visiting ParenNode");
ssign7.
        n.node.accept(this);
ssign7.
    }
ssign7.

ssign7.
    public void visit(ConditionalNode n)
ssign7.
    {
ssign7.
        System.out.println("IfStatement/ConditionalNode");
ssign7.
//          if (n.condition.type != Type.Bool){
ssign7.
//             error("Conditional Must be Boolean");
ssign7.
//         }
ssign7.
        n.condition.accept(this);
ssign7.
        n.stmt.accept(this);
ssign7.
        if (n.elseStmt != null)
ssign7.
        {
ssign7.
            System.out.println("Else Clause");
ssign7.
            n.elseStmt.accept(this);
ssign7.
        }
ssign7.
    }
ssign7.

ssign7.
    public void visit(WhileNode n)
ssign7.
    {
ssign7.
        whileLoop = true;
ssign7.
        System.out.println("visiting WhileNode");
ssign7.
       
ssign7.
        n.condition.accept(this);
ssign7.
        n.stmt.accept(this);
ssign7.
        whileLoop = false;
ssign7.
    }
ssign7.

ssign7.
    public void visit(DoWhileNode n)
ssign7.
    {
ssign7.
        doLoop = true;
ssign7.
        System.out.println("visiting DoWhileNode");
ssign7.
       
ssign7.
        n.stmt.accept(this);
ssign7.
         
ssign7.
        n.condition.accept(this);
ssign7.
        doLoop = false;
ssign7.
    }
ssign7.

ssign7.
    public void visit (ArrayIDNode n)
ssign7.
    {
ssign7.
        System.out.println("visiting ArrayIDNode");
ssign7.
//         n.id.accept(this);
ssign7.
//         n.node.accept(this);
ssign7.
//         if (n.node.type != Type.Int){
ssign7.
//             error("Index of Array must be an Integer");
ssign7.
//         }
ssign7.
//         if(n.id != null)
ssign7.
//         {
ssign7.
//             n.id.accept(this);
ssign7.
//         }
ssign7.
    }
ssign7.

ssign7.
    public void visit(ArrayTypeNode n)
ssign7.
    {
ssign7.
        System.out.println("ArrayDimsNode");
ssign7.
        //n.size.accept(this);
ssign7.
//         if (n.size.type != Type.Int){
ssign7.
//             error("Index of Array must be an Integer");
ssign7.
//         }
ssign7.
//         if(n.type != null)
ssign7.
//         {
ssign7.
//             n.type.accept(this);
ssign7.
//         }
ssign7.
    }
ssign7.

ssign7.
    public void visit(BreakNode n)
ssign7.
    {
ssign7.
        System.out.println("visiting BreakNode");
ssign7.
//         if (whileLoop == false){
ssign7.
//             error("Break called outside of loop");
ssign7.
//         }
ssign7.
    }
ssign7.

ssign7.
    public void visit(AssignmentNode n)
ssign7.
    {
ssign7.
        System.out.println("AssignmentNode");
ssign7.
        n.left.accept(this);
ssign7.

ssign7.
        IdentifierNode leftId = (IdentifierNode)n.left;
ssign7.
        Type leftType = leftId.type;
ssign7.

ssign7.
        println("In TypeChecker, AssignmentNode's left type: "+leftType);
ssign7.

ssign7.
        Type rightType = null;
ssign7.
        if(n.right instanceof IdentifierNode){
ssign7.
            ((IdentifierNode)n.right).accept(this);
ssign7.
	    println("Debug: " + n.right.type);}
ssign7.
        else if (n.right instanceof NumNode)
ssign7.
        {
ssign7.
            ((NumNode)n.right).accept(this);
ssign7.
            rightType = Type.Int;
ssign7.
        }
ssign7.
        else if(n.right instanceof RealNode)
ssign7.
            ((RealNode)n.right).accept(this);
ssign7.
        else if(n.right instanceof ArrayIDNode)              //Dr. Lee uses ArrayNodes differently
ssign7.
            ((ArrayIDNode)n.right).accept(this);
ssign7.
        else if(n.right instanceof ParenNode)
ssign7.
            ((ParenNode)n.right).accept(this);
ssign7.
        else{
ssign7.
            ((BinExprNode)n.right).accept(this);
ssign7.
            rightType = ((BinExprNode)n.right).type;
ssign7.
        }
ssign7.

ssign7.
	rightType = n.right.type;
ssign7.
        if(leftType == Type.Int)
ssign7.
            println("************* leftType is Type.Int");
ssign7.
        if(leftType != rightType)
ssign7.
        {
ssign7.
            error("The right-hand side of an assignment of type: " + rightType + " is incompatible to the left-hand side "+leftId.id +" of type: " +leftType);
ssign7.
        }
ssign7.
    }
ssign7.

ssign7.
    public void visit(BinExprNode n) {
ssign7.
        System.out.println("visiting BinExprNode" + n.op);
ssign7.

ssign7.
        Type leftType = null;
ssign7.
        IdentifierNode leftId = null;
ssign7.

ssign7.
        if (n.left instanceof IdentifierNode) {
ssign7.
            ((IdentifierNode) n.left).accept(this);
ssign7.

ssign7.
            leftId = (IdentifierNode) n.left;
ssign7.
            leftType = leftId.type;
ssign7.
        } else if (n.left instanceof NumNode) {
ssign7.
            ((NumNode) n.left).accept(this);
ssign7.
        } else if (n.left instanceof RealNode) {
ssign7.
            ((RealNode) n.left).accept(this);
ssign7.
        }
ssign7.
        else if (n.left instanceof BooleanNode)
ssign7.
        {
ssign7.
            ((BooleanNode)n.left).accept(this);
ssign7.
        }
ssign7.
        //else if (n.left instanceof ArrayIDNode)            //Dr,lee Array node conversion???
ssign7.
        //{
ssign7.
        //    ((ArrayIDNode) n.left).accept(this);
ssign7.
        //    }
ssign7.
         else if (n.left instanceof ParenNode) {
ssign7.
            ((ParenNode) n.left).accept(this);
ssign7.
        } else
ssign7.
            ((BinExprNode) n.left).accept(this);
ssign7.

ssign7.
        Type rightType = null;
ssign7.
	 IdentifierNode rightId = null;
ssign7.
        if (n.right != null) {
ssign7.
            if (n.right instanceof IdentifierNode) {
ssign7.
                ((IdentifierNode) n.right).accept(this);
ssign7.

ssign7.
                rightId = (IdentifierNode) n.right;
ssign7.
                rightType = rightId.type;
ssign7.
            } else if (n.right instanceof NumNode) {
ssign7.
                ((NumNode) n.right).accept(this);
ssign7.
            } else if (n.right instanceof RealNode) {
ssign7.
                ((RealNode) n.right).accept(this);
ssign7.

ssign7.

ssign7.
            } //else if (n.right instanceof ArrayIDNode)              //Dr.Lee Array conversion???
ssign7.
            //{
ssign7.
            //    ((ArrayIDNode) n.right).accept(this);
ssign7.
           // }
ssign7.

ssign7.
            else if (n.right instanceof BooleanNode)
ssign7.
            {
ssign7.
                ((BooleanNode) n.right).accept(this);
ssign7.
            }
ssign7.
            else if (n.right instanceof ParenNode) {
ssign7.
                ((ParenNode) n.right).accept(this);
ssign7.
            } else {
ssign7.
                ((BinExprNode) n.right).accept(this);
ssign7.
            }
ssign7.
        } else {
ssign7.
            System.out.println("@@@ n.right == null in BinExprNode: " + n.right);
ssign7.
        }
ssign7.
        if (leftType == Type.Float || rightType == Type.Float) {
ssign7.
            n.type = Type.Float;
ssign7.
        } else {
ssign7.
            n.type = Type.Int;
ssign7.
        }
ssign7.
    }
ssign7.

ssign7.
    public void visit(StatementNode n)
ssign7.
    {
ssign7.
        System.out.println("Visiting Statement");
ssign7.
    }
ssign7.

ssign7.

ssign7.
    public void visit(IdentifierNode n)
ssign7.
    {
ssign7.
        System.out.println("visiting IdentifierNode");
ssign7.
	//if(top.get(n.w) != null) println(n.w +" in symbol table");
ssign7.
	println (n.w + " in Identifier Node");
ssign7.
// 		println("Variable is already declared: " +n.w);
ssign7.
// 	}
ssign7.
 	println("Type: " + n.type);
ssign7.
//         if(n.type == null){
ssign7.
// 		error("Syntax error: Variable " + n.id + " not declared. Cannot use undeclared variable.");
ssign7.
// 	}
ssign7.
	
ssign7.
        if (n.array != null)
ssign7.
        {
ssign7.
            n.array.accept(this);
ssign7.
        }
ssign7.

ssign7.
    }
ssign7.

ssign7.
    public void visit(BooleanNode n)
ssign7.
    {
ssign7.
        System.out.println("visiting BooleanNode");
ssign7.

ssign7.
    }
ssign7.

ssign7.
    public void visit(NumNode n)
ssign7.
    {
ssign7.
        System.out.println("visiting NumNode");
ssign7.

ssign7.
    }
ssign7.

ssign7.
    public void visit(RealNode n)
ssign7.
    {
ssign7.
        System.out.println("visiting RealNode");
ssign7.
    }
ssign7.

ssign7.

ssign7.
}
ssign7.
