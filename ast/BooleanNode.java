package assign7.ast;

import assign7.lexer.Word;
import assign7.visitor.ASTVisitor;

public class BooleanNode extends ExprNode
{
    public Word bool;

    public BooleanNode(Word v)
    {
        this.bool = v;
    }

    public BooleanNode()
    {

    }

    public void accept(ASTVisitor v)
    {
        v.visit(this);
    }

}


