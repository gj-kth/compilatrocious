// EXT:BDJ 

class OrExp {
    public static void main(String[] args) {
        int a; 
        int b; 
        a = 0; 
        b = 0;

        if(5 < 5 || 5 < 6) {
            a = 6; 
        }
        else {
            a = 5; 
        }

        if(5 < 5 && 3 < 4 || 3 < 4) {
            b = 3; 
        }
        else {
            b = 0 - 1; 
        }

        System.out.println(a); 
        System.out.println(b); 
    }
}

