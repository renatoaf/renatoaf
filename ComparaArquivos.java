import java.io.File;
import java.util.Scanner;

public class ComparaArquivos {
	public static void main(String[] args) throws Exception {
		Scanner arq1 = new Scanner(new File("out.txt"));
		Scanner arq2 = new Scanner(new File("outDeles.txt"));

		int i = 1;
		boolean diferentes = false;

		while (arq1.hasNext() && arq2.hasNext()) {
			String linhaArq1 = arq1.nextLine();
			String linhaArq2 = arq2.nextLine();

			if (!linhaArq1.equals(linhaArq2)) {
				System.out.println("Diferentes na linha: " + i);
				System.out.println("Linha do arq1: " + linhaArq1);
				System.out.println("Linha do arq2: " + linhaArq2);
				System.out.println();
				diferentes = true;
			}
			i++;
		}

		if (diferentes) {
			System.out.println("\nDiferentes");
		} else {
			System.out.println("Iguais");
		}

		if (arq1.hasNext()) {
			System.out.println(">>> Sobrou coisa no arquivo 1... Olha:");
			while (arq1.hasNext()) {
				System.out.println(arq1.nextLine());
			}
		}
		if (arq2.hasNext()) {
			System.out.println(">>> Sobrou coisa no arquivo 2... Olha:");
			while (arq2.hasNext()) {
				System.out.println(arq2.nextLine());
			}
		}

		arq1.close();
		arq2.close();
	}
}