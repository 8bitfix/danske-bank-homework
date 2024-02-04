package com.vladasnarkauskas.homework.repository;

import com.vladasnarkauskas.homework.dao.AccountHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountHistoryRepository extends CrudRepository<AccountHistory, Long> {
//    @Query("SELECT ah FROM AccountHistory ah WHERE ah.accountId = :accountId ORDER BY ah.date DESC")
    List<AccountHistory> findByAccountId(Long accountId);
}
