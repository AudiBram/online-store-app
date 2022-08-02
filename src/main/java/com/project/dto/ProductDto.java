package com.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Long price;

    @NotNull
    private String description;

    @NotNull
    private Long categoryId;

}
