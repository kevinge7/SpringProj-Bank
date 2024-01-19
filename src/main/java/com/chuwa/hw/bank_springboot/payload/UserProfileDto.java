package com.chuwa.hw.bank_springboot.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UserProfileDto {
    private Long id;

    public UserProfileDto(Long id, String name, String addr, String phone, String email) {
        this.id = id;
        this.name = name;
        this.addr = addr;
        this.phone = phone;
        this.email = email;
    }

    public UserProfileDto() {
    }

    private String name;
    private String addr;
    private String phone;
    private String email;

}
