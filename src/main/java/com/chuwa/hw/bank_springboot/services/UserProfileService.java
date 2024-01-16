package com.chuwa.hw.bank_springboot.services;

import com.chuwa.hw.bank_springboot.dao.UserProfileRepository;
import com.chuwa.hw.bank_springboot.entities.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProfileService {
    @Autowired
    private UserProfileRepository userProfileRepository;

    public UserProfile getUserProfileById(Long id){
        return userProfileRepository.findById(id).orElse(null);

    }

    public List<UserProfile> getAllUserProfiles(){
        return userProfileRepository.findAll();
    }

    public UserProfile saveOrUpdateProfile(UserProfile userProfile){
        return userProfileRepository.save(userProfile);
    }

    public void deleteUserProfile(Long id){
        UserProfile userProfile = userProfileRepository.findById(id).orElseThrow(() -> new RuntimeException("User profile not found for this id :: " + id));
        userProfileRepository.delete(userProfile);
    }


}