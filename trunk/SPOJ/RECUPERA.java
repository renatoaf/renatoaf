import java.util.Scanner;

class RECUPERA {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		StringBuffer saida = new StringBuffer();

		int caso = 1;
		while (in.hasNextInt()) {
			int n = in.nextInt();

			int[] sequencia = new int[n];
			for (int i = 0; i < n; i++) {
				sequencia[i] = in.nextInt();
			}

			int soma = 0;
			boolean acabou = false;
			for (int i = 0; i < n; i++) {
				if (soma == sequencia[i]) {
					acabou = true;
					break;
				}
				soma += sequencia[i];
			}

			saida.append("Instancia " + caso + "\n");
			if (acabou) {
				saida.append(soma);
			} else {
				saida.append("nao achei");
			}
			saida.append("\n\n");
			caso++;
		}

		System.out.print(saida);
	}
}
