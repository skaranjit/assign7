package assign6.visitor;
ssign7.

ssign7.
import assign6.ast.*;
ssign7.
import assign6.parser.*;
ssign7.

ssign7.
public class ASTVisitor
ssign7.
{
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
        n.id.accept(this);
ssign7.
    }
ssign7.

ssign7.
    public void visit(TypeNode n)
ssign7.
    {
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
        n.type.accept(this);
ssign7.
    }
ssign7.

ssign7.
    public void visit(Statements n)
ssign7.
    {
ssign7.
        if (n.stmts != null)
ssign7.
        {
ssign7.
            n.stmt.accept(this);
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
        n.left.accept(this);
ssign7.

ssign7.
        if (n.right instanceof IdentifierNode)
ssign7.
        {
ssign7.
            ((IdentifierNode)n.right).accept(this);
ssign7.
        }
ssign7.
        else if (n.right instanceof NumNode)
ssign7.
        {
ssign7.
            ((NumNode)n.right).accept(this);
ssign7.
        }
ssign7.
        else if (n.right instanceof RealNode)
ssign7.
        {
ssign7.
            ((RealNode)n.right).accept(this);
ssign7.
        }
ssign7.
        else
ssign7.
            ((BinExprNode)n.right).accept(this);
ssign7.

ssign7.
    }
ssign7.

ssign7.
    public void visit(ExprNode n)
ssign7.
    {
ssign7.

ssign7.
    }
ssign7.

ssign7.
    public void visit(BinExprNode n)
ssign7.
    {
ssign7.
        //n.left.accept(this);
ssign7.
        //n.right.accept(this);
ssign7.
    }
ssign7.

ssign7.
    public void visit(NumNode n)
ssign7.
    {
ssign7.

ssign7.
    }
ssign7.

ssign7.
    public void visit(IdentifierNode n)
ssign7.
    {
ssign7.

ssign7.
    }
ssign7.

ssign7.
    public void visit(RealNode n)
ssign7.
    {
ssign7.

ssign7.
    }
ssign7.

ssign7.
    public void visit(BooleanNode n)
ssign7.
    {
ssign7.

ssign7.
    }
ssign7.

ssign7.
    public void visit(DoWhileNode n)
ssign7.
    {
ssign7.

ssign7.
    }
ssign7.

ssign7.
    public void visit(ConditionalNode n)
ssign7.
    {
ssign7.

ssign7.
    }
ssign7.

ssign7.
    public void visit(WhileNode n)
ssign7.
    {
ssign7.

ssign7.
    }
ssign7.

ssign7.
    public void visit (ArrayIDNode n)
ssign7.
    {
ssign7.

ssign7.
    }
ssign7.

ssign7.
    public void visit(BreakNode n)
ssign7.
    {
ssign7.

ssign7.
    }
ssign7.

ssign7.
    public void visit(ParenNode n)
ssign7.
    {
ssign7.

ssign7.
    }
ssign7.
}
ssign7.
