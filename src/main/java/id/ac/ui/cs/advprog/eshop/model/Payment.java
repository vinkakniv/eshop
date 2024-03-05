package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;

@Getter @Setter
public class Payment {
    protected String id;
    protected PaymentMethod method;
    protected String status;
    protected Map<String, String> paymentData;
    protected Order order;

    public Payment(String id, String method, Map<String, String> paymentData, Order order) {
        this.id = id;
        this.method = validatePaymentMethod(method);
        this.paymentData = paymentData;
        this.status = "PENDING";

        if (order == null){
            throw new IllegalArgumentException();
        }else{
            this.order = order;
        }
    }

    protected void setPaymentData(Map<String, String> paymentData) {
        this.paymentData = paymentData;
    }

    private PaymentMethod validatePaymentMethod(String method) {
        if (!PaymentMethod.contains(method)) {
            throw new IllegalArgumentException();
        }
        return PaymentMethod.valueOf(method);
    }
    
}