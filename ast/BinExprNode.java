package assign7.ast;

import assign7.visitor.*;
import assign7.lexer.*;
import assign7.intercode.*;
import java.util.*;

public class BinExprNode extends ExprNode
{
    public ExprNode left;
    public ExprNode right;
    public Token op;
    public Type type;
     //AssignmentNode which will be added before IFStatementNode for Intermediate Code
    
    public List<AssignmentNode> assigns = new ArrayList<AssignmentNode>();
    
    
    //Label for intermediate Code
    public LabelNode falseLabel;
    
    public BinExprNode()
    {

    }

    public BinExprNode(ExprNode left, ExprNode right)
    {
        this.left = left;
        this.right = right;
    }

    public BinExprNode(Token op, ExprNode left, ExprNode right)
    {
        this.op = op;
        this.left = left;
        this.right = right;
    }

    public void accept(ASTVisitor v)
    {
        v.visit(this);
    }

}
