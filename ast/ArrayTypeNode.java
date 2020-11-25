package assign7.ast;

import assign7.visitor.*;
import assign7.lexer.*;
public class ArrayTypeNode extends TypeNode
{
    public TypeNode type;
    public ExprNode size;

    public ArrayTypeNode()
    {

    }

    public ArrayTypeNode(ExprNode size, TypeNode type)
    {
        this.size = size;
        this.type = type;
    }

    public void accept(ASTVisitor v)
    {
        v.visit(this);
    }
}
