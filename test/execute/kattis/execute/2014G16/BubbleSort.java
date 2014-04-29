class BubbleSort {
  public static void main(String[] args) {
    int[] a1;
    a1 = new int[10];
    a1[0] = 9;
    a1[1] = 1;
    a1[2] = 7;
    a1[3] = 3;
    a1[4] = 4;
    a1[5] = 2;
    a1[6] = 6;
    a1[7] = 8;
    a1[8] = 0;
    a1[9] = 5;
    a1 = new Print().print(a1);
    a1 = new Sort().sort(a1);
    a1 = new Print().print(a1);
  }
}

class Sort {
  public int[] sort(int[] a) {
    boolean swapped;
    int i;
    int junk;
    swapped = true;
    while (swapped) {
      swapped = false;
      i = 1;
      while (i < a.length) {
        if (a[i] < a[i - 1]) {
          junk = this.swap(a, i, i - 1);
          swapped = true;
        } else {}
        i = i + 1;
      }
    }
    return a;
  }

  public int swap(int[] a, int i1, int i2) {
    int tmp;
    tmp = a[i1];
    a[i1] = a[i2];
    a[i2] = tmp;
    return 0;
  }
}

class Print {
  public int[] print(int[] a) {
    int i;
    i = 0;
    while (i < a.length) {
      System.out.println(a[i]);
      i = i + 1;
    }
    return a;
  }
}
