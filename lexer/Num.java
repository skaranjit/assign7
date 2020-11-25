
ssign7.

ssign7.
package assign6.lexer;
ssign7.

ssign7.

ssign7.
public class Num extends Token
ssign7.
{
ssign7.
    public final int value;
ssign7.

ssign7.
    public Num(int v)
ssign7.
    {
ssign7.
        super(Tag.NUM);
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

ssign7.
