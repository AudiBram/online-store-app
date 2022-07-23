package com.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

    private Long id;

    @NotNull
    private Long productId;

    @NotNull
    private Integer quantity;

}
