package assign7.intercode;

import assign7.ast.*;
import assign7.visitor.*;
import assign7.parser.*;
import assign7.lexer.*;
import java.io.*;
import java.util.*;

public class InterCodeGen extends ASTVisitor {
    public Parser parser = null;
    public CompilationUnit cu = null;
   

    int level = 0;
    String indent = "...";

    public Env top = null;

    public InterCodeGen(Parser parser)
    {
            this.parser = parser;
            cu = parser.cu;
            visit(cu);
    }
    public InterCodeGen()
    {
            visit(this.parser.cu);
    }

  //////////////////////////////////////////////////////////////////////////////////////////////////
  ////                                                                                          ////
  ////                                    UTILITY METHODS                                      //// 
  ////                                                                                         ////
  /////////////////////////////////////////////////////////////////////////////////////////////////
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
        println("\nTypechecker starts");

        n.block.accept(this);
	println("*************End of the Program*************");
    }

    public void visit (BlockStatementNode n)
    {
        println("Start of BlockStatemet");
	top = new Env(n.sTable);
        n.decls.accept(this);
        n.stmts.accept(this);
	println("**********End of the BlockStatement**********");
    }

    public void visit(Declarations n)
    {
        System.out.println("visiting Declarations");
        if(n.decls != null)
        {
            n.decl.accept(this);
            n.decls.accept(this);
        }
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

    public void visit (Statements n)
    {
        println("Visiting Statements");
       if (n.stmts != null)
       {
       	   if(n.stmt!=null) n.stmt.accept(this);
	   if(n.decls != null) n.decls.accept(this);
            n.stmts.accept(this);
        }
    }
    
    public void visit(ParenNode n) {
        System.out.println("visiting ParenNode");
        n.node.accept(this);
    }

    public void visit(ConditionalNode n)
    {
        System.out.println("IfStatement/ConditionalNode");
        n.condition.accept(this);
        n.stmt.accept(this);
        if (n.elseStmt != null)
        {
            System.out.println("Else Clause");
            n.elseStmt.accept(this);
        }
    }
    

    public void visit(WhileNode n)
    {
        System.out.println("visiting WhileNode");
        n.condition.accept(this);
        n.stmt.accept(this);
    }

    public void visit(DoWhileNode n)
    {
        System.out.println("visiting DoWhileNode");
        n.stmt.accept(this);
        n.condition.accept(this);
    }

    public void visit (ArrayIDNode n)
    {
        System.out.println("visiting ArrayIDNode");
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
        System.out.println("ArrayDimsNode");
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
        System.out.println("visiting BreakNode");
    }

    public void visit(AssignmentNode n)
    {
        System.out.println("AssignmentNode");
        n.left.accept(this);

        IdentifierNode leftId = (IdentifierNode)n.left;
        Type leftType = leftId.type;

        println("In TypeChecker, AssignmentNode's left type: "+leftType);

        Type rightType = null;
        if(n.right instanceof IdentifierNode){
            ((IdentifierNode)n.right).accept(this);
	}else if (n.right instanceof NumNode){
            ((NumNode)n.right).accept(this);
            rightType = Type.Int;
        }else if(n.right instanceof RealNode)
            ((RealNode)n.right).accept(this);
        else if(n.right instanceof ArrayIDNode)              //Dr. Lee uses ArrayNodes differently
            ((ArrayIDNode)n.right).accept(this);
        else if(n.right instanceof ParenNode)
            ((ParenNode)n.right).accept(this);
        else{
            ((BinExprNode)n.right).accept(this);
            rightType = ((BinExprNode)n.right).type;
	}
    }

    public void visit(BinExprNode n) {
        System.out.println("visiting BinExprNode" + n.op);

        Type leftType = null;
        IdentifierNode leftId = null;

        if (n.left instanceof IdentifierNode){
	        leftId = (IdentifierNode)n.left;
	      	System.out.println(leftId.w);
       		((IdentifierNode) n.left).accept(this);
       		leftType= ((IdentifierNode) n.left).type;
        }else if (n.left instanceof NumNode) {
            ((NumNode) n.left).accept(this);
        }else if (n.left instanceof RealNode) {
            ((RealNode) n.left).accept(this);
        }else if (n.left instanceof BooleanNode)
        {
            ((BooleanNode)n.left).accept(this);
        }else if (n.left instanceof ParenNode) {
            ((ParenNode) n.left).accept(this);
        }else
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
            } 
            else if (n.right instanceof BooleanNode)
            {
                ((BooleanNode) n.right).accept(this);
            }
            else if (n.right instanceof ParenNode) {
                ((ParenNode) n.right).accept(this);
            } else {
                ((BinExprNode) n.right).accept(this);
            }
        } else {
            System.out.println("@@@ n.right == null in BinExprNode: " + n.right);
        }
    }

    public void visit(StatementNode n)
    {
        System.out.println("Visiting Statement");
	      n.stmt.accept(this);
    }


    public void visit(IdentifierNode n)
    {
        System.out.println("visiting IdentifierNode: "+n.w+" of type: "+n.type);
        if (n.array != null)
        {
            n.array.accept(this);
        }

    }

    public void visit(BooleanNode n)
    {
        System.out.println("visiting BooleanNode");
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
