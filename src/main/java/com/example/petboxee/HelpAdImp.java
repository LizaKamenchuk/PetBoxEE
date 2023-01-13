package com.example.petboxee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class HelpAdImp {
    private ArrayList<HelpAd> helpAds = new ArrayList<>();
    private Connection connection = null;

    public ArrayList<HelpAd> initializeHelpAds()
    {
    PreparedStatement ps;
    ResultSet rs;
    HelpAd helpAd;
        try {
        Class.forName(SQLData.DRIVER);
        System.out.println("Диск успешно загружен!");

        if (connection == null) {
            connection = DriverManager.getConnection(SQLData.URL, SQLData.USER, SQLData.PASSWORD);
            System.out.println("Успешное подключение к базе данных!");
        } else System.out.println("Вы пытаетесь подключиться к базе данных, но вы уже подключены!");

        String[] strings = {"*"};
        String sql = SQLRequest.selectHelpAds();
        ps = connection.prepareStatement(sql);
        rs = ps.executeQuery(sql);

        while (rs.next()) {
            helpAd = new HelpAd();
            helpAd.setId(rs.getInt("id"));
            helpAd.setType(rs.getString("type"));
            helpAd.setDescription(rs.getString("description"));
            helpAd.setContactName(rs.getString("contactName"));
            helpAd.setContactEmail(rs.getString("contactEmail"));
            helpAd.setAddress(rs.getString("address"));
            helpAd.setShelterName(rs.getString("shelterName"));
            helpAds.add(helpAd);
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
        return helpAds;
}

    public int insertHelpAd(HelpAd helpAd){
        int i=0;

        try {
            Class.forName(SQLData.DRIVER);
            System.out.println("Диск успешно загружен!");

            if (connection == null) {
                connection = DriverManager.getConnection(SQLData.URL, SQLData.USER, SQLData.PASSWORD);
                System.out.println("Успешное подключение к базе данных!");
            } else System.out.println("Вы пытаетесь подключиться к базе данных, но вы уже подключены!");

            System.out.println("В helpAdimp: " + helpAd.toString());

            PreparedStatement ps;
            ResultSet rs;
            String sql;

            sql = SQLRequest.insertHelpAd(helpAd, "helpAds");
            System.out.println(sql);
            ps = connection.prepareStatement(sql);
            i = ps.executeUpdate(sql);
            System.out.println(i);

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
