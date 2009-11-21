/*
 * Longest Nap
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

class Problem_10191 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder saida = new StringBuilder();
		String separador = System.getProperty("line.separator");

		int dia = 1;
		while (true) {
			String nTarefas = in.readLine();
			if (nTarefas == null) {
				break;
			}
			int n = Integer.parseInt(nTarefas);

			if (n == 0) {
				saida
						.append("Day #"
								+ dia++
								+ ": the longest nap starts at 10:00 and will last for 8 hours and 0 minutes."
								+ separador);
				continue;
			}

			ArrayList<Tarefa> tarefas = new ArrayList<Tarefa>();
			for (int i = 0; i < n; i++) {
				String[] tarefa = in.readLine().split("\\s+");
				String[] config1 = tarefa[0].split(":");
				String[] config2 = tarefa[1].split(":");
				int h1 = Integer.parseInt(config1[0]);
				int m1 = Integer.parseInt(config1[1]);
				int h2 = Integer.parseInt(config2[0]);
				int m2 = Integer.parseInt(config2[1]);
				tarefas.add(new Tarefa(h1, m1, h2, m2));
			}

			Collections.sort(tarefas);

			int maiorIntervalo = tarefas.get(0).minutosInicio() - (10 * 60);
			int tarefaFinal = -1;

			for (int i = 0; i < tarefas.size() - 1; i++) {
				int intervalo = tarefas.get(i + 1).minutosInicio()
						- tarefas.get(i).minutosFim();
				if (intervalo > maiorIntervalo) {
					maiorIntervalo = intervalo;
					tarefaFinal = i;
				}
			}

			int intervaloFinal = (18 * 60)
					- tarefas.get(tarefas.size() - 1).minutosFim();
			if (intervaloFinal > maiorIntervalo) {
				maiorIntervalo = intervaloFinal;
				tarefaFinal = tarefas.size() - 1;
			}

			int horasDeIntervalo = 0;
			int minutosDeIntervalo = 0;
			while (maiorIntervalo >= 60) {
				horasDeIntervalo++;
				maiorIntervalo -= 60;
			}
			minutosDeIntervalo = maiorIntervalo;

			Tarefa fim = (tarefaFinal != -1) ? tarefas.get(tarefaFinal)
					: new Tarefa(10, 0, 10, 0);
			saida.append("Day #" + dia + ": the longest nap starts at " + fim
					+ " and will last for ");

			if (horasDeIntervalo == 0) {
				saida.append(minutosDeIntervalo + " minutes.");
			} else {
				saida.append(horasDeIntervalo + " hours and "
						+ minutosDeIntervalo + " minutes.");
			}

			saida.append(separador);
			dia++;
		}

		System.out.print(saida);
	}
}

class Tarefa implements Comparable<Tarefa> {
	private DecimalFormat format = new DecimalFormat("00");
	private int horasInicio;
	private int minutosInicio;
	private int horasFim;
	private int minutosFim;

	public Tarefa(int h1, int m1, int h2, int m2) {
		this.horasInicio = h1;
		this.minutosInicio = m1;
		this.horasFim = h2;
		this.minutosFim = m2;
	}

	public int minutosInicio() {
		return (horasInicio * 60) + minutosInicio;
	}

	public int minutosFim() {
		return (horasFim * 60) + minutosFim;
	}

	public int compareTo(Tarefa o) {
		return this.minutosInicio() - o.minutosInicio();
	}

	public String toString() {
		return format.format(horasFim) + ":" + format.format(minutosFim);
	}
}