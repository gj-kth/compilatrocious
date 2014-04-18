package mjc.parser;

import java.util.*;

public class VisitorUtil{

	public static class ClassData implements HasPrefixedToString{
		boolean hasSuperClass;
		String superClass;
		SymbolTable fieldsTable;
		SymbolTable methodsTable;

		public ClassData(String superClass, SymbolTable f, SymbolTable m){
			this(f,m);
			hasSuperClass = true;
			this.superClass = superClass;
		}

		public ClassData(SymbolTable f, SymbolTable m){
			fieldsTable = f;
			methodsTable = m;
		}

		public String toString(String prefix){
			String inheritance = hasSuperClass? ("(extends " + superClass + ")\n") : "";
			return prefix + inheritance + "fields:\n" + fieldsTable.toString(prefix + "\t") + "\n" + prefix + "methods:\n" + methodsTable.toString(prefix + "\t");
		}

        public String toString() {
            return toString("");
        }
	}

	public static class MethodData implements HasPrefixedToString{
		String returnType;
		SymbolTable paramsTable;
		List<String> paramTypes;
		SymbolTable localsTable;
		public MethodData(String r, SymbolTable p, List<String> pt, SymbolTable l){
			returnType = r;
			paramsTable = p;
			paramTypes = pt;
			localsTable = l;
			if(paramsTable == null){
				throw new IllegalArgumentException("paramsTable is null");
			}
			if(paramTypes == null){
				throw new IllegalArgumentException("paramTypes is null");	
			}
			if(localsTable == null){
				throw new IllegalArgumentException("localsTable is null");
			}
		}

		public String toString(String prefix){
			return prefix + returnType + "\n" + prefix + "params:\n" + paramsTable.toString(prefix + "\t") + "\n" + prefix + "locals:\n" + localsTable.toString(prefix + "\t");
		}

	    public String toString() {
	        return toString("");
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

	public static class Context{
		String className;
		String methodName;
		String varName;

		Context(String c){
			this(c, null,null);
		}
		Context(String c, String m){
			this(c,m,null);
		}
		Context(String c, String m, String v){
			className = c;
			methodName = m;
			varName = v;
		}
		Context(Object other){
			this(((Context)other).className, ((Context)other).methodName, ((Context)other).varName);
		}
		public String toString(){
			String s = className;
			if(methodName != null){
				s += "." + methodName + "()";
			}
			if(varName != null){
				s += "." + varName;
			}
			return s;
		}
	}

}
