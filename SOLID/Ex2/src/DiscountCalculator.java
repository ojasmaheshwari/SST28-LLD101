public class DiscountCalculator {

    public static class DiscountResult {
        public final double discount;

        public DiscountResult(double discount) {
            this.discount = discount;
        }
    }

    public DiscountResult calculate(String customerType, double subtotal, int distinctLines) {
        double discount = DiscountRules.discountAmount(customerType, subtotal, distinctLines);
        return new DiscountResult(discount);
    }
}
