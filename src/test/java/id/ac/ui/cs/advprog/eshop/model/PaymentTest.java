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
        Payment validPayment = new Payment("9", "VOUCHER_CODE", paymentData, order);
        assertEquals(PaymentMethod.VOUCHER_CODE, validPayment.getMethod());
    }

    @Test
    public void testValidVoucherPayment() {
        Map<String, String> paymentData2 = new HashMap<>();
        paymentData2.put("voucherCode", "ESHOP12345678ABC");
        VoucherPayment voucherPay = new VoucherPayment("190", paymentData2, order);

        assertEquals("SUCCESS", voucherPay.getStatus());
        assertEquals("SUCCESS", order.getStatus());
    }

    @Test
    public void testInvalidVoucherPayment() {
        Map<String, String> paymentData3 = new HashMap<>();
        paymentData3.put("voucherCode", "INVALID");
        VoucherPayment voucherPay = new VoucherPayment("18888", paymentData3, order);

        assertEquals("REJECTED", voucherPay.getStatus());
        assertEquals("FAILED", order.getStatus());
    }

    @Test
    public void testInvalidVoucherPaymentLength() {
        Map<String, String> paymentData4 = new HashMap<>();
        paymentData4.put("voucherCode", "ESHOP12345678AB"); // 15 characters
        VoucherPayment payment = new VoucherPayment("17", paymentData4, order);

        assertEquals("REJECTED", payment.getStatus());
        assertEquals("FAILED", order.getStatus());
    }

    @Test
    public void testInvalidVoucherPaymentPrefix() {
        Map<String, String> paymentData5 = new HashMap<>();
        paymentData5.put("voucherCode", "SHOP12345678ABCD"); // Does not start with "ESHOP"
        VoucherPayment payment = new VoucherPayment("4", paymentData5, order);

        assertEquals("REJECTED", payment.getStatus());
        assertEquals("FAILED", order.getStatus());
    }

    @Test
    public void testInvalidVoucherPaymentNum() {
        Map<String, String> paymentData6 = new HashMap<>();
        paymentData6.put("voucherCode", "ESHOP1234567ABCD"); // 7 numerical characters
        VoucherPayment payment = new VoucherPayment("27", paymentData6, order);

        assertEquals("REJECTED", payment.getStatus());
        assertEquals("FAILED", order.getStatus());
    }

}