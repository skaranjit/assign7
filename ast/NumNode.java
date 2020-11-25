package assign6.ast;
ssign7.

ssign7.
import assign6.lexer.*;
ssign7.
import assign6.visitor.*;
ssign7.

ssign7.
public class NumNode extends ExprNode
ssign7.
{
ssign7.
    public int value;
ssign7.
    public Num v;
ssign7.

ssign7.
    public NumNode()
ssign7.
    {
ssign7.

ssign7.
    }
ssign7.

ssign7.
    public NumNode(Num v)
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
        System.out.println("NumNode: " + value);
ssign7.
    }
ssign7.

ssign7.
}
ssign7.
