import java.util.StringTokenizer;

class Problem_4216 {
	static String formata(int[] arr) {
		String sep = "";
		String form = "";
		for (int i = 0; i < arr.length; i++) {
			form += sep + arr[i];
			sep = " ";
		}
		return form;
	}

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
			int n = Integer.parseInt(readln());

			if (n == 0) {
				break;
			}

			boolean[] visitado = new boolean[n];
			int[] grid = new int[n];
			int somaPosMod = 0;

			boolean valido = true;
			for (int i = 0; i < n; i++) {
				StringTokenizer config = new StringTokenizer(readln());
				int numero = Integer.parseInt(config.nextToken());
				int posMod = Integer.parseInt(config.nextToken());

				somaPosMod += posMod;

				int posInicial = i + posMod;
				if (posInicial < 0 || posInicial >= n) {
					valido = false;
				} else if (visitado[posInicial]) {
					valido = false;
				} else {
					visitado[posInicial] = true;
					grid[posInicial] = numero;
				}
			}

			if (somaPosMod != 0) {
				valido = false;
			}

			saida.append(valido ? formata(grid) : "-1");
			saida.append("\n");
		}

		System.out.print(saida);
	}
}