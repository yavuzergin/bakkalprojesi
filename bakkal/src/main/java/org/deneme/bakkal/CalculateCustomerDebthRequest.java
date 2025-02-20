package org.deneme.bakkal;

public class CalculateCustomerDebthRequest {
    private long customerId;

    private double totalDebth;

    public CalculateCustomerDebthRequest (long customerId, double totalDebth) {
        this.customerId = customerId;
        this.totalDebth = totalDebth;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public double getTotalDebth() {
        return totalDebth;
    }

    public void setTotalDebth(double totalDebth) {
        this.totalDebth = totalDebth;
    }
}
