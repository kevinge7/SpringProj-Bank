package com.chuwa.hw.bank_springboot.controller;

import com.chuwa.hw.bank_springboot.dao.UserProfileRepository;
import com.chuwa.hw.bank_springboot.entities.UserProfile;
import com.chuwa.hw.bank_springboot.payload.UserProfileDto;
import com.chuwa.hw.bank_springboot.services.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user-profile")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @GetMapping
    public ResponseEntity<List<UserProfileDto>> getAllUserProfiles(){
        List<UserProfile> userProfiles = userProfileService.getAllUserProfiles();
        List<UserProfileDto> responses = userProfiles.stream()
                .map(this::mapToUserProfileResponse)
                .collect(Collectors.toList());
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfileDto> getUserProfileById(@PathVariable Long id){
        UserProfile userProfile = userProfileService.getUserProfileById(id);
        return new ResponseEntity<>(mapToUserProfileResponse(userProfile), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserProfileDto> createOrUpdateUserProfile(@RequestBody UserProfileDto userProfileDto){
        UserProfile userProfile = mapToUserProfile(userProfileDto);
        UserProfile savedUserProfile = userProfileService.saveOrUpdateProfile(userProfile);
        return new ResponseEntity<>(mapToUserProfileResponse(savedUserProfile), HttpStatus.OK);
    }

    private UserProfileDto mapToUserProfileResponse(UserProfile userProfile) {
        UserProfileDto response = new UserProfileDto();
        response.setId(userProfile.getId());
        response.setName(userProfile.getName());
        response.setAddr(userProfile.getAddr());
        response.setPhone(userProfile.getPhone());
        response.setEmail(userProfile.getEmail());
        return response;
    }

    private UserProfile mapToUserProfile(UserProfileDto request) {
        UserProfile userProfile = new UserProfile();
        userProfile.setId(request.getId());
        userProfile.setName(request.getName());
        userProfile.setAddr(request.getAddr());
        userProfile.setPhone(request.getPhone());
        userProfile.setEmail(request.getEmail());
        return userProfile;
    }

}
