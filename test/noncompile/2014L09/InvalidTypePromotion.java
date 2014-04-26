// EXT:LONG
class InvalidTypePromotion {
  public static void main(String[] args) {
    int i;
    long j;
    i = 3;
    j = i;
    System.out.println(j);
  }
}
