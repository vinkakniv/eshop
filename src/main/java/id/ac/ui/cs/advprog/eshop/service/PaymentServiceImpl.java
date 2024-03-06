package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.BankTransferPayment;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.VoucherPayment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;


@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment addPayment(String method, Map<String, String> paymentData, Order order) {
        UUID uuid = UUID.randomUUID();
        Payment payment;
        if (PaymentMethod.VOUCHER_CODE.name().equals(method)) {
            payment = new VoucherPayment(uuid.toString(), paymentData, order);
        } else if (PaymentMethod.BANK_TRANSFER.name().equals(method)) {
            payment = new BankTransferPayment(uuid.toString(), paymentData, order);
        } else {
            payment = new Payment(uuid.toString(), method, paymentData, order);
        }
        return paymentRepository.save(payment);
    }

    public void setStatus(Payment payment, String status) {
        if (!PaymentStatus.contains(status)) {
            throw new IllegalArgumentException("Invalid status: " + status);
        }
        payment.setStatus(status);
        if (PaymentStatus.SUCCESS.name().equals(status)) {
            payment.getOrder().setStatus("SUCCESS");
        } else if (PaymentStatus.REJECTED.name().equals(status)) {
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

