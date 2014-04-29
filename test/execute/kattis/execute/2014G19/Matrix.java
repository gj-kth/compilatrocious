// EXT:IWE
// EXT:BDJ
// EXT:CLE
// EXT:CGE
// EXT:CGT
// EXT:NBD
// EXT:CNE
// EXT:CEQ
// EXT:LONG
// EXT:ISC

class Matrix {
	public static void main(String[] args){
		internalMatrix m;
		int i;
		int j;
		internalMatrix n;
		matrixWithPrint o;
		long x;
		long y;
		boolean scrap;
		
		m = new internalMatrix();
		n = new internalMatrix();
		scrap = m.Init(2,2);
		scrap = n.Init(2,2);
		
		//Check the lazy evaluation
		if((true || false) && true){
			System.out.println(true);
		}
		
		i = 0;
		j = 0;
		
		while(i <= m.getMatrixSize()){
			System.out.println(m.getData(i,j));
			i = i + 1;
		}

		i = 0;
		//Set the m and n matrix to only 2
		while(i < m.getColumnLength()){
			j = 0;
			while(j < m.getColumnLength()){
				scrap = m.setData(i,j,2);
				scrap = n.setData(i,j,2);
				j = j + 1;
			}
			i = i + 1;
		}
		
		o = m.matrixmultiplication(m,n);

		scrap = o.printMatrix();
		
		x = 13L;
		y = 13;
		i = 13;
		
		if(x == y){
			System.out.println(true);
			if(y == i){
				System.out.println(true);
			}
		}
	}
}

class internalMatrix{
	int row;
	int column;
	int[] data;
	
	public boolean Init(int rowIn, int columnIn){
		row = rowIn;
		column = columnIn;
		data = new int[rowIn*columnIn];
		return true;
	}
	
	public int getRowLength(){
		return row;
	}
	
	public int getColumnLength(){
		return column;
	}
	
	public int getMatrixSize(){
		return data.length;
	}
	
	public boolean setData(int rowIn, int colIn, int dataIn){
		boolean status;
		//Check so we're in range
		if(this.getMatrixSize() > ((this.getRowLength()*rowIn)+colIn)){
			data[((this.getRowLength()*rowIn)+colIn)] = dataIn;
			status = true;
		}
		else {
			status = false;
		}
		//This will only happen if we're out of range
		return status;
	}
	
	public int getData(int rowIn, int colIn){
		int result;
		result = 0;
		if(((this.getRowLength()*rowIn)+colIn) < this.getMatrixSize()){
			result = data[((this.getRowLength()*rowIn)+colIn)];
		}	
		return result;
	}

	public matrixWithPrint matrixmultiplication(internalMatrix a,internalMatrix b){
		int aRows;
		int aColumns;
		int bRows;
		int bColumns;
		int i;
		int j;
		
		matrixWithPrint c;


		aRows = a.getRowLength();
		aColumns = a.getColumnLength();
		bRows = b.getRowLength();
		bColumns = b.getColumnLength();

		if(aColumns != bRows){
			c = new matrixWithPrint();
			if(c.Init(0, 0)){}
		}
		else {
			c = new matrixWithPrint();
			if(c.Init(aRows,bColumns)){}

			i = 0;
			while(i < aRows){
				j = 0;
				while(j < bColumns){
					if(c.setData(i,j,0)){}
					j = j + 1;
				}
				i = i + 1;
			}

			i = 0;
			j = 0;

			while(i < aRows){
				j = 0;
				while(j < bColumns){
					int k;
					k = 0;
					while(k < aColumns){
						if(c.setData(i,j, c.getData(i,j)+(a.getData(i,k)*b.getData(k,j)))){}
						k = k + 1;
					}
					j = j + 1;
				}
				i = i + 1;
			}
		}
		return c;
	}	
}

class matrixWithPrint extends internalMatrix{
	
	public boolean printMatrix(){
		int i;
		int j;
		
		i = 0;
		
		while(i < this.getRowLength()){
			j = 0;
			while(j < this.getColumnLength()){
				System.out.println(this.getData(i,j));
				j = j + 1;
			}
			i = i + 1;
		}
		
		return true;
	}
}

