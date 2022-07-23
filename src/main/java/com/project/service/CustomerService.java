package com.project.service;

import com.project.dto.SignInDto;
import com.project.dto.SignUpDto;
import com.project.dto.SignupResponseDto;
import com.project.entity.Customer;
import com.project.entity.Token;
import com.project.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final TokenService tokenService;

    @Transactional
    public SignupResponseDto signUp(SignUpDto signUpDto) {
        Customer email = customerRepository.findCustomerByEmail(signUpDto.getEmail());
        if(email != null) {
            throw new IllegalArgumentException("Email has been taken");
        }

        String encryptedpassword = signUpDto.getPassword();
        try {
            encryptedpassword = makePassword(signUpDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        Customer customer =
                new Customer(signUpDto.getFirstName(), signUpDto.getLastName(), signUpDto.getEmail(), encryptedpassword);
        customerRepository.save(customer);

        Token token = new Token(customer);
        tokenService.saveToken(token);

        SignupResponseDto signupResponseDto = new SignupResponseDto("Success", "Successfully");
        return signupResponseDto;
    }

    public SignupResponseDto signIn(SignInDto signInDto) {
        Customer customer = customerRepository.findCustomerByEmail(signInDto.getEmail());

        if(customer == null) {
            throw new IllegalArgumentException("user is not valid");
        }

        try {
            if(!customer.getPassword().equals(makePassword(signInDto.getPassword()))) {
                throw new IllegalArgumentException("Wrong password");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        Token token = tokenService.getToken(customer);
        if(token == null) {
            throw new IllegalArgumentException("token is not valid");
        }
        return new SignupResponseDto("Success", token.getToken());
    }

    private String makePassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String hash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return hash;
    }
}
