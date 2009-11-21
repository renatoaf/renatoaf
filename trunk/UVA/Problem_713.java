/*
 * Adding Reversed Numbers
 */

import java.math.BigInteger;
import java.util.Scanner;

class Problem_713 {
	public static BigInteger inverteNumero(BigInteger numero) {
		StringBuffer s = new StringBuffer();
		s.append(numero);
		return new BigInteger(s.reverse().toString());
	}

	public static void main(String[] args) {
		StringBuffer saida = new StringBuffer();
		Scanner in = new Scanner(System.in);
		String separador = "";
		int n = in.nextInt();

		for (int i = 0; i < n; i++) {
			saida.append(separador);
			BigInteger um = in.nextBigInteger();
			BigInteger dois = in.nextBigInteger();
			BigInteger soma = inverteNumero(um).add(inverteNumero(dois));
			saida.append(inverteNumero(soma));
			separador = System.getProperty("line.separator");
		}

		System.out.println(saida);
	}
}