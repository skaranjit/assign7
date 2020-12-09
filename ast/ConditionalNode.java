package assign7.ast;

import assign7.visitor.*;
import assign7.intercode.*;
import assign7.lexer.*;
import java.util.*;


public class ConditionalNode extends StatementNode
{
    public ExprNode condition;
    public StatementNode stmt;
    public StatementNode elseStmt;

    public ConditionalNode()
    {

    }

    public ConditionalNode(ExprNode condition, StatementNode stmt, StatementNode elseStmt)
    {
        this.condition = condition;
        this.stmt = stmt;
        this.elseStmt = elseStmt;
    }

    public void accept(ASTVisitor v)
    {
        v.visit(this);
    }

}
