package mjc.parser;

public class SymbolTable implements HasPrefixedToString{

	final int SIZE = 256;
	SymbolTableBucket table[] = new SymbolTableBucket[SIZE];
	
	private int hash(String s) {
		int h=0;
		for(int i=0; i<s.length(); i++)
			h=h*65599+s.charAt(i);
		return h;
	}

	void insert(Symbol s, Object b) {
		int index = getIndex(s);
		table[index]=new SymbolTableBucket(s, b, table[index]);
	}

	Object lookup(Symbol s) {
		int index = getIndex(s);
		for (SymbolTableBucket b = table[index]; b!=null; b=b.next)
			if (s.equals(b.key)) 
				return b.binding;
		return null;
	}

	void pop(Symbol s) {
		int index = getIndex(s);
		table[index]=table[index].next;
	}

	private int getIndex(Symbol s){
		int index=hash(s.toString())%SIZE;
		if(index < 0 ){
			index += SIZE;
		}
		return index;
	}

	void insert(String s, Object b) {
		insert(Symbol.symbol(s), b);
	}

	Object lookup(String s){
		return lookup(Symbol.symbol(s));
	}

	void pop(String s) {
		pop(Symbol.symbol(s));
	}

	public String toString(String prefix){
		String s = prefix + "*SYMTABLE*";
		for(int i = 0; i < table.length; i++){
			if(table[i] != null){
				s += "\n" + table[i].toString(prefix + "\t") ;	
			}
		}
		return s;
	}
}