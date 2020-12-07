package assign7.ast;

import assign7.visitor.*;
import assign7.intercode.*;
import assign7.lexer.*;
import java.util.*;


public class ConditionalNode extends StatementNode
{
    public ExprNode condition;
    public StatementNode stmt;
    public StatementNode elseStmt;

    //AssignmentNode which will be added before IFStatementNode for Intermediate Code
    
    public List<AssignmentNode> assigns = new ArrayList<AssignmentNode>();
    
    
    //Label for intermediate Code
    public LabelNode falseLabel;
    
    public GotoNode IfGoto;
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
