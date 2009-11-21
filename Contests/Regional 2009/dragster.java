import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.StringTokenizer;

class dragster {
	static double probs[][];

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		DecimalFormat df = new DecimalFormat("0.000000");
		DecimalFormatSymbols dfs = new DecimalFormatSymbols();
		dfs.setDecimalSeparator('.');
		df.setDecimalFormatSymbols(dfs);

		while (true) {
			int N = Integer.parseInt(in.readLine());

			if (N == 0)
				break;

			probs = new double[N][N];

			for (int i = 0; i < N; i++) {
				StringTokenizer config = new StringTokenizer(in.readLine());
				for (int j = 0; j < N; j++) {
					probs[i][j] = Double.parseDouble(config.nextToken());
				}
			}

			No[] corridas = new No[N - 1];
			for (int i = 0; i < N - 1; i++) {
				corridas[i] = new No(N);
			}

			for (int i = 0; i < N - 1; i++) {
				StringTokenizer config = new StringTokenizer(in.readLine());
				int A = Integer.parseInt(config.nextToken());
				int B = Integer.parseInt(config.nextToken());

				if (A <= N) { // folha
					corridas[i].esq = new No(N);
					corridas[i].esq.pc[A - 1] = 1;
					corridas[i].esq.parent = corridas[i];
				} else { // nao-folha
					corridas[i].esq = corridas[A - N - 1];
					corridas[i].esq.parent = corridas[i];
				}

				if (B <= N) { // folha
					corridas[i].dir = new No(N);
					corridas[i].dir.pc[B - 1] = 1;
					corridas[i].dir.parent = corridas[i];
				} else { // nao-folha
					corridas[i].dir = corridas[B - N - 1];
					corridas[i].dir.parent = corridas[i];
				}
			}

			No raiz = null;
			for (int i = 0; i < N - 1; i++) {
				if (corridas[i].parent == null) {
					raiz = corridas[i];
					break;
				}
			}

			getProbabilidades(raiz, N);
			out.append(df.format(raiz.pc[0]) + "\n");
		}

		System.out.print(out);
	}

	public static void getProbabilidades(No raiz, int n) {
		// caminhamento pos-order
		if (raiz == null || raiz.folha()) {
			return;
		}
		getProbabilidades(raiz.esq, n);
		getProbabilidades(raiz.dir, n);

		for (int i = 0; i < n; i++) {
			double sumEsq = 0, sumDir = 0;
			for (int j = 0; j < n; j++) {
				sumEsq += (probs[i][j] * raiz.dir.pc[j]);
				sumDir += (probs[i][j] * raiz.esq.pc[j]);
			}
			// prob de i vencer a corrida atual eh:
			// (prob de i ter vencido a corrida anterior E i vencer os j's
			// potenciais adversarios E dos j's potenciais adversarios terem
			// vencido a corrida anterior) OU (caso simetrico pra direita)
			raiz.pc[i] = (raiz.esq.pc[i] * sumEsq) + (raiz.dir.pc[i] * sumDir);
		}
	}
}

class No {
	double pc[]; // pc[i] representa a probabilidade de i vencer essa corrida
	No parent;
	No esq;
	No dir;

	public No(int n) {
		pc = new double[n];
	}

	public boolean folha() {
		return esq == null && dir == null;
	}
}