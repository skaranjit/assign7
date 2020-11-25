package assign6.ast;
ssign7.

ssign7.
import assign6.visitor.*;
ssign7.
import assign6.lexer.*;
ssign7.

ssign7.
public class RealNode extends ExprNode
ssign7.
{
ssign7.
    public float value;
ssign7.
    public Real v;
ssign7.

ssign7.
    public RealNode(Real v)
ssign7.
    {
ssign7.
        this.value = v.value;
ssign7.
        this.v = v;
ssign7.
    }
ssign7.

ssign7.
    public RealNode()
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
    public void printNode()
ssign7.
    {
ssign7.
        System.out.println("RealNode: " + value);
ssign7.
    }
ssign7.

ssign7.
}
ssign7.
