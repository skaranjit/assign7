package assign7.ast;

import assign7.lexer.Type;
import assign7.visitor.*;
import java.util.*; 

public class AssignmentNode extends StatementNode
{
    // **for the future version of Compiler, it should not be IdentifierNode, it should be Array for final version**
    public IdentifierNode left;
    public ExprNode right;
    public List<AssignmentNode> assigns = new ArrayList<AssignmentNode>();
    
    public AssignmentNode()
    {

    }

    //public AssignmentNode(IdentifierNode, id, BinExprNode right){
    public AssignmentNode(IdentifierNode id, ExprNode right)
    {
        this.left = id;
        this.right = right;
    }

    public void accept(ASTVisitor v)
    {
        v.visit(this);
    }
}
