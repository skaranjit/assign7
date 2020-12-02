package assign7.ast;

import assign7.lexer.*;
import assign7.visitor.*;

public class NumNode extends ExprNode
{
    public int value;
    public Num v;

    public NumNode()
    {

    }

    public NumNode(Num v)
    {
        this.value = v.value;
        this.v = v;
        this.type = Type.Int;
    }

    public void accept(ASTVisitor v)
    {
        v.visit(this);
    }

    public void printNode()
    {
        System.out.println("NumNode: " + value);
    }

}
