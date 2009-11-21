import java.util.StringTokenizer;

class DOCE {
	static String readln() {
		String newLine = System.getProperty("line.separator");
		StringBuffer buffer = new StringBuffer();
		int car = -1;
		try {
			car = System.in.read();
			while ((car > 0) && (car != newLine.charAt(0))) {
				buffer.append((char) car);
				car = System.in.read();
			}
			if (car == newLine.charAt(0))
				System.in.skip(newLine.length() - 1);
		} catch (java.io.IOException e) {
			return (null);
		}
		if ((car < 0) && (buffer.length() == 0))
			return (null); /* eof */
		if (buffer.length() == 0)
			return ""; // string vazia
		return (buffer.toString()).trim();
	}

	public static void main(String[] args) {
		StringBuffer saida = new StringBuffer();

		while (true) {
			StringTokenizer config = new StringTokenizer(readln());
			int M = Integer.parseInt(config.nextToken());
			int N = Integer.parseInt(config.nextToken());

			if (M == 0 && N == 0) {
				break;
			}

			int[] somaColuna = new int[M];
			for (int i = 0; i < M; i++) {
				config = new StringTokenizer(readln());
				int[] somaLinha = new int[N];

				for (int j = 0; j < N; j++) {
					int atual = Integer.parseInt(config.nextToken());
					int anterior = (j > 0 ? somaLinha[j - 1] : 0);
					int antAnterior = (j > 1 ? somaLinha[j - 2] : 0);
					somaLinha[j] = Math.max(atual + antAnterior, anterior);
				}

				int atual = somaLinha[N - 1];
				int anterior = (i > 0 ? somaColuna[i - 1] : 0);
				int antAnterior = (i > 1 ? somaColuna[i - 2] : 0);
				somaColuna[i] = Math.max(atual + antAnterior, anterior);
			}

			saida.append(somaColuna[M - 1] + "\n");
		}

		System.out.print(saida);
	}
}