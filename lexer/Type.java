package assign6.lexer;
ssign7.

ssign7.
public class Type extends Word
ssign7.
{
ssign7.
    public int width = 0;
ssign7.

ssign7.
    public static final Type Int = new Type("int", Tag.BASIC, 4);
ssign7.
    public static final Type Float = new Type("float", Tag.BASIC, 8);
ssign7.
    public static final Type Char = new Type("char", Tag.BASIC, 1);
ssign7.
    public static final Type Bool = new Type("bool", Tag.BASIC, 1);
ssign7.
    public static final Type String = new Type("string", Tag.BASIC,100);
ssign7.

ssign7.
    public Type(String s, int tag, int w)
ssign7.
    {
ssign7.
        super(s, tag);
ssign7.
        width = w;
ssign7.
    }
ssign7.

ssign7.
    public static boolean numeric(Type p)
ssign7.
    {
ssign7.
        if (p == Type.Char || p == Type.Int || p == Type.Float)
ssign7.
            return true;
ssign7.
        else
ssign7.
            return false;
ssign7.
    }
ssign7.
    public boolean isArray(){ return false;}
ssign7.

ssign7.
    public static Type max(Type p1, Type p2)
ssign7.
    {
ssign7.
        if (! numeric(p1) || ! numeric(p2))
ssign7.
            return null;
ssign7.
        else if (p1 == Type.Float || p2 == Type.Float)
ssign7.
            return Type.Float;
ssign7.
        else if (p1 == Type.Int || p2 == Type.Int)
ssign7.
            return Type.Int;
ssign7.
        else
ssign7.
            return Type.Char;
ssign7.
    }
ssign7.

ssign7.
}
ssign7.

ssign7.
