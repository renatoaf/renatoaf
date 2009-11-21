import java.util.Scanner;

class Problem_10038 {
	public static boolean ehJollyJumper(int[] sequencia) {
		int n = sequencia.length;
		int[] diferencas = new int[n - 1];

		for (int i = 0; i < n - 1; i++) {
			int diferenca = Math.abs(sequencia[i] - sequencia[i + 1]);
			if (diferenca == 0 || diferenca >= n) {
				return false;
			}
			diferencas[diferenca - 1]++;
		}

		for (int i = 0; i < n - 1; i++) {
			if (diferencas[i] == 0) {
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		StringBuffer saida = new StringBuffer();
		String separador = System.getProperty("line.separator");

		while (in.hasNext()) {
			int[] sequencia = new int[in.nextInt()];
			for (int i = 0; i < sequencia.length; i++) {
				sequencia[i] = in.nextInt();
			}
			saida.append((ehJollyJumper(sequencia) ? "Jolly" : "Not jolly")
					+ separador);
		}

		System.out.print(saida);
	}
}