package assign6.ast;
ssign7.

ssign7.
import assign6.visitor.*;
ssign7.
import assign6.lexer.*;
ssign7.

ssign7.

ssign7.
public class TypeNode extends Node
ssign7.
{
ssign7.
    public Type basic;
ssign7.
    public ArrayTypeNode array = null;
ssign7.

ssign7.
    public TypeNode()
ssign7.
    {
ssign7.

ssign7.
    }
ssign7.

ssign7.
    public TypeNode(Type basic, ArrayTypeNode array)
ssign7.
    {
ssign7.
        this.basic = basic;
ssign7.
        this.array = array;
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
