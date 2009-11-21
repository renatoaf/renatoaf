import java.util.LinkedList;
import java.util.Scanner;

public class registrador {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		StringBuilder saida = new StringBuilder();

		while (true) {
			int nBits = Integer.parseInt(in.next().trim());
			int nTorn = Integer.parseInt(in.next().trim());

			if (nBits == 0 && nTorn == 0) {
				break;
			}

			int[] torns = new int[nTorn];
			for (int i = 0; i < nTorn; i++) {
				torns[i] = Integer.parseInt(in.next().trim());
			}

			LinkedList<Boolean> init = praBin(in.next().trim(), nBits);
			LinkedList<Boolean> fim = praBin(in.next().trim(), nBits);
			LinkedList<Boolean> atual = new LinkedList<Boolean>(init);

			int numClock = 0;
			boolean imps = false;

			while (!atual.equals(fim)) {
				atual = proxClock(atual, torns);
				if (atual.equals(init)) {
					imps = true;
					break;
				}
				numClock++;
			}

			if (imps) {
				saida.append("*\n");
			} else {
				saida.append(numClock + "\n");
			}
		}

		System.out.print(saida);
	}

	private static LinkedList<Boolean> proxClock(LinkedList<Boolean> atual,
			int[] line2) {
		boolean res = atual.getFirst();
		for (int i = 1; i < line2.length; i++) {
			res = res ^ atual.get(line2[i]);
		}
		atual.addLast(res);
		atual.pollFirst();
		return atual;
	}

	private static LinkedList<Boolean> praBin(String hexa, int nBits) {
		String bin = Integer.toBinaryString(Integer.parseInt(hexa, 16));
		LinkedList<Boolean> binario = new LinkedList<Boolean>();
		while (bin.length() < nBits) {
			bin = "0" + bin;
		}
		for (int i = 0; i < nBits; i++) {
			binario.addLast(bin.charAt(bin.length() - i - 1) == '1');
		}
		return binario;
	}
}