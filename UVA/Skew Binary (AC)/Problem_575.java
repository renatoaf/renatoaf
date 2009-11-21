import java.util.Scanner;

class Problem_575 {
	public static int skewBinary(String n) {
		int decimal = 0;
		for (int i = 0; i < n.length(); i++) {
			int digito = n.charAt(n.length() - i - 1);
			if (digito == '1') {
				decimal += Math.pow(2, i + 1) - 1;
			} else if (digito == '2') {
				decimal += 2 * (Math.pow(2, i + 1) - 1);
			}
		}
		return decimal;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		StringBuffer saida = new StringBuffer();
		String separador = System.getProperty("line.separator");
		String n;
		while (!(n = in.nextLine()).equals("0")) {
			saida.append(skewBinary(n) + separador);
		}
		System.out.print(saida);
	}
}