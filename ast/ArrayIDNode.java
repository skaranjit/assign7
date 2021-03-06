package assign7.ast;

import assign7.visitor.*;
import assign7.ast.*;
import assign7.intercode.*;
import java.util.*;
public class ArrayIDNode extends IdentifierNode
{
    public IdentifierNode iden;
    public ArrayIDNode id;
    public ExprNode node;
    public List<AssignmentNode> assigns = new ArrayList<AssignmentNode>();
    public ArrayIDNode()
    {

    }

    public ArrayIDNode(ExprNode n, ArrayIDNode id)
    {
        this.node = n;
        this.id = id;
    }

    public void accept(ASTVisitor v)
    {
        v.visit(this);
    }

}
