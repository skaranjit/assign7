package assign6.ast;
ssign7.

ssign7.
import assign6.visitor.*;
ssign7.

ssign7.

ssign7.
public class WhileNode extends StatementNode
ssign7.
{
ssign7.
    public ExprNode condition;
ssign7.
    public StatementNode stmt;
ssign7.

ssign7.
    public WhileNode()
ssign7.
    {
ssign7.

ssign7.
    }
ssign7.

ssign7.
    public WhileNode(ExprNode node, StatementNode stmt)
ssign7.
    {
ssign7.
        this.condition = node;
ssign7.
        this.stmt = stmt;
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
