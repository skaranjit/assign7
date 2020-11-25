package assign6.lexer;
ssign7.

ssign7.
public class Word extends Token
ssign7.
{
ssign7.
    public String lexeme = "";
ssign7.

ssign7.
    public static final Word True  = new Word("true", Tag.TRUE);
ssign7.
    public static final Word False = new Word("false", Tag.FALSE);
ssign7.
    public static final Word and   = new Word("&&", Tag.AND);
ssign7.
    public static final Word or    = new Word("||", Tag.OR);
ssign7.
    public static final Word eq    = new Word("==", Tag.EQ);
ssign7.
    public static final Word ne    = new Word("!=", Tag.NE);
ssign7.
    public static final Word le    = new Word("<=", Tag.LE);
ssign7.
    public static final Word ge    = new Word(">=", Tag.GE);
ssign7.
    public static final Word minus = new Word("minus", Tag.MINUS);
ssign7.
    public static final Word temp  = new Word("t", Tag.TEMP);
ssign7.

ssign7.
    // ** new code
ssign7.
    public static final Word eof   = new Word("eof", Tag.EOF);
ssign7.

ssign7.
    public Word (String s, int tag)
ssign7.
    {
ssign7.
        super(tag) ;
ssign7.
        lexeme = s ;
ssign7.
    }
ssign7.

ssign7.
    public String toString()
ssign7.
    {
ssign7.
        return lexeme;
ssign7.
    }
ssign7.

ssign7.
}
ssign7.
