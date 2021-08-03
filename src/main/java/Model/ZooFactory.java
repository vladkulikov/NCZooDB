package Model;

public class ZooFactory {

    public IAnimal createAnimal (Species species, String name) {
        IAnimal IAnimal = null;
        IAnimal = switch (species) {
            case LEON -> new Leon(name, species);
            case GIRAFFE -> new Giraffe(name, species);
            case SQUIRREL -> new Squirrel(name, species);
            case PENGUIN -> new Penguin(name, species);
        };
        return IAnimal;
    }

}