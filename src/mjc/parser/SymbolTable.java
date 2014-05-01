package mjc.parser;

import java.util.*;

public class SymbolTable implements HasPrefixedToString{

    private LinkedHashMap<Symbol,Object> map = new LinkedHashMap<Symbol,Object>();
	
	public SymbolTable(){

	}

	public SymbolTable(LinkedHashMap<String,String> map){
		for(String key : map.keySet()){
			insert(key, map.get(key));
		}
	}

    public LinkedHashMap<Symbol,Object> getHashMap(){
        return map;
    }

	void insert(Symbol s, Object b) {
        if(map.get(s) != null) {
            throw new DuplicateDeclaration(s, b, map.get(s));
        }
        map.put(s,b);
	}

	Object lookup(Symbol s) {
        return map.get(s);
	}

	void insert(String s, Object b) {
		insert(Symbol.symbol(s), b);
	}

	Object lookup(String s){
		return lookup(Symbol.symbol(s));
	}

	public String toString(String prefix){
		StringBuilder sb = new StringBuilder(prefix + "*SYMTABLE*");

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
