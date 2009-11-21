import java.util.Scanner;

class Problem_10591 {
	private static long somaQuadradoDigitos(long num) {
		int soma = 0;
		while (num > 0) {
			soma += ((num % 10) * (num % 10));
			num /= 10;
		}
		return soma;
	}

	private static boolean happyNumber(long num) {
		num = somaQuadradoDigitos(num);
		while (num > 9) {
			num = somaQuadradoDigitos(num);
		}
		return num == 1;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String separador = System.getProperty("line.separator");
		StringBuffer saida = new StringBuffer();

		int casos = in.nextInt();
		for (int i = 1; i <= casos; i++) {
			long n = in.nextLong();
			saida.append("Case #" + i + ": " + n + " is "
					+ (happyNumber(n) ? "a Happy" : "an Unhappy") + " number."
					+ separador);
		}

		System.out.print(saida);
	}
}