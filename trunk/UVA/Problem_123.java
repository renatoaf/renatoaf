/*
 * Searching Quickly
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.TreeSet;

class Problem_123 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();

		ArrayList<String> palavrasIgnoradas = new ArrayList<String>();
		TreeSet<PalavraChave> palavrasChave = new TreeSet<PalavraChave>();

		String linha;
		while (!(linha = in.readLine()).equals("::")) {
			palavrasIgnoradas.add(linha.trim());
		}

		while (true) {
			linha = in.readLine();
			if (linha == null) {
				break;
			}

			String[] palavras = linha.split("\\s+");

			for (int i = 0; i < palavras.length; i++) {

				if (!palavrasIgnoradas.contains(palavras[i].toLowerCase())) {
					PalavraChave p = new PalavraChave(palavras[i]);
					if (!palavrasChave.contains(p)) {
						palavrasChave.add(p);
					} else {
						for (PalavraChave palavra : palavrasChave) {
							if (palavra.equals(p)) {
								p = palavra;
							}
						}
					}

					if (!p.titulos.contains(linha)) {
						p.titulos.add(linha);
					}
				}
			}
		}

		for (PalavraChave keyWord : palavrasChave) {
			saida.append(keyWord);
		}

		System.out.print(saida);
	}
}

class PalavraChave implements Comparable<PalavraChave> {
	String palavra;
	ArrayList<String> titulos;

	public PalavraChave(String palavra) {
		this.palavra = palavra;
		this.titulos = new ArrayList<String>();
	}

	public int compareTo(PalavraChave outra) {
		return palavra.compareToIgnoreCase(outra.palavra);
	}

	public boolean equals(Object o) {
		if (!(o instanceof PalavraChave)) {
			return false;
		}
		PalavraChave p = (PalavraChave) o;
		return p.palavra.equals(palavra);
	}

	public String toString() {
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");
		for (String titulo : titulos) {
			String pChave = palavra.toLowerCase();

			int palavraMarcada = -1;
			while (true) {
				boolean marcou = false;
				String[] palavras = titulo.toLowerCase().split("\\s+");
				String tit = "";

				for (int i = 0; i < palavras.length; i++) {
					if (i > palavraMarcada && !marcou
							&& palavras[i].equalsIgnoreCase(pChave)) {
						palavras[i] = pChave.toUpperCase();
						palavraMarcada = i;
						marcou = true;
					}

					tit += palavras[i];
					if (i != palavras.length - 1) {
						tit += " ";
					}
				}

				if (!marcou) {
					break;
				}

				saida.append(tit + separador);
			}
		}

		return saida.toString();
	}
}