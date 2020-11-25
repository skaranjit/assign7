package assign7.ast;

import assign7.visitor.*;
import assign7.parser.Env;

// CompilationUnit is root node
// BlockStatementNode is just below CompilationUnit (i.e. - child of CompilationUnit)

public class CompilationUnit extends Node
{
    public BlockStatementNode block;
    public Env symbolTable;

    public CompilationUnit()
    {

    }

    public CompilationUnit(BlockStatementNode assign)
    {
        this.block = block;
    }

    public void accept(ASTVisitor v)
    {
        v.visit(this);
    }

}
