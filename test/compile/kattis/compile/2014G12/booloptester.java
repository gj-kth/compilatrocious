//EXT:CLE
//EXT:CGE
//EXT:CEQ
//EXT:CNE
//EXT:CGT
//EXT:BDJ

/*
A simple class checking the compability and bindnings of boolean operators.
*/

class Compilable{
	public static void main(String[] args){
		boolean b;
		BoolMonster bm;
		bm = new BoolMonster();
		b = bm.kek();
		b = bm.lol(1, new int[5], true);
	}
}

class BoolMonster{
	public boolean kek(){
		return 1!=2 && 2==0 || 1>=0 && !(1<=2);
	}
	public boolean lol(int i, int[] j, boolean b){
		boolean a;
		a = i != j[i] || !b == b;
		return a;
	}
}
