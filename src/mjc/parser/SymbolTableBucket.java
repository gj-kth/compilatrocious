package mjc.parser;
public class SymbolTableBucket{
	String key; 
	Object binding; 
	SymbolTableBucket next;
	SymbolTableBucket(String k, Object b, SymbolTableBucket n) {
		key=k; 
		binding=b; 
		next=n;
	}
}
