package com.asadkhan.schoolbustracking.Parents_Activity;

public class Parents_Model_class {
    String Parent_name,Parent_mobile,Parent_age,Parent_mail,Parent_Addres,usertype,Parent_password;
    public Parents_Model_class() {
    }

    public Parents_Model_class(String parent_name, String parent_mobile, String parent_age, String parent_mail, String parent_Addres, String usertype, String parent_password) {
        Parent_name = parent_name;
        Parent_mobile = parent_mobile;
        Parent_age = parent_age;
        Parent_mail = parent_mail;
        Parent_Addres = parent_Addres;
        usertype = usertype;
        Parent_password = parent_password;
    }

    public String getParent_name() {
        return Parent_name;
    }

    public void setParent_name(String parent_name) {
        Parent_name = parent_name;
    }

    public String getParent_mobile() {
        return Parent_mobile;
    }

    public void setParent_mobile(String parent_mobile) {
        Parent_mobile = parent_mobile;
    }

    public String getParent_age() {
        return Parent_age;
    }

    public void setParent_age(String parent_age) {
        Parent_age = parent_age;
    }

    public String getParent_mail() {
        return Parent_mail;
    }

    public void setParent_mail(String parent_mail) {
        Parent_mail = parent_mail;
    }

    public String getParent_Addres() {
        return Parent_Addres;
    }

    public void setParent_Addres(String parent_Addres) {
        Parent_Addres = parent_Addres;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getParent_password() {
        return Parent_password;
    }

    public void setParent_password(String parent_password) {
        Parent_password = parent_password;
    }
}

