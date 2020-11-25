package assign6.ast;
ssign7.

ssign7.
import assign6.visitor.*;
ssign7.

ssign7.

ssign7.
public class DeclarationNode extends Node
ssign7.
{
ssign7.
    public TypeNode type;
ssign7.
    public IdentifierNode id;
ssign7.
   
ssign7.
    public DeclarationNode()
ssign7.
    {
ssign7.

ssign7.
    }
ssign7.

ssign7.
    public DeclarationNode(TypeNode type, IdentifierNode id)
ssign7.
    {
ssign7.
        this.type = type;
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
