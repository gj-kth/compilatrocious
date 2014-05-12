/*

Someone that accidently thinks that to solve the problem that "new int [3][3]" is not allowed,
the solution is to throw an error after the parsing, if array indexing node on a new int expression is found.

*/



class NoMultidim {
	public static void main(String[] args) {
		int val1;
		int val2;
		val2 = (new int[3])[2];
		val1 = (new int[(new int[17])[13] + 1])[0] + (new int[3])[0];
		System.out.println(val1 + val2);
	}
}
