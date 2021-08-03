package Model;

import java.io.IOException;

public enum Species {
    LEON, GIRAFFE, SQUIRREL, PENGUIN;

    public static Species getSpecies(String name) throws IOException {
        Species species = null;

        switch (name.toUpperCase()) {
            case "LEON" -> species = Species.LEON;
            case "GIRAFFE" -> species = Species.GIRAFFE;
            case "SQUIRREL" -> species = Species.SQUIRREL;
            case "PENGUIN" -> species = Species.PENGUIN;
        }
        if (species == null) {
            throw new IOException("Incorrect species. Try again.");
        }
        return species;
    }
}