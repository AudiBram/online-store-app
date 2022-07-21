package com.project.service;

import com.project.entity.Customer;
import com.project.entity.Token;
import com.project.repository.TokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TokenService {

    private final TokenRepository tokenRepository;

    public void saveToken(Token token) {
        tokenRepository.save(token);
    }

    public Token getToken(Customer customer) {
        return tokenRepository.findTokenByCustomer(customer);
    }
}
