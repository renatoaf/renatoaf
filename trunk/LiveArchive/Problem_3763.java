import java.util.StringTokenizer;

class Problem_3763 {
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
		String entrada = readln();
		int n = 1;
		while (!entrada.equals(".")) {
			StringTokenizer s = new StringTokenizer(entrada, "+*=.");
			int a = somar(s.nextToken());
			int b = somar(s.nextToken());
			int c = somar(s.nextToken());

			if (entrada.indexOf("*") != -1) {
				if ((a * b) % 9 == c % 9) {
					System.out.println(n + ". PASS");
				} else {
					System.out.println(n + ". NOT!");
				}
			} else {
				if ((a + b) % 9 == c % 9) {
					System.out.println(n + ". PASS");
				} else {
					System.out.println(n + ". NOT!");
				}
			}
			n++;
			entrada = readln();
		}
	}

	private static int somar(String s) {
		int soma = 0;
		for (int i = 0; i < s.length(); i++) {
			soma += Integer.parseInt(s.charAt(i) + "");
		}
		return soma;
	}
}
