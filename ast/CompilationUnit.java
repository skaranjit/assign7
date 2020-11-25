package assign6.ast;
ssign7.

ssign7.
import assign6.visitor.*;
ssign7.
import assign6.parser.Env;
ssign7.

ssign7.
// CompilationUnit is root node
ssign7.
// BlockStatementNode is just below CompilationUnit (i.e. - child of CompilationUnit)
ssign7.

ssign7.
public class CompilationUnit extends Node
ssign7.
{
ssign7.
    public BlockStatementNode block;
ssign7.
    public Env symbolTable;
ssign7.

ssign7.
    public CompilationUnit()
ssign7.
    {
ssign7.

ssign7.
    }
ssign7.

ssign7.
    public CompilationUnit(BlockStatementNode assign)
ssign7.
    {
ssign7.
        this.block = block;
ssign7.
    }
ssign7.

ssign7.
    public void accept(ASTVisitor v)
ssign7.
    {
ssign7.
        v.visit(this);
ssign7.
    }
ssign7.

ssign7.
}
ssign7.
