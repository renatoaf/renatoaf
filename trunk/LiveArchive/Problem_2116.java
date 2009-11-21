import java.util.StringTokenizer;

class Problem_2116 {
	static int m(int num) {
		if (num == 1) {
			return 1;
		}

		int div = 2;
		int ant = -1;
		int p = 0;
		while (num > 1 && div <= num) {
			if (num % div == 0) {
				num /= div;

				if (ant != div) {
					ant = div;
					p++;
				} else {
					return 0;
				}
			} else {
				div++;
			}
		}
		return p % 2 == 0 ? 1 : -1;
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

		boolean acabou = false;
		boolean primeiro = true;

		while (!acabou) {
			String linha = readln();
			StringTokenizer s = new StringTokenizer(linha);

			while (s.hasMoreElements()) {
				int num = Integer.parseInt(s.nextToken());

				if (num == -1) {
					acabou = true;
					break;
				} else if (!primeiro) {
					saida.append("\n");
				}
				primeiro = false;
				saida.append("M(" + num + ") = " + m(num) + "\n");
			}
		}

		System.out.print(saida);
	}
}