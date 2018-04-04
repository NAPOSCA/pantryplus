package org.wecancodeit.pantryplus;

public class LineItemRequest {

	private long productId;
	private int quantity;

	public LineItemRequest(long productId, int quantity) {
		this.productId = productId;
		this.quantity = quantity;
	}

	public long getProductId() {
		return productId;
	}

	public int getQuantity() {
		return quantity;
	}
}
