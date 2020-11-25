package assign6.ast;
ssign7.

ssign7.
import assign6.visitor.*;
ssign7.
import assign6.lexer.*;
ssign7.

ssign7.
public class BinExprNode extends ExprNode
ssign7.
{
ssign7.
    public ExprNode left;
ssign7.
    public ExprNode right;
ssign7.
    public Token op;
ssign7.
    public Type type;
ssign7.

ssign7.
    public BinExprNode()
ssign7.
    {
ssign7.

ssign7.
    }
ssign7.

ssign7.
    public BinExprNode(ExprNode left, ExprNode right)
ssign7.
    {
ssign7.
        this.left = left;
ssign7.
        this.right = right;
ssign7.
    }
ssign7.

ssign7.
    public BinExprNode(Token op, ExprNode left, ExprNode right)
ssign7.
    {
ssign7.
        this.op = op;
ssign7.
        this.left = left;
ssign7.
        this.right = right;
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
