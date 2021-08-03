package Logic;

import Logic.InhibitionLog;
import Model.Species;
import Model.ZooFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ParserCommand {

    ZooFactory zooFactory = new ZooFactory();
    Scanner keyboard = new Scanner(System.in);
    DataBaseAccess dataBaseAccess = new DataBaseAccess();

    public ParserCommand() throws IOException, IOException {
    }


    public void getResult() throws IOException, SQLException {
        while (true) {
            String command = keyboard.nextLine();
            String[] word = command.split("\\s");
            String c = word[0];
            switch (c) {
                case "check-in" -> {
                    dataBaseAccess.addAnimal(zooFactory.createAnimal(Species.getSpecies(word[1]), word[2]));
                    dataBaseAccess.addInLog(zooFactory.createAnimal(Species.getSpecies(word[1]), word[2]));
                }
                case "check-out" -> {
                    dataBaseAccess.removeAnimal(zooFactory.createAnimal(Species.getSpecies(word[1]), word[2]));
                    dataBaseAccess.addOutLog(zooFactory.createAnimal(Species.getSpecies(word[1]), word[2]));
                }
                case "log" -> dataBaseAccess.getLog();
                case "clearlog" -> dataBaseAccess.clearLog();
                case "exit" -> {
                    dataBaseAccess.closeConnection();
                    System.exit(1);
                }
                default -> System.out.println("Incorrect command. Try again.");
            }
        }
    }
}