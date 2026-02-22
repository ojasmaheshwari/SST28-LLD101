import java.util.*;

public class BillGenerator {

    public static class Bill {
        public final double subtotal;
        public final List<Double> lineTotals;
        public final double taxPct;
        public final double tax;
        public final double discount;
        public final double total;

        public Bill(double subtotal, List<Double> lineTotals, double taxPct,
                double tax, double discount, double total) {
            this.subtotal = subtotal;
            this.lineTotals = lineTotals;
            this.taxPct = taxPct;
            this.tax = tax;
            this.discount = discount;
            this.total = total;
        }
    }

    private final PricingCalculator pricingCalculator = new PricingCalculator();
    private final TaxCalculator taxCalculator = new TaxCalculator();
    private final DiscountCalculator discountCalculator = new DiscountCalculator();

    public Bill generate(String customerType, Map<String, MenuItem> menu, List<OrderLine> lines) {
        PricingCalculator.PricingResult pricing = pricingCalculator.calculate(menu, lines);
        TaxCalculator.TaxResult taxResult = taxCalculator.calculate(customerType, pricing.subtotal);
        DiscountCalculator.DiscountResult discountResult = discountCalculator.calculate(customerType, pricing.subtotal,
                lines.size());

        double total = pricing.subtotal + taxResult.tax - discountResult.discount;

        return new Bill(pricing.subtotal, pricing.lineTotals,
                taxResult.taxPct, taxResult.tax, discountResult.discount, total);
    }
}
