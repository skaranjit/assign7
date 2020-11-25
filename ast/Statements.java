package assign6.ast;
ssign7.

ssign7.
import assign6.visitor.*;
ssign7.

ssign7.

ssign7.
public class Statements extends Node
ssign7.
{
ssign7.
    public Statements stmts;
ssign7.
    public StatementNode stmt;
ssign7.
    public DeclarationNode decls;
ssign7.
    public Statements()
ssign7.
    {
ssign7.

ssign7.
    }
ssign7.

ssign7.
    public Statements(Statements stmts, AssignmentNode assign)
ssign7.
    {
ssign7.
        this.stmts = stmts;
ssign7.
        this.stmt = assign;
ssign7.
    }
ssign7.

ssign7.
    public void accept(ASTVisitor v)
ssign7.
    {
ssign7.
        v.visit(this);
ssign7.
    }
ssign7.

ssign7.
}
ssign7.
