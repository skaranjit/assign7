package assign6.ast;
ssign7.

ssign7.
import assign6.visitor.*;
ssign7.

ssign7.
public class ArrayIDNode extends IdentifierNode
ssign7.
{
ssign7.
    public ArrayIDNode id;
ssign7.
    public ExprNode node;
ssign7.

ssign7.
    public ArrayIDNode()
ssign7.
    {
ssign7.

ssign7.
    }
ssign7.

ssign7.
    public ArrayIDNode(ExprNode n, ArrayIDNode id)
ssign7.
    {
ssign7.
        this.node = n;
ssign7.
        this.id = id;
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
