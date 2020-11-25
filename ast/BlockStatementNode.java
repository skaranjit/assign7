package assign7.ast;

import assign7.visitor.*;
import assign7.parser.*;

/*
public class BlockStatementNode extends StatementNode
{
    public Declarations decls;
    public Statements stmts;

    public BlockStatementNode()
    {

    }

    public BlockStatementNode(Declarations decls, Statements stmts)
    {
        this.decls = decls;
        this.stmts = stmts;
    }

    public void accept(ASTVisitor v)
    {
        v.visit(this);
    }
}
*/

// new code
public class BlockStatementNode extends StatementNode
{
    public Declarations decls;
    public Statements stmts;

    public Env sTable;

    public BlockStatementNode()
    {

    }

    public BlockStatementNode(Declarations decls, Statements stmts)
    {
        this.decls = decls;
        this.stmts = stmts;
    }

    public void accept(ASTVisitor v)
    {
        v.visit(this);
    }
}
