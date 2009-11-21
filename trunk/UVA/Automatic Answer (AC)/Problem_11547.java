import java.util.Scanner;

class Problem_11547 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		StringBuffer saida = new StringBuffer();
		String separador = System.getProperty("line.separator");

		int k = in.nextInt();
		for (int i = 0; i < k; i++) {
			int n = in.nextInt();
			long resultado = (315 * n) + 36962; // eq. simplificada
			String digitos = String.valueOf(resultado);
			char dezena = digitos.charAt((digitos.length() - 2));
			saida.append(dezena + separador);
		}

		System.out.print(saida);
	}
}