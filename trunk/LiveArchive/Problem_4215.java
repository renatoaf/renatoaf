/*
 * Feynman
 */

class Problem_4215 {
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
		int[] respostas = new int[101];
		for (int i = 1; i < 101; i++) {
			respostas[i] = (i * i) + respostas[i - 1];
		}

		while (true) {
			int n = Integer.parseInt(readln());
			if (n == 0) {
				break;
			}
			saida.append(respostas[n] + "\n");
		}

		System.out.print(saida);
	}
}