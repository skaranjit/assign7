package assign6.lexer;
ssign7.

ssign7.

ssign7.
public class Real extends Token
ssign7.
{
ssign7.
    public final float value;
ssign7.

ssign7.
    public Real(float v)
ssign7.
    {
ssign7.
        super(Tag.REAL);
ssign7.
        value = v;
ssign7.
    }
ssign7.

ssign7.
    public String toString()
ssign7.
    {
ssign7.
        return "" + value;
ssign7.
    }
ssign7.

ssign7.
}
ssign7.
