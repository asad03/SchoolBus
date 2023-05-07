package com.asadkhan.schoolbustracking;

public class Admin_ModalClass {
    String mobile,email;
    public Admin_ModalClass() {
    }

    public Admin_ModalClass(String mobile, String email) {
        this.mobile = mobile;
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
