package assign7.intercode;

import assign7.visitor.ASTVisitor;
import assign7.lexer.*;
import java.util.*;
import assign7.parser.*;

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
