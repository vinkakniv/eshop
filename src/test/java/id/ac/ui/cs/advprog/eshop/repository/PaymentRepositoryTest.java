package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    List<Payment> payments;

    @BeforeEach
    void setUp(){

        List <Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                products, 1708560000L, "Safira Sudrajat");
        Order order3 = new Order("e334ef40-9eff-4da8-9487-8ee697ecbf1e",
                products, 1708570000L, "Bambang Sudrajat");

        paymentRepository = new PaymentRepository();

        payments = new ArrayList<>();

        Map<String, String> bankTransferData = new HashMap<>();
        Map<String, String> voucherCodeData = new HashMap<>();

        bankTransferData.put("address", "Jalan Pacil");
        bankTransferData.put("deliveryFee", "17000");
        voucherCodeData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment1 = new Payment("11111111-1111-1111-1111-111111111111", "VOUCHER_CODE", voucherCodeData, order1);
        Payment payment2 = new Payment("22222222-2222-2222-2222-222222222222", "BANK_TRANSFER", bankTransferData, order3);
        payments.add(payment1);
        payments.add(payment2);
    }

    @Test
    void testSaveCreate(){
        Payment payment = payments.getFirst();
        Payment result = paymentRepository.save(payment);

        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getMethod(), result.getMethod());
        assertSame(payment.getPaymentData(), result.getPaymentData());
        assertSame(payment.getOrder(), result.getOrder());
        assertEquals(payment.getStatus(), result.getStatus());
    }

    @Test
    void testSaveUpdate() {
        List <Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);
        Order order3 = new Order("e334ef40-9eff-4da8-9487-8ee697ecbf1e",
                products, 1708570000L, "Bambang Sudrajat");

        Payment payment = payments.getFirst();
        // edit the order
        Payment newPayment = new Payment("11111111-1111-1111-1111-111111111111", "VOUCHER_CODE", payment.getPaymentData(), order3);

        Payment result = paymentRepository.save(newPayment);

        assertEquals(newPayment.getId(), result.getId());
        assertEquals(newPayment.getPaymentData(), result.getPaymentData());
        assertSame(newPayment.getOrder(), result.getOrder());
        assertEquals(newPayment.getMethod(), result.getMethod());
        assertEquals(newPayment.getStatus(), result.getStatus());
    }

    @Test
    void testGetPaymentFound(){
        Payment payment = payments.getFirst();
        paymentRepository.save(payment);

        Payment result = paymentRepository.getPayment(payment.getId());

        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getMethod(), result.getMethod());
        assertSame(payment.getPaymentData(), result.getPaymentData());
        assertSame(payment.getOrder(), result.getOrder());
        assertEquals(payment.getStatus(), result.getStatus());
    }


    @Test
    void testGetPaymentNotFound(){
        assertNull(paymentRepository.getPayment("pacilcilpa"));
    }

    @Test
    void testGetAllPayments(){
        for (Payment payment : payments){
            paymentRepository.save(payment);
        }
        List<Payment> result = paymentRepository.getAllPayments();
        assertEquals(2, result.size());
    }

    @Test
    void testSavePaymentWithSameId(){
        Payment payment = payments.getFirst();

        Payment payment1 = new Payment("11111111-1111-1111", "VOUCHER_CODE", payment.getPaymentData(), payment.getOrder());
        Payment payment2 = new Payment("11111111-1111-1111", "VOUCHER_CODE",payment.getPaymentData(), payment.getOrder());

        paymentRepository.save(payment1);
        Payment result = paymentRepository.save(payment2);

        assertEquals(payment2.getId(), result.getId());
        assertEquals(payment2.getMethod(), result.getMethod());
        assertSame(payment2.getPaymentData(), result.getPaymentData());
        assertSame(payment.getOrder(), result.getOrder());
        assertEquals(payment.getStatus(), result.getStatus());
    }
}