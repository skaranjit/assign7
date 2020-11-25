package assign6.ast;
ssign7.

ssign7.
import assign6.lexer.Word;
ssign7.
import assign6.visitor.ASTVisitor;
ssign7.

ssign7.
public class BooleanNode extends ExprNode
ssign7.
{
ssign7.
    public Word bool;
ssign7.

ssign7.
    public BooleanNode(Word v)
ssign7.
    {
ssign7.
        this.bool = v;
ssign7.
    }
ssign7.

ssign7.
    public BooleanNode()
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

ssign7.

ssign7.
