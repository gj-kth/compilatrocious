/* This is a simple and very bad implementation of a queue
 * in the minijava language. It is slow as it always moves
 * all elements to the beginning on removal due to the use
 * of integer arrays instead of a fast removal datastruct-
 * ure as a linked list.
 */
class Main {
	public static void main(String[] args){
		boolean scrapBool;
		int scrapInt;
		Queue q;
		PriorityQueue pq;
		int[] set;
		set = new int[12];
		set[0] = 7;
		set[5] = 8;
		set[set.length-1] = 9;

		q = new Queue();
		scrapBool = q.init(5);
		scrapBool = q.add(10);
		scrapBool = q.add(2);
		scrapBool = q.add(3);
		scrapBool = q.add(4);
		scrapBool = q.add(5);
		scrapBool = q.add(6); //Will increase size of queue due to initial size 5
		scrapInt = q.remove();	//Remove 10 store in i
		System.out.println(q.remove()); //Will print 2
		scrapBool = q.print(); // Will print 3 4 5 6
		System.out.println(q.size()); //Will print 4
		scrapBool = q.clear(); 
		System.out.println(q.size()); //Will print 0
		scrapInt = q.remove(); //Empty so will print false
		scrapBool = q.addSet(set.length, set);

		scrapBool = q.print(); //Will print 7 0 0 0 0 8 0 0 0 0 0 9

		System.out.println(q.size()); //Will print 12
		scrapBool = q.add(10);
		System.out.println(q.size()); //Will print 13
		pq = new PriorityQueue();
		scrapBool = pq.init(q.size()+1);
		scrapBool = pq.addSet(q.size(),q.asArray());
		scrapBool = pq.print(); //Will print 0 0 0 0 0 0 0 0 0 7 8 9 10
		scrapBool = pq.add(2);
		scrapBool = pq.print(); //Will print 0 0 0 0 0 0 0 0 0 2 7 8 9 10
		scrapInt = pq.remove();
		scrapInt = pq.remove();
		scrapInt = pq.remove();
		scrapInt = pq.remove();
		scrapInt = pq.remove();
		scrapInt = pq.remove();
		scrapInt = pq.remove();
		scrapInt = pq.remove();
		scrapInt = pq.remove();
		scrapBool = pq.print(); //Will print 2 7 8 9 10
		System.out.println(pq.size()); //Will print 5

		


		//int b;
		//	int b;
		/*int b;*/
		/*	int b;	*/
		//b = 7;
		//	b = 7;
		/*b = 7;*/
		/*	b = 7;	*/
		//System.out.println(b);
		//	System.out.println(b);
		/*System.out.println(b);*/
		/*	System.out.println(b);	*/
	}
}

class PriorityQueue {
	Queue q;
	Sorter s;
	public boolean add(int element){
		boolean retBool;
		boolean scrap;
		retBool = q.add(element);
		scrap = s.sortQueue(q);
		return retBool;
	}
	
	public boolean addSet(int size, int[] values){
		boolean retBool;
		boolean scrap;
		retBool = q.addSet(size,values);
		scrap = s.sortQueue(q);
		return retBool;
	}
	
	public int remove(){ 
		return q.remove();
	}
	
	public boolean isEmpty(){
		return q.isEmpty();
	}
	
	public int size(){
		return q.size();
	}
	
	public int[] asArray(){
		return q.asArray();
	}
	
	public boolean clear(){ 
		return q.clear();
	}
	
	public boolean init(int v){
		q = new Queue();
		s = new Sorter();
		return q.init(v);
	}
	
	public boolean print(){
		return q.print();
	}
}

class Queue {
	int[] list;
	int rightPointer;
	int sum;
	int size;

	public boolean add(int element){
		int[] tmp;
		int index;
		if(  size < sum+1 ){
			tmp = new int[size*2];
			index = 0;
			while(index < size){
				tmp[index] = list[index];
				index = index + 1;
			}
			size = size * 2;
			list = tmp;
		} else {
			//Do nothing
		}

		sum = sum + 1;
		list[rightPointer]=element;
		rightPointer = rightPointer + 1;

		return true;
	}

	public boolean addSet(int size, int[] values){
		int index;
		boolean ret;
		ret = false;
		if(values.length < size)
			System.out.println(false); //Can not add more elements than list has
		else {
			index = 0;
			while(index < size){
				ret = this.add(values[index]);
				index = index + 1;
			}
		}
		return ret;
	}

	public int remove(){ 
		int ret;
		int index;
		if(!this.isEmpty()){
			ret = list[0];
			index = 1;
			while(index < sum){
				list[index-1] = list[index];
				index = index + 1;
			}
			rightPointer = rightPointer - 1;
			sum = sum - 1;
		} else {
			System.out.println(false); //Should tell that something went wrong, can not throw error/return negative or w/e needed for this
			ret = 0;
		}
		return ret;

	}

	public boolean isEmpty(){
		return sum<1;
	}

	public int size(){
		return sum;
	}

	public int[] asArray(){
		return list;
	}

	public boolean clear(){ 
		sum = 0;
		rightPointer = 0;
		return true; //Should not be used
	}
	public boolean init(int v){ //Must remember to call constructor in minijava
		list = new int[v];
		size = list.length;
		return this.clear();
	}

	public boolean print(){
		int index;
		index = 0;
		while( index < rightPointer ){
			System.out.println(list[index]);
			index = index + 1;
		}
		return true;
	}
}

class Sorter {
	public boolean sortQueue(Queue q) {
		boolean exchange;
		int temp;
		int[] array;
		int len;
		int i;
		int j;
		i = 0;
		array = q.asArray();
		len = q.size();
		while( i < len ){
			exchange = false;
			temp = 0;
			j = 0;
			while( j < len - i - 1){
				if(array[j+1] < array[j]){
					temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
					exchange = true;
				} else {
					
				}
				j = j + 1;
			}

			if(!exchange) 
				i = len+1;
			else
				i = i + 1;
		}
		exchange = q.clear();
		exchange = q.addSet(len, array);
		return exchange;

	}
}
// //-comment without content might consume <EOF> token. This should not happen.
//