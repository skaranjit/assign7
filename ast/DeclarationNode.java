package assign7.ast;

import assign7.visitor.*;


public class DeclarationNode extends Node
{
    public TypeNode type;
    public IdentifierNode id;
   
    public DeclarationNode()
    {

    }

    public DeclarationNode(TypeNode type, IdentifierNode id)
    {
        this.type = type;
        this.id = id;
    }

    public void accept(ASTVisitor v)
    {
        v.visit(this);
    }

}
