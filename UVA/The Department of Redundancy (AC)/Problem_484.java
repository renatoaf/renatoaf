import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Problem_484 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		ArrayList<Integer> ordem = new ArrayList<Integer>();
		HashMap<Integer, Contador> mapa = new HashMap<Integer, Contador>();
		while (in.hasNext()) {
			int num = in.nextInt();
			Contador cont = mapa.get(num);
			if (cont == null) {
				ordem.add(num);
				mapa.put(num, new Contador());
			} else {
				cont.cont++;
			}
		}

		for (int i = 0; i < ordem.size(); i++) {
			int n = ordem.get(i);
			Contador cont = mapa.get(n);
			saida.append(n + " " + cont.cont + separador);
		}
		System.out.print(saida);
	}
}

class Contador {
	int cont = 1;
}