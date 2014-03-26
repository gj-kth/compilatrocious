package mjc.parser;

public class SymbolTableBucket implements HasPrefixedToString{
	Symbol key; 
	Object binding; 
	SymbolTableBucket next;
	SymbolTableBucket(Symbol k, Object b, SymbolTableBucket n) {
		key=k; 
		binding=b; 
		next=n;
	}

	public String toString(String prefix){
		String s = prefix + "*Bucket*\n" + prefix + key + ": ";
		for(SymbolTableBucket b = this; b != null; b = b.next){
			if(binding instanceof HasPrefixedToString){
				s += "\n" + ((HasPrefixedToString)b.binding).toString(prefix + "\t");	
			}else{
				s += "\n" + prefix + "\t" + b.binding;
			}
			
		}
		return s;
	}
}
