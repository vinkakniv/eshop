package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment addPayment(String method, Map<String, String> paymentData, Order order) {
        UUID uuid = UUID.randomUUID();
        Payment payment = new Payment(uuid.toString(), method, paymentData, order);
        return paymentRepository.save(payment);
    }

    public void setStatus(Payment payment, String status) {
        if (!Arrays.asList("SUCCESS", "REJECTED", "PENDING").contains(status)) {
            throw new IllegalArgumentException("Invalid status: " + status);
        }
        
        payment.setStatus(status);
        if ("SUCCESS".equals(status)) {
            payment.getOrder().setStatus("SUCCESS");
        } else if ("REJECTED".equals(status)) {
            payment.getOrder().setStatus("FAILED");
        }
    }

    public Payment getPayment(String paymentId) {
        return paymentRepository.getPayment(paymentId);
    }

    public List<Payment> getAllPayment() {
        return paymentRepository.getAllPayments();
    }

}

