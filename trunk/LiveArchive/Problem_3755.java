class Problem_3755 {
	public static void main(String[] args) {
		StringBuffer saida = new StringBuffer();
		String separador = System.getProperty("line.separator");
		int c = 1;
		while (true) {
			String linha = readln();

			int i = 0;
			String r = "";
			while (i < linha.length() && linha.charAt(i) != ' ') {
				if (Character.isDigit(linha.charAt(i))) {
					r += linha.charAt(i);
				}
				i++;
			}

			int R = Integer.parseInt(r);
			if (R == 0) {
				break;
			}

			while (i < linha.length() && linha.charAt(i) == ' ') {
				i++;
			}

			String n = "";
			while (i < linha.length() && linha.charAt(i) != ' ') {
				n += linha.charAt(i);
				i++;
			}
			int N = Integer.parseInt(n);

			while (i < linha.length() && linha.charAt(i) == ' ') {
				i++;
			}

			String p1 = "";
			boolean quotes = linha.charAt(i) == '"';
			if (quotes) {
				i++;
			}

			while (i < linha.length()) {
				if (quotes && linha.charAt(i) == '"') {
					i++;
					break;
				} else if (!quotes && linha.charAt(i) == ' ') {
					i++;
					break;
				}
				p1 += linha.charAt(i++);
			}

			while (i < linha.length() && linha.charAt(i) == ' ') {
				i++;
			}

			String p2 = "";
			quotes = linha.charAt(i) == '"';
			if (quotes) {
				i++;
			}

			while (i < linha.length()) {
				if (quotes && linha.charAt(i) == '"') {
					break;
				}
				p2 += linha.charAt(i++);
			}

			saida.append(c++
					+ (". ")
					+ ((R - 1 / N) % 2 == 0 ? p1.toLowerCase() : p2
							.toLowerCase()));
			saida.append(separador);
		}

		System.out.print(saida);
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
}