package com.example.petboxee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DeleteAnimalImp {
    private Connection connection = null;
    public int deleteAnimal(int id) {
        int i = 0;
        try {
            Class.forName(SQLData.DRIVER);
            System.out.println("Диск успешно загружен!");

            if (connection == null) {
                connection = DriverManager.getConnection(SQLData.URL, SQLData.USER, SQLData.PASSWORD);
                System.out.println("Успешное подключение к базе данных!");
            } else System.out.println("Вы пытаетесь подключиться к базе данных, но вы уже подключены!");

            System.out.println("В deleteAnimalImp");

            PreparedStatement ps;
            ResultSet rs;
            String sql;

            sql = SQLRequest.deleteAnimal(id);
            ps = connection.prepareStatement(sql);
            i = ps.executeUpdate(sql);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Успешное отключение от базы данных!");
                } catch (Exception e) {
                    System.out.println("Неуспешное отключение от базы данных!");
                }
            }
        }

        return i;
    }
}
