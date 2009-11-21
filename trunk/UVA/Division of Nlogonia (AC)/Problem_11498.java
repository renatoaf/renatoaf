import java.util.Scanner;

class Problem_11498 {
	public static boolean naDivisa(int x, int y, int xDivisa, int yDivisa) {
		return x == xDivisa || y == yDivisa;
	}

	public static boolean nordeste(int x, int y, int xDivisa, int yDivisa) {
		return x > xDivisa && y > yDivisa;
	}

	public static boolean noroeste(int x, int y, int xDivisa, int yDivisa) {
		return x < xDivisa && y > yDivisa;
	}

	public static boolean sudeste(int x, int y, int xDivisa, int yDivisa) {
		return x > xDivisa && y < yDivisa;
	}

	public static boolean sudoeste(int x, int y, int xDivisa, int yDivisa) {
		return x < xDivisa && y < yDivisa;
	}

	public static void main(String[] args) {
		StringBuffer saida = new StringBuffer();
		Scanner in = new Scanner(System.in);
		String separador = "";
		int k;
		while ((k = in.nextInt()) != 0) {

			int xDivisa = in.nextInt();
			int yDivisa = in.nextInt();

			for (int i = 0; i < k; i++) {
				int x = in.nextInt();
				int y = in.nextInt();
				saida.append(separador);
				separador = System.getProperty("line.separator");
				if (naDivisa(x, y, xDivisa, yDivisa)) {
					saida.append("divisa");
				} else if (nordeste(x, y, xDivisa, yDivisa)) {
					saida.append("NE");
				} else if (noroeste(x, y, xDivisa, yDivisa)) {
					saida.append("NO");
				} else if (sudeste(x, y, xDivisa, yDivisa)) {
					saida.append("SE");
				} else if (sudoeste(x, y, xDivisa, yDivisa)) {
					saida.append("SO");
				}
			}
		}
		System.out.println(saida);
	}
}
