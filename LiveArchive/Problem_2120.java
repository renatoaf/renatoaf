import java.util.StringTokenizer;
import java.util.Vector;

class Problem_2120 {
	static boolean[] crivo(int tamanho) {
		boolean[] primo = new boolean[tamanho];
		for (int i = 2; i < tamanho; i++) {
			primo[i] = true;
		}
		for (int i = 2; i * i < tamanho; i++) {
			if (primo[i]) {
				for (int j = i; i * j < tamanho; j++) {
					primo[i * j] = false;
				}
			}
		}
		return primo;
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

	static int getIndiceNaEspiral(int x, int y) {
		if (x == 0 && y == 0) {
			return 0;
		}
		int maior = Math.max(Math.abs(x), Math.abs(y));
		int elementosDoQuadradoAnt = 1;
		int k = maior;
		while (--k > 0) {
			elementosDoQuadradoAnt += 2;
		}
		int indice = elementosDoQuadradoAnt * elementosDoQuadradoAnt;
		int i = maior;
		int j = -maior + 1;
		while (j < maior) {
			if (i == x && j == y) {
				return indice;
			}
			indice++;
			j++;
		}
		while (i > -maior) {
			if (i == x && j == y) {
				return indice;
			}
			indice++;
			i--;
		}
		while (j > -maior) {
			if (i == x && j == y) {
				return indice;
			}
			indice++;
			j--;
		}
		while (i < maior) {
			if (i == x && j == y) {
				return indice;
			}
			indice++;
			i++;
		}
		return indice;
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		boolean[] primo = crivo(10001);
		Vector listaPrimos = new Vector();
		listaPrimos.addElement(new Integer(2));
		for (int i = 3; i < primo.length; i += 2) {
			if (primo[i]) {
				listaPrimos.addElement(new Integer(i));
			}
		}

		StringBuffer saida = new StringBuffer();
		int c = 1;
		while (true) {
			StringTokenizer token = new StringTokenizer(readln());

			int x = Integer.parseInt(token.nextToken());
			if (x == -999) {
				break;
			}
			int y = Integer.parseInt(token.nextToken());

			if (c != 1) {
				saida.append("\n");
			}
			saida.append("Case " + c + ": The prime at location (" + x + ","
					+ y + ") is "
					+ listaPrimos.elementAt(getIndiceNaEspiral(x, y)) + ".\n");
			c++;
		}
		System.out.print(saida);
	}
}