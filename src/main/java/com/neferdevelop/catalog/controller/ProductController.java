package com.neferdevelop.catalog.controller;

import com.neferdevelop.catalog.entity.Product;
import com.neferdevelop.catalog.entity.User;
import com.neferdevelop.catalog.service.ProductService;
import com.neferdevelop.catalog.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
@Controller
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private ProductService productService;

    private UserService userService;

    @Autowired
    public ProductController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String productPage(Model model, Principal principal){
        logger.info("Username{}", principal.getName());
        model.addAttribute("products", productService.findByUsername(principal.getName()));
        model.addAttribute("product", new Product());
        return "product";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute(value = "product")Product product, Principal principal){
        logger.info("Username{}", principal.getName());
        User user = userService.findByUsername(principal.getName()).get();
        product.setUser(user);

        List<Product> products = productService.getAllProduct();
        int newOrderNumber = products.size() + 1;
        product.setOrder_number(newOrderNumber);
        productService.saveProduct(product);
        productService.updateOrderNumbers();
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showEditProductPage(Model model, @PathVariable(value = "id")Long id){
        Optional<Product> product = productService.getProductByID(id);
        model.addAttribute("product",product);
        return "product-edit";
    }
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable(value = "id")Long id){
        productService.deleteProductByID(id);
        productService.updateOrderNumbers();
        return "redirect:/";
    }


}