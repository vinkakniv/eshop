package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.Mockito.*;

class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProductPage() {
        Product product = new Product();
        when(productService.create(any(Product.class))).thenReturn(product);

        String view = productController.createProductPage(model);

        verify(model, times(1)).addAttribute(eq("product"), any(Product.class));
        assert(view.equals("createProduct"));
    }

    @Test
    void testCreateProductPost() {
        Product product = new Product();
        when(productService.create(any(Product.class))).thenReturn(product);

        String view = productController.createProductPost(product, model);

        verify(productService, times(1)).create(product);
        assert(view.equals("redirect:list"));
    }

    @Test
    void testProductListPage() {
        List<Product> allProducts = Arrays.asList(new Product(), new Product());
        when(productService.findAll()).thenReturn(allProducts);

        String view = productController.productListPage(model);

        verify(model, times(1)).addAttribute("products", allProducts);
        assert(view.equals("productList"));
    }

    @Test
    void testEditProductPage() {
        Product product = new Product();
        when(productService.findById(anyString())).thenReturn(product);

        String view = productController.editProductPage("1", model);

        verify(model, times(1)).addAttribute("product", product);
        assert(view.equals("editProduct"));
    }

    @Test
    void testEditProductPost() {
        Product product = new Product();
        when(productService.update(any(Product.class))).thenReturn(product);

        String view = productController.editProductPost(product);

        verify(productService, times(1)).update(product);
        assert(view.equals("redirect:/product/list"));
    }

    @Test
    void testDeleteProductPost() {
        Product product = new Product();
        when(productService.findById(anyString())).thenReturn(product);
        doNothing().when(productService).delete(product);

        String view = productController.deleteProductPost("1");

        verify(productService, times(1)).delete(product);
        assert(view.equals("redirect:/product/list"));
    }

    @Test
    void testEditProductPage_ProductDoesNotExist() {
        when(productService.findById(anyString())).thenThrow(NoSuchElementException.class);

        String view = productController.editProductPage("1", model);

        verify(productService, times(1)).findById("1");
        assert(view.equals("redirect:/product/list"));
    }
}
