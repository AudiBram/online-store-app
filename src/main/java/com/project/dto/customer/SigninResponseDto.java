package com.project.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SigninResponseDto {

    private String status;
    private String token;

}
