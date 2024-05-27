
import entity.*;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import services.*;
import util.*;
public class GraphMain {
    public static void main(String[] args) {
       int maxVerts = GraphView.promptMaxVertices();
       GraphServices graphServices = GraphServiceFactory.createGraphService(maxVerts);
       ArrayList<String> vertices = new ArrayList<>();
       ArrayList<String> edges = new ArrayList<>();

       addVertices(graphServices, vertices, maxVerts);
       addEdges(graphServices, edges);

       displayAdjacencyMatrix(graphServices);
       kirimData(vertices, edges);
    }
    // kirim data
    public static void kirimData(ArrayList<String> vertices, ArrayList<String> edges) {
        try {
            // URL endpoint untuk POST request
            URL url = new URL("https://tubesprakpro.bhadrikais.my.id/graph");

            // Membuat HttpURLConnection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            // Data JSON body yang akan dikirim
            StringBuilder jsonDataBuilder = new StringBuilder();
            jsonDataBuilder.append("{\n");
            jsonDataBuilder.append("    \"vertices\": [\n");
            for (int i = 0; i < vertices.size(); i++) {
                jsonDataBuilder.append("        { \"name\": \"").append(vertices.get(i)).append("\" }");
                if (i < vertices.size() - 1) {
                    jsonDataBuilder.append(",");
                }
                jsonDataBuilder.append("\n");
            }
            jsonDataBuilder.append("    ],\n");
            jsonDataBuilder.append("    \"edges\": [\n");
            for (int i = 0; i < edges.size(); i++) {
                jsonDataBuilder.append("        ").append(edges.get(i));
                if (i < edges.size() - 1) {
                    jsonDataBuilder.append(",");
                }
                jsonDataBuilder.append("\n");
            }
            jsonDataBuilder.append("    ]\n");
            jsonDataBuilder.append("}");

            String jsonData = jsonDataBuilder.toString();

            // Mengirim data JSON body
            OutputStream os = conn.getOutputStream();
            byte[] input = jsonData.getBytes("utf-8");
            os.write(input, 0, input.length);

            // Menerima respons dari server
            int responseCode = conn.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private static void addVertices(GraphServices graphServices, ArrayList<String> vertices, int maxVertices) {
        String[] expectedTypes = {"Dosen", "Mata Kuliah", "Mahasiswa"};
        for (String expectedType : expectedTypes) {
            String name = GraphView.promptName(expectedType);
            switch (expectedType) {
                case "Dosen":
                    vertices.add(name);
                    graphServices.addVertex(new Dosen(name));
                    break;
                case "Mata Kuliah":
                    vertices.add(name);
                    graphServices.addVertex(new Kelas(name));
                    break;
                case "Mahasiswa":
                    vertices.add(name);
                    graphServices.addVertex(new Mahasiswa(name));
                    break;
                default:
                    System.out.println("Tipe vertex tidak valid.");
                    break;
            }
        }

        // Check if the user wants to add more vertices
        System.out.print("Apakah Anda ingin menambahkan lebih banyak simpul ? (ya/tidak): ");
        String response = InputUtil.inputString("");
        if (response.equalsIgnoreCase("ya")) {
            while (graphServices.vertexCount() < maxVertices) {
                String vertexType = GraphView.promptVertexType();
                if (vertexType.equalsIgnoreCase("selesai")) {
                    break;
                }
                String name = GraphView.promptName(vertexType);
                switch (vertexType) {
                    case "Dosen":
                        vertices.add(name);
                        graphServices.addVertex(new Dosen(name));
                        break;
                    case "Mata Kuliah":
                        vertices.add(name);
                        graphServices.addVertex(new Kelas(name));
                        break;
                    case "Mahasiswa":
                        vertices.add(name);
                        graphServices.addVertex(new Mahasiswa(name));
                        break;
                    default:
                        System.out.println("Simpul tidak valid.");
                        break;
                }
            }
        }
    }





    private static void addEdges(GraphServices graphServices, ArrayList<String> edges) {
        String edgeInput;
        while (!(edgeInput = GraphView.promptEdge()).equalsIgnoreCase("selesai")) {
            String[] edgeTokens = edgeInput.split(" ");
            if (edgeTokens.length != 2) {
                GraphView.displayError("Format salah");
                continue;
            }

            String startLabel = edgeTokens[0];
            String endLabel = edgeTokens[1];

            // Periksa apakah kedua label tidak kosong
            if (startLabel.isEmpty() || endLabel.isEmpty()) {
                GraphView.displayError("Label tidak boleh kosong");
                continue;
            }

            // Periksa apakah vertex dengan label yang dimasukkan ada dalam graf
            if (!graphServices.vertexExists(startLabel) || !graphServices.vertexExists(endLabel)) {
                GraphView.displayError("Vertices not found");
                continue;
            }

            // Dapatkan tipe dari kedua vertex
            String startType = graphServices.getVertexType(startLabel);
            String endType = graphServices.getVertexType(endLabel);

            // Periksa apakah kedua vertex memiliki tipe yang berbeda
            if (!startType.equals(endType)) {
                // Tambahkan edge ke dalam graf
                edges.add("{\"from\": \""+startLabel+"\", \"to\": \""+endLabel+"\"}");
                graphServices.addEdge(startLabel, endLabel);
            } else {
                GraphView.displayError("Tidak bisa menambahkan edge antara vertex dengan tipe yang sama");
            }
        }
    }
    private static void displayAdjacencyMatrix(GraphServices graphService) {
        GraphView.displayAdjacencyMatrix(graphService.getAdjacencyMatrix());
    }
}

