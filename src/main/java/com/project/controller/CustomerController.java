package com.project.controller;

import com.project.dto.SignupResponseDto;
import com.project.dto.customer.SignInDto;
import com.project.dto.customer.SignUpDto;
import com.project.entity.Category;
import com.project.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @Operation(summary = "Sign Up Customer", description = "Sign Up Customer", tags = "Customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Save the Customer",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Category.class))}),
            @ApiResponse(responseCode = "404", description = "Customer not found",
                    content = @Content)})
    @PostMapping("/signup")
    public SignupResponseDto signUp(@RequestBody SignUpDto signUpDto) {
        return customerService.signUp(signUpDto);
    }


    @Operation(summary = "Sign In Customer", description = "Save a Customer", tags = "Customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Save the Customer",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Category.class))}),
            @ApiResponse(responseCode = "404", description = "Customer not found",
                    content = @Content)})
    @PostMapping("/signin")
    public SignupResponseDto signIn(@RequestBody SignInDto signInDto) {
        return customerService.signIn(signInDto);
    }

}
