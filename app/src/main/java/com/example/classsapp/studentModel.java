package com.example.classsapp;

public class studentModel
{

    String ClassName,TeacherPhoneNumber,passcode;

    studentModel()
    {

    }
    public studentModel(String className, String teacherPhoneNumber, String passcode) {
        ClassName = className;
        TeacherPhoneNumber = teacherPhoneNumber;
        this.passcode = passcode;
    }
    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public String getTeacherPhoneNumber() {
        return TeacherPhoneNumber;
    }

    public void setTeacherPhoneNumber(String teacherPhoneNumber) {
        TeacherPhoneNumber = teacherPhoneNumber;
    }

    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }


}

