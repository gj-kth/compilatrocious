//EXT:CNE
/* Multiple declarations of variables */

class Main
{
    public static void main(String[] a){
        System.out.println(new Test().start());
    }
}

class Test {
    int a;
    int a;
    int a;
    Other b;
    Other a;
    //b = new Other();

    public int start() {
        a = 17;
        if(a != 4) {

        } else {

        }

        return a;
    }
}

class Test {
    int x;
    public int v() {
        return 1;
    }
    public int v() {
        return 1;
    }
}

//class Other {
//    int a;
//    boolean a;
//
//    return a;
//}
