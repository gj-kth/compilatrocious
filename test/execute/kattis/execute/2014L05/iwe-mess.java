// EXT:IWE
// EXT:CGT

class iwe {
    public static void main(String [] str) {
        int a;
        a = 5;
        if (a > 1) 
            if (a > 2)
                if (a > 10)
                    System.out.println(0);
                else if (a > 3)
                    if (a > 4)
                        if (a < 0)
                            System.out.println(0);
                        else
                            System.out.println(1);
                    else System.out.println(0);
                else System.out.println(0);
            else System.out.println(0);
        else System.out.println(0);
    }
}
