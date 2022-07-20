package com.project.service;

import com.project.dto.ProductDto;
import com.project.entity.Category;
import com.project.entity.Product;
import com.project.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductDto productDto, Category category) {
        Product product = new Product();
        product.setName(product.getName());
        product.setPrice(product.getPrice());
        product.setDescription(productDto.getDescription());
        product.setCategory(category);
        productRepository.save(product);
    }

    public List<ProductDto> getAll() {
        List<Product> allProducts = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();

        for(Product product : allProducts) {
            productDtos.add(getProduct(product));
        }
        return productDtos;
    }

    public ProductDto getProduct(Product product) {
        ProductDto dto = new ProductDto();
        dto.setCategoryId(product.getCategory().getId());
        dto.setName(product.getName());
        dto.setPrice(dto.getPrice());
        dto.setDescription(product.getDescription());
        return dto;
    }

    public void updateProduct(ProductDto productDto, Long id) {
        Optional<Product> optional = productRepository.findById(id);
        if(optional.isEmpty()) {
            throw new IllegalArgumentException(String.format("Product %s not fount", id));
        }
        Product product = optional.get();
        product.setName(product.getName());
        product.setPrice(product.getPrice());
        product.setDescription(productDto.getDescription());
        productRepository.save(product);
    }

}
