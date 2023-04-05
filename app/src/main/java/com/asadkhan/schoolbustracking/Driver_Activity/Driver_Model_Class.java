package com.asadkhan.schoolbustracking.Driver_Activity;

public class Driver_Model_Class {
    String driver_name,driver_mobile,driver_age,drivere_mail,driver_Addres,usertype,bus,driver_password;
    public Driver_Model_Class() {
    }

    public Driver_Model_Class(String driver_name, String driver_mobile, String driver_age, String drivere_mail, String driver_Addres, String usertype, String bus, String driver_password) {
        this.driver_name = driver_name;
        this.driver_mobile = driver_mobile;
        this.driver_age = driver_age;
        this.drivere_mail = drivere_mail;
        this.driver_Addres = driver_Addres;
        this.usertype = usertype;
        this.bus = bus;
        this.driver_password = driver_password;
    }

    public String getDriver_name() {
        return driver_name;
    }

    public void setDriver_name(String driver_name) {
        this.driver_name = driver_name;
    }

    public String getDriver_mobile() {
        return driver_mobile;
    }

    public void setDriver_mobile(String driver_mobile) {
        this.driver_mobile = driver_mobile;
    }

    public String getDriver_age() {
        return driver_age;
    }

    public void setDriver_age(String driver_age) {
        this.driver_age = driver_age;
    }

    public String getDrivere_mail() {
        return drivere_mail;
    }

    public void setDrivere_mail(String drivere_mail) {
        this.drivere_mail = drivere_mail;
    }

    public String getDriver_Addres() {
        return driver_Addres;
    }

    public void setDriver_Addres(String driver_Addres) {
        this.driver_Addres = driver_Addres;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }

    public String getDriver_password() {
        return driver_password;
    }

    public void setDriver_password(String driver_password) {
        this.driver_password = driver_password;
    }
}
