package assign6.ast;
ssign7.

ssign7.
import assign6.visitor.*;
ssign7.
import assign6.parser.*;
ssign7.

ssign7.
/*
ssign7.
public class BlockStatementNode extends StatementNode
ssign7.
{
ssign7.
    public Declarations decls;
ssign7.
    public Statements stmts;
ssign7.

ssign7.
    public BlockStatementNode()
ssign7.
    {
ssign7.

ssign7.
    }
ssign7.

ssign7.
    public BlockStatementNode(Declarations decls, Statements stmts)
ssign7.
    {
ssign7.
        this.decls = decls;
ssign7.
        this.stmts = stmts;
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
}
ssign7.
*/
ssign7.

ssign7.
// new code
ssign7.
public class BlockStatementNode extends StatementNode
ssign7.
{
ssign7.
    public Declarations decls;
ssign7.
    public Statements stmts;
ssign7.

ssign7.
    public Env sTable;
ssign7.

ssign7.
    public BlockStatementNode()
ssign7.
    {
ssign7.

ssign7.
    }
ssign7.

ssign7.
    public BlockStatementNode(Declarations decls, Statements stmts)
ssign7.
    {
ssign7.
        this.decls = decls;
ssign7.
        this.stmts = stmts;
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
}
ssign7.
