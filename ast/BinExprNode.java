package assign7.ast;

import assign7.visitor.*;
import assign7.lexer.*;

public class BinExprNode extends ExprNode
{
    public ExprNode left;
    public ExprNode right;
    public Token op;
    public Type type;

    public BinExprNode()
    {

    }

    public BinExprNode(ExprNode left, ExprNode right)
    {
        this.left = left;
        this.right = right;
    }

    public BinExprNode(Token op, ExprNode left, ExprNode right)
    {
        this.op = op;
        this.left = left;
        this.right = right;
    }

    public void accept(ASTVisitor v)
    {
        v.visit(this);
    }

}
