package com.neferdevelop.catalog.service;

import com.neferdevelop.catalog.entity.Product;
import com.neferdevelop.catalog.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    ProductRepository productRepository;

    @Autowired
    public void setProductRepositories(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }
    public void saveProduct(Product product){
        productRepository.save(product);
    }
    public void deleteProductByID(Long id){productRepository.deleteById(id);}

    public void updateOrderNumbers() {
        List<Product> products = productRepository.findAll();
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            product.setOrder_number(i + 1);
            productRepository.save(product);
        }
    }
}
