package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {
    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("e0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());

    }

    @Test
    void testUpdateProductExists() {
        Product product = new Product();
        product.setProductId("existing-id");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product newProduct = new Product();
        newProduct.setProductId(product.getProductId());
        newProduct.setProductName("Sampo Cap Bambang Baru");
        newProduct.setProductQuantity(200);

        Product updatedProduct = productRepository.update(newProduct);

        assertNotNull(updatedProduct);
        assertEquals(newProduct.getProductId(), updatedProduct.getProductId());
        assertEquals(newProduct.getProductName(), updatedProduct.getProductName());
        assertEquals(newProduct.getProductQuantity(), updatedProduct.getProductQuantity());
    }

    @Test
    void testUpdateProductDoesNotExist() {
        Product newProduct = new Product();
        newProduct.setProductId("non-existing-id");
        newProduct.setProductName("Sampo Cap Bambang Baru");
        newProduct.setProductQuantity(200);

        Product updatedProduct = productRepository.update(newProduct);

        assertNull(updatedProduct);
    }

    @Test
    void testUpdateNewProductIdIsNull() {
        Product product = new Product();
        product.setProductId("existing-id");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product newProduct = new Product();
        newProduct.setProductId(null);
        newProduct.setProductName("Sampo Cap Bambang Baru");
        newProduct.setProductQuantity(200);

        Product updatedProduct = productRepository.update(newProduct);

        assertNull(updatedProduct);
    }

    @Test
    void testUpdateExistingProductIdIsNull() {
        Product product = new Product();
        product.setProductId(null);
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product newProduct = new Product();
        newProduct.setProductId("existing-id");
        newProduct.setProductName("Sampo Cap Bambang Baru");
        newProduct.setProductQuantity(200);

        Product updatedProduct = productRepository.update(newProduct);

        assertNull(updatedProduct);
    }
    @Test
    void testDeleteProductPositive() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        productRepository.delete(product);
        Product deletedProduct = productRepository.findById(product.getProductId());

        assertNull(deletedProduct);
    }

    @Test
    void testDeleteProductNegative() {
        Product product = new Product();
        product.setProductId("non-existing-id");

        assertDoesNotThrow(() -> productRepository.delete(product));
        Product result = productRepository.findById(product.getProductId());

        assertNull(result);
    }

    @Test
    void testFindByIdProductExists() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product foundProduct = productRepository.findById(product.getProductId());

        assertNotNull(foundProduct);
        assertEquals(product.getProductId(), foundProduct.getProductId());
        assertEquals(product.getProductName(), foundProduct.getProductName());
        assertEquals(product.getProductQuantity(), foundProduct.getProductQuantity());
    }

    @Test
    void testFindByIdProductDoesNotExist() {
        Product product = new Product();
        product.setProductId("existing-id");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product foundProduct = productRepository.findById("non-existing-id");

        assertNull(foundProduct);
    }

    @Test
    void testFindByIdIdIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            productRepository.findById(null);
        });
    }

}