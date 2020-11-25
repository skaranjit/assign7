package assign6.ast;
ssign7.

ssign7.
import assign6.visitor.*;
ssign7.

ssign7.

ssign7.
public class Declarations extends Node
ssign7.
{
ssign7.
    public Declarations decls;
ssign7.
    public DeclarationNode decl;
ssign7.

ssign7.
    public Declarations()
ssign7.
    {
ssign7.

ssign7.
    }
ssign7.

ssign7.
    public Declarations(Declarations decls, DeclarationNode decl)
ssign7.
    {
ssign7.
        this.decls = decls;
ssign7.
        this.decl = decl;
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
