import java.util.Scanner;

class Problem_494 {
	private static String trataString(String texto) {
		String saida = "";
		for (int i = 0; i < texto.length(); i++) {
			char c = texto.charAt(i);
			if (Character.isLetter(c)) {
				saida += c;
			} else {
				saida += " ";
			}
		}
		return saida;
	}

	private static int contaPalavras(String texto) {
		int contador = 0;
		String[] palavras = texto.split(" ");
		for (int i = 0; i < palavras.length; i++) {
			if ((palavras[i].trim()).length() != 0) {
				contador++;
			}
		}
		return contador;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		StringBuffer saida = new StringBuffer();
		String separador = System.getProperty("line.separator");

		while (in.hasNextLine()) {
			saida.append(contaPalavras(trataString(in.nextLine())) + separador);
		}

		System.out.print(saida);
	}
}
