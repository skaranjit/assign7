package assign7.ast;

import assign7.visitor.*;
import java.util.*;
import assign7.intercode.*;
import assign7.lexer.*;

public class WhileNode extends StatementNode
{
    public ExprNode condition;
    public StatementNode stmt;
    public List<AssignmentNode> assigns = new ArrayList<AssignmentNode>();
    
    
    //Label for intermediate Code
    public LabelNode falseLabel;
    public GotoNode wGoto;
    public WhileNode()
    {

    }

    public WhileNode(ExprNode node, StatementNode stmt)
    {
        this.condition = node;
        this.stmt = stmt;
    }

    public void accept(ASTVisitor v)
    {
        v.visit(this);
    }

}
