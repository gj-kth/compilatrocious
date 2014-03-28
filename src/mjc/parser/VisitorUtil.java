package mjc.parser;

public class VisitorUtil{
	public static class ClassData implements HasPrefixedToString{
		SymbolTable fieldsTable;
		SymbolTable methodsTable;
		public ClassData(SymbolTable f, SymbolTable m){
			fieldsTable = f;
			methodsTable = m;
		}

		public String toString(String prefix){
			return prefix + "fields:\n" + fieldsTable.toString(prefix + "\t") + "\n" + prefix + "methods:\n" + methodsTable.toString(prefix + "\t");
		}
	}

	public static class MethodData implements HasPrefixedToString{
		String returnType;
		SymbolTable paramsTable;
		SymbolTable localsTable;
		public MethodData(String r, SymbolTable p, SymbolTable l){
			returnType = r;
			paramsTable = p;
			localsTable = l;
			if(paramsTable == null){
				throw new IllegalArgumentException("paramsTable is null");
			}
			if(localsTable == null){
				throw new IllegalArgumentException("localsTable is null");
			}
		}

		public String toString(String prefix){
			return prefix + returnType + "\n" + prefix + "params:\n" + paramsTable.toString(prefix + "\t") + "\n" + prefix + "locals:\n" + localsTable.toString(prefix + "\t");
		}
	}

	public static class Pair<T1,T2>{
		T1 first;
		T2 second;
		Pair(T1 f, T2 s){
			first = f;
			second = s;
		}
	}
}