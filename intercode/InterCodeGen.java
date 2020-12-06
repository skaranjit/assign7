package assign7.intercode;

import assign7.ast.*;
import assign7.visitor.*;
import assign7.parser.*;
import assign7.lexer.*;
import assign7.typechecker.*;
import java.io.*;
import java.util.*;

public class InterCodeGen extends ASTVisitor {
    public TypeChecker checker = null;
    public CompilationUnit cu = null;
    ExprNode bR = null;
    ExprNode temp1 = null;
    ExprNode last = null;
    int Count = 0;
    public List<AssignmentNode> Bassigns = new ArrayList<AssignmentNode>();
    IdentifierNode a = null;
    int level = 0;

    public Env top = null;

    public InterCodeGen(TypeChecker checker)
    {
            this.checker = checker;
            this.cu = checker.cu;
            visit(cu);
    }
    public InterCodeGen()
    {
            visit(this.checker.cu);
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
     int indent = 0;

    void indentUp()
    {
        indent++;
    }

    void indentDown()
    {
        indent--;
    }

    void printIndent()
    {
        String s = "";
        for (int i = 0; i < indent; i++)
        {
            s += "   ";
        }
        print(s);
    }

  

    public void visit (CompilationUnit n)
    {
        println("Intercode Generator starts");

        n.block.accept(this);
	println("*************End of the InterCode Generator*************");
    }

    public void visit (BlockStatementNode n)
    {
	for(DeclarationNode decl : n.decls)
	     decl.accept(this);
	for(StatementNode stmt : n.stmts){
	     Bassigns = new ArrayList<AssignmentNode>();
	     stmt.accept(this);
	}
    }

    public void visit(DeclarationNode n)
    {
        n.type.accept(this);
	n.id.type =n.type.basic;
	
        n.id.accept(this);
	println("");
    }

    public void visit(TypeNode n)
    {
        print(" "+n.basic);
        if(n.array != null)
        {
            n.array.accept(this);
        }
    }

    
    public void visit(ParenNode n) {
	n.node.accept(this);
    }

    public void visit(ConditionalNode n)
    {
	println("IfStatementNode");
        n.condition.accept(this);
	IdentifierNode temp = TempNode.newTemp();
	ParenNode cond = (ParenNode)n.condition;
	ExprNode expr = null;
	if(cond.node instanceof BinExprNode){
		expr = (BinExprNode)cond.node;	
	} else if (cond.node instanceof BooleanNode){
		expr = (BooleanNode)cond.node;
	}
	AssignmentNode assign = new AssignmentNode(temp, expr);
	n.assigns = Bassigns;
	n.assigns.add(Bassigns.get(Bassigns.size()-1));
	((ParenNode)n.condition).node = temp;
	n.falseLabel = LabelNode.newLabel();
        n.stmt.accept(this);
	
	if (n.elseStmt != null)
        {
            print("Else Clause");
            n.elseStmt.accept(this);
        }
    }
    
    public void visit(GotoNode n){
	    
	    
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
    }

    public void visit(AssignmentNode n)
    {
        n.left.accept(this);
        IdentifierNode leftId = (IdentifierNode)n.left;
        Type leftType = leftId.type;
	print(" =");
        n.right.accept(this);
	println("");
    }
    
    public void visit(BinExprNode n) {
	
	ExprNode expr = null;
	if(!(Bassigns.isEmpty())){
		bR = n.left;
		Count++;
		n.left = a;
		temp1 = n.right;
		n.right = bR;
	}
	
	IdentifierNode temp = TempNode.newTemp();
	a = temp;
	//println("In Binary Expression Node:");
        if (n.left instanceof IdentifierNode){
       	    ((IdentifierNode) n.left).accept(this);
        }else if (n.left instanceof NumNode) {
            ((NumNode) n.left).accept(this);
        }else if (n.left instanceof RealNode) {
            ((RealNode) n.left).accept(this);
        }else if (n.left instanceof BooleanNode){
            ((BooleanNode)n.left).accept(this);
        }else if (n.left instanceof ParenNode) {
            ((ParenNode) n.left).accept(this);
        }else
            ((BinExprNode) n.left).accept(this);
        print(" "+n.op);
        if (n.right != null) {
            if (n.right instanceof IdentifierNode) {
                ((IdentifierNode) n.right).accept(this);
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
           }e
	   AssignmentNode assign = new AssignmentNode(temp, n);
	   Bassigns.add(assign);
		
        } else {
            println("@@@ n.right == null in BinExprNode: " + n.right);
        }

	
    }

    public void visit(StatementNode n)
    {
	    printIndent();
	    n.stmt.accept(this);
    }


    public void visit(IdentifierNode n)
    {
        print (" " + n.w);
        if (n.array != null)
        {
            n.array.accept(this);
        }

    }

    public void visit(BooleanNode n)
    {
       
    }

    public void visit(NumNode n)
    {
        
	print(" "+n.value);
    }

    public void visit(RealNode n)
    {
       
	print(" "+n.value);
    }



}
