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
    public LabelNode globalLabel;

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
        Bassigns = new ArrayList<AssignmentNode>();

	    println("IfStatementNode");
        n.condition.accept(this);
        IdentifierNode temp = TempNode.newTemp();
        ParenNode cond = (ParenNode)n.condition;
        ExprNode expr = null;
        if(cond.node instanceof BinExprNode){
            expr = Bassigns.get(Bassigns.size()-1).left;
        } else if (cond.node instanceof BooleanNode){
            expr = (BooleanNode)cond.node;
        }
        AssignmentNode assign = new AssignmentNode(temp, expr);
        for(AssignmentNode assign1 : Bassigns){
            n.assigns.add(assign1);
        }
        n.assigns.add(assign);
        ((ParenNode)n.condition).node = temp;
        n.falseLabel = LabelNode.newLabel();
        n.toGoto = new GotoNode(n.falseLabel, n.stmt);
        n.toGoto.accept(this);
    // 	Bassigns = Bassigns1;
        if (n.elseStmt != null)
        {
            print("Else Clause");
            n.elseStmt.accept(this);
        }
    }
    
    public void visit(GotoNode n){
	   n.stmt.accept(this);
    }
    public void visit(WhileNode n)
    {
        System.out.println("WhileStatement");
        Bassigns = new ArrayList<AssignmentNode>();
        n.startLabel = LabelNode.newLabel();
        n.condition.accept(this);
        IdentifierNode temp = TempNode.newTemp();
        ParenNode cond = (ParenNode)n.condition;
        ExprNode expr = null;
        if(cond.node instanceof BinExprNode){
            expr = Bassigns.get(Bassigns.size()-1).left;
        } else if (cond.node instanceof BooleanNode){
            expr = (BooleanNode)cond.node;
        }
        AssignmentNode assign = new AssignmentNode(temp, expr);
        for(AssignmentNode assign1 : Bassigns){
            n.assigns.add(assign1);
        }
        n.assigns.add(assign);
	    	((ParenNode)n.condition).node = temp;

        n.falseLabel = LabelNode.newLabel();
	    globalLabel = n.falseLabel;
        n.toGoto = new GotoNode(n.falseLabel, n.stmt);
	    n.toGoto.accept(this);
       // n.stmt.accept(this);
    }

    public void visit(DoWhileNode n)
    {
        Bassigns = new ArrayList<AssignmentNode>();
        System.out.println("visiting DoWhileNode");
        n.startLabel = LabelNode.newLabel();
        n.toGoto = new GotoNode(n.startLabel, n.stmt);
        n.toGoto.accept(this);
        println(n.startLabel.id + ": ");
        n.condition.accept(this);
	    IdentifierNode temp = TempNode.newTemp();
        ParenNode cond = (ParenNode)n.condition;
        ExprNode expr = null;
        if(cond.node instanceof BinExprNode){
            expr = Bassigns.get(Bassigns.size()-1).left;
        } else if (cond.node instanceof BooleanNode){
            expr = (BooleanNode)cond.node;
        }
	    AssignmentNode assign = new AssignmentNode(temp, expr);
        for(AssignmentNode assign1 : Bassigns){
            n.assigns.add(assign1);
        }
        n.assigns.add(assign);
	    	((ParenNode)n.condition).node = temp;

        n.falseLabel = LabelNode.newLabel();
    }

    public void visit (ArrayIDNode n)
    {
        System.out.println("visiting ArrayIDNode");
//         List<AssignmentNode> temp1 = new ArrayList<AssignmentNode>();
//         temp1 = Bassigns;
//         Bassigns = new ArrayList<AssignmentNode>();
        IdentifierNode temp = TempNode.newTemp();
	ExprNode expr = null;
        n.node.accept(this);
	IdentifierNode temp2 = TempNode.newTemp();
        expr = new BinExprNode(new Word("*",Tag.ID),temp,new NumNode(new Num(8)));
        AssignmentNode assign2 = new AssignmentNode (temp2 ,expr);
        expr = temp2;
        n.assigns.add(assign2);
        IdentifierNode temp3 = TempNode.newTemp();
        IdentifierNode x = n.iden;
        x.array = new ArrayIDNode();
        x.array.node = expr;
        AssignmentNode assign3 = new AssignmentNode (temp3,x);
        n.assigns.add(assign3);
        if(n.node instanceof BinExprNode){
            expr = Bassigns.get(Bassigns.size()-1).left;
        } else if (n.node instanceof NumNode){
            expr = (NumNode)n.node;
        }else if (n.node instanceof IdentifierNode){
            expr = (IdentifierNode)n.node;
        }
	for(AssignmentNode assign1 : Bassigns){
            n.assigns.add(assign1);
        }
	AssignmentNode assign = new AssignmentNode(temp, expr);
	             n.assigns.add(assign);

	n.node = temp3;
        if(n.id != null)
        {
            n.id.accept(this);
        }
	   // Bassigns = temp1;
    }

    public void visit(ArrayTypeNode n)
    {
        System.out.println("ArrayDimsNode");
        List<AssignmentNode> temp1 = new ArrayList<AssignmentNode>();
        temp1 = Bassigns;
        Bassigns = new ArrayList<AssignmentNode>();
        n.size.accept(this);
        IdentifierNode temp = TempNode.newTemp();
        ExprNode expr = null;
        if(n.size instanceof BinExprNode){
            expr = (BinExprNode)n.size;
            expr = Bassigns.get(Bassigns.size()-1).left;
        } else if (n.size instanceof NumNode){
            expr = (NumNode)n.size;
        }else if (n.size instanceof IdentifierNode){
            expr = (IdentifierNode)n.size;
        }
        for(AssignmentNode assign1 : Bassigns){
            n.assigns.add(assign1);
        }
        AssignmentNode assign = new AssignmentNode(temp, expr);
        n.assigns.add(assign);
        
        IdentifierNode temp2 = TempNode.newTemp();
        expr = new BinExprNode(new Word("*",Tag.ID),temp,new NumNode(new Num(8)));
        AssignmentNode assign2 = new AssignmentNode (temp2 ,expr);
       
        n.assigns.add(assign2);
	    n.size = temp2;
        if(n.type != null)
        {
            n.type.accept(this);
        }
	Bassigns= temp1;
    }

    public void visit(BreakNode n)
    {
        n.falseLabel = globalLabel;
    }

    public void visit(AssignmentNode n)
    {
        n.left.accept(this);
        List<AssignmentNode> temp1 = new ArrayList<AssignmentNode>();
        temp1 = Bassigns;
        Bassigns = new ArrayList<AssignmentNode>();
        IdentifierNode leftId = (IdentifierNode)n.left;
        Type leftType = leftId.type;
	    print(" =");
        ExprNode expr = null;
         n.right.accept(this);
        n.assigns = Bassigns;
        println("");
	    Bassigns = temp1;
    }
    ExprNode t = null;
    public void visit(BinExprNode n) 
    {
        ExprNode expr = null;
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
        }else if(n.left instanceof BinExprNode){

            ((BinExprNode) n.left).accept(this);
        } else { 	
        }
        print(" "+n.op);
        if (n.right != null) {
            if (n.right instanceof IdentifierNode) {
                println("onmyleft");
                ((IdentifierNode) n.right).accept(this);
            } else if (n.right instanceof NumNode) {
                ((NumNode) n.right).accept(this);
            } else if (n.right instanceof RealNode) {
                ((RealNode) n.right).accept(this);
            } else if (n.right instanceof BooleanNode){
                ((BooleanNode) n.right).accept(this);
            } else if (n.right instanceof ParenNode) {
                ((ParenNode) n.right).accept(this);
            } else if(n.right instanceof BinExprNode) {
                ((BinExprNode) n.right).accept(this);
           } else { 
		    
	    }
        } else {
            println("@@@ n.right == null in BinExprNode: " + n.right);
        }
        // if(n.left != null){
        IdentifierNode temp = TempNode.newTemp();
        if(!Bassigns.isEmpty()) n.left =a;
        last = n.left;
        t = new BinExprNode(n.op,n.left,n.right);
        AssignmentNode assign = new AssignmentNode(temp, t);
        a = temp;
        Bassigns.add(assign);
        //}
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
