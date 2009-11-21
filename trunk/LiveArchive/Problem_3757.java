/*
 * Wooden Blocks
 */

class Problem_3757 {
	public static void main(String[] args) {
		int count = 1;
		String[] ligacoes = { "0", "45", "0", "45", "23", "8", "32", "8", "67" };
		String entrada = readln();

		while (!entrada.equals("0")) {
			if (entrada.charAt(0) != '1'
					|| entrada.charAt(entrada.length() - 1) != '2') {
				System.out.println(count + ". NOT");
			} else {
				boolean valido = true;
				for (int i = 1; i < entrada.length(); i++) {
					if (ligacoes[Integer.parseInt(entrada.charAt(i - 1) + "")]
							.indexOf(entrada.charAt(i) + "") == -1) {
						System.out.println(count + ". NOT");
						valido = false;
						break;
					}
				}
				if (valido) {
					System.out.println(count + ". VALID");
				}
			}
			count++;
			entrada = readln();
		}
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