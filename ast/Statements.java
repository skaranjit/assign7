package assign7.ast;

import assign7.visitor.*;


public class Statements extends Node
{
    public Statements stmts;
    public StatementNode stmt;
    public DeclarationNode decls;
    public Statements()
    {

    }

    public Statements(Statements stmts, AssignmentNode assign)
    {
        this.stmts = stmts;
        this.stmt = assign;
    }

    public void accept(ASTVisitor v)
    {
        v.visit(this);
    }

}
