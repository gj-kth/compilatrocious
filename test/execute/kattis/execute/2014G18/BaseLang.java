class Main{
  public static void main(String[] args){
    int[] array;
    int[] result;
    Sorter sorter;
    int a;

    array = new int[10];
    array[0] = 4;
    array[1] = 1;
    array[2] = 43;
    array[3] = 5;
    array[4] = 3;
    array[5] = 2;
    array[6] = 9;
    array[7] = 10;
    array[8] = 4;
    array[9] = 3;



    sorter = new Sorter();

    result = sorter.selectionSort(array);
    a = sorter.printArray(result);
  }
}

class Sorter{

  public int[] selectionSort(int[] data){
    int len;
    int j;
    int tmp;
    int i;
    int k;
    i = 0;
    len = data.length;
    while(i < len){
      j = i;
      k = i;
      while(k < len){
        if(data[k] < data[j]){
          j = k;
        } else {
          j = j;
        }
        k = k + 1;
      }
      tmp = data[i];
      data[i] = data[j];
      data[j] = tmp;
      i = i + 1;
    }
    return data;
  }

  public int printArray(int[] data) {
    int i;
    i = 0;
    while(i < data.length){
      System.out.println(data[i]);
      i = i + 1;
    }
    return 0;
  }
}