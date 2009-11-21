/*
 * Octal Fractions
 */

class Problem_2245 {
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

	static double converte(String octal) {
		double div = 8.0;
		double res = 0.0;
		for (int i = 2; i < octal.length(); i++) {
			res += (octal.charAt(i) - '0') / div;
			div *= 8.0;
		}
		return res;
	}

	public static void main(String[] args) {
		StringBuffer saida = new StringBuffer();

		while (true) {
			String octal = readln();

			if (octal == null) {
				break;
			}

			double dec = converte(octal);

			saida.append(octal + " [8] = 0.");
			while (dec > 0) {
				dec *= 10;
				int digito = (int) dec;
				saida.append(digito);
				dec -= digito;
			}
			saida.append(" [10]\n");
		}

		System.out.print(saida);
	}
}