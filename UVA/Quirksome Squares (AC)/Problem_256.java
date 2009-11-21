import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

class Problem_256 {
	static DecimalFormat format2 = new DecimalFormat("00");
	static DecimalFormat format4 = new DecimalFormat("0000");
	static DecimalFormat format6 = new DecimalFormat("000000");
	static DecimalFormat format8 = new DecimalFormat("00000000");

	public static boolean ehQuirksome(String n) {
		int n1 = Integer.parseInt(n.substring(0, (n.length() / 2)));
		int n2 = Integer.parseInt(n.substring(n.length() / 2));
		int quadrado = (n1 + n2) * (n1 + n2);
		int num = Integer.parseInt(n);
		return num == quadrado;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		while (true) {
			String linha = in.readLine();

			if (linha == null) {
				break;
			}

			switch (Integer.parseInt(linha.trim())) {
			case 2:
				saida.append("00" + separador + "01" + separador + "81");
				break;
			case 4:
				saida.append("0000" + separador + "0001" + separador + "2025"
						+ separador + "3025" + separador + "9801");
				break;
			case 6:
				saida.append("000000" + separador + "000001" + separador
						+ "088209" + separador + "494209" + separador
						+ "998001");
				break;
			case 8:
				saida.append("00000000" + separador + "00000001" + separador
						+ "04941729" + separador + "07441984" + separador
						+ "24502500" + separador + "25502500" + separador
						+ "52881984" + separador + "60481729" + separador
						+ "99980001");
				break;
			}

			saida.append(separador);
		}

		System.out.print(saida);

		// forca bruta... lento!!!
		// ArrayList<String> doisDigitos = new ArrayList<String>();
		// ArrayList<String> quatroDigitos = new ArrayList<String>();
		// ArrayList<String> seisDigitos = new ArrayList<String>();
		// ArrayList<String> oitoDigitos = new ArrayList<String>();
		//
		// for (int i = 0; i < 100; i++) {
		// String n = format2.format(i);
		// if (ehQuirksome(n)) {
		// doisDigitos.add(n);
		// }
		// }
		//
		// System.out.println(doisDigitos);
		// for (int i = 0; i < 10000; i++) {
		// String n = format4.format(i);
		// if (ehQuirksome(n)) {
		// quatroDigitos.add(n);
		// }
		// }
		// System.out.println(quatroDigitos);
		// for (int i = 0; i < 1000000; i++) {
		// String n = format6.format(i);
		// if (ehQuirksome(n)) {
		// seisDigitos.add(n);
		// }
		// }
		// System.out.println(seisDigitos);
		// for (int i = 0; i < 100000000; i++) {
		// String n = format8.format(i);
		// if (ehQuirksome(n)) {
		// oitoDigitos.add(n);
		// }
		// }
		// System.out.println(oitoDigitos);
	}
}