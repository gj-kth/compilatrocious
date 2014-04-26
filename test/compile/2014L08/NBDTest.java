// EXT:NBD

/*

Reuse of variable names are allowed if they are in blocks that are not
active at the same time

*/

class NBDTest {
	public static void main(String[] args) {
		{
			{
				int a;
				a = 3;
			}
			{
				int a;
				a = 4;
			}
		}
		{
			int a;
			a = 5;
		}
	}
}
