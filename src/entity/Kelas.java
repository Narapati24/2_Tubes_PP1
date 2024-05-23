package entity;

public class Kelas implements Vertex {
    private String label;
    private final String type = "kelas";

    public Kelas(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public String getType() {
        return type;
    }
}
