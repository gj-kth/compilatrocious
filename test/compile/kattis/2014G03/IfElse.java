// EXT:IWE

class IfElse {
    public static void main(String[] args) {
        int a; 
        int b;
        a = 0;
        b = 0; 

        if(5 < 5)
            a = 5; 

        if(6 < 5) 
            a = 6; 
        else 
            a = 7; 

        if(b < a) {
            if(8 < 5) {
                b = 0 - 1; 
            }
            else {
                b = 1; 
                if(7 < 6) {
                    b = 0 - 10; 
                }
            }
        }
        System.out.println(a);
        System.out.println(b); 
    }
}
