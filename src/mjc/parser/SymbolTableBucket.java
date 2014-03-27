package mjc.parser;

public class SymbolTableBucket implements HasPrefixedToString{
	Symbol key; 
	Object binding; 
	SymbolTableBucket(Symbol k, Object b) {
		key=k; 
		binding=b; 
	}

	public String toString(String prefix){
		String s = prefix + "*Bucket*\n" + prefix + key + ": ";
		if(binding instanceof HasPrefixedToString){
			s += "\n" + ((HasPrefixedToString)binding).toString(prefix + "\t");	
		}else{
			s += "\n" + prefix + "\t" + binding;
		}
		return s;
	}
}
