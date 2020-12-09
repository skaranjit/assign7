package assign7.ast;

import assign7.visitor.ASTVisitor;
import java.util.*;
import assign7.inter.*;

public class StatementNode
{
    public StatementNode stmt;
     //AssignmentNode which will be added before IFStatementNode for Intermediate Code
    
    public List<AssignmentNode> assigns = new ArrayList<AssignmentNode>();
    
    
    //Label for intermediate Code
    public LabelNode falseLabel;
    public LabelNode startLabel;
    public GotoNode toGoto;
    public void accept(ASTVisitor v)
    {
        v.visit(this);
    }

}
