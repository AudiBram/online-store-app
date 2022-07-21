package com.project.repository;

import com.project.entity.Customer;
import com.project.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    Token findTokenByCustomer(Customer customer);

}
