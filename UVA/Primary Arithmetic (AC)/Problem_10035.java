import java.util.Scanner;

class Problem_10035 {
	public static int[] arrayDeDigitos(int n) {
		char[] charArray = String.valueOf(n).toCharArray();
		int[] arrayDeDigitos = new int[charArray.length];
		for (int i = 0; i < arrayDeDigitos.length; i++) {
			arrayDeDigitos[i] = Integer.valueOf(String
					.valueOf(charArray[arrayDeDigitos.length - i - 1]));
		}
		return arrayDeDigitos;
	}

	public static int contaVaiUns(int n1, int n2) {
		int[] digitos1 = arrayDeDigitos(n1);
		int[] digitos2 = arrayDeDigitos(n2);
		int maiorTamanho = Math.max(digitos1.length, digitos2.length);
		int contador = 0;
		int carryAtual = 0;
		for (int i = 0; i < maiorTamanho; i++) {
			int soma = 0;
			if (i >= digitos1.length) {
				soma = 0 + digitos2[i] + carryAtual;
			} else if (i >= digitos2.length) {
				soma = digitos1[i] + 0 + carryAtual;
			} else {
				soma = digitos1[i] + digitos2[i] + carryAtual;
			}

			if (soma > 9) {
				contador++;
				carryAtual = 1;
			} else {
				carryAtual = 0;
			}
		}
		return contador;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		StringBuffer saida = new StringBuffer();
		String separador = "";
		int a = in.nextInt(), b = in.nextInt();
		while (a != +0 || b != 0) {
			saida.append(separador);
			int vaiUns = contaVaiUns(a, b);
			if (vaiUns == 0) {
				saida.append("No carry operation.");
			} else if (vaiUns == 1) {
				saida.append("1 carry operation.");
			} else {
				saida.append(vaiUns + " carry operations.");
			}
			separador = System.getProperty("line.separator");
			a = in.nextInt();
			b = in.nextInt();
		}
		System.out.println(saida);
	}
}