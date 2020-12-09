package assign7.ast;
import assign7.intercode.*;
import assign7.visitor.ASTVisitor;

public class BreakNode extends StatementNode
{
    public BreakNode()
    {

    }

    public void accept(ASTVisitor v)
    {
        v.visit(this);
    }

}
