import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

class Problem_10107 {
	static double getMediana(ArrayList<Integer> ordenado) {
		if (ordenado.size() % 2 != 0) {
			return (double) ordenado.get(ordenado.size() / 2);
		}
		int n1 = ordenado.get(ordenado.size() / 2);
		int n2 = ordenado.get((ordenado.size() - 1) / 2);
		return ((double) (n1 + n2)) / 2.0;
	}

	static void insereOrdenado(ArrayList<Integer> lista, int elem) {
		for (int i = 0; i < lista.size(); i++) {
			if (elem < lista.get(i)) {
				lista.add(i, elem);
				return;
			} else if (elem == lista.get(i)) {
				lista.add(i, elem);
				return;
			}
		}
		lista.add(elem);
	}

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(
				System.in)));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		ArrayList<Integer> listaOrdenada = new ArrayList<Integer>();
		while (in.hasNext()) {
			insereOrdenado(listaOrdenada, in.nextInt());
			saida.append((int) getMediana(listaOrdenada) + separador);
		}
		System.out.print(saida);
	}
}