package assign6.lexer;
ssign7.

ssign7.
import java.io.* ;
ssign7.
import java.util.* ;
ssign7.

ssign7.

ssign7.
public class Lexer
ssign7.
{
ssign7.
    public int line = 0;
ssign7.
    private char peek = ' ';
ssign7.

ssign7.
    private BufferedInputStream bin;
ssign7.
    private FileInputStream in;
ssign7.

ssign7.
    private Hashtable<String, Word> words = new Hashtable<String, Word>();
ssign7.

ssign7.
    public Lexer(){
ssign7.
        reserve(new Word("if", Tag.IF));
ssign7.
        reserve(new Word("else", Tag.ELSE));
ssign7.
        reserve(new Word("while", Tag.WHILE));
ssign7.
        reserve(new Word("do", Tag.DO));
ssign7.
        reserve(new Word("break", Tag.BREAK));
ssign7.

ssign7.
        reserve(Word.True);
ssign7.
        reserve(Word.False);
ssign7.

ssign7.
        // ** new code
ssign7.
        reserve(Word.eof);
ssign7.

ssign7.
        reserve(Type.Int);
ssign7.
        reserve(Type.Char);
ssign7.
        reserve(Type.Bool);
ssign7.
        reserve(Type.Float);
ssign7.

ssign7.
        setupIOStream();
ssign7.
    }
ssign7.

ssign7.
    void reserve (Word w)
ssign7.
    {
ssign7.
        words.put(w.lexeme, w);
ssign7.
    }
ssign7.

ssign7.
    void setupIOStream()
ssign7.
    {
ssign7.
        try
ssign7.
        {
ssign7.
            in = new FileInputStream("input.txt");
ssign7.
            bin = new BufferedInputStream(in);
ssign7.
        }
ssign7.
        catch (IOException e)
ssign7.
        {
ssign7.
            System.out.println("IOException");
ssign7.
        }
ssign7.
    }
ssign7.

ssign7.
    void readch() throws IOException
ssign7.
    {
ssign7.
        peek = (char)bin.read();
ssign7.
    }
ssign7.

ssign7.
    boolean readch(char c) throws IOException
ssign7.
    {
ssign7.
        readch();
ssign7.

ssign7.
        if(peek != c) return false;
ssign7.
        peek = ' ';
ssign7.

ssign7.
        return true;
ssign7.
    }
ssign7.

ssign7.
    public Token scan() throws IOException
ssign7.
    {
ssign7.
        for ( ; ; readch())
ssign7.
        {
ssign7.
            if (peek == ' ' || peek == '\t')
ssign7.
            {
ssign7.
                continue ;
ssign7.
            }
ssign7.
            else if (peek == '\n')
ssign7.
            {
ssign7.
                line = line + 1;
ssign7.
            }
ssign7.
            else if(peek == '\r'){
ssign7.
                continue;
ssign7.
            }
ssign7.
            else
ssign7.
                break;
ssign7.
        }
ssign7.

ssign7.
        switch (peek)
ssign7.
        {
ssign7.
            case '&':
ssign7.
                if (readch('&'))
ssign7.
                    return Word.and;
ssign7.
                else return new Token('&');
ssign7.

ssign7.
            case '|':
ssign7.
                if (readch('|'))
ssign7.
                    return Word.or;
ssign7.
                else return new Token('|');
ssign7.

ssign7.
            case '=':
ssign7.
                if (readch('='))
ssign7.
                    return Word.eq;
ssign7.
                else return new Token('=');
ssign7.

ssign7.
            case '!':
ssign7.
                if (readch('='))
ssign7.
                    return Word.ne;
ssign7.
                else return new Token('!');
ssign7.

ssign7.
            case '<':
ssign7.
                if (readch('='))
ssign7.
                    return Word.le;
ssign7.
                else return new Token('<');
ssign7.

ssign7.
            case '>':
ssign7.
                if (readch('='))
ssign7.
                    return Word.ge;
ssign7.
                else return new Token('>');
ssign7.
        }
ssign7.

ssign7.

ssign7.
        if (Character.isDigit(peek))
ssign7.
        {
ssign7.
            int v = 0;
ssign7.

ssign7.
            do
ssign7.
            {
ssign7.
                v = 10 * v + Character.digit(peek, 10);
ssign7.
                readch();
ssign7.
            }
ssign7.

ssign7.
            while (Character.isDigit(peek));
ssign7.

ssign7.
            if (peek != '.')
ssign7.
                return new Num(v);
ssign7.

ssign7.
            float x = v;
ssign7.
            float d = 10;
ssign7.

ssign7.
            for (; ; )
ssign7.
            {
ssign7.
                readch();
ssign7.

ssign7.
                if (!Character.isDigit(peek)) break;
ssign7.

ssign7.
                x = x + Character.digit(peek, 10) / d;
ssign7.
                d = d * 10;
ssign7.
            }
ssign7.

ssign7.
            return new Real(x);
ssign7.
        }
ssign7.

ssign7.
        if (Character.isLetter(peek))
ssign7.
        {
ssign7.

ssign7.
            StringBuffer b = new StringBuffer();
ssign7.

ssign7.
            do
ssign7.
            {
ssign7.
                b.append(peek);
ssign7.
                readch();
ssign7.
            }
ssign7.

ssign7.
            while (Character.isLetterOrDigit(peek));
ssign7.

ssign7.
            String s = b.toString();
ssign7.
            Word w = words.get(s);
ssign7.

ssign7.
            if (w != null)
ssign7.
                return w;
ssign7.

ssign7.
            w = new Word(s, Tag.ID);
ssign7.
            words.put(s, w);
ssign7.

ssign7.
            return w;
ssign7.
        }
ssign7.

ssign7.
        // ** new code **
ssign7.
        if ((int)peek == 65535){
ssign7.
            System.out.println("@@@@@@@@@ EOF reached...");
ssign7.

ssign7.
            return Word.eof;
ssign7.
        }
ssign7.

ssign7.
        Token t = new Token(peek);
ssign7.
        peek = ' ';
ssign7.

ssign7.
        return t;
ssign7.
    }
ssign7.

ssign7.
}
ssign7.
