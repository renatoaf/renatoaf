import java.util.StringTokenizer;

class Problem_2115 {
	static int f(int n) {
		if (n == 1) {
			return 1;
		} else if (n % 2 == 0) {
			return n / 2;
		}
		n = (n - 1) / 2;
		return f(n) + f(n + 1);
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
				saida.append("f(" + num + ") = " + f(num) + "\n");
			}
		}

		System.out.print(saida);
	}
}