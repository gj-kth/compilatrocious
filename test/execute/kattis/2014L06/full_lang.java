// EXT:IWE
// EXT:BDJ
// EXT:CLE
// EXT:CGT
// EXT:CGE
// EXT:CEQ
// EXT:CNE

class Main2 {
    public static void main(String[] args) {
        Ext2 ext;

        if (10 > 2 && 5 == 5 && 7 != 2 && 10 >= 9 && 9 <= 10) {
            System.out.println(true);
        }

        if (5 == 6) {
            System.out.println(99);
        }

        if (5 != 5) {
            System.out.println(85);
        }

        if (9 >= 10) {
            System.out.println(73);
        }

        if (10 <= 9) {
            System.out.println(155);
        }

        if (false || true) {
            System.out.println(true || false);
        }

        ext = new Ext2();
        if (true || ext.printThis(7)) {
            System.out.println(8);
        }

        if (false || ext.printThis(9)) {
            System.out.println(10);
        }
    }
}

class Ext2 {
    public boolean printThis(int a) {
        System.out.println(a);
        return true;
    }
}