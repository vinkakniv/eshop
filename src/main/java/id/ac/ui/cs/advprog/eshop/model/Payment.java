package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter @Setter
public class Payment {
    protected String id;
    protected String method;
    protected String status;
    protected Map<String, String> paymentData;
    protected Order order;

    public Payment(String id, String method, Map<String, String> paymentData, Order order) {
        this.id = id;
        this.method = method;
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
}