package com.example.petboxee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AnimalImp {
    private ArrayList<Animal> animals = new ArrayList<>();
    private Connection connection = null;

    public ArrayList<Animal> initializeAnimals()
    {
        PreparedStatement ps;
        ResultSet rs;
        Animal animal;

        try {
            Class.forName(SQLData.DRIVER);
            System.out.println("Диск успешно загружен!");

            if (connection == null) {
                connection = DriverManager.getConnection(SQLData.URL, SQLData.USER, SQLData.PASSWORD);
                System.out.println("Успешное подключение к базе данных!");
            } else System.out.println("Вы пытаетесь подключиться к базе данных, но вы уже подключены!");

            String[] strings = {"*"};
            String sql = SQLRequest.selectAnimals();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery(sql);

            while (rs.next()) {
                animal = new Animal();
                animal.setPhoto(rs.getInt("photo"));
                animal.setId(rs.getInt("id"));
                animal.setType(rs.getString("type"));
                animal.setColor(rs.getString("color"));
                animal.setGender(rs.getString("gender"));
                animal.setName(rs.getString("name"));
                animal.setCharacters(rs.getString("characters"));
                animal.setAge(rs.getInt("age"));
                animal.setAgeMeasure(rs.getString("ageMeasure"));
                animal.setContactName(rs.getString("contactName"));
                animal.setContactEmail(rs.getString("contactEmail"));
                animal.setAddress(rs.getString("address"));
                animal.setShelterName(rs.getString("shelterName"));
                animals.add(animal);
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
        return animals;
    }

    public int insertAnimal(Animal animal){
        int i=0;

        try {
            Class.forName(SQLData.DRIVER);
            System.out.println("Диск успешно загружен!");

            if (connection == null) {
                connection = DriverManager.getConnection(SQLData.URL, SQLData.USER, SQLData.PASSWORD);
                System.out.println("Успешное подключение к базе данных!");
            } else System.out.println("Вы пытаетесь подключиться к базе данных, но вы уже подключены!");

            System.out.println("В animalimp: " + animal.toString());

            PreparedStatement ps;
            String sql;

            sql = SQLRequest.insertAnimal(animal, "animals");
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

