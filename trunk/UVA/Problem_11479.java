/*
 * Is this the easiest problem
 */

import java.util.Scanner;

class Problem_11479 {
	public static boolean naoExisteTriangulo(long a, long b, long c) {
		return (a <= 0 || b <= 0 || c <= 0) || ((a + b) <= c) || ((b + c <= a))
				|| ((a + c) <= b);
	}

	public static boolean ehTrianguloEquilatero(long a, long b, long c) {
		return a == b && b == c;
	}

	public static boolean ehTrianguloIsosceles(long a, long b, long c) {
		return a == b || b == c || a == c;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		StringBuffer saida = new StringBuffer();
		String separador = "";
		int n = in.nextInt();
		long a;
		long b;
		long c;

		for (int i = 0; i < n; i++) {
			saida.append(separador);
			a = in.nextLong();
			b = in.nextLong();
			c = in.nextLong();

			separador = System.getProperty("line.separator");
			saida.append("Case " + (i + 1) + ": ");
			if (naoExisteTriangulo(a, b, c)) {
				saida.append("Invalid");
			} else if (ehTrianguloEquilatero(a, b, c)) {
				saida.append("Equilateral");
			} else if (ehTrianguloIsosceles(a, b, c)) {
				saida.append("Isosceles");
			} else {
				saida.append("Scalene");
			}
		}
		System.out.println(saida);
	}
}