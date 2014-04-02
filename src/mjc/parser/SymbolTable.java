package mjc.parser;

import java.util.*;

public class SymbolTable implements HasPrefixedToString{

	final int SIZE = 256;
	SymbolTableBucket table[] = new SymbolTableBucket[SIZE];
        private HashMap<Symbol,Object> map = new HashMap<Symbol,Object>();
	
	public SymbolTable(){

	}

	public SymbolTable(HashMap<String,String> map){
		for(String key : map.keySet()){
			insert(key, map.get(key));
		}
	}

	private int hash(String s) {
		int h=0;
		for(int i=0; i<s.length(); i++)
			h=h*65599+s.charAt(i);
		return h;
	}

	void insert(Symbol s, Object b) {
            /*
		int index = getIndex(s);
                if(table[index] != null) {
                    throw new DuplicateDeclaration(s, b, table[index]);
                }
		table[index]=new SymbolTableBucket(s, b);
                */

                if(map.get(s) != null) {
                    throw new DuplicateDeclaration(s, b, map.get(s));
                }
                map.put(s,b);
	}

	Object lookup(Symbol s) {
            /*
		int index = getIndex(s);
		if (table[index]!=null && s.equals(table[index].key)) 
			return table[index].binding;
		return null;
                */
            return map.get(s);
	}

        /*
	void pop(Symbol s) {
		int index = getIndex(s);
		table[index]=null;
	}
        */

        /*
	private int getIndex(Symbol s){
		int index=hash(s.toString())%SIZE;
		if(index < 0 ){
			index += SIZE;
		}
		return index;
	}
        */

	void insert(String s, Object b) {
		insert(Symbol.symbol(s), b);
	}

	Object lookup(String s){
		return lookup(Symbol.symbol(s));
	}

        /*
	void pop(String s) {
		pop(Symbol.symbol(s));
	}
        */

	public String toString(String prefix){
		StringBuilder sb = new StringBuilder(prefix + "*SYMTABLE*");
                /*
		for(int i = 0; i < table.length; i++){
			if(table[i] != null){
				s += "\n" + table[i].toString(prefix + "\t") ;	
			}
		}
                */
                Iterator<Symbol> it = map.keySet().iterator();
                Symbol sym;
                Object obj;
                prefix = prefix + "\t"; //Move right one level
                while(it.hasNext()) {
                    sym = it.next();
                    obj = map.get(sym);
                    sb.append("\n" + prefix + sym + ": ");
                    if(obj instanceof HasPrefixedToString){
                            sb.append("\n" + ((HasPrefixedToString)obj).toString(prefix + "\t"));
                    }else{
                            sb.append("\n" + prefix + "\t" + obj);
                    }
                }

		return sb.toString();
	}

        public String toString() {
            return toString("");
        }
}
