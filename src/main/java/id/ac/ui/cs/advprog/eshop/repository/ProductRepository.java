package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository implements ProductRepositoryInterface {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        product.setProductId(UUID.randomUUID().toString());
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product findById(String id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        for (Product product : productData) {
            if (product.getProductId().equals(id)) {
                return product;
            }
        }
        return null;
    }
    public Product update(Product newProduct) {
        for (int i = 0; i < productData.size(); i++) {
            Product product = productData.get(i);
            if (product.getProductId().equals(newProduct.getProductId())) {
                productData.set(i, newProduct);
                return newProduct;
            }
        }
        return null;
    }

    public void delete(Product deletedProduct) {
        String id = deletedProduct.getProductId();
        productData.removeIf(product -> product.getProductId().equals(id));
    }

}