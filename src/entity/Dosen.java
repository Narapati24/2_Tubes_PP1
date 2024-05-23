package entity;

public class Dosen implements Vertex {
    private String label;
    private final String type = "dosen";

    public Dosen(String label) {
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

