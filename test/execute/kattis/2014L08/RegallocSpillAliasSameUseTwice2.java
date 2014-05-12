// EXT:SPILL

/*

A test that broke my compiler which Tigris accepted

*/

class RegallocSpillAliasSameUseTwice2 {
	public static void main(String[] args) {
		int a;
		int b;
		int c;
		int d;
		int e;
		int f;
		int g;
		int h;
		int i;
		int j;
		int k;
		int l;
		int m;
		
		a = 1;
		b = 2;
		d = a;
		c = d + d;
		e = 1;
		f = 2;
		g = d + d;
		h = b;
		i = d + d;
		j = d + d;
		j = d + d;
		i = d + d;
		g = d + d;
		g = f + f;
		g = a + a;
		//b = a + a;
		c = a + a;
		e = c + c;
		f = e + e;
		l = f + 3;
		m = l + 4;
		
		System.out.println(c);
		System.out.println(d);
		k = (l + f);
		l = k;
		k = h + b;
		System.out.println(k+l);
		System.out.println(a);
		System.out.println(c);
		System.out.println(d);
		System.out.println(e);
		System.out.println(f);
		System.out.println(g);
		System.out.println(i);
		System.out.println(j);
		System.out.println(k);
		System.out.println(l);
		System.out.println(m);
		System.out.println(a);
		System.out.println(c);
		System.out.println(d);
		System.out.println(e);
		System.out.println(f);
		System.out.println(g);
		System.out.println(i);
		System.out.println(j);
		System.out.println(k);
		System.out.println(h);
		System.out.println(b);
		
		System.out.println(new OtherTest().run());
	}
}

class OtherTest {
	public int run() {
		int a;
		int b;
		int c;
		int d;
		int e;
		int f;
		int g;
		int h;
		int i;
		int j;
		int k;
		int l;
		int m;
		int a1;
		int b1;
		int c1;
		int d1;
		int e1;
		int f1;
		int g1;
		int h1;
		int i1;
		int j1;
		int k1;
		int l1;
		int m1;
		int a2;
		int b2;
		int c2;
		int d2;
		int e2;
		int f2;
		int g2;
		int h2;
		int i2;
		int j2;
		int k2;
		int l2;
		int m2;
		
		a = 1;
		b = 2;
		d = a;
		c = d + d;
		e = 1;
		f = 2;
		g = d + d;
		h = b;
		i = d + d;
		j = d + d;
		j = d + d;
		i = d + d;
		g = d + d;
		g = f + f;
		g = a + a;
		c = a + a;
		e = c + c;
		f = e + e;
		k = 17;
		l = f + 3;
		m = l + 4;
		a1 = 1;
		b1 = 2;
		d1 = a1;
		c1 = d1 + d1;
		e1 = 1;
		f1 = 2;
		g1 = d1 + d1;
		h1 = b1;
		i1 = d1 + d1;
		j1 = d1 + d1;
		j1 = d1 + d1;
		i1 = d1 + d1;
		g1 = d1 + d1;
		g1 = f1 + f1;
		g1 = a1 + a1;
		b1 = a1 + a1;
		c1 = a1 + a1;
		e1 = c1 + c1;
		f1 = e1 + e1;
		k1 = 17;
		l1 = f1 + 3;
		m1 = l1 + 4;
		a2 = 1;
		b2 = 2;
		d2 = a2;
		c2 = d2 + d2;
		e2 = 1;
		f2 = 2;
		g2 = d2 + d2;
		h2 = b2;
		i2 = d2 + d2;
		j2 = d2 + d2;
		j2 = d2 + d2;
		i2 = d2 + d2;
		g2 = d2 + d2;
		g2 = f2 + f2;
		g2 = a2 + a2;
		c2 = a2 + a2;
		e2 = c2 + c2;
		f2 = e2 + e2;
		k2 = 17;
		l2 = f2 + 3;
		m2 = l2 + 4;
		System.out.println(a+b+c+d+e+f+g+h+i+j+k+l+m);
		System.out.println(a1+b1+c1+d1+e1+f1+g1+h1+i1+j1+k1+l1+m1);
		System.out.println(a2+b2+c2+d2+e2+f2+g2+h2+i2+j2+k2+l2+m2);
		return m2;
	}
}
