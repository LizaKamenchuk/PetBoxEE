package com.example.petboxee.servise;

import com.example.petboxee.dao.SQLData;
import com.example.petboxee.dao.SQLRequest;
import com.example.petboxee.models.UserBeam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class GetAllUsersImp {

    ArrayList<UserBeam> users = new ArrayList<>();
    private Connection connection = null;
    public ArrayList<UserBeam> initializeUsers() {
        PreparedStatement ps;
        ResultSet rs;
        UserBeam user;
        try {
            Class.forName(SQLData.DRIVER);
            System.out.println("Диск успешно загружен!");

            if (connection == null) {
                connection = DriverManager.getConnection(SQLData.URL, SQLData.USER, SQLData.PASSWORD);
                System.out.println("Успешное подключение к базе данных!");
            } else System.out.println("Вы пытаетесь подключиться к базе данных, но вы уже подключены!");


            String sql = SQLRequest.selectAllUsers();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery(sql);

            while (rs.next()) {
                System.out.println("Получение пользователей");
                user = new UserBeam();
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setAddress(rs.getString("address"));
                user.setShelter(rs.getString("shelterName"));
                user.setLastname(rs.getString("lastname"));
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        return users;
    }
}
