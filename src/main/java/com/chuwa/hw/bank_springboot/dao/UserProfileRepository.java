package com.chuwa.hw.bank_springboot.dao;

import com.chuwa.hw.bank_springboot.entities.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}
