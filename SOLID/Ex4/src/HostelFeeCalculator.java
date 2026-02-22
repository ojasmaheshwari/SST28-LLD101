import java.util.*;

public class HostelFeeCalculator {
	private final FakeBookingRepo repo;
	private final List<MoneyCalculator> moneyCalculators = new ArrayList<>();

	public HostelFeeCalculator(FakeBookingRepo repo) {
		this.repo = repo;

		// add all calculators here
		moneyCalculators.add(new RoomPricingCalculator());
		moneyCalculators.add(new AddOnCalculator());
	}

	// OCP violation: switch + add-on branching + printing + persistence.
	public void process(BookingRequest req) {
		Money monthly = calculateMonthly(req);
		Money deposit = new Money(5000.00);

		ReceiptPrinter.print(req, monthly, deposit);

		String bookingId = "H-" + (7000 + new Random(1).nextInt(1000)); // deterministic-ish
		repo.save(bookingId, req, monthly, deposit);
	}

	private Money calculateMonthly(BookingRequest req) {
		Money totalMoney = new Money(0);

		for (MoneyCalculator mc : moneyCalculators) {
			totalMoney = totalMoney.plus(mc.calculate(req));
		}

		return totalMoney;
	}
}
