package assign7.intercode;

import assign7.ast.*;
import assign7.lexer.*;
import assign7.visitor.*;

public class TempNode extends IdentifierNode {
  public static int num = 0;
  public TempNode() {
    
  }
  public static IdentifierNode newTemp() {
    num ++;
    return new IdentifierNode(new Word("t" + num, Tag.ID),null);
  }
  public static void Tempminus(){
      num --;
  }
  
  public void accept(ASTVisitor v) {
     v.visit(this);
  }
}
