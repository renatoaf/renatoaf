import java.text.DecimalFormat;
import java.util.Scanner;

class E_MirrorClock {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		DecimalFormat df = new DecimalFormat("00");

		int nCasos = Integer.parseInt(in.nextLine().trim());

		for (int i = 0; i < nCasos; i++) {
			String[] horaDele = in.nextLine().split(":");
			int hDele = Integer.parseInt(horaDele[0]);
			int mDele = Integer.parseInt(horaDele[1]);
			if (hDele == 12) {
				hDele = 0;
			}
			if (mDele > 0) {
				hDele++;
			}

			int mReal = (60 - mDele) % 60;
			int hReal = Math.abs((12 - hDele));
			if (hReal == 0) {
				hReal = 12;
			}
			System.out.println(df.format(hReal) + ":" + df.format(mReal));
		}
	}
}
