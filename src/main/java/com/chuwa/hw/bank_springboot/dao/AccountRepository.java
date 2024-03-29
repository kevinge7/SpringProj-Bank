package com.chuwa.hw.bank_springboot.dao;

import com.chuwa.hw.bank_springboot.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByUserProfileId(Long userProfileId);

}
