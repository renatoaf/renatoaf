import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

class Problem_100 {
	private static int[] ciclos = new int[1000001];

	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(
				System.in)));

		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		while (in.hasNext()) {
			int a = in.nextInt();
			int b = in.nextInt();
			int min = Math.min(a, b);
			int max = Math.max(a, b);

			int maiorCiclo = 0;
			for (int i = min; i <= max; i++) {
				if (ciclos[i] == 0) {
					long n = i;
					int numCiclos = 1;
					while (n != 1) {
						if (n >= 0 && n <= 1000000 && ciclos[(int) n] != 0) {
							numCiclos += (ciclos[(int) n] - 1);
							break;
						}
						n = (n % 2 == 0) ? (n / 2) : ((3 * n) + 1);
						numCiclos++;
					}
					ciclos[i] = numCiclos;
				}
				if (ciclos[i] > maiorCiclo) {
					maiorCiclo = ciclos[i];
				}
			}
			saida.append(a + " " + b + " " + maiorCiclo + separador);
		}

		System.out.print(saida);
	}
}