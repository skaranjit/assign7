package assign7.typechecker;

import assign7.ast.*;
import assign7.visitor.*;
import assign7.parser.*;
import assign7.lexer.*;
import java.io.*;
import java.util.*;

public class TypeChecker extends ASTVisitor
{
    public Parser parser = null;
    public CompilationUnit cu = null;
    public boolean whileLoop = false;
    public boolean doLoop = false;
    public boolean isBool = false;
    

    int level = 0;
    String indent = "...";

    public Env top = null;

    public TypeChecker(Parser parser)
    {
            this.parser = parser;
            cu = parser.cu;
            visit(cu);
    }
    public TypeChecker()
    {
            visit(this.parser.cu);
    }

    void error(String s)
    {
        println(s);
        exit(1);
    }

    void exit(int n)
    {
        System.exit(n);
    }

    void print(String s)
    {
        System.out.print(s);
    }

    void println(String s)
    {
        System.out.println(s);
    }

    void printSpace()
    {
        System.out.print(" ");
    }

    public void visit (CompilationUnit n)
    {
       println("\n*********TypeChecker starts************");

        n.block.accept(this);
	println("*********TypeChecker Passed***********");
    }

    public void visit (BlockStatementNode n)
    {
  	println("visiting Block");
        for(DeclarationNode decl: n.decls) 
	     decl.accept(this);
        for(StatementNode stmt : n.stmts)
	     stmt.accept(this);
    }

    public void visit(DeclarationNode n)
    {
        println("visiting DeclarationNode");
        n.type.accept(this);
	n.id.type =n.type.basic;
	
        n.id.accept(this);
	
    }

    public void visit(TypeNode n)
    {
        println("visiting TypeNode "+n.basic);
        if(n.array != null)
        {
            n.array.accept(this);
        }
    }

   
    
    public void visit(ParenNode n) {
        println("visiting ParenNode");
        n.node.accept(this);
    }

    public void visit(ConditionalNode n)
    {
        println("IfStatement/ConditionalNode");
        n.condition.accept(this);
	if (!isBool) error("Condition Must be boolean");
        n.stmt.accept(this);
        if (n.elseStmt != null)
        {
            System.out.println("Else Clause");
            n.elseStmt.accept(this);
        }
    }
    

    public void visit(WhileNode n)
    {
        whileLoop = true;
        println("visiting WhileNode");
       
        n.condition.accept(this);
	if (!isBool) error("Condition Must be boolean");
        n.stmt.accept(this);
        whileLoop = false;
    }

    public void visit(DoWhileNode n)
    {
        println("visiting DoWhileNode");
        whileLoop = true;
        n.stmt.accept(this);
        whileLoop = false;
        n.condition.accept(this);
	if (!isBool) error("Condition Must be boolean");
        whileLoop = true;
    }

    public void visit (ArrayIDNode n)
    {
        println("visiting ArrayIDNode");
	if(n.node instanceof NumNode) ((NumNode)n.node).accept(this);
	if(n.node instanceof IdentifierNode) ((IdentifierNode)n.node).accept(this);
        if (n.node.type != Type.Int){
            error("Index of Array must be an Integer");
        }
        if(n.id != null)
        {
            n.id.accept(this);
        }
    }

    public void visit(ArrayTypeNode n)
    {
        println("ArrayDimsNode");
        n.size.accept(this);
        if (n.size.type != Type.Int ){
            error("Index of Array must be an Integer");
        }
        if(n.type != null)
        {
            n.type.accept(this);
        }
    }

    public void visit(BreakNode n)
    {
        println("visiting BreakNode");
        if (whileLoop == false){
            error("Break called outside of loop");
        }
    }

    public void visit(AssignmentNode n)
    {
        println("AssignmentNode");
        n.left.accept(this);

        IdentifierNode leftId = (IdentifierNode)n.left;
        Type leftType = leftId.type;

        println("In TypeChecker, AssignmentNode's left type: "+leftType);

        Type rightType = null;
        if(n.right instanceof IdentifierNode){
            ((IdentifierNode)n.right).accept(this);
	    println("Debug: " + n.right.type);}
        else if (n.right instanceof NumNode)
        {
            ((NumNode)n.right).accept(this);
            rightType = Type.Int;
        }
        else if(n.right instanceof RealNode)
            ((RealNode)n.right).accept(this);
        else if(n.right instanceof ArrayIDNode)              //Dr. Lee uses ArrayNodes differently
            ((ArrayIDNode)n.right).accept(this);
        else if(n.right instanceof ParenNode)
            ((ParenNode)n.right).accept(this);
        else{
            ((BinExprNode)n.right).accept(this);
            rightType = ((BinExprNode)n.right).type;
        }

	rightType = n.right.type;
        if(leftType == Type.Int)
            println("************* leftType is Type.Int");
        if(leftType != rightType)
        {
            error("The right-hand side of an assignment of type: " + rightType + " is incompatible to the left-hand side "+leftId.id +" of type: " +leftType);
        }
    }

    public void visit(BinExprNode n) {
        System.out.println("visiting BinExprNode" + n.op);
	String[] boolOperator = {">=",">","<","<=","==","!="};
	if(Arrays.asList(boolOperator).contains(n.op.toString())){
		isBool = true;
	}else isBool = false;
        Type leftType = null;
        IdentifierNode leftId = null;

        if (n.left instanceof IdentifierNode) {
            ((IdentifierNode) n.left).accept(this);
	    leftType = n.left.type;
        } else if (n.left instanceof NumNode) {
            ((NumNode) n.left).accept(this);
        } else if (n.left instanceof RealNode) {
            ((RealNode) n.left).accept(this);
        } else if (n.left instanceof BooleanNode)
        {
            ((BooleanNode)n.left).accept(this);
        } else if (n.left instanceof ParenNode) {
            ((ParenNode) n.left).accept(this);
        } else
            ((BinExprNode) n.left).accept(this);

        Type rightType = null;
	IdentifierNode rightId = null;
        if (n.right != null) {
            if (n.right instanceof IdentifierNode) {
                ((IdentifierNode) n.right).accept(this);
                rightId = (IdentifierNode) n.right;
                rightType = rightId.type;
            } else if (n.right instanceof NumNode) {
                ((NumNode) n.right).accept(this);
            } else if (n.right instanceof RealNode) {
                ((RealNode) n.right).accept(this);
            } else if (n.right instanceof BooleanNode){
                ((BooleanNode) n.right).accept(this);
            } else if (n.right instanceof ParenNode) {
                ((ParenNode) n.right).accept(this);
            } else {
                ((BinExprNode) n.right).accept(this);
            }
        } else {
            println("@@@ n.right == null in BinExprNode: " + n.right);
        }
	
        if (leftType == Type.Float || rightType == Type.Float) {
            n.type = Type.Float;
        } else {
            n.type = Type.Int;
        }
    }

    public void visit(StatementNode n)
    {
        System.out.println("Visiting Statement");
	n.stmt.accept(this);
	isBool = false;
    }


    public void visit(IdentifierNode n)
    {
        System.out.println("visiting IdentifierNode: "+n.w+" of type: "+n.type);
	if(n.type == null) error("Variable "+n.w+" is not declared."); 
        if (n.array != null)
        {
            n.array.accept(this);
        }

    }

    public void visit(BooleanNode n)
    {
        System.out.println("visiting BooleanNode");
	isBool = true;

    }

    public void visit(NumNode n)
    {
        System.out.println("visiting NumNode");
	n.printNode();

    }

    public void visit(RealNode n)
    {
        System.out.println("visiting RealNode");
	n.printNode();
    }


}
