package assign7.ast;

import assign7.visitor.ASTVisitor;

public class StatementNode
{
    public StatementNode stmt;
    public void accept(ASTVisitor v)
    {
        v.visit(this);
    }

}
