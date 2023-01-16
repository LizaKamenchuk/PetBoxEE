package com.example.petboxee.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class UserBeam {
    private String name;
    private String lastname;
    private String email;
    private String password;
    private String address;
    private String shelter;

    public String toStringForSQLInsert(){
        return "('" + email + "', '" +
                password + "', '" +
               // salt + "', '" +
                lastname + "', '" +
                name + "', '" +
                address + "', '" +
                shelter + "')";
    }
}
