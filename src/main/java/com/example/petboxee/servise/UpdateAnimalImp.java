package com.example.petboxee.servise;

import com.example.petboxee.dao.SQLData;
import com.example.petboxee.dao.SQLRequest;
import com.example.petboxee.models.Animal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UpdateAnimalImp {
    private Connection connection = null;

    public int updateAnimal(Animal animal){
        int i=0;

        try {
            Class.forName(SQLData.DRIVER);
            System.out.println("Диск успешно загружен!");

            if (connection == null) {
                connection = DriverManager.getConnection(SQLData.URL, SQLData.USER, SQLData.PASSWORD);
                System.out.println("Успешное подключение к базе данных!");
            } else System.out.println("Вы пытаетесь подключиться к базе данных, но вы уже подключены!");

            System.out.println("В UpdateAnimalImp: " + animal.toString());

            PreparedStatement ps;
            ResultSet rs;
            String sql;

            sql = SQLRequest.updateAnimal(animal);
            System.out.println(sql);
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
