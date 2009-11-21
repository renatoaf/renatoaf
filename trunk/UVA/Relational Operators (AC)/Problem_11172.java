import java.util.Scanner;

class Problem_11172 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		StringBuffer saida = new StringBuffer();
		String separador = System.getProperty("line.separator");
		int n = in.nextInt();

		for (int i = 0; i < n; i++) {
			int a = in.nextInt();
			int b = in.nextInt();

			if (a > b) {
				saida.append(">");
			} else if (a < b) {
				saida.append("<");
			} else {
				saida.append("=");
			}

			saida.append(separador);
		}

		System.out.print(saida);
	}
}
