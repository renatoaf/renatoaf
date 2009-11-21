import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Problem_498 {
	static int[] solucaoPolinomio(int[] coeficientes, int[] args) {
		int[] solucao = new int[args.length];

		for (int s = 0; s < args.length; s++) {
			int sol = 0;
			for (int i = 0; i < coeficientes.length; i++) {
				int n = coeficientes.length - i - 1;
				sol += (coeficientes[i] * ((int) Math.pow(args[s], n)));
			}
			solucao[s] = sol;
		}

		return solucao;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		while (true) {
			String linha = in.readLine();

			if (linha == null) {
				break;
			}

			String[] coef = linha.split("\\s+");
			String[] arg = in.readLine().split("\\s+");

			int[] coeficientes = new int[coef.length];
			int[] x = new int[arg.length];

			for (int i = 0; i < coef.length; i++) {
				coeficientes[i] = Integer.parseInt(coef[i]);
			}
			for (int i = 0; i < arg.length; i++) {
				x[i] = Integer.parseInt(arg[i]);
			}

			int[] solucao = solucaoPolinomio(coeficientes, x);
			for (int i = 0; i < solucao.length; i++) {
				saida.append(solucao[i]);
				if (i != solucao.length - 1) {
					saida.append(" ");
				}
			}
			saida.append(separador);
		}

		System.out.print(saida);
	}
}