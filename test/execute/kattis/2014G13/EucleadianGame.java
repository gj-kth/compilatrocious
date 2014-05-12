//EXT:CGE

class EucleandianGame {        
        public static void main(String[] args)  {
		Game g;
		g = new Game();
		System.out.println(g.calcWinner(34,12));
		System.out.println(g.calcWinner(15,24));
		System.out.println(g.calcWinner(42000101,135806163));
		System.out.println(g.calcWinner(1883639682,1579735732));
        }
}

/* Game rules defines at: https://kth.kattis.scrool.se/problems/euclidsgame */
class Game {
	public int calcWinner(int n, int m) {
                boolean turn;
                boolean win;
		int t;
		int ret;
		turn = true;
		win = false;
                if(n >= m) {
                        t = n;
                        n = m;
                        m = t;
                } else { t = 0; }
                while(!win) {
			// 2*n should overflow in the last test input.
                        if(m >= 2*n) {
                                win = true;
                        } else {
		                t = m-n;
		                m = n;
		                n = t;
		                
		                turn = !turn;
			}
                }
                
                if(turn) {
                        ret = 1;
                } else {
                        ret = 0;
                }
		return ret;
        }
}
