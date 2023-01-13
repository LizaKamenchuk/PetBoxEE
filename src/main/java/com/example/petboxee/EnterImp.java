package com.example.petboxee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EnterImp {
    private Connection connection = null;
    public UserBeam autorizeUser(String email,String password) {
        PreparedStatement ps;
        ResultSet rs;
        UserBeam userBeam=null;

        try {
            Class.forName(SQLData.DRIVER);
            System.out.println("Диск успешно загружен!");

            if (connection == null) {
                connection = DriverManager.getConnection(SQLData.URL, SQLData.USER, SQLData.PASSWORD);
                System.out.println("Успешное подключение к базе данных!");
            } else System.out.println("Вы пытаетесь подключиться к базе данных, но вы уже подключены!");

            String[] strings={"*"};
            String sql = SQLRequest.selectUser(email,"users",strings);
             ps = connection.prepareStatement(sql);
             rs = ps.executeQuery(sql);

             while (rs.next()){
                 if (rs.getString("password").equals(password)) {
                     userBeam=new UserBeam();
                     userBeam.setEmail(rs.getString("email"));
                     userBeam.setPassword(rs.getString("password"));
                     userBeam.setName(rs.getString("name"));
                     userBeam.setLastname(rs.getString("lastname"));
                     userBeam.setAddress(rs.getString("address"));
                     userBeam.setShelter(rs.getString("shelterName"));
                }
                 }
             }catch (Exception e){
            e.printStackTrace();
        }
            finally {
            if(connection!=null){
                try{
                    connection.close();
                    System.out.println("Успешное отключение от базы данных!");
                }catch (Exception e){
                    System.out.println("Неуспешное отключение от базы данных!");
                }
            }
        }

        return userBeam;
    }
}
