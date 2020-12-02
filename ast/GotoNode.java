package assign7.ast;

import assign7.visitor.ASTVisitor;

public class GotoNode extends StatementNode
{
    int Lnum;
    int Tnum;
    
    public GotoNode()
    {

    }
   
    public void accept(ASTVisitor v)
    {
        v.visit(this);
    }

}
