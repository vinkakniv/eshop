package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Mock
    private PaymentRepository paymentRepository;

    Order order1;
    Payment payment;
    Map<String, String> paymentData;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                products, 1708560000L, "Safira Sudrajat");

        paymentData = new HashMap<>();

        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        payment = new Payment("1", "VOUCHER_CODE", paymentData, order1);

    }

    @Test
    public void testAddPayment() {
        Payment payment2 = new Payment("11111", "VOUCHER_CODE", paymentData, order1);
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment2);

        Payment result = paymentService.addPayment("VOUCHER_CODE", paymentData, order1);
        result.setId("11111");

        assertEquals(payment2.getId(), result.getId());
        assertEquals(payment2.getMethod(), result.getMethod());
        assertEquals(payment2.getPaymentData(), result.getPaymentData());
        assertEquals(payment2.getOrder(), result.getOrder());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    public void testSetStatusValid() {
        paymentService.setStatus(payment, "REJECTED");

        assertEquals("REJECTED", payment.getStatus());
        assertEquals("FAILED", payment.getOrder().getStatus());

        paymentService.setStatus(payment, "SUCCESS");

        assertEquals("SUCCESS", payment.getStatus());
        assertEquals("SUCCESS", payment.getOrder().getStatus());

    }

    @Test
    void testSetStatusInvalid(){
        assertThrows(IllegalArgumentException.class, ()->
            paymentService.setStatus(payment, "stress")
        );
    }

    @Test
    public void testGetPaymentFound() {

        when(paymentRepository.getPayment(anyString())).thenReturn(payment);

        Payment result = paymentService.getPayment(payment.getId());

        assertEquals(payment, result);
        verify(paymentRepository, times(1)).getPayment(anyString());
    }

    @Test
    void testGetPaymentNotFound(){
        assertNull(paymentService.getPayment("pacilcilpa"));
    }

    @Test
    public void testGetAllPayment() {

        when(paymentRepository.getAllPayments()).thenReturn(Collections.singletonList(payment));

        assertEquals(1, paymentService.getAllPayment().size());
        verify(paymentRepository, times(1)).getAllPayments();
    }
}
