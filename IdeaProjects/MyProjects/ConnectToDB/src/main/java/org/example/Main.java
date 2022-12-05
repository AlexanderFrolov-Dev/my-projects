package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
//    private static Connection connection;

    public static void main(String[] args) {
        System.out.println("Hello world!");

////        Connection connection = DriverManager.getConnection("jdbc:sqlserver://172.16.16.225/sitex_esrn_main", "test", "123456Qwe");
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
////        connection = DriverManager.getConnection("jdbc:sqlserver://172.16.16.225;databaseName=sitex_esrn_main", "test", "123456Qwe");
//        connection = DriverManager.getConnection("jdbc:sqlserver://172.16.16.225;databaseName=sitex_esrn_main;encrypt = true;trustServerCertificate=true;");
//        boolean con = connection.isValid(30);
//
//        System.out.println(con);

//        try {
//            Class.forName("org.gjt.mm.mysql.Driver").newInstance();
//        } catch (Exception e) {
//            System.out.println("Error 1");
//            System.out.println(e.getStackTrace());
//        }
//        try {
//            connection = DriverManager.getConnection("jdbc:mysql://192.168.149.153:3306/databaseName=departament", "root", "ImBc403695");
//        } catch (Exception e) {
//            System.out.println("Error 2");
//            System.out.println(e.getStackTrace());
//        }

        String url = "jdbc:mysql://192.168.149.153:3306/departament";
        String user = "newuser";
        String pass = "403695_Qwe";
//        String user = "root";
//        String pass = "ImBc403695";
//        String user = "manager@manager.com";
//        String pass = "11111111";

        try {
            Connection connection = DriverManager.getConnection(url, user, pass);

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM categories");
            while (resultSet.next()) {
                String testQuery = resultSet.getString("id");
                System.out.println(testQuery);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        String url = "jdbc:mysql://localhost:3306/shop_toys";
//        String user = "root";
//        String pass = "pr0bn14ek";
//
//        try {
//            Connection connection = DriverManager.getConnection(url, user, pass);
//
//            Statement statement = connection.createStatement();
//
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM customers");
//            while (resultSet.next()) {
//                String testQuery = resultSet.getString("cust_id");
//                System.out.println(testQuery);
//            }
//
//            resultSet.close();
//            statement.close();
//            connection.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}