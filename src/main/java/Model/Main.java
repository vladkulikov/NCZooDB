package Model;

import Logic.ParserCommand;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {

        System.out.println("Типы команд:");
        System.out.println("check-in [species] [name] - позволяет поселить животное в клетку");
        System.out.println("check-out [species] [name] - позволяет выселить животное из клетки");
        System.out.println("log - вывод логов");
        System.out.println("clearlog - очистка логов");
        System.out.println("exit - выход из программы");
        ParserCommand parserCommand = new Logic.ParserCommand();
        parserCommand.getResult();

    }
}