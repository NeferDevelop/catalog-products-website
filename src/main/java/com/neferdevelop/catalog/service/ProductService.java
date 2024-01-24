package com.neferdevelop.catalog.service;

import com.neferdevelop.catalog.entity.Product;
import com.neferdevelop.catalog.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Product> getProductByID(Long id){
        return productRepository.findById(id);
    }


    public void saveOrUpdateProduct(Product product){
        Optional<Product> existingProduct = productRepository.findById(product.getId());
        if (existingProduct.isPresent()) {
            Product oldProduct = existingProduct.get();
            oldProduct.setName(product.getName());
            oldProduct.setPrice(product.getPrice());

            productRepository.save(oldProduct);
        } else {
            productRepository.save(product);
        }

        updateOrderNumbers();

    }
    public void updateOrderNumbers() {
        List<Product> products = productRepository.findAll();
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            product.setOrder_number(i + 1);
            productRepository.save(product);
        }
    }

    public List<Product> findByUsername(String username) {
        return productRepository.findByUserUsername(username);
    }
}
