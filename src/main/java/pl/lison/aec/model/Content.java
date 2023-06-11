package pl.lison.aec.model;

public class Content {

    private String name;

    public Content() {
    }

    public Content(String name) {
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
        return "Content{" +
                "name='" + name + '\'' +
                '}';
    }
}
