import java.util.Scanner;

class Problem_10018 {
	private static long inverteNumero(long n) {
		StringBuffer num = new StringBuffer(String.valueOf(n));
		return Long.parseLong(num.reverse().toString());
	}

	private static boolean ehPalindromo(long n) {
		return n == inverteNumero(n);
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		StringBuffer saida = new StringBuffer();
		String separador = "";
		int n = in.nextInt();

		for (int i = 0; i < n; i++) {
			saida.append(separador);
			long numero = in.nextLong();
			numero += inverteNumero(numero);
			int contador = 1;

			while (!ehPalindromo(numero)) {
				numero += inverteNumero(numero);
				contador++;
			}

			separador = System.getProperty("line.separator");
			saida.append(contador + " " + numero);
		}

		System.out.println(saida);
	}
}
