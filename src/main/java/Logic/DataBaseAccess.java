package Logic;

import Model.IAnimal;

import java.sql.*;
import java.util.Date;

public class DataBaseAccess {
    static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/zoo";
    static final String USER = "postgres";
    static final String PASS = "1111";
    Connection connection;
    boolean success = true;

    {
        try {
            connection = DriverManager
                    .getConnection(DB_URL, USER, PASS);

        } catch (SQLException e) {
            System.out.println("Connection Failed");
        }
    }


    public void addAnimal(IAnimal animal) throws SQLException {
        String name = animal.getName();
        String species = animal.getSpecies().toString();

        String str = "SELECT * FROM cage";
        boolean flag = true;
        ResultSet resultSet = connection.createStatement().executeQuery(str);
        Statement statement = connection.createStatement();
        while (resultSet.next()) {
            if((resultSet.getString("access").equals("1"))
                    &(resultSet.getString(animal.getSpecies().toString()).equals("1")))
            {
                String update = "UPDATE cage SET access = 0 WHERE idcage = " + resultSet.getString("idcage");
                String insert = "INSERT INTO animal VALUES ('" + name + "','" + species + "'," + resultSet.getString("idcage") + ")";
                statement.execute(update);
                statement.execute(insert);
                flag = false;
                success = true;
                System.out.println("Заселили");
                break;
            }
        }
        if(flag){
            System.out.println("Мест нет");
            success = false;
        }
        statement.close();
    }

    public void removeAnimal(IAnimal animal) throws SQLException {
        boolean flag = true;
        String str = "SELECT * FROM animal";
        ResultSet resultSet = connection.createStatement().executeQuery(str);
        while (resultSet.next()){
            if (resultSet.getString("name").equals(animal.getName())){
                String delete = "DELETE FROM animal WHERE name = '" + animal.getName() + "'";
                String update = "UPDATE cage SET access = 1 WHERE idcage = " + resultSet.getString("idcage");
                Statement statement = connection.createStatement();
                statement.execute(delete);
                statement.execute(update);
                statement.close();
                flag = false;
                success = true;
                System.out.println("Выселили");
            }
        }
        resultSet.close();
        if(flag){
            success = false;
            System.out.println("Такого животного нет");
        }
    }



    public void addInLog(IAnimal animal) throws SQLException {
        String result;
        if(success){
            result = "success";
        }else{
            result = "Not success";
        }

        String str = "INSERT INTO log VALUES (default, '" +         new InhibitionLog.Builder().
               withInDate(new Date()).
                withName(animal.getName()).
                withSpecies(animal.getSpecies()).
                withResult(result).
                build().toString() + "')";

        Statement statement = connection.createStatement();
        statement.executeUpdate(str);
        statement.close();
    }

    public void addOutLog(IAnimal animal) throws SQLException {
        String result;
        if(success){
            result = "success";
        }else{
            result = "Not success";
        }

        String str = "INSERT INTO log VALUES (default, '" +         new InhibitionLog.Builder().
                withOutDate(new Date()).
                withName(animal.getName()).
                withSpecies(animal.getSpecies()).
                withResult(result).
                build().toString() + "')";

        Statement statement = connection.createStatement();
        statement.executeUpdate(str);
        statement.close();
    }

    public void getLog() throws SQLException {
        String str = "SELECT * FROM log";
        ResultSet resultSet = connection.createStatement().executeQuery(str);
        while (resultSet.next()){
            System.out.println(resultSet.getString("log"));
        }
        resultSet.close();
    }

    public void clearLog() throws SQLException{
        String str = "DELETE FROM log";
        Statement statement = connection.createStatement();
        statement.execute(str);
        statement.close();
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }
}




