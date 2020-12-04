package assign7.ast;

import assign7.visitor.*;


public class ConditionalNode extends StatementNode
{
    public ExprNode condition;
    public StatementNode stmt;
    public StatementNode elseStmt;

    //AssignmentNode which will be added before IFStatementNode for Intermediate Code
    
    public List<AssignmentNode> assigns = new ArrayList<AssignmnetNode>();
    
    
    //Label for intermediate Code
    public LabelNode falseLabel;
    
    
    public ConditionalNode()
    {

    }

    public ConditionalNode(ExprNode condition, StatementNode stmt, StatementNode elseStmt)
    {
        this.condition = condition;
        this.stmt = stmt;
        this.elseStmt = elseStmt;
    }

    public void accept(ASTVisitor v)
    {
        v.visit(this);
    }

}
