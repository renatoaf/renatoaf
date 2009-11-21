import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class Problem_10062 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		boolean primeiro = true;
		while (true) {
			String linha = in.readLine();
			if (linha == null) {
				break;
			}

			if (!primeiro) {
				saida.append(separador);
			}
			primeiro = false;

			char[] letras = linha.toCharArray();
			Arrays.sort(letras);

			ArrayList<Codigo> frequencia = new ArrayList<Codigo>();
			for (int i = 0; i < letras.length; i++) {
				int asc = (int) letras[i];

				Codigo code = new Codigo(asc);
				while (i + 1 < letras.length && letras[i + 1] == asc) {
					code.c++;
					i++;
				}

				frequencia.add(code);
			}

			Collections.sort(frequencia);
			for (Codigo cod : frequencia) {
				saida.append(cod + separador);
			}
		}

		System.out.print(saida);
	}
}

class Codigo implements Comparable<Codigo> {
	int codigo;
	int c;

	public Codigo(int codigo) {
		this.codigo = codigo;
		this.c = 1;
	}

	public int compareTo(Codigo o) {
		int dif = c - o.c;
		if (dif != 0) {
			return dif;
		}
		return o.codigo - codigo;
	}

	public String toString() {
		return codigo + " " + c;
	}
}