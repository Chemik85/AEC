package pl.lison.aec.model;

public class Mage {
    private String name;

    public Mage() {
    }

    public Mage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Mage{" +
                "name='" + name + '\'' +
                '}';
    }
}
