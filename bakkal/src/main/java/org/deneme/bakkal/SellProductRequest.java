package org.deneme.bakkal;

public class SellProductRequest {
    private long customerId;
    private long productId;
    private int quantity;

    public SellProductRequest(Long customerId, long productId, int count) {
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

