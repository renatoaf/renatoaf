/*
 * Pascal's Triangle of Death
 */

import java.math.BigInteger;

class Problem_485 {
	public static void main(String[] args) {
		double fim = Math.pow(10, 60);

		StringBuilder saida = new StringBuilder();
		BigInteger n = BigInteger.ONE;
		BigInteger[] linha = { n };

		boolean acabou = false;
		while (!acabou) {
			String separador = "";
			BigInteger[] novaLinha = new BigInteger[linha.length + 1];

			for (int i = 0; i < novaLinha.length; i++) {
				BigInteger n1 = (i < linha.length) ? linha[i] : BigInteger.ZERO;
				BigInteger n2 = (i - 1 >= 0 && i - 1 < linha.length) ? linha[i - 1]
						: BigInteger.ZERO;

				novaLinha[i] = n1.add(n2);

				if (!acabou && novaLinha[i].doubleValue() >= fim) {
					acabou = true;
				}

				if (i != novaLinha.length - 1) {
					saida.append(separador + linha[i]);
					separador = " ";
				}
			}

			saida.append("\n");
			linha = novaLinha;
		}

		// linha final
		for (int i = 0; i < linha.length; i++) {
			saida.append(linha[i]);
			if (i != linha.length - 1) {
				saida.append(" ");
			}
		}

		System.out.print(saida + "\n");
	}
}