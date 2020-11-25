package assign6.lexer;

import java.io.* ;
import java.util.* ;


public class Lexer
{
    public int line = 0;
    private char peek = ' ';

    private BufferedInputStream bin;
    private FileInputStream in;

    private Hashtable<String, Word> words = new Hashtable<String, Word>();

    public Lexer(){
        reserve(new Word("if", Tag.IF));
        reserve(new Word("else", Tag.ELSE));
        reserve(new Word("while", Tag.WHILE));
        reserve(new Word("do", Tag.DO));
        reserve(new Word("break", Tag.BREAK));

        reserve(Word.True);
        reserve(Word.False);

        // ** new code
        reserve(Word.eof);

        reserve(Type.Int);
        reserve(Type.Char);
        reserve(Type.Bool);
        reserve(Type.Float);

        setupIOStream();
    }

    void reserve (Word w)
    {
        words.put(w.lexeme, w);
    }

    void setupIOStream()
    {
        try
        {
            in = new FileInputStream("input.txt");
            bin = new BufferedInputStream(in);
        }
        catch (IOException e)
        {
            System.out.println("IOException");
        }
    }

    void readch() throws IOException
    {
        peek = (char)bin.read();
    }

    boolean readch(char c) throws IOException
    {
        readch();

        if(peek != c) return false;
        peek = ' ';

        return true;
    }

    public Token scan() throws IOException
    {
        for ( ; ; readch())
        {
            if (peek == ' ' || peek == '\t')
            {
                continue ;
            }
            else if (peek == '\n')
            {
                line = line + 1;
            }
            else if(peek == '\r'){
                continue;
            }
            else
                break;
        }

        switch (peek)
        {
            case '&':
                if (readch('&'))
                    return Word.and;
                else return new Token('&');

            case '|':
                if (readch('|'))
                    return Word.or;
                else return new Token('|');

            case '=':
                if (readch('='))
                    return Word.eq;
                else return new Token('=');

            case '!':
                if (readch('='))
                    return Word.ne;
                else return new Token('!');

            case '<':
                if (readch('='))
                    return Word.le;
                else return new Token('<');

            case '>':
                if (readch('='))
                    return Word.ge;
                else return new Token('>');
        }


        if (Character.isDigit(peek))
        {
            int v = 0;

            do
            {
                v = 10 * v + Character.digit(peek, 10);
                readch();
            }

            while (Character.isDigit(peek));

            if (peek != '.')
                return new Num(v);

            float x = v;
            float d = 10;

            for (; ; )
            {
                readch();

                if (!Character.isDigit(peek)) break;

                x = x + Character.digit(peek, 10) / d;
                d = d * 10;
            }

            return new Real(x);
        }

        if (Character.isLetter(peek))
        {

            StringBuffer b = new StringBuffer();

            do
            {
                b.append(peek);
                readch();
            }

            while (Character.isLetterOrDigit(peek));

            String s = b.toString();
            Word w = words.get(s);

            if (w != null)
                return w;

            w = new Word(s, Tag.ID);
            words.put(s, w);

            return w;
        }

        // ** new code **
        if ((int)peek == 65535){
            System.out.println("@@@@@@@@@ EOF reached...");

            return Word.eof;
        }

        Token t = new Token(peek);
        peek = ' ';

        return t;
    }

}
