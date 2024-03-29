package com.edureka.bankProject.repo;

import com.edureka.bankProject.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditCardRepo extends JpaRepository<CreditCard, Long> {
    Optional<CreditCard> findByName(String name);
}
