package assign6.ast;
ssign7.

ssign7.
import assign6.visitor.*;
ssign7.

ssign7.

ssign7.
public class ConditionalNode extends StatementNode
ssign7.
{
ssign7.
    public ExprNode condition;
ssign7.
    public StatementNode stmt;
ssign7.
    public StatementNode elseStmt;
ssign7.

ssign7.
    public ConditionalNode()
ssign7.
    {
ssign7.

ssign7.
    }
ssign7.

ssign7.
    public ConditionalNode(ExprNode condition, StatementNode stmt, StatementNode elseStmt)
ssign7.
    {
ssign7.
        this.condition = condition;
ssign7.
        this.stmt = stmt;
ssign7.
        this.elseStmt = elseStmt;
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
