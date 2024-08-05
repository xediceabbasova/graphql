package com.company.inventoryservice.service;

import com.company.inventoryservice.model.Product;
import com.company.inventoryservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public Product updateStock(int id, int stock) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("product not found with id " + id));
        product.setStock(stock);
        return productRepository.save(product);
    }

    public Product receiveNewShipment(int id, int quantity) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("product not found with id " + id));
        product.setStock(product.getStock() + quantity);
        return productRepository.save(product);
    }

}
