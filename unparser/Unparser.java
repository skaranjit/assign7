package assign6.unparser;
ssign7.

ssign7.
import assign6.ast.*;
ssign7.
import assign6.parser.*;
ssign7.
import assign6.typechecker.TypeChecker;
ssign7.
import assign6.visitor.*;
ssign7.
import java.io.*;
ssign7.

ssign7.

ssign7.

ssign7.
public class Unparser extends ASTVisitor
ssign7.
{
ssign7.

ssign7.
    public File tempFile;
ssign7.
    public FileWriter tempFileWriter;
ssign7.
    public TypeChecker typeCheck;
ssign7.

ssign7.
    public Unparser(TypeChecker typeChecker)
ssign7.
    {
ssign7.
        try
ssign7.
        {
ssign7.
            tempFile = new File("output.txt");
ssign7.
            tempFileWriter = new FileWriter(tempFile);
ssign7.
            System.out.println("\n** Output.txt created successfully! ** ");
ssign7.
        }
ssign7.
        catch (IOException e)
ssign7.
        {
ssign7.

ssign7.
        }
ssign7.

ssign7.
        this.typeCheck = typeChecker;
ssign7.
        visit(this.typeCheck.cu);
ssign7.

ssign7.
        try
ssign7.
        {
ssign7.
            tempFileWriter.close();
ssign7.
        }
ssign7.
        catch (IOException e)
ssign7.
        {
ssign7.

ssign7.
        }
ssign7.
    }
ssign7.

ssign7.
    void print(String s)
ssign7.
    {
ssign7.
        try
ssign7.
        {
ssign7.
            tempFileWriter.append(s);
ssign7.
        }
ssign7.
        catch (IOException e)
ssign7.
        {
ssign7.

ssign7.
        }
ssign7.
    }
ssign7.

ssign7.
    void println(String s)
ssign7.
    {
ssign7.
        try
ssign7.
        {
ssign7.
            tempFileWriter.append(s + "\n");
ssign7.
        }
ssign7.
        catch (IOException e)
ssign7.
        {
ssign7.

ssign7.
        }
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

ssign7.
    int indent = 0;
ssign7.

ssign7.
    void indentUp()
ssign7.
    {
ssign7.
        indent++;
ssign7.
    }
ssign7.

ssign7.
    void indentDown()
ssign7.
    {
ssign7.
        indent--;
ssign7.
    }
ssign7.

ssign7.
    void printIndent()
ssign7.
    {
ssign7.
        String s = "";
ssign7.
        for (int i = 0; i < indent; i++)
ssign7.
        {
ssign7.
            s += "   ";
ssign7.
        }
ssign7.
        print(s);
ssign7.
    }
ssign7.

ssign7.
    public void visit(CompilationUnit n)
ssign7.
    {
ssign7.
        n.block.accept(this);
ssign7.
    }
ssign7.

ssign7.
    public void visit(BlockStatementNode n)
ssign7.
    {
ssign7.
        println("{");
ssign7.

ssign7.
        indentUp();
ssign7.
        n.decls.accept(this);
ssign7.
        indentDown();
ssign7.

ssign7.
        indentUp();
ssign7.
        n.stmts.accept(this);
ssign7.
        indentDown();
ssign7.
        printIndent();
ssign7.
        println("}");
ssign7.
    }
ssign7.

ssign7.
    public void visit(Declarations n)
ssign7.
    {
ssign7.
        if (n.decls != null)
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
            n.type.accept(this);
ssign7.
            print(" ");
ssign7.
            n.id.accept(this);
ssign7.

ssign7.
            println(" ;");
ssign7.
    }
ssign7.

ssign7.
    public void visit(TypeNode n)
ssign7.
    {
ssign7.
        printIndent();
ssign7.
        print(n.basic.toString());
ssign7.

ssign7.
        if(n.array != null)
ssign7.
            n.array.accept(this);
ssign7.
    }
ssign7.

ssign7.
    public void visit(ArrayTypeNode n)
ssign7.
    {
ssign7.
        print("[");
ssign7.
        print("" +n.size);
ssign7.
        print("]");
ssign7.

ssign7.
        if(n.type != null)
ssign7.
            n.type.accept(this);
ssign7.
    }
ssign7.

ssign7.
    public void visit(Statements n)
ssign7.
    {
ssign7.
        if (n.stmts != null){
ssign7.
            if(n.stmt!= null)n.stmt.accept(this);
ssign7.
             if(n.decls!= null)n.decls.accept(this);
ssign7.
            n.stmts.accept(this);
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

ssign7.
    }
ssign7.

ssign7.
    public void visit(AssignmentNode n)
ssign7.
    {
ssign7.
        printIndent();
ssign7.
        n.left.accept(this);
ssign7.
        print(" = ");
ssign7.

ssign7.
        if (n.right instanceof  IdentifierNode)
ssign7.
            ((IdentifierNode)n.right).accept(this);
ssign7.
        else if (n.right instanceof NumNode)
ssign7.
            ((NumNode)n.right).accept(this);
ssign7.
        else if (n.right instanceof RealNode)
ssign7.
            ((RealNode)n.right).accept(this);
ssign7.
        else {
ssign7.
            ((BinExprNode)n.right).accept(this);
ssign7.
        }
ssign7.
        println(";");
ssign7.
    }
ssign7.

ssign7.
    public void visit(BinExprNode n)
ssign7.
    {
ssign7.
        if (n.left instanceof IdentifierNode)
ssign7.
        {
ssign7.
            ((IdentifierNode)n.left).accept(this);
ssign7.
        }
ssign7.
        else if (n.left instanceof NumNode)
ssign7.
        {
ssign7.
            ((NumNode)n.left).accept(this);
ssign7.
        }
ssign7.
        else if (n.left instanceof RealNode)
ssign7.
        {
ssign7.
            ((RealNode)n.left).accept(this);
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
        else if (n.left instanceof ParenNode)
ssign7.
        {
ssign7.
            ((ParenNode)n.left).accept(this);
ssign7.
        }
ssign7.
        else
ssign7.
            ((BinExprNode)n.left).accept(this);
ssign7.

ssign7.
        if (n.op != null)
ssign7.
        {
ssign7.
            print(" " + n.op.toString() + " ");
ssign7.
        }
ssign7.

ssign7.
        if (n.right != null)
ssign7.
        {
ssign7.
            if (n.right instanceof IdentifierNode)
ssign7.
            {
ssign7.
                ((IdentifierNode) n.right).accept(this);
ssign7.
            }
ssign7.
            else if (n.right instanceof NumNode)
ssign7.
            {
ssign7.
                ((NumNode) n.right).accept(this);
ssign7.
            }
ssign7.
            else if (n.right instanceof RealNode)
ssign7.
            {
ssign7.
                ((RealNode) n.right).accept(this);
ssign7.
            }
ssign7.
            else if (n.right instanceof BooleanNode)
ssign7.
            {
ssign7.
                ((BooleanNode) n.right).accept(this);
ssign7.
            }
ssign7.
            else if (n.right instanceof ParenNode)
ssign7.
            {
ssign7.
                ((ParenNode) n.right).accept(this);
ssign7.
            }
ssign7.
            else
ssign7.
                ((BinExprNode) n.right).accept(this);
ssign7.

ssign7.
        }
ssign7.
    }
ssign7.

ssign7.
    public void visit(IdentifierNode n)
ssign7.
    {
ssign7.
        print(n.id);
ssign7.
        if (n.array != null)
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
    public void visit(ArrayIDNode n)
ssign7.
    {
ssign7.
        print("[");
ssign7.
        n.node.accept(this);
ssign7.
        print("]");
ssign7.
        if (n.array != null)
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
    public void visit(NumNode n)
ssign7.
    {
ssign7.
        print("" + n.value);
ssign7.
    }
ssign7.

ssign7.
    public void visit(RealNode n)
ssign7.
    {
ssign7.
        print("" + n.value);
ssign7.
    }
ssign7.

ssign7.
    public void visit(BreakNode n)
ssign7.
    {
ssign7.
        printIndent();
ssign7.
        println("break;");
ssign7.
    }
ssign7.

ssign7.
    public void visit(ConditionalNode n)
ssign7.
    {
ssign7.
        printIndent();
ssign7.
        print("if (");
ssign7.
        n.condition.accept(this);
ssign7.
        println(")");
ssign7.
        indentUp();
ssign7.
        n.stmt.accept(this);
ssign7.
        indentDown();
ssign7.
        if (n.elseStmt != null)
ssign7.
        {
ssign7.
            println("else");
ssign7.
            indentUp();
ssign7.
            n.elseStmt.accept(this);
ssign7.
            indentDown();
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
        printIndent();
ssign7.
        print("while (");
ssign7.
        n.condition.accept(this);
ssign7.
        print(")");
ssign7.
        indentUp();
ssign7.
        n.stmt.accept(this);
ssign7.
        indentDown();
ssign7.
    }
ssign7.

ssign7.
    public void visit(DoWhileNode n)
ssign7.
    {
ssign7.
       printIndent();
ssign7.
       println("do");
ssign7.
       indentUp();
ssign7.
       n.stmt.accept(this);
ssign7.
       indentDown();
ssign7.
       printIndent();
ssign7.
       print("while (");
ssign7.
       n.condition.accept(this);
ssign7.
       println(");");
ssign7.
    }
ssign7.

ssign7.
    public void visit(BooleanNode n)
ssign7.
    {
ssign7.
        print(n.bool.toString());
ssign7.
    }
ssign7.

ssign7.
    public void visit(ParenNode n)
ssign7.
    {
ssign7.
        print("(");
ssign7.
        n.node.accept(this);
ssign7.
        print(")");
ssign7.
    }
ssign7.
}
ssign7.

ssign7.
