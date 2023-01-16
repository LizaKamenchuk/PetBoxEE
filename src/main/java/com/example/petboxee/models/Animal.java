package com.example.petboxee.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Animal {
    private String name;
    private String characters;
    private String gender;
    private String color;
    private int age;

    private  String ageMeasure;
    private int photo;
    private int id;
    private String type;


    private String address;
    private String contactName;
    private String contactEmail;
    private String shelterName;

public String toStringForSQLInsert(){
    return "('" + name + "', '" +
            type + "', '" +
            color + "', '" +
            characters + "', '" +
            gender + "', " +
            age + ", " +
            photo+ ", '" +
            contactEmail+"', ' "+
            ageMeasure + "')";
}
}
