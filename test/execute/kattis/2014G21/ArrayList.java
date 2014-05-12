// EXT:IWE
// EXT:CLE
// EXT:CGT
// EXT:CGE
// EXT:CEQ
// EXT:CNE
// EXT:BDJ

class Main{
	public static void main(String[] args) {
		ArrayList al;
		boolean yesh;
		int mesh;
		int bash;
		al = new ArrayList();
		yesh = al.init(2);
		System.out.println(al.isEmpty());
		yesh = al.add(2);
		yesh = al.add(56565);
		yesh = al.add(3);
		yesh = al.print();
		al = al.subList(0,2);
		System.out.println(al.isEmpty());
		yesh = al.remove(0) != 151515 || al.remove(0) != 23423423; //Lazy evaluation
		yesh = al.print();
		yesh = al.clear();
		System.out.println(al.isEmpty());
	}
}

class ArrayList{
	int[] list;
	int size;

	public boolean init(int v){
		list = new int[v];
		return this.clear();
	}

	public boolean print(){
		int index;
		index = 0;
		while( index < size ){
			System.out.println(list[index]);
			index = index + 1;
		}
		return true;
	}

	public boolean clear(){ 
		size = 0;
		return true;
	}

	public boolean isEmpty(){
		return size == 0;
	}

	public int size(){
		return size;
	}

	public int[] toArray(){
		return list;
	}
	
	public boolean add(int element){
		int[] tmp;
		int index;
		if(  size >= list.length ){
			if(list.length == 0){
				tmp = new int[1];
			} else {
				tmp = new int[list.length*2];
			}
			index = 0;
			while(index < size){
				tmp[index] = list[index];
				index = index + 1;
			}
			list = tmp;
		}

		list[size]=element;
		size = size + 1;

		return true;
	}
	

	public boolean addAll(int size, int[] values){
		int index;
		boolean ret;
		ret = false;
		index = 0;
		while(index < values.length){
			ret = this.add(values[index]);
			index = index + 1;
		}

		return ret;
	}

	public int remove(int idx){ 
		int ret;
		int index;
		ret = list[idx];
		index = idx;
		while(size > index+1){
			list[index] = list[index+1];
			index = index + 1;
		}
		size = size - 1;
		return ret;

	}
	
	public int get(int idx) {
		int ret;
		ret = 1337;
		if(0 <= idx && idx < list.length) {
			ret = list[idx];
		}
		return ret;
	}
	
	public ArrayList subList(int fromIndex, int toIndex) {
		ArrayList temp;
		boolean scrapBool;
		temp = new ArrayList();
		scrapBool = temp.init(0);
		if(fromIndex < toIndex) {
			while(fromIndex != toIndex) {
				scrapBool = temp.add(list[fromIndex]);
				fromIndex = fromIndex + 1;
			}
		}
		return temp;
	}
}
// //-comment without content might consume <EOF> token. This should not happen.
//