package com.geekbrains.lector;


import java.sql.*;

public class MainSQL {
    private static Connection connection;//
    private static Statement statement;//Позволяет отправлять запросы в БД
    private static PreparedStatement preparedStatement;//Подготовленный запрос - позволяет недопустить
    //введения неверных данных со стороны пользователя.

    public static void main(String[] args) {
        try {
            connect();
            //создадим таблицу students
            //statement.executeUpdate("CREATE TABLE students (id INT AUTO_INCREMENT, name VARCHAR(20), score INT);");

            //пример вставки строки в SQLite.
            statement.executeUpdate("INSERT INTO students (name, score) VALUES ('Bob3', 100)");

            //Если хотим заменить какую-то строчку:
            statement.executeUpdate("UPDATE students SET score = 12 WHERE id = 1;");

            //Удаление:
            statement.executeUpdate("DELETE FROM students WHERE id = 2;");

            //Если хотим полностью почистить таблицу:
            statement.executeUpdate("DELETE FROM students;");

            //Если хотим удалить таблицу:
            //statement.executeUpdate("DROP TABLE students;");

            //Для ускорения работы БД:
            connection.setAutoCommit(false);

            //Для замера времени обработки табли БД:
            long time = System.currentTimeMillis();

            //Для PreparedStatement подготавливаем предварительные данные, а затем отправляем
            //их в БД:
            for (int i=0; i<10000; i++){//если не оключать AutoCommit, то каждый цикл - отдельная
                //транзакция.
                preparedStatement.setString(1, "Bob"+(i+1));
                preparedStatement.setInt(2, 50);
                //Когда мы не знаем, какой объект подставить, можно использовать
                //preparedStatement.setObject();
                preparedStatement.executeUpdate();
            }
            connection.commit();//в цикле формируются все 10 тыс. запросов и этой строкой отправляются
            //одной транзакцией
            System.out.println(System.currentTimeMillis()-time);

            //ResultSet - это интерфейс, который наследуется от  AutoCloseable, поэтому
            //потенциально мы его можем закрывать в конструкции try - catch.
            //Если мы запрашиваем строку, а в таблице в этом столбце int (или наоборот),
            //программа может выдать Exception, а может и не выдать - это зависит от реализации
            //драйвера JDBC.
            //запрос всех строк из таблицы: Если вместо * поставить "name" или "score", то запрос
            //выдаст только интересующие столбцы.
            try (ResultSet rs = statement.executeQuery("SELECT * FROM students;")) {
                //затем перебираем все полученные строки:
                while (rs.next()) {
                    //System.out.println(rs.getInt(1) + " " + rs.getString("name") + " " + rs.getInt("score"));
                }
            }catch (SQLException e){
                e.printStackTrace();
            }


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

            //Подготовленный запрос проходит прекомпиляцию и выдает Exception, если данные
            //не соответствуют:
            preparedStatement = connection.prepareStatement("INSERT INTO students (name, score) VALUES (?, ?);");

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
