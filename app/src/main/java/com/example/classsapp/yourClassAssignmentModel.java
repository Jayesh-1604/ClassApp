package com.example.classsapp;

public class yourClassAssignmentModel {
    String assiDesc;
    String assiName;

    String assiClassCode;

    String assiTeacherPh;


    yourClassAssignmentModel()
    {

    }

    public yourClassAssignmentModel(String assiDesc, String assiName, String assiClassCode, String assiTeacherPh) {
        this.assiDesc = assiDesc;
        this.assiName = assiName;
        this.assiClassCode = assiClassCode;
        this.assiTeacherPh = assiTeacherPh;
    }

    public String getAssiDesc() {
        return assiDesc;
    }

    public void setAssiDesc(String assiDesc) {
        this.assiDesc = assiDesc;
    }

    public String getAssiName() {
        return assiName;
    }

    public void setAssiName(String assiName) {
        this.assiName = assiName;
    }

    public String getAssiClassCode() {
        return assiClassCode;
    }

    public void setAssiClassCode(String assiClassCode) {
        this.assiClassCode = assiClassCode;
    }

    public String getAssiTeacherPh() {
        return assiTeacherPh;
    }

    public void setAssiTeacherPh(String assiTeacherPh) {
        this.assiTeacherPh = assiTeacherPh;
    }
}

