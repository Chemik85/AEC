package pl.lison.aec.model;


public class Nemesis {
    private String name;
    private int level;

    public Nemesis() {
    }

    public Nemesis(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public Nemesis(String nemesisName, String nemesisLevel) {
        this.name = nemesisName;
        this.level = Integer.parseInt(nemesisLevel);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Nemesis{" +
                "name='" + name + '\'' +
                ", level=" + level +
                '}';
    }
}
