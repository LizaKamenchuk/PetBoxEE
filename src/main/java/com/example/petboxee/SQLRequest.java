package com.example.petboxee;


public class SQLRequest {
    public static String insertUser(UserBeam user, String TableName){
        return "INSERT INTO " +
                TableName +
                "(email, password, lastname, name, address, shelterName) VALUES " +
                user.toStringForSQLInsert();
    }

    public static String selectUser(int id, String TableName, String ... requestedArgs){
        String s = "SELECT ";
        for(String args: requestedArgs){
            s+=args + " ";
        }
        s+="FROM " + TableName + " WHERE id = " + Integer.toString(id);
        return s;
    }

    public static String selectUser(String email, String TableName, String ... requestedArgs){
        String s = "SELECT ";
        for(String args: requestedArgs){
            s+=args + " ";
        }
        s+="FROM " + TableName + " WHERE email = '" + email + "'";
        return s;
    }


    public static String selectUser(String email, String password, String TableName, String ... requestedArgs){
        String s = "SELECT ";
        for(String args: requestedArgs){
            s+=args + " ";
        }
        s+="FROM " + TableName + " WHERE email = '" + email + "' and password = '" + password + "'";
        return s;
    }

    public static String selectAnimals(){
        return "SELECT * FROM allaboutAnimal";
    }
    public static String insertAnimal(Animal animal, String TableName){
        return "INSERT INTO " +
                TableName +
                "(name, type, color, characters, gender, age, photo, shelter_id,ageMeasure) VALUES " +
                animal.toStringForSQLInsert();
    }

    public static String updateAnimal(Animal animal){
        return "UPDATE animals SET name = "+ " '"+animal.getName()+"'"+
                " ,type = "+ " '"+animal.getType()+"' "+
                ", color = "+" '"+ animal.getColor()+"' "+
                ", characters = "+" '"+ animal.getCharacters()+"' "+
                ", gender = "+"'"+animal.getGender()+"'"+
                ", age = "+"'"+animal.getAge()+"'"+
                ", photo = "+animal.getPhoto()+
                ", ageMeasure = "+"'"+animal.getAgeMeasure()+"'"+
                " WHERE id = "+ animal.getId()+" ;";
    }

    public static String deleteAnimal(int id){
        return "DELETE FROM animals WHERE id = " + id;
    }

    public static String selectHelpAds(){return "SELECT * FROM allaboutHelpAd";}
    public  static  String insertHelpAd(HelpAd helpAd,String TableName){
        return "INSERT INTO " +
                TableName +
                "(type, description, shelter_id) VALUES " +
                helpAd.toStringForSQLInsert();
    }

    public static String deleteHelpAd(int id){
        return "DELETE FROM helpAds WHERE id = " + id;
    }

    public static String selectAllUsers(){
     return  "SELECT email,name,lastname,address,shelterName FROM users";
    }

}
