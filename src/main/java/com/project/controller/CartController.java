package com.project.controller;

import com.project.common.Response;
import com.project.dto.CartDto;
import com.project.dto.CartListDto;
import com.project.entity.Category;
import com.project.service.CartService;
import com.project.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;
    private final TokenService tokenService;

    @Operation(summary = "Save New Cart", description = "Save to Cart", tags = "Cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Save the Product",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Category.class))}),
            @ApiResponse(responseCode = "404", description = "Cannot at Cart",
                    content = @Content)})
    @PostMapping("/create")
    public ResponseEntity<Response> addCart(@RequestBody CartDto cartDto) {
        cartService.addCart(cartDto);
        return new ResponseEntity<>(new Response("Success", "Added Cart"), HttpStatus.CREATED);
    }

    // get all
    @GetMapping
    public ResponseEntity<CartListDto> getAllCart() {
        CartListDto cartListDto = cartService.getAll();
        return new ResponseEntity<>(cartListDto, HttpStatus.OK);
    }

    // delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteCart(@PathVariable("id") Long id) {
        cartService.deleteCart(id);
        return new ResponseEntity<>(new Response("Success", "Delete Successfully"), HttpStatus.OK);
    }

}
