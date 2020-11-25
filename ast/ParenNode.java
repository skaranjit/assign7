package assign6.ast;
ssign7.

ssign7.
import assign6.visitor.ASTVisitor;
ssign7.

ssign7.
public class ParenNode extends ExprNode
ssign7.
{
ssign7.
    public Node node;
ssign7.

ssign7.
    public ParenNode()
ssign7.
    {
ssign7.

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
