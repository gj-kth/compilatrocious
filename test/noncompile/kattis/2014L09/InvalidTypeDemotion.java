// EXT:LONG
class InvalidTypeDemotion {
  public static void main(String[] args) {
    int i;
    long j;
    j = 3;
    i = j;
    System.out.println(i);
  }
}
