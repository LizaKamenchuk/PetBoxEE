package com.example.petboxee;

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


    public Animal(String type,String name, String characters, String gender, int age, int photo, int id,String color){
        this.type=type;
        this.name=name;
        this.characters=characters;
        this.gender=gender;
        this.age=age;
        this.photo = photo;
        this.id=id;
        this.color= color;
    }
    public Animal(){

    }

    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name=name;
    }

    public String getCharacters(){
        return this.characters;
    }
    public  void  setCharacters(String characters){
        this.characters=characters;
    }

    public String getGender(){return this.gender;}
    public void setGender(String gender){this.gender=gender;}

    public int getPhoto(){
        return this.photo;
    }
    public  void setPhoto(int photo){
        this.photo = photo;
    }

    public int getAge() {
        return this.age;
    }
    public void setAge(int age){
        this.age=age;
    }

    public String getAgeMeasure() {
        return ageMeasure;
    }

    public void setAgeMeasure(String ageMeasure) {
        this.ageMeasure = ageMeasure;
    }

    public int getId() {return this.id;}
    public void setId(int id) {this.id = id;}

    public String getAddress(){return this.address;}
    public void setAddress(String address) {this.address = address;}

    public String getContactName() {
        return contactName;
    }
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactEmail() {
        return contactEmail;
    }
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getShelterName() {
        return shelterName;
    }
    public void setShelterName(String shelterName) {
        this.shelterName = shelterName;
    }

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
