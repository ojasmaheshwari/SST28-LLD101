package com.example.payments;

class FastPayAdapter implements PaymentGateway {
	private FastPayClient fastPayClient;

	public FastPayAdapter(FastPayClient fastPayClient) {
		this.fastPayClient = fastPayClient;
	}

	public String charge(String customerId, int amountCents) {
		return fastPayClient.payNow(customerId, amountCents);
	}
}
