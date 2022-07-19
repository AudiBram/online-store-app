package com.project.controller;

import com.project.common.Response;
import com.project.entity.Category;
import com.project.service.CategoryService;
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

@AllArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(summary = "Save Category", description = "Save a Category", tags = "Post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Save the Category",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Category.class))}),
            @ApiResponse(responseCode = "404", description = "Category not found",
                    content = @Content)})
    @PostMapping("/created")
    public ResponseEntity<Response> saveCategory(@RequestBody Category category) {
        categoryService.saveCategory(category);
        return new ResponseEntity<>(new Response("Success", category), HttpStatus.CREATED);
    }

    @Operation(summary = "Get All Categories", description = "Get All Categories", tags = "Get")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieved all categories"),
            @ApiResponse(responseCode = "404", description = "Category not found",
                    content = @Content)})
    @GetMapping
    public List<Category> findCategories() {
        return categoryService.findAll();
    }

    @Operation(summary = "Update Category", description = "Update a Category", tags = "Put")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update the Category",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Category.class))}),
            @ApiResponse(responseCode = "404", description = "Category not found",
                    content = @Content)})
    @PutMapping("/update/{id}")
    public ResponseEntity<Response> updateCategory(@PathVariable("id") Long id, @RequestBody Category category) {
        categoryService.updateCategory(id, category);
        if(categoryService.findById(id)) {
            return new ResponseEntity<>(new Response("Failed", "Category not exist"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new Response("Success", category), HttpStatus.OK);
    }

    @Operation(summary = "Delete Category", description = "Delete Category", tags = "Delete")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete Category"),
            @ApiResponse(responseCode = "404", description = "Category not found",
                    content = @Content)})
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(new Response("Success", "Delete Successfully"), HttpStatus.OK);
    }
}
