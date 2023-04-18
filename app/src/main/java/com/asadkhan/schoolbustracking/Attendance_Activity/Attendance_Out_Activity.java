package com.asadkhan.schoolbustracking.Attendance_Activity;

public class Attendance_Out_Activity {
    String bus,studnet_RNumber,morningentryDate,morningentryTime,MorningStatus,morningDateandTime,student_name;

    public Attendance_Out_Activity() {
    }

    public Attendance_Out_Activity(String bus, String studnet_RNumber, String morningentryDate, String morningentryTime, String morningStatus, String morningDateandTime, String student_name) {
        this.bus = bus;
        this.studnet_RNumber = studnet_RNumber;
        this.morningentryDate = morningentryDate;
        this.morningentryTime = morningentryTime;
        MorningStatus = morningStatus;
        this.morningDateandTime = morningDateandTime;
        this.student_name = student_name;
    }

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }

    public String getStudnet_RNumber() {
        return studnet_RNumber;
    }

    public void setStudnet_RNumber(String studnet_RNumber) {
        this.studnet_RNumber = studnet_RNumber;
    }

    public String getMorningentryDate() {
        return morningentryDate;
    }

    public void setMorningentryDate(String morningentryDate) {
        this.morningentryDate = morningentryDate;
    }

    public String getMorningentryTime() {
        return morningentryTime;
    }

    public void setMorningentryTime(String morningentryTime) {
        this.morningentryTime = morningentryTime;
    }

    public String getMorningStatus() {
        return MorningStatus;
    }

    public void setMorningStatus(String morningStatus) {
        MorningStatus = morningStatus;
    }

    public String getMorningDateandTime() {
        return morningDateandTime;
    }

    public void setMorningDateandTime(String morningDateandTime) {
        this.morningDateandTime = morningDateandTime;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }
}
