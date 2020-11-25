package assign6.lexer;
ssign7.
public class Array extends Type {
ssign7.
   public Type of;                  // array *of* type
ssign7.
   public int size = 1;             // number of elements
ssign7.
   public Array(int sz, Type p) {
ssign7.
      super("[]", Tag.INDEX, sz*p.width); size = sz;  of = p;
ssign7.
   }
ssign7.
   public boolean isArray() { return true;}
ssign7.
   public String toString() { return "[" + size + "] " + of.toString(); }
ssign7.
 }
ssign7.
