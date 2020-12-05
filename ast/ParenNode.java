package assign7.ast;

import assign7.visitor.ASTVisitor;

public class ParenNode extends ExprNode
{
    public Node node;
    public LabelNode falseLabel;
    public List<AssignmentNode> assignsparen = new ArrayList<AssignmentNode>();
    public ParenNode()
    {

    }

    public void accept(ASTVisitor v)
    {
        v.visit(this);
    }

}
