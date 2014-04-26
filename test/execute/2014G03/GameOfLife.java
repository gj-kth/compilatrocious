
// A testcase which simulates a few generations of Conway's Game of Life. 
class GameOfLife {
    public static void main(String[] args) {
        int W; 
        int H; 
        int gens; 
        int i; 
        int ii; 
        GoLBoard gb; 
        int dummy; 

        W = 40; 
        H = 15; 
        gens = 30; 
        gb = new GoLBoard();  
        dummy = gb.init(W, H); 
        dummy = gb.writeGosperGliderGun(1, 1); 

        i = 0; 
        while(i < gens) {
            dummy = gb.step(); 
            i = i + 1; 
        } 
        dummy = gb.print(); 
    }
}


class GoLBoard {

    int[] board; 
    int w; 
    int h; 

    public int init(int wi, int he) {
        w = wi; 
        h = he; 
        board = new int[w * h]; 
        return 1; 
    }
    /* 
     * Writes a Gosper glider gun at position x, y. 
     */
    public int writeGosperGliderGun(int x, int y) {
        board[y*w + x + 24] = 1; 

        board[(y+1)*w + x + 22] = 1; 
        board[(y+1)*w + x + 24] = 1;  

        board[(y+2)*w + x + 12] = 1; 
        board[(y+2)*w + x + 13] = 1; 
        board[(y+2)*w + x + 20] = 1; 
        board[(y+2)*w + x + 21] = 1;  
        board[(y+2)*w + x + 34] = 1; 
        board[(y+2)*w + x + 35] = 1; 

        board[(y+3)*w + x + 11] = 1; 
        board[(y+3)*w + x + 15] = 1; 
        board[(y+3)*w + x + 20] = 1; 
        board[(y+3)*w + x + 21] = 1;  
        board[(y+3)*w + x + 34] = 1; 
        board[(y+3)*w + x + 35] = 1; 

        board[(y+4)*w + x +  0] = 1; 
        board[(y+4)*w + x +  1] = 1; 
        board[(y+4)*w + x + 10] = 1; 
        board[(y+4)*w + x + 16] = 1; 
        board[(y+4)*w + x + 20] = 1; 
        board[(y+4)*w + x + 21] = 1; 

        board[(y+5)*w + x +  0] = 1; 
        board[(y+5)*w + x +  1] = 1; 
        board[(y+5)*w + x + 10] = 1; 
        board[(y+5)*w + x + 14] = 1; 
        board[(y+5)*w + x + 16] = 1; 
        board[(y+5)*w + x + 17] = 1; 
        board[(y+5)*w + x + 22] = 1; 
        board[(y+5)*w + x + 24] = 1; 

        board[(y+6)*w + x + 10] = 1; 
        board[(y+6)*w + x + 16] = 1; 
        board[(y+6)*w + x + 24] = 1; 

        board[(y+7)*w + x + 11] = 1; 
        board[(y+7)*w + x + 15] = 1; 

        board[(y+8)*w + x + 12] = 1; 
        board[(y+8)*w + x + 13] = 1; 

        return 1; 
    }

    /* 
     * To avoid out of bounds, the outermost boundries 
     * will always be left blank. 
    */
    public int step() {
        int[] next; 
        int x; 
        int y; 
        int sum; 

        next = new int[w*h]; 
        
        x = 1; 
        y = 1; 
        while(y < h-1) {
            while(x < w-1) {
                sum = board[(y-1)*w + x-1] + 
                      board[(y-1)*w + x+0] + 
                      board[(y-1)*w + x+1] + 
                      board[(y+0)*w + x-1] + 
                      board[(y+0)*w + x+1] + 
                      board[(y+1)*w + x-1] + 
                      board[(y+1)*w + x+0] + 
                      board[(y+1)*w + x+1]; 

                if(0 < board[y*w + x]) { 
                    if(sum < 2) 
                        next[y*w + x] = 0; 
                    else if(3 < sum) 
                        next[y*w + x] = 0; 
                    else 
                        next[y*w + x] = 1; 
                }
                else { 
                    if(2 < sum) {
                        if(sum < 4) 
                            next[y*w + x] = 1; 
                        else {
                        }
                    }
                    else {
                        
                    }
                }

                x = x + 1; 
            }
            y = y + 1; 
            x = 0; 
        }

        board = next; 
        return 1; 
    }


    public int print() {
        int i; 
        int ii; 
        i = 0; 
        ii = 0; 
        while(i < h) {
            while(ii < w) {
                System.out.println(board[i*w + ii]);
                ii = ii + 1; 
            }
            //System.out.println(); 
            i = i + 1; 
            ii = 0; 
        }
        return 1; 
    }
}

