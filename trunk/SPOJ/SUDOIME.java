import java.util.Scanner;

public class SUDOIME {
	static int[][] sudoku = new int[9][9];

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		StringBuilder out = new StringBuilder();
		int n = in.nextInt();
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < sudoku.length; j++) {
				for (int j2 = 0; j2 < sudoku.length; j2++) {
					sudoku[j][j2] = in.nextInt();
				}
			}
			if (isValid()) {
				out.append("Instancia " + i + "\nSIM\n\n");
			} else {
				out.append("Instancia " + i + "\nNAO\n\n");
			}

		}
		System.out.print(out);
	}

	private static boolean isValid() {
		for (int i = 0; i < 9; i++) {
			boolean[] valid = new boolean[10];
			for (int j = 0; j < 9; j++) {
				valid[sudoku[i][j]] = true;
			}
			for (int j = 1; j <= 9; j++)
				if (!valid[j])
					return false;
		}

		for (int i = 0; i < 9; i++) {
			boolean[] valid = new boolean[10];
			for (int j = 0; j < 9; j++) {
				valid[sudoku[j][i]] = true;
			}
			for (int j = 1; j <= 9; j++)
				if (!valid[j])
					return false;
		}

		for (int i = 0; i < 9; i += 3) {
			for (int j = 0; j < 9; j += 3) {
				boolean[] valid = new boolean[10];
				for (int k = 0; k < 3; k++) {
					for (int l = 0; l < 3; l++) {
						valid[sudoku[i + k][j + l]] = true;
					}
				}
				for (int k = 1; k <= 9; k++)
					if (!valid[k])
						return false;
			}
		}
		return true;
	}
}