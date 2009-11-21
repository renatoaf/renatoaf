/*
 * Pascal Library
 */

import java.util.StringTokenizer;

class Problem_3470 {
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
			int N = Integer.parseInt(config.nextToken());
			int D = Integer.parseInt(config.nextToken());

			if (N == 0 && D == 0) {
				break;
			}

			int[] alunos = new int[N];

			boolean yes = false;
			for (int i = 0; i < D; i++) {
				config = new StringTokenizer(readln());
				for (int j = 0; j < N; j++) {
					if (Integer.parseInt(config.nextToken()) == 1) {
						alunos[j]++;
					}

					if (!yes && i == (D - 1) && alunos[j] == D) {
						yes = true;
					}
				}
			}

			saida.append(yes ? "yes\n" : "no\n");
		}

		System.out.print(saida);
	}
}