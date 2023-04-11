package com.asadkhan.schoolbustracking.Attendance_Activity;

public class Attendance_Out_Activity {
    String bus,studnet_RNumber,eveningentryDate,eveningentryTime,MorningStatus,eveningDateandTime,student_name;

    public Attendance_Out_Activity() {
    }

    public Attendance_Out_Activity(String bus, String studnet_RNumber, String eveningentryDate, String eveningentryTime, String morningStatus, String eveningDateandTime, String student_name) {
        this.bus = bus;
        this.studnet_RNumber = studnet_RNumber;
        this.eveningentryDate = eveningentryDate;
        this.eveningentryTime = eveningentryTime;
        MorningStatus = morningStatus;
        this.eveningDateandTime = eveningDateandTime;
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

    public String getEveningentryDate() {
        return eveningentryDate;
    }

    public void setEveningentryDate(String eveningentryDate) {
        this.eveningentryDate = eveningentryDate;
    }

    public String getEveningentryTime() {
        return eveningentryTime;
    }

    public void setEveningentryTime(String eveningentryTime) {
        this.eveningentryTime = eveningentryTime;
    }

    public String getMorningStatus() {
        return MorningStatus;
    }

    public void setMorningStatus(String morningStatus) {
        MorningStatus = morningStatus;
    }

    public String getEveningDateandTime() {
        return eveningDateandTime;
    }

    public void setEveningDateandTime(String eveningDateandTime) {
        this.eveningDateandTime = eveningDateandTime;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }
}
