import java.util.*;

public class CafeteriaSystem {
	private final Map<String, MenuItem> menu = new LinkedHashMap<>();
	private final FileStore store = new FileStore();
	private final InvoiceGenerator invoiceGenerator = new InvoiceGenerator();

	public void addToMenu(MenuItem i) {
		menu.put(i.id, i);
	}

	public void checkout(String customerType, List<OrderLine> lines) {
		InvoiceGenerator.Result result = invoiceGenerator.generate(customerType, menu, lines);

		String invId = result.id;
		String printable = result.invoiceContent;

		System.out.print(printable);

		store.save(invId, printable);
		System.out.println("Saved invoice: " + invId + " (lines=" + store.countLines(invId) + ")");
	}
}
