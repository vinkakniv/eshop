package id.ac.ui.cs.advprog.eshop.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomePageTest {

    @Mock
    Model model;

    @Test
    public void testCreateHomePage() {
        HomePage homePage = new HomePage();
        String view = homePage.createHomePage(model);
        assertEquals("homePage", view);
    }
}
