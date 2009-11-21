import java.util.Scanner;

class Problem_10924 {
	public static boolean ehPrimo(int n) {
		for (int num = 2; num <= (n / 2); num++) {
			if (n % num == 0) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String separador = System.getProperty("line.separator");
		StringBuffer saida = new StringBuffer();

		while (in.hasNext()) {
			String linha = in.nextLine();
			int soma = 0;
			for (int i = 0; i < linha.length(); i++) {
				char c = linha.charAt(i);
				if (Character.isLetter(c)) {
					if (Character.isLowerCase(c)) {
						soma += (-96 + c);
					} else {
						soma += (-38 + c);
					}
				}
			}
			if (ehPrimo(soma)) {
				saida.append("It is a prime word.");
			} else {
				saida.append("It is not a prime word.");
			}
			saida.append(separador);
		}

		System.out.print(saida);
	}
}