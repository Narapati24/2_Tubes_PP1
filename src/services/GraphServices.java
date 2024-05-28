package services;

import  entity.Vertex;

import java.util.ArrayList;

public interface GraphServices {
    void addVertex(Vertex v);
    void addEdge(String startLabel, String endLabel);
    void displayAdjacenyMatrix();
    String getAdjacencyMatrix();
    boolean vertexExists(String label);
    int vertexCount(); // Tambahkan metode untuk mendapatkan jumlah vertex
    String getVertexType(String label);

    ArrayList<String> getAllLabel();

    ArrayList<String> getAllEdges();
}
