package com.chuwa.hw.bank_springboot.controller;

import com.chuwa.hw.bank_springboot.entities.UserProfile;
import com.chuwa.hw.bank_springboot.payload.UserProfileDto;
import com.chuwa.hw.bank_springboot.services.impl.UserProfileServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class UserProfileControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(UserProfileControllerTest.class);

//    @Mock
//    private UserProfileRepository userProfileRepositoryMock;
    @Mock
    private UserProfileServiceImpl userProfileServiceImplMock;

    @Spy
    private ModelMapper modelMapper;

    @InjectMocks
    private UserProfileController userProfileController;

    private UserProfile userProfile;
    private UserProfileDto userProfileDto;


    @BeforeAll
    static void beforeAll(){
        logger.info("START test");
    }

    @BeforeEach
    void setup(){
        logger.info("set up Post for each test");

        this.userProfile = new UserProfile(1L, "name", "123 address", "12345", "gmail@gmail.com");
        ModelMapper modelMapper1 = new ModelMapper();
        this.userProfileDto = modelMapper1.map(this.userProfile, UserProfileDto.class);
//        Mockito.when(userProfileServiceImplMock.saveOrUpdateProfile(ArgumentMatchers.any(UserProfile.class)))
//                .thenReturn(userProfile);
    }


    @Test
    void getAllUserProfiles() {
        List<UserProfile> userProfiles = new ArrayList<>();
        userProfiles.add(userProfile);
        Mockito.when(userProfileServiceImplMock.getAllUserProfiles()).thenReturn(userProfiles);

        List<UserProfileDto> userProfileDtoList = userProfileController.getAllUserProfiles().getBody();

        Assertions.assertNotNull(userProfileDtoList);
        Assertions.assertEquals(1, userProfileDtoList.size());

    }

    @Test
    void getUserProfileById() {
        Mockito.when(userProfileServiceImplMock.getUserProfileById(ArgumentMatchers.anyLong()))
                .thenReturn(userProfile);


        UserProfileDto userProfileResponse = userProfileController.getUserProfileById(1L).getBody();

        Assertions.assertNotNull(userProfileResponse);
        Assertions.assertEquals(userProfileResponse.getEmail(), userProfileDto.getEmail());
        Assertions.assertEquals(userProfileResponse.getPhone(), userProfileDto.getPhone());
        Assertions.assertEquals(userProfileResponse.getAddr(), userProfileDto.getAddr());

    }

    @Test
    void createOrUpdateUserProfile() {
        Mockito.when(userProfileServiceImplMock.saveOrUpdateProfile(ArgumentMatchers.any(UserProfile.class)))
                .thenReturn(userProfile);

        UserProfileDto userProfileResponse = userProfileController.createOrUpdateUserProfile(userProfileDto).getBody();

        Assertions.assertNotNull(userProfileResponse);
        Assertions.assertEquals(userProfileResponse.getId(), userProfileDto.getId(), "UserProfile ID Dis-match.");
        Assertions.assertEquals(userProfileResponse.getName(), userProfileDto.getName(), "UserProfile name Dis-match.");
        Assertions.assertEquals(userProfileResponse.getAddr(), userProfileDto.getAddr());
        Assertions.assertEquals(userProfileResponse.getEmail(), userProfileDto.getEmail());
    }
}