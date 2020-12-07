package assign7.intercode;

import assign7.visitor.ASTVisitor;
import assign7.lexer.*;
import java.util.*;
import assign7.parser.*;

public class GotoNode extends StatementNode
{
    public LabelNode gotoLabel = null;
    public StatementNode gotostmts = null;
    
    public GotoNode(LabelNode label, StatementNode stmts)
    {
        this.gotoLabel = label;
        this.gotostmts = stmts;
    }
   
    public void accept(ASTVisitor v)
    {
        v.visit(this);
    }

}
