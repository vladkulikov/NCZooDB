package Model;

public class AbstractIAnimal implements IAnimal {
    private final String name;
    private final Species species;

    public AbstractIAnimal(String name, Species species) {
        this.name = name;
        this.species = species;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Species getSpecies() {
        return species;
    }
}