package com.chuwa.hw.bank_springboot.services.impl;

import com.chuwa.hw.bank_springboot.dao.UserProfileRepository;
import com.chuwa.hw.bank_springboot.entities.UserProfile;
import com.chuwa.hw.bank_springboot.services.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    @Autowired
    private UserProfileRepository userProfileRepository;

    public UserProfile getUserProfileById(Long id){
        return userProfileRepository.findById(id).orElse(null);

    }

    @Override
    public List<UserProfile> getAllUserProfiles(){
        return userProfileRepository.findAll();
    }

    @Override
    public UserProfile saveOrUpdateProfile(UserProfile userProfile){
        return userProfileRepository.save(userProfile);
    }

    @Override
    public void deleteUserProfile(Long id){
        UserProfile userProfile = userProfileRepository.findById(id).orElseThrow(() -> new RuntimeException("User profile not found for this id :: " + id));
        userProfileRepository.delete(userProfile);
    }


}