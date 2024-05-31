
import entity.*;
import services.*;
import util.*;
public class GraphMain {
    public static void main(String[] args) {
        //reset
        GraphAPI.kirimData("reset", "","");
        int maxVerts;
        while (true) {
            maxVerts = GraphView.promptMaxVertices();
            if (maxVerts < 2) {
                System.out.println("Nilai minimal untuk jumlah total simpul adalah 2 dalam graf");
            } else {
                break;
            }
        }

        GraphServices graphServices = GraphServiceFactory.createGraphService(maxVerts);

        while (true) {
            addVertices(graphServices, maxVerts);
            System.out.println("Pilih operasi selanjutnya:");
            System.out.println("1. Tambah garis");
            System.out.println("2. Tampilkan matriks ketetanggaan");
            System.out.println("3. Selesai");
            System.out.print("Pilih: ");
            int operationChoice = InputUtil.inputInt("");

            switch (operationChoice) {
                case 1:
                    addEdges(graphServices);
                    break;
                case 2:
                    GraphView.displayAdjacencyMatrix(graphServices.getAdjacencyMatrix());
                    break;
                case 3:
                    GraphAPI.kirimData(graphServices.getAllLabel(), graphServices.getAllEdges());
                    System.exit(0);
                    break;
                default:
                    System.out.println("Pilihan tidak valid");
            }
        }
    }

    private static void addVertices(GraphServices graphServices, int maxVertices) {
        while (graphServices.vertexCount() < maxVertices) {
            String name, expectedType = null;
            int choice = MenuView.displayMenu();
            if (choice == 4) break;
            switch (choice) {
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

