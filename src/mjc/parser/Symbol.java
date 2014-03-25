package mjc.parser;

import java.util.HashMap;

public class Symbol {
    private String name;

    private static HashMap<String,Symbol> symbols = new HashMap<String,Symbol>();

    private Symbol(String n) {
        name = n;
    }

    public String toString() {
        return name;
    }

    public static Symbol symbol(String n) {
        String u = n.intern();
        Symbol s = symbols.get(u);
        if(s==null) {
            s = new Symbol(u);
            symbols.put(u, s);
        }
        return s;
    }
}
