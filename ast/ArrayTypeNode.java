package assign6.ast;
ssign7.

ssign7.
import assign6.visitor.*;
ssign7.
import assign6.lexer.*;
ssign7.
public class ArrayTypeNode extends TypeNode
ssign7.
{
ssign7.
    public TypeNode type;
ssign7.
    public BinExprNode size;
ssign7.

ssign7.
    public ArrayTypeNode()
ssign7.
    {
ssign7.

ssign7.
    }
ssign7.

ssign7.
    public ArrayTypeNode(BinExprNode size, TypeNode type)
ssign7.
    {
ssign7.
        this.size = size;
ssign7.
        this.type = type;
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
