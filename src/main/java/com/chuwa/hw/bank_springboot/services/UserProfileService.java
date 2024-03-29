package com.chuwa.hw.bank_springboot.services;

import com.chuwa.hw.bank_springboot.entities.UserProfile;

import java.util.List;

public interface UserProfileService {
    List<UserProfile> getAllUserProfiles();

    UserProfile saveOrUpdateProfile(UserProfile userProfile);

    void deleteUserProfile(Long id);
}
