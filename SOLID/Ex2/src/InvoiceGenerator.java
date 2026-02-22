import java.util.*;

public class InvoiceGenerator {
	private int invoiceSeq = 1000;

	private final BillGenerator billGenerator = new BillGenerator();

	public static class Result {
		public String id;
		public String invoiceContent;
	};

	public Result generate(String customerType,
			Map<String, MenuItem> menu, List<OrderLine> lines) {
		String invoiceId = "INV-" + (++invoiceSeq);

		BillGenerator.Bill bill = billGenerator.generate(customerType, menu, lines);

		StringBuilder out = new StringBuilder();
		out.append("Invoice# ").append(invoiceId).append("\n");

		for (int i = 0; i < lines.size(); i++) {
			OrderLine l = lines.get(i);
			MenuItem item = menu.get(l.itemId);
			double lineTotal = bill.lineTotals.get(i);
			out.append(String.format("- %s x%d = %.2f\n", item.name, l.qty, lineTotal));
		}

		out.append(String.format("Subtotal: %.2f\n", bill.subtotal));
		out.append(String.format("Tax(%.0f%%): %.2f\n", bill.taxPct, bill.tax));
		out.append(String.format("Discount: -%.2f\n", bill.discount));
		out.append(String.format("TOTAL: %.2f\n", bill.total));

		Result result = new Result();

		result.id = invoiceId;
		result.invoiceContent = InvoiceFormatter.identityFormat(out.toString());

		return result;
	}
}
