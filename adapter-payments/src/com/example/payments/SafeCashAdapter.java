package com.example.payments;

class SafeCashAdapter implements PaymentGateway {
	private SafeCashClient safeCashClient;

	public SafeCashAdapter(SafeCashClient safeCashClient) {
		this.safeCashClient = safeCashClient;
	}

	public String charge(String customerId, int amountCents) {
		SafeCashPayment payment = safeCashClient.createPayment(amountCents, customerId);
		return payment.confirm();
	}
}
