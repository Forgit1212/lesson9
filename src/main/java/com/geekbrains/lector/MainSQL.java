package com.geekbrains.lector;


import java.sql.*;

public class MainSQL {
    private static Connection connection;//
    private static Statement statement;//Позволяет отправлять запросы в БД
    //PreparedStatement preparedStatement;

    public static void main(String[] args) {
        try {
            connect();
            //clearTableEx();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    public static void connect() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:main.db");//открывает соединение с БД
            statement = connection.createStatement();//затем по готовому соединению можем создать запрос
            //preparedStatement = connection.prepareStatement("INSERT INTO students (name, score) VALUES (?, ?);");
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Unable to connect");
        }
    }

    public static void disconnect() {//отключаем подключенные ресурсы в обратном порядке
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
