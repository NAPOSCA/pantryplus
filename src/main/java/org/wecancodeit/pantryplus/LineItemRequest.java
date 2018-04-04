package org.wecancodeit.pantryplus;

public class LineItemRequest {

	private long id;
	private int quantity;

	public LineItemRequest(long id, int quantity) {
		this.id = id;
		this.quantity = quantity;
	}

	public long getId() {
		return id;
	}

	public int getQuantity() {
		return quantity;
	}
}
