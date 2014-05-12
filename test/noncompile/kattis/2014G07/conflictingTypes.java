class MainClass {
  public static void main(String[] argz) {
    int abc; int cde; MainClass x; boolean b;
	x = new MainClass();
	b = new A().b();
if (new A().a(123,new B(),new A()).a()) {
		abc = 10;
	} else {
		abc = 9;	
	}
  }
}
class A {
	public B a(int xzz,B c,A x) {
		return c;
	}
	public int b() {
		B y; int a;
		a = 10;
		y = new B();
        return this.a(a,y,this).a(); // this.a(…) is type B, of which a() method returns bool, but this method returns int (conflict)
	}
}
class B {public boolean a() {return true;}}
