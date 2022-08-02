package com.project.controller;

import com.project.common.Response;
import com.project.dto.ProductDto;
import com.project.entity.Category;
import com.project.repository.CategoryRepository;
import com.project.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryRepository categoryRepository;

    @Operation(summary = "Save Product", description = "Save a Product", tags = "Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Save the Product",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Category.class))}),
            @ApiResponse(responseCode = "404", description = "Product not found",
                    content = @Content)})
    @PostMapping("/create")
    public ResponseEntity<Response> saveProduct(@RequestBody ProductDto productDto) {
        Optional<Category> id = categoryRepository.findById(productDto.getCategoryId());
        if (id.isEmpty()) {
            return new ResponseEntity<>(new Response("Failed", "category not exists"), HttpStatus.BAD_REQUEST);
        }
        productService.createProduct(productDto, id.get());
        return new ResponseEntity<>(new Response("Success", productDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Get All Products", description = "Get All Products", tags = "Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieved all Products"),
            @ApiResponse(responseCode = "404", description = "Product not found",
                    content = @Content)})
    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<ProductDto> productDtos = productService.getAll();
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    @Operation(summary = "Update Product", description = "Update a Product", tags = "Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update the Category",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Category.class))}),
            @ApiResponse(responseCode = "404", description = "Product not found",
                    content = @Content)})
    @PutMapping("/update/{id}")
    public ResponseEntity<Response> updateProduct(@PathVariable("id") Long id, @RequestBody ProductDto productDto) {
        Optional<Category> byId = categoryRepository.findById(productDto.getCategoryId());
        if (byId.isEmpty()) {
            return new ResponseEntity<>(new Response("Failed", "Product not exists"), HttpStatus.BAD_REQUEST);
        }
        productService.updateProduct(productDto, id);
        return new ResponseEntity<>(new Response("Success", productDto), HttpStatus.OK);
    }
}
