package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PaymentTest {
    private Payment payment;
    private Order order;
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        Product product2 = new Product();
        product2.setProductId("e2c62328-4a37-4664-83c7-f32db8620155");
        product2.setProductName("Sabun Cap Usep");
        product2.setProductQuantity(1);
        List<Product> products = Arrays.asList(product1, product2);

        order = new Order("1", products, 1708560000L, "Safira Sudrajat");

        paymentData = new HashMap<>();
        paymentData.put("key", "value");

        payment = new Payment("1", "VOUCHER_CODE", paymentData, order);

    }

    @Test
    public void testGetId() {
        assertEquals("1", payment.getId());
    }

    @Test
    public void testGetMethod() {
        assertEquals("VOUCHER_CODE", payment.getMethod().getValue());
    }

    @Test
    public void testGetStatus() {
        assertEquals("PENDING", payment.getStatus());
    }

    @Test
    public void testGetPaymentData() {
        assertEquals(paymentData, payment.getPaymentData());
    }

    @Test
    public void testGetOrder() {
        assertEquals(order, payment.getOrder());
    }

    @Test
    public void testSetPaymentData() {
        paymentData.put("newKey", "newValue");
        payment.setPaymentData(paymentData);
        assertEquals(paymentData, payment.getPaymentData());
    }

    @Test
    public void testPaymentWithNullOrder() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("2", "VOUCHER_CODE", paymentData, null);
        });
    }

    @Test
    public void testInvalidPaymentMethod() {
        paymentData.put("method", "INVALID_METHOD");
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("7", "INVALID_METHOD", paymentData, order);
        });
    }

    @Test
    public void testValidPaymentMethod() {
        Payment validPayment = new Payment("1", "VOUCHER_CODE", paymentData, order);
        assertEquals(PaymentMethod.VOUCHER_CODE, validPayment.getMethod());
    }

}