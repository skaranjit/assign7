package assign7.ast;

import assign7.visitor.*;
import java.util.*;
import assign7.intercode.*;
import assign7.lexer.*;

public class DoWhileNode extends StatementNode
{
    public StatementNode stmt;
    public ExprNode condition;

    public DoWhileNode()
    {

    }

    public DoWhileNode(ExprNode node, StatementNode stmt)
    {
        this.condition = node;
        this.stmt = stmt;
    }

    public void accept(ASTVisitor v)
    {
        v.visit(this);
    }

}
