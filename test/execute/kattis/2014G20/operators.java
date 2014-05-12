class operators
{
    public static void main(String[] s) 
    {
	System.out.println(new A().f(0, 0, true, false));
    }
}


class A { 
    int z;    
    public int f(int x, int y, boolean b1, boolean b2) 
    {
	x = 1-2-3;
	System.out.println(x);
	x = 1-(2-3);
	System.out.println(x);
	x = 2*2+3;
	System.out.println(x);
	x = 2*(2+3);
	System.out.println(x);
	x = 1+2*3;
	System.out.println(x);
	b2 = 1 < 2 && !(x < y) && !b2 && (1 < x);
	System.out.println(b2);
	b2 = 1+2<3+4 && 5-6<7-8;	
	System.out.println(b2);
	return x*x+y;	
    }
}


