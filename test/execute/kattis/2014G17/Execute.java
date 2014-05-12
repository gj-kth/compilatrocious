// Output 152239

class Main {
	public static void main(String[] args) {
		Compute co;
		int[] x;
		int[] y;

		co = new Compute();
		x = new int[10];
		y = new int[10];

		x[0] = 0;
		y[0] = 0;

		x[1] = 5;
		y[1] = 0 - 3;

		x[2] = 10;
		y[2] = 2;

		x[3] = 50;
		y[3] = 5;

		x[4] = 6;
		y[4] = 3;

		x[5] = 9;
		y[5] = 3;

		x[6] = 1;
		y[6] = 1;

		x[7] = 5;
		y[7] = 8;

		x[8] = 7;
		y[8] = 2;

		x[9] = 20;
		y[9] = 10;

		System.out.println(co.printValue(x, y));
	}
}

class Compute {
	public int printValue(int[] x, int[] y) {
		int i;
		int j;
		int[] z;
		Operation op;
		boolean bool;
		boolean trueT;
		boolean falseF;

		trueT = true;
		falseF = false;

		i = x[0] + x[5] * x[8]; // 0+9*7 = 63
		j = y[0] + y[5] * y[8]; // 0+3*2= 6
		z = new int[2];
		bool = false;
		op = new Operation();

		if (j * 10 < i) { // true
			x[0] = 100;
			bool = true;
		} else {
			x[0] = 0 - 100;
			bool = false;
		}

		z[0] = 10;
		z[1] = 0 - 10;

		if (bool && true && ((1 + 1) < 3)) {
			z[0] = z[0] + op.addArray(x, y);
			z[0] = z[0] + op.addElements(x);
		} else {

		}

		if (1 < 2 && (0 - 1) < 1) {
			z[0] = z[0] + 1000;
		} else {
			z[0] = 0;
		}

		if (op.evenArray(x) && op.evenArray(y)) {
			z[0] = 0;
		} else {
			z[0] = z[0] + 1000;
		}

		z[0] = z[0]
				+ op.addNumber(z[0], z[0], z[0], z[0], z[0], z[0], z[0], z[0],
						z[0]);

		z[0] = z.length + op.addElements(z) + z[0] + z[1];

		if (false && falseF) {
			z[0] = 0;
		} else {
			z[0] = z[0] + 1000;
		}
		if (falseF && trueT) {
			z[0] = 0;
		} else {
			z[0] = z[0] + 1000;
		}
		if (trueT && falseF) {
			z[0] = 0;
		} else {
			z[0] = z[0] + 1000;
		}
		if (trueT && trueT) {
			z[0] = z[0] + 1000;
		} else {
			z[0] = 0;
		}

		if (!(1 < 1 && (0 - 1) < 1)) {
			z[0] = z[0] + 1000;
		} else {
			z[0] = 0;
		}
		if (!(1 < 2 && (0 - 1) < 1)) {
			z[0] = 0;
		} else {
			z[0] = z[0] + 1000;
		}
		if (true && falseF && trueT && false) {
			z[0] = 0;
		} else {
			z[0] = z[0] + 1000;
		}

		if (true && trueT && trueT && falseF) {
			z[0] = 0;
		} else {
			z[0] = z[0] + 1000;
		}
		if (trueT && true && trueT && (0 < 1 && 1 < 2 && true)) {
			z[0] = z[0] + 1000;
		} else {
			z[0] = 0;
		}
		if (true && trueT && true && (1 < 1 && 1 < 2 && trueT)) {
			z[0] = 0;
		} else {
			z[0] = z[0] + 1000;
		}

		if (1 < 1 && (0 - 1) < 1) {
			z[0] = 0;
		} else {
			z[0] = z[0] + 1000;
		}

		x[0] = 50;
		x[1] = 5;
		x[2] = 5;
		x[3] = 50;
		x[4] = 50;
		x[5] = 5;
		x[6] = 5;
		x[7] = 50;
		x[8] = 50;
		x[9] = 5;

		if (true && trueT && true && trueT) {
			z[0] = z[0] + 1000;
		} else {
			z[0] = 0;
		}

		z[0] = z[0] + op.addElements(x) + op.multArray(x, y, 20);

		return z[0] + 20 + 25 + 10 + z[1] + op.getValue();
	}
}

class Operation {

	public Car getCar() {

		return new Car();

	}

	public int addElements(int[] x) {
		int value;
		int i;

		value = 0;
		i = 0;

		if (0 < x.length) {
			while (i < x.length) {
				value = value + x[i];
				i = i + 1;
			}
		} else {
			value = 0;
		}

		return value;

	}

	public int getValue() {
		return 0 - 25252;
	}

	public int addNumber(int i, int j, int k, int l, int m, int n, int o,
			int p, int q) {
		return (((i - 1) + (j - 2)) - 1) * 4
				+ ((((((k - 8) * 2) + 2) + 6) + 4) * 4) + l * 5 + 2 * m + 2 * n
				+ 2 * o + 2 * p + 2 * q;

	}

	public int addArray(int[] x, int[] y) {
		int value;
		int i;

		value = 0;
		i = (((((((((0)))))))));

		{
			i = 0;
			{
				i = i + 2;
				{
					i = i + 4;
					{
						i = i + 8;
					}
					i = i + 16;
				}
				i = i + 32;
			}
			i = i + 64;
		}

		if (i < 127 && 125 < i) {
			i = 0;
		} else {
			value = 0;
		}

		if (x.length - 1 < y.length && y.length < x.length + 1) {
			while (i < x.length) {
				value = value + x[i] + y[i];
				i = i + 1;
			}
		} else {
			value = 0;
		}

		{
			i = 2;
			{
				i = 22;
				{
					i = 222;
					{
						i = 2222;
						{
							{
								{
									{
										{
											{
												{
													i = 0;
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}

		if (0 < i) {
			value = 0;
		} else {
			value = value + 100;
		}

		return value;

	}

	public int multArray(int[] o, int[] p, int times) {

		int value;
		int t;
		int i;

		t = 0;
		value = 0;
		i = 0;

		while (t < times) {
			while (i < o.length) {
				value = value
						+ (((((((((((((((1 - 1) + ((((((((((((((((o[i] * p[i]) + (1 - 1))))) + (1 - 1))))))) + (1 - 1))))) + (1 - 1))))) + (1 - 1)))) + (1 - 1))))) + (1 - 1)))));
				if (i < 5) {
					value = value - 2;
					value = value + 2;
					value = value + i;
					value = value - i;
					value = value;

					t = t;

				} else {
					value = value;
				}
				i = i + 1;
			}
			t = t + 1;

		}

		return value;
	}

	public boolean evenArray(int[] array) {
		int value;
		int add;
		int add2;
		int minus;
		int car;
		Operation op;

		op = new Operation();

		add = op.addElements(array);
		add2 = op.addArray(array, array);
		minus = 0 - op.addArray(array, array);
		car = op.getCar().init();

		value = add + add2 + minus+ car;

		return true
				&& (1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 < 5555 + 2 && true)
				&& true
				&& true
				&& true
				&& true
				&& true
				&& true
				&& true
				&& true
				&& true
				&& true
				&& true
				&& true
				&& true
				&& true
				&& true
				&& false
				&& (1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1
						+ 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1
						+ 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1 + 1
						+ 1 + 1 + 1 + 1 < 2);
	}

}

class Car {

	int seats;

	public int init() {
		seats = 100;
		return seats;
	}

}
