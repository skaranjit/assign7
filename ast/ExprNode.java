package assign6.ast;
ssign7.

ssign7.
import assign6.visitor.*;
ssign7.
import assign6.lexer.*;
ssign7.

ssign7.
public class ExprNode extends Node
ssign7.
{
ssign7.
    public Type type = null;
ssign7.
    public ExprNode()
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
}
ssign7.
