public class Surveyor {
	public static int area(String direction, int[] length) {
		int n = direction.length();
		int[] pontosX = new int[n];
		int[] pontosY = new int[n];
		int x = 0;
		int y = 0;
		for (int i = 0; i < n; i++) {
			pontosX[i] = x;
			pontosY[i] = y;
			char direcao = direction.charAt(i);

			switch (direcao) {
			case 'N':
				y += length[i];
				break;
			case 'E':
				x -= length[i];
				break;
			case 'W':
				x += length[i];
				break;
			case 'S':
				y -= length[i];
				break;
			}
		}
		return ((int) findArea(pontosX, pontosY));
	}

	public static double findArea(int[] x, int[] y) {
		double r = 0.0;
		int size = x.length;
		for (int i = 0; i < size; i++) {
			int j = (i + 1) % size;
			r += x[i] * y[j] - x[j] * y[i];
		}
		return Math.abs(r) / 2.0;
	}
}