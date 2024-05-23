package entity;

public class Mahasiswa implements Vertex {
    private String label;
    private final String type = "mahasiswa";

    public Mahasiswa(String label) {
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

