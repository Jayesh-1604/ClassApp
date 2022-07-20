package com.example.classsapp;

public class fileinfomodel
{
    String assiName,pdfUrl,studentName,studentEmail;

    public fileinfomodel(String assiName, String pdfUrl, String studentName,String studentEmail) {

        this.assiName = assiName;
        this.pdfUrl = pdfUrl;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
    }

    public String getAssiName() {
        return assiName;
    }

    public void setAssiName(String assiName) {
        this.assiName = assiName;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }
}

