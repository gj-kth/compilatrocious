//EXT:IWE
//EXT:LONG
//EXT:CLE
//EXT:CGT
//EXT:CGE
//EXT:CEQ
//EXT:CNE
//EXT:BDJ

/*
This program finds all paths and the length of them in a graph.
*/

class FindPathLengths{
	public static void main(String[] args){
		Graph graph;
		Pathfinder pf;
		boolean waste;
	 	long[] pathsLength;
	 	int i;
	 	int l;

		graph = new Graph();
		waste = graph.init();
		pf = new Pathfinder();
		pathsLength = pf.findPathLengths(graph);
		i = 0;
		l = pathsLength.length;
		while(i<l){
			System.out.println(pathsLength[i]);
			i = i + 1;
		}


	}
}
class Pathfinder{

	public int findNumberOfPaths(Graph graph){
		return this.findEnd(graph, graph.getStartNode(), graph.getEndNode());
	}

	public int findEnd(Graph graph, int start, int end){
		int[] edgeFrom;
		int[] edgeTo;
		int startNode;
		int endNode;
		int paths;
		int i;

		edgeFrom = graph.getEdgeFrom();
		edgeTo = graph.getEdgeTo();
		i = edgeFrom.length - 1;
		paths = 0;
		while(i>=0){
			if(edgeFrom[i] == start){
				if(edgeTo[i] == end){
					paths = paths+1;
				} else{
					paths = paths + this.findEnd(graph, edgeTo[i], end);
				}
			}
			i = i-1;
		}
		return paths;
	}

	public long[] findPathLengths(Graph graph){
		IndexHolder ih;
		boolean trash;
		ih = new IndexHolder();
		trash = ih.init();

		return this.findLength(graph, graph.getStartNode(), graph.getEndNode(), 0, new long[this.findNumberOfPaths(graph)], ih);

	}

	public long[] findLength(Graph graph, int currNode, int goalNode, int index, long[] lengths, IndexHolder ih){
		int i;
		int[] edgeFrom;
		int[] edgeTo;
		long[] edgeLength;
		int e;
		boolean more;
		int indexPos;
		long[] trash;
		long startLength;
		boolean trashb;

		i = 0;
		edgeFrom = graph.getEdgeFrom();
		edgeTo = graph.getEdgeTo();
		edgeLength = graph.getEdgeLength();
		e = edgeFrom.length;
		startLength = lengths[ih.getIndex()];


		more = false;
		while(i<e){
			if((edgeFrom[i] == currNode)){
				if(more){
					trashb = ih.incIndex();
					lengths[ih.getIndex()] = startLength; //kek
				}
				more = true;
				 if(edgeTo[i] == goalNode){
				 	lengths[ih.getIndex()] = lengths[ih.getIndex()] + edgeLength[i];
				 }else{
				 	lengths[ih.getIndex()] = lengths[ih.getIndex()] + edgeLength[i];
				 	trash = this.findLength(graph, edgeTo[i], goalNode, index, lengths, ih);
				 }

			}
			i = i + 1;
		}
		return lengths;
	}
}

class IndexHolder{
	int index;

	public boolean init(){
		index = 0;
		return true;
	}

	public int getIndex(){
		return index;
	}
	public boolean incIndex(){
		index = index + 1;
		return true;
	}
}

//This must be a graph where all ways actually lead to the endNode.
class Graph{
	int numberOfNodes;
	int numberOfEdges;
	int[] edgeFrom;
	int[] edgeTo;
	long[] edgeLength;
	int startNode;
	int endNode;
	
	public int getNumberOfNodes(){
		return numberOfNodes;
	}
	public int[] getEdgeFrom(){
		return edgeFrom;
	}
	public int[] getEdgeTo(){
		return edgeTo;
	}
	public long[] getEdgeLength(){
		return edgeLength;
	}
	public int getStartNode(){
		return startNode;
	}
	public int getEndNode(){
		return endNode;
	}
	
	public boolean init(){
		Tools tool;
		long bigNumber;
		tool = new Tools();
		bigNumber = tool.thisIsLargerThanInt();
		edgeFrom = new int[10];
		edgeTo = new int[10];
		edgeLength = new long[10];
		numberOfEdges = edgeLength.length;
		numberOfNodes = 7;
		startNode = 0;
		endNode = 6;
		//bigNumber = 0L;
		bigNumber = tool.thisIsLargerThanInt();
		
		edgeFrom[0] = 0;
		edgeTo[0] = 1;
		edgeLength[0] = bigNumber + 150L;
		edgeFrom[1] = 0;
		edgeTo[1] = 2;
		edgeLength[1] = bigNumber + 200L;
		edgeFrom[2] = 0;
		edgeTo[2] = 3;
		edgeLength[2] = bigNumber + 50L; 
		edgeFrom[3] = 1;
		edgeTo[3] = 4;
		edgeLength[3] = bigNumber + 100L;
		edgeFrom[4] = 2;
		edgeTo[4] = 4;
		edgeLength[4] = bigNumber + 150L;
		edgeFrom[5] = 2;
		edgeTo[5] = 6;
		edgeLength[5] = bigNumber + 200L;
		edgeFrom[6] = 2;
		edgeTo[6] = 5;
		edgeLength[6] = bigNumber + 50L;
		edgeFrom[7] = 3;
		edgeTo[7] = 5;
		edgeLength[7] = bigNumber + 100L;
		edgeFrom[8] = 4;
		edgeTo[8] = 6;
		edgeLength[8] = bigNumber + 200L;
		edgeFrom[9] = 5;
		edgeTo[9] = 6;
		edgeLength[9] = bigNumber + 50L;
		return true;
	}
}


class Tools{
	public long thisIsLargerThanInt(){
		long l;
		int i;
		l = 2L;
		i = 40;
		while(i >= 0){
			l = l*2L;
			i = i-1;
		}
		return l;
	}
}
