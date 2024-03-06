package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;

import java.util.List;
import java.util.Map;

public interface PaymentService {
    Payment addPayment(String method, Map<String, String> paymentData, Order order);
    List<Payment> getAllPayment();
    void setStatus(Payment payment, String status);
    Payment getPayment(String id);
}