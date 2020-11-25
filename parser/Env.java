package assign6.parser;
ssign7.

ssign7.
import java.util.*; 
ssign7.
import assign6.lexer.*; 
ssign7.
import assign6.ast.*;
ssign7.

ssign7.
/*
ssign7.
public class Env {
ssign7.

ssign7.
	public Hashtable<String,Type> table;
ssign7.
	protected Env prev;
ssign7.

ssign7.
	public Env() {
ssign7.
		table = new Hashtable<String,Type>();
ssign7.
	}
ssign7.

ssign7.
	public Env(Env n) {
ssign7.
		table = new Hashtable<String,Type>();
ssign7.
		prev = n;
ssign7.
	}
ssign7.

ssign7.
	public void put(String w, Type i) { table.put(w, i); }
ssign7.

ssign7.
	public Type get(String w) {
ssign7.
		for( Env e = this; e != null; e = e.prev ) {
ssign7.
			Type found = (Type)(e.table.get(w));
ssign7.
			if( found != null ) return found;
ssign7.
		}
ssign7.
		return null;
ssign7.
	}
ssign7.
}
ssign7.
*/
ssign7.

ssign7.
// new code
ssign7.
public class Env {
ssign7.

ssign7.
	private Hashtable table;
ssign7.
	public Env prev;
ssign7.

ssign7.
	public Env(Env n) {
ssign7.
		table = new Hashtable();
ssign7.
		prev = n;
ssign7.
	}
ssign7.

ssign7.
	public void put(Token w, IdentifierNode i) {
ssign7.
		table.put(w, i);
ssign7.
	}
ssign7.

ssign7.
	public IdentifierNode get(Token w) {
ssign7.
		for (Env e = this; e != null; e = e.prev) {
ssign7.
			IdentifierNode found = (IdentifierNode)(e.table.get(w));
ssign7.
			if (found != null)
ssign7.
				return found;
ssign7.
		}
ssign7.
		return null;
ssign7.
	}
ssign7.
}
ssign7.
