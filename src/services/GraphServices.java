package services;

import  entity.Vertex;
public interface GraphServices {
    void addVertex(Vertex v);
    void addEdge(String startLabel, String endLabel);
    void displayAdjacenyMatrix();
    String getAdjacencyMatrix();
    boolean vertexExists(String label);
    int vertexCount(); // Tambahkan metode untuk mendapatkan jumlah vertex
    String getVertexType(String label);
}
