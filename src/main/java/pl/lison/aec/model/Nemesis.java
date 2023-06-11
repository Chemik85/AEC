package pl.lison.aec.model;

public class Nemesis {
    private String name;
    private int level;
    private Content content;

    public Nemesis() {
    }

//    public Nemesis(String name, int level, Content content) {
//        this.name = name;
//        this.level = level;
//        this.content = content;
//    }

    public Nemesis(String nemesisName, String nemesisLevel, Content content) {
        this.name = nemesisName;
        this.level = Integer.parseInt(nemesisLevel);
        this.content = content;
    }

    public Nemesis(String nemesisName, String nemesisLevel, String content) {

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

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Nemesis{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", content=" + content.getName() +
                '}';
    }
}
