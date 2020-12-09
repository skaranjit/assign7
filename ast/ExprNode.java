package assign7.ast;

import assign7.visitor.*;
import assign7.lexer.*;

public class ExprNode extends Node
{
    //AssignmentNode which will be added before IFStatementNode for Intermediate Code
    
    public List<AssignmentNode> assigns = new ArrayList<AssignmentNode>();
    
    
    //Label for intermediate Code
    public LabelNode falseLabel;
    
    public Type type = null;
    public ExprNode()
    {

    }

    public void accept(ASTVisitor v)
    {
        v.visit(this);
    }
}
