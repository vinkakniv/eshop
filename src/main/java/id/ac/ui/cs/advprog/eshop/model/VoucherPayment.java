package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;

public class VoucherPayment extends Payment {
    public VoucherPayment(String id, Map<String, String> paymentData, Order order) {
        super(id, "VOUCHER_CODE", paymentData, order);
        setPaymentData(paymentData);
    }

    @Override
    protected void setPaymentData(Map<String, String> paymentData) {
        String voucherCode = paymentData.get("voucherCode");
        int numOfNumerics = 0;
        for (char c : voucherCode.toCharArray()) {
            if (Character.isDigit(c)) {
                numOfNumerics++;
            }
        }
        if (voucherCode.length() != 16 || !voucherCode.startsWith("ESHOP") || numOfNumerics != 8) {
            this.setStatus("REJECTED");
            this.order.setStatus("FAILED");
        } else {
            this.setStatus("SUCCESS");
            this.order.setStatus("SUCCESS");
            this.paymentData = paymentData;
        }
    }
}
