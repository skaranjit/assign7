package assign7.intercode;

import assign7.lexer.*;
import java.util.*;
import assign7.parser.*;
import assign7.ast.*;

public class GotoNode extends StatementNode
{
    public LabelNode gotoLabel = null;
    
    public GotoNode(LabelNode label, StatementNode stmts)
    {
        this.gotoLabel = label;
        this.stmt = stmts;
    }
    public GotoNode(){ 
    }
    public void accept(ASTVisitor v)
    {
        v.visit(this);
    }

}
