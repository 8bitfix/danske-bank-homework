package com.vladasnarkauskas.homework.repository;

import com.vladasnarkauskas.homework.dao.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    Optional<Account> findByIdAndCustomerId(Long id, Long customerId);
}
