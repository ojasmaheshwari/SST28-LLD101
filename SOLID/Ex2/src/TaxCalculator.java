public class TaxCalculator {

    public static class TaxResult {
        public final double taxPct;
        public final double tax;

        public TaxResult(double taxPct, double tax) {
            this.taxPct = taxPct;
            this.tax = tax;
        }
    }

    public TaxResult calculate(String customerType, double subtotal) {
        double taxPct = TaxRules.taxPercent(customerType);
        double tax = subtotal * (taxPct / 100.0);
        return new TaxResult(taxPct, tax);
    }
}
