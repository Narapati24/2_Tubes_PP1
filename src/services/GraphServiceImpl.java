package services;

import entity.Vertex;

public class GraphServiceImpl implements GraphServices {
    private Vertex vertexList[];
    private int adjMat[][];
    private int nVerts;

    public GraphServiceImpl(int maxVerts) {
        vertexList = new Vertex[maxVerts];
        adjMat = new int[maxVerts][maxVerts];
        nVerts = 0;
        for (int i = 0; i < maxVerts; i++) {
            for (int j = 0; j < maxVerts; j++) {
                adjMat[i][j] = 0;
            }
        }
    }

    @Override
    public void addVertex(Vertex v) {
        vertexList[nVerts++] = v;
    }

    @Override
    public void addEdge(String startLabel, String endLabel) {
        int startIndex = findVertexIndex(startLabel);
        int endIndex = findVertexIndex(endLabel);
        if (startIndex != -1 && endIndex != -1) {
            adjMat[startIndex][endIndex] = 1; // Set to 1 to represent an edge exists
            adjMat[endIndex][startIndex] = 1; // For undirected graph, set both directions
        } else {
            System.out.println("Error: Vertices not found");
        }
    }

    @Override
    public void displayAdjacenyMatrix() {
        System.out.print("  ");
        for (int i = 0; i < nVerts; i++) {
            System.out.print(vertexList[i].getLabel() + " ");
        }
        System.out.println();
        for (int i = 0; i < nVerts; i++) {
            System.out.print(vertexList[i].getLabel() + " ");
            for (int j = 0; j < nVerts; j++) {
                System.out.print(adjMat[i][j] + " ");
            }
            System.out.println();
        }
    }


    private int findVertexIndex(String label) {
        for (int i = 0; i < nVerts; i++) {
            if (vertexList[i].getLabel().equals(label)) {
                return i;
            }
        }
        return -1; // not found
    }

    public String getAdjacencyMatrix() {
        StringBuilder matrix = new StringBuilder();
        for (int i = 0; i < nVerts; i++) {
            for (int j = 0; j < nVerts; j++) {
                matrix.append(adjMat[i][j]).append(" ");
            }
            matrix.append("\n");
        }
        return matrix.toString();
    }

    public boolean vertexExists(String label) {
        for (Vertex vertex : vertexList) {
            if (vertex.getLabel().equals(label)) {
                return true; // Vertex ditemukan
            }
        }
        return false; // Vertex tidak ditemukan
    }

    @Override
    public int vertexCount() {
        return nVerts;
    }
    @Override
    public String getVertexType(String label) {
        // Temukan vertex dengan label yang diberikan
        for (Vertex vertex : vertexList) {
            if (vertex.getLabel().equals(label)) {
                // Kembalikan tipe vertex
                return vertex.getType();
            }
        }
        // Kembalikan null jika vertex tidak ditemukan
        return null;
    }
}
