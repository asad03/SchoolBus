package com.asadkhan.schoolbustracking.Parents_Activity;

public class Show_Child_ModalClass {
    String student_registerationNo, stdent_fullname, student_fatherName, student_class, student_mobileNo, student_email, student_location,sbus;
    public Show_Child_ModalClass() {
    }

    public Show_Child_ModalClass(String student_registerationNo, String stdent_fullname, String student_fatherName, String student_class, String student_mobileNo, String student_email, String student_location, String sbus) {
        this.student_registerationNo = student_registerationNo;
        this.stdent_fullname = stdent_fullname;
        this.student_fatherName = student_fatherName;
        this.student_class = student_class;
        this.student_mobileNo = student_mobileNo;
        this.student_email = student_email;
        this.student_location = student_location;
        this.sbus = sbus;
    }

    public String getStudent_registerationNo() {
        return student_registerationNo;
    }

    public void setStudent_registerationNo(String student_registerationNo) {
        this.student_registerationNo = student_registerationNo;
    }

    public String getStdent_fullname() {
        return stdent_fullname;
    }

    public void setStdent_fullname(String stdent_fullname) {
        this.stdent_fullname = stdent_fullname;
    }

    public String getStudent_fatherName() {
        return student_fatherName;
    }

    public void setStudent_fatherName(String student_fatherName) {
        this.student_fatherName = student_fatherName;
    }

    public String getStudent_class() {
        return student_class;
    }

    public void setStudent_class(String student_class) {
        this.student_class = student_class;
    }

    public String getStudent_mobileNo() {
        return student_mobileNo;
    }

    public void setStudent_mobileNo(String student_mobileNo) {
        this.student_mobileNo = student_mobileNo;
    }

    public String getStudent_email() {
        return student_email;
    }

    public void setStudent_email(String student_email) {
        this.student_email = student_email;
    }

    public String getStudent_location() {
        return student_location;
    }

    public void setStudent_location(String student_location) {
        this.student_location = student_location;
    }

    public String getSbus() {
        return sbus;
    }

    public void setSbus(String sbus) {
        this.sbus = sbus;
    }
}
