package com.chuwa.hw.bank_springboot.payload;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String name;
    private String addr;
    private String phone;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
