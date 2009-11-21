import java.util.ArrayList;
import java.util.Scanner;

public class ROUBA {
	public static final int NoBolo = 1;
	public static final int NoOutro = 2;
	public static final int NoMeu = 3;
	public static final int Nenhum = 4;

	public static int numJ;
	public static int jogadorDaVez;
	public static ArrayList<Integer> areaDescarte;
	public static int[] topoDosJ;
	public static int[] tamanhoDosJ;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (true) {
			int numC = in.nextInt();

			if (numC == 0) {
				break;
			}

			numJ = in.nextInt();
			jogadorDaVez = 0;
			areaDescarte = new ArrayList<Integer>();
			topoDosJ = new int[numJ];
			tamanhoDosJ = new int[numJ];

			for (int i = 0; i < numC; i++) {
				int carta = in.nextInt();
				caso(carta);
			}

			String saida = "0";
			int maior = 0;
			for (int i = 0; i < tamanhoDosJ.length; i++) {
				if (tamanhoDosJ[i] > maior) {
					saida = tamanhoDosJ[i] + " " + (i + 1);
					maior = tamanhoDosJ[i];
				} else if (tamanhoDosJ[i] == maior) {
					saida += " " + (i + 1);
				}
			}
			System.out.println(saida);
		}
	}

	private static void caso(int carta) {
		if (areaDescarte.contains(carta)) {
			areaDescarte.remove(areaDescarte.indexOf(carta));
			topoDosJ[jogadorDaVez] = carta;
			tamanhoDosJ[jogadorDaVez] += 2;
			return;
		}
		for (int i = 0; i < numJ; i++) {
			if (topoDosJ[i] == carta) {
				if (i == jogadorDaVez) {
					topoDosJ[jogadorDaVez] = carta;
					tamanhoDosJ[jogadorDaVez]++;
					return;
				} else {
					tamanhoDosJ[jogadorDaVez] += tamanhoDosJ[i];
					tamanhoDosJ[i] = 0;
					topoDosJ[i] = 0;
					topoDosJ[jogadorDaVez] = carta;
					tamanhoDosJ[jogadorDaVez]++;
					return;
				}
			}
		}
		areaDescarte.add(carta);
		jogadorDaVez = (jogadorDaVez + 1) % numJ;
	}
}