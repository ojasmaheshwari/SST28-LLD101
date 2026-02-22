import java.util.*;

public class PricingCalculator {

    public static class PricingResult {
        public final double subtotal;
        public final List<Double> lineTotals;

        public PricingResult(double subtotal, List<Double> lineTotals) {
            this.subtotal = subtotal;
            this.lineTotals = lineTotals;
        }
    }

    public PricingResult calculate(Map<String, MenuItem> menu, List<OrderLine> lines) {
        double subtotal = 0.0;
        List<Double> lineTotals = new ArrayList<>();
        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            double lineTotal = item.price * l.qty;
            lineTotals.add(lineTotal);
            subtotal += lineTotal;
        }
        return new PricingResult(subtotal, lineTotals);
    }
}
