package assign6.ast;
ssign7.

ssign7.
import assign6.lexer.Type;
ssign7.
import assign6.visitor.*;
ssign7.

ssign7.
public class AssignmentNode extends StatementNode
ssign7.
{
ssign7.
    // **for the future version of Compiler, it should not be IdentifierNode, it should be Array for final version**
ssign7.
    public IdentifierNode left;
ssign7.
    public ExprNode right;
ssign7.

ssign7.
    public AssignmentNode()
ssign7.
    {
ssign7.

ssign7.
    }
ssign7.

ssign7.
    //public AssignmentNode(IdentifierNode, id, BinExprNode right){
ssign7.
    public AssignmentNode(IdentifierNode id, ExprNode right)
ssign7.
    {
ssign7.
        this.left = id;
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
}
ssign7.
