package assign6.ast;
ssign7.

ssign7.
import assign6.visitor.*;
ssign7.
import assign6.lexer.*;
ssign7.

ssign7.
public class IdentifierNode extends ExprNode
ssign7.
{
ssign7.
    public String id;
ssign7.
    public ArrayIDNode array;
ssign7.
    public Word w;
ssign7.

ssign7.

ssign7.
    public IdentifierNode()
ssign7.
    {
ssign7.

ssign7.
    }
ssign7.

ssign7.
    public IdentifierNode(Word w)
ssign7.
    {
ssign7.
        this.id = w.lexeme;
ssign7.
        this.w = w;
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
        System.out.println("IdentifierNode: " + id);
ssign7.
    }
ssign7.

ssign7.
}
ssign7.
