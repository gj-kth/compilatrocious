// EXT:IWE
// EXT:CEQ

class Objects {

	public static void main(String[] args) {
		if(new Object().getObject().initialize().getArray()[3] == (new int[40])[5 + new Object().getObject().initialize().getArray()[7] + 7]) {
		}
	}

}

class Object {
	
	int[] arr;
	
	public Object getObject() {
		return this;
	}
	
	public int[] getArray() {
		return arr;
	}
	
	public Object initialize() {
		int counter;
		counter = 0;
		arr = new int[45];
		while(counter < 45) {
			arr[counter] = 45 - counter * 3;

			if(counter == (45 - 1) == true)
				arr[counter] = 2147483647;
			counter = counter + 1;
		}
		
		return this;
	}
}
