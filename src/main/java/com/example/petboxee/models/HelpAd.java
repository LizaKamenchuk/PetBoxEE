package com.example.petboxee.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class HelpAd {

    private int id;
   private String type;
    private String description;
    private String address;
    private String contactName;
    private String contactEmail;
    private String shelterName;

    public String toStringForSQLInsert(){
        return "('" + type + "', '" +
                description + "', '" +
                contactEmail + "')";
    }
}
