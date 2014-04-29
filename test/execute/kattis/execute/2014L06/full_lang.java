// EXT:IWE
// EXT:BDJ
// EXT:CLE
// EXT:CGT
// EXT:CGE
// EXT:CEQ
// EXT:CNE

class Main2 {
    public static void main(String[] args) {
        if (10 > 2 && 5 == 5 && 7 != 2 && 10 >= 9 && 9 <= 10) {
            System.out.println(true);
        }

        if (false || true) {
            System.out.println(true || false);
        }
    }
}