package com.SimpleProject.School_Management.model;

import lombok.Data;

@Data //it will create the getter setter internally for all the variable and methods
public class Student {
    private  int studentId;
    private String name;
    private  String dob;
    private String gender;
    private  String grade;

}
