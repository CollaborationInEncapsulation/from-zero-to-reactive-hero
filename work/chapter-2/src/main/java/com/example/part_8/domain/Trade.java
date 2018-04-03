package com.example.part_8.domain;

import org.bson.types.ObjectId;

public class Trade {

	private ObjectId id;
	private long     timestamp;
	private float    price;
	private float    amount;
	private String   currency;
	private String   market;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Trade trade = (Trade) o;

		if (timestamp != trade.timestamp) {
			return false;
		}
		if (Float.compare(trade.price, price) != 0) {
			return false;
		}
		if (Float.compare(trade.amount, amount) != 0) {
			return false;
		}
		if (id != null ? !id.equals(trade.id) : trade.id != null) {
			return false;
		}
		if (!currency.equals(trade.currency)) {
			return false;
		}
		return market.equals(trade.market);
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (int) (timestamp ^ (timestamp >>> 32));
		result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
		result = 31 * result + (amount != +0.0f ? Float.floatToIntBits(amount) : 0);
		result = 31 * result + currency.hashCode();
		result = 31 * result + market.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "Trade{" + "id=" + id + ", timestamp=" + timestamp + ", price=" + price + ", amount=" + amount + ", currency='" + currency + '\'' + ", market='" + market + '\'' + '}';
	}
}
