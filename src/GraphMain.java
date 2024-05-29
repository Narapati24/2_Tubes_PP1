
import entity.*;
import services.*;
import util.*;
public class GraphMain {
    public static void main(String[] args) {
        //reset
       GraphAPI.kirimData("reset", "","");
       int maxVerts = GraphView.promptMaxVertices();
       GraphServices graphServices = GraphServiceFactory.createGraphService(maxVerts);

       addVertices(graphServices, maxVerts);
       addEdges(graphServices);
       displayAdjacencyMatrix(graphServices);
    }

    private static void addVertices(GraphServices graphServices, int maxVertices) {
        while (graphServices.vertexCount() < maxVertices) {
            String name, expectedType = null;
            int choice = MenuView.displayMenu();
            if (choice == 4) break;
            switch (choice){
                case 1 -> expectedType = "Dosen";
                case 2 -> expectedType = "Mata Kuliah";
                case 3 -> expectedType = "Mahasiswa";
                default -> {
                    System.out.println("Pilihan Tidak Ada");
                    continue;
                }
            }
            name = GraphView.promptName(expectedType);
            switch (expectedType) {
                case "Dosen" -> graphServices.addVertex(new Dosen(name));
                case "Mata Kuliah" -> graphServices.addVertex(new Kelas(name));
                case "Mahasiswa" -> graphServices.addVertex(new Mahasiswa(name));
                default -> System.out.println("Tipe vertex tidak valid.");
            }
            GraphAPI.kirimData("addVertice", name,"");
        }

        // Check if the user wants to add more vertices
        //System.out.print("Apakah Anda ingin menambahkan lebih banyak simpul ? (ya/tidak): ");
        //String response = InputUtil.inputString("");
        //if (response.equalsIgnoreCase("ya")) {
        //    while (graphServices.vertexCount() < maxVertices) {
        //        String vertexType = GraphView.promptVertexType();
        //        if (vertexType.equalsIgnoreCase("selesai")) {
        //            break;
        //        }
        //        String name = GraphView.promptName(vertexType);
        //        switch (vertexType) {
        //            case "Dosen":
        //                graphServices.addVertex(new Dosen(name));
        //                break;
        //            case "Mata Kuliah":
        //                graphServices.addVertex(new Kelas(name));
        //                break;
        //            case "Mahasiswa":
        //                graphServices.addVertex(new Mahasiswa(name));
        //                break;
        //            default:
        //                System.out.println("Simpul tidak valid.");
        //                break;
        //        }
        //   }
        //}
    }





    private static void addEdges(GraphServices graphServices) {
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
                graphServices.addEdge(startLabel, endLabel);
                GraphAPI.kirimData("addEdge", startLabel, endLabel);
            } else {
                GraphView.displayError("Tidak bisa menambahkan edge antara vertex dengan tipe yang sama");
            }
        }
    }
    private static void displayAdjacencyMatrix(GraphServices graphService) {
        GraphView.displayAdjacencyMatrix(graphService.getAdjacencyMatrix());
    }
}

