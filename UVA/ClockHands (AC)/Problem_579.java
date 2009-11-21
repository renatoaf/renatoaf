import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Scanner;

class Problem_579 {
	public static double anguloMinutos(double minutos) {
		return (minutos / 5) * 30;
	}

	public static double anguloHora(double hora, double minutos) {
		hora += (minutos / 60);
		double angulo = 30 * hora;
		return (angulo < 360) ? (angulo) : (angulo - 360);
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String separador = System.getProperty("line.separator");
		StringBuffer saida = new StringBuffer();
		DecimalFormat formatador = new DecimalFormat("0.000");
		formatador.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.US));

		String linha;
		while (!(linha = in.nextLine()).equals("0:00")) {
			String[] horario = linha.split(":");
			double hora = Double.parseDouble(horario[0]);
			double minutos = Double.parseDouble(horario[1]);
			double menorAng = Math.abs(anguloHora(hora, minutos)
					- anguloMinutos(minutos));

			saida.append(((menorAng > 180) ? formatador.format(360 - menorAng)
					: formatador.format(menorAng))
					+ separador);
		}

		System.out.print(saida);
	}
}