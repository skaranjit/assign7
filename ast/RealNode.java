package assign7.ast;

import assign7.visitor.*;
import assign7.lexer.*;

public class RealNode extends ExprNode
{
    public float value;
    public Real v;

    public RealNode(Real v)
    {
        this.value = v.value;
        this.v = v;
    }

    public RealNode()
    {

    }

    public void accept(ASTVisitor v)
    {
        v.visit(this);
    }

    public void printNode()
    {
        System.out.println("RealNode: " + value);
    }

}
