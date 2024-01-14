package com.neferdevelop.catalog.controller;

import com.neferdevelop.catalog.entity.Product;
import com.neferdevelop.catalog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String productPage(Model model){
        model.addAttribute("products", productService.getAllProduct());
        model.addAttribute("product", new Product());
        return "product";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute(value = "product")Product product){
        List<Product> products = productService.getAllProduct();
        int newOrderNumber = products.size() + 1;
        product.setOrder_number(newOrderNumber);
        productService.saveProduct(product);
        productService.updateOrderNumbers();
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable(value = "id")Long id){
        productService.deleteProductByID(id);
        productService.updateOrderNumbers();
        return "redirect:/";
    }
}
