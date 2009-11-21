import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Happy {
	private static long somaQuadradoDigitos(long num, int b) {
		int soma = 0;
		while (num > 0) {
			soma += ((num % b) * (num % b));
			num /= b;
		}
		return soma;
	}

	private static boolean happyNumber(long num, int b) {
		num = somaQuadradoDigitos(num, b);
		while (num > b - 1) {
			num = somaQuadradoDigitos(num, b);
		}
		return num == 1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int casos = Integer.parseInt(in.readLine());
		for (int c = 1; c <= casos; c++) {
			String[] bases = in.readLine().split("\\s+");
			int[] b = new int[bases.length];
			for (int i = 0; i < bases.length; i++) {
				b[i] = Integer.parseInt(bases[i]);
			}
			int minHappy = 2;
			while (true) {
				boolean all = true;
				for (int i = 0; i < b.length && all; i++) {
					if (!happyNumber(minHappy, b[i])) {
						all = false;
					}
				}
				if (all) {
					break;
				}
				minHappy++;
			}
			System.out.println("Case #" + c + ": " + minHappy);
		}
	}
}