package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;

public class BankTransferPayment extends Payment {
    public BankTransferPayment(String id, Map<String, String> paymentData, Order order) {
        super(id, "BANK_TRANSFER", paymentData, order);
        setPaymentData(paymentData);
    }

    @Override
    protected void setPaymentData(Map<String, String> paymentData) {
        String bankName = paymentData.get("bankName");
        String referenceCode = paymentData.get("referenceCode");
        if (bankName == null || bankName.isEmpty() || referenceCode == null || referenceCode.isEmpty()) {
            this.status = "REJECTED";
            this.order.setStatus("FAILED");
        } else {
            this.status = "SUCCESS";
            this.order.setStatus("SUCCESS");
            this.paymentData = paymentData;
        }
    }
}