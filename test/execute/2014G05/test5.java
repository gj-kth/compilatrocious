
class Main {
  public static void main(String[] args) {
    Array id;
    Array a1;
    Array a2;
    Array _;

    // 3x3 identity matrix
    id = new Array().mk_rank2(3,3);
    _ = id.set2(0,0, 1)
          .set2(1,1, 1)
          .set2(2,2, 1);

    // a1
    a1 = new Array().mk_rank2(2,3);
    _ = a1.set2(0,0, 1)
          .set2(1,0, 2)
          .set2(0,1, 3)
          .set2(1,1, 1)
          .set2(0,2, 0)
          .set2(1,2, 2);

    // a2
    a2 = new Array().mk_rank2(3,2);
    _ = a2.set2(0,0, 1)
          .set2(1,0, 2)
          .set2(2,0, 0)
          .set2(0,1, 1)
          .set2(1,1, 0-1)
          .set2(2,1, 1);

    // a1 × a2 × id =
    //   3  0  2
    //   4  5  1
    //   2 ⁻2  2
    _ = a1.mul(a2).mul(id).dump();
  }
}

class Array {
  int[] r;
  int[] data;

  //-- Initializing functions -------------------
  public Array mk_rank0(int v) {
    r    = new int[0];
    data = new int[1];
    data[0] = v;
    return this.clean();
  }
  public Array mk_rank1(int r0) {
    r = new int[1];
    r[0] = r0;
    data = new int[r0];
    return this.clean();
  }
  public Array mk_rank2(int r0, int r1) {
    r = new int[2];
    r[0] = r0;
    r[1] = r1;
    data = new int[r0 * r1];
    return this.clean();
  }

  // Helper
  public Array clean() {
    int i;
    i = 0;
    while (i < data.length) {
      data[i] = 0;
      i = i + 1;
    }
    return this;
  }

  //-- Setters ----------------------------------
  public Array set0(int v) {
    data[0] = v;
    return this;
  }
  public Array set1(int i, int v) {
    data[i] = v;
    return this;
  }
  public Array set2(int i, int j, int v) {
    data[i + r[0]*j] = v;
    return this;
  }

  //-- Getters ----------------------------------
  public int get0() {
    return data[0];
  }
  public int get1(int i) {
    return data[i];
  }
  public int get2(int i, int j) {
    return data[i + r[0]*j];
  }

  //-- Matrix multiplication --------------------
  // (naïve)
  public Array mul(Array other) {
    Array res;
    Array _;
    int[] or;
    int[] odata;
    int x;
    int y;
    int i;
    int v;

    or = other.get_rank();
    res = new Array().mk_rank2(r[1], or[0]);

    x = 0;
    while (x < or[0]) {
      y = 0;
      while (y < r[1]) {
        v = 0;
        i = 0;
        while (i < r[0]) {
          v = v + this.get2(i,y) * other.get2(x,i);
          i = i + 1;
        }
        _ = res.set2(x,y, v);
        y = y + 1;
      }
      x = x + 1;
    }

    return res;
  }

  // Helper
  public int[] get_rank() {
    return r;
  }

  //-- Dumper function --------------------------
  // Dumps the array data plainly to stdout, since there isn't really any
  // possibility to pretty-print it even remotely...
  public Array dump() {
    int i;
    i = 0;
    while (i < data.length) {
      System.out.println(data[i]);
      i = i + 1;
    }
    return this;
  }
}
