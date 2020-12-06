package assign7.ast;

import assign7.visitor.ASTVisitor;
import assign7.ast.*;
import assign7.intercode.*;
import java.util.*;

public class ParenNode extends ExprNode
{
    public Node node;
    public LabelNode falseLabel;
    public ParenNode()
    {

    }

    public void accept(ASTVisitor v)
    {
        v.visit(this);
    }

}
