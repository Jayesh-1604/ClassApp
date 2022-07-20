package com.example.classsapp;

public class myClassModel {

    String ClassName,ClassPassCode;


    myClassModel()
    {

    }
    public myClassModel(String className, String classPassCode) {
        ClassName = className;
        ClassPassCode = classPassCode;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public String getClassPassCode() {
        return ClassPassCode;
    }

    public void setClassPassCode(String classPassCode) {
        ClassPassCode = classPassCode;
    }
}
