package furymachinery.api.util;

import java.text.NumberFormat;

public class NumberUtil {
	public static String getFormattedEnergy(double energy) {
		if (energy < 10000d) {
			int e0 = (int) energy;
			return new String("§a" + String.valueOf(e0) + " §1 FE");
		} else if (energy < 1000000d) {
			double e0 = energy / 1000d;
			NumberFormat nf = NumberFormat.getInstance();
			nf.setMaximumFractionDigits(1);
			String e1 = nf.format(e0);
			return new String("§a" + e1 + " §1 kFE");
		} else if (energy < 1000000000d) {
			double e0 = energy / 1000000d;
			NumberFormat nf = NumberFormat.getInstance();
			nf.setMaximumFractionDigits(1);
			String e1 = nf.format(e0);
			return new String("§a" + e1 + " §1 MFE");
		} else if (energy < 1000000000000d) {
			double e0 = energy / 1000000000d;
			NumberFormat nf = NumberFormat.getInstance();
			nf.setMaximumFractionDigits(1);
			String e1 = nf.format(e0);
			return new String("§a" + e1 + " §1 GFE");
		} else if (energy < 1000000000000000d){
			double e0 = energy / 1000000000000d;
			NumberFormat nf = NumberFormat.getInstance();
			nf.setMaximumFractionDigits(1);
			String e1 = nf.format(e0);
			return new String("§a" + e1 + " §1 TFE");
		}
		return "out of range";
	}
}
