package util;

public class GraphView {

    public static int promptMaxVertices() {
        return InputUtil.inputInt("Masukkan maxsimal simpul");
    }

    public static String promptName(String vertexType) {
        return InputUtil.inputString("Masukkan Nama " + vertexType );
    }

    public static String promptEdge() {
        return InputUtil.inputString("Tambah garis (format: masukan nama simpul 1 dan 2) atau ketik 'selesai' untuk selesai");
    }

    public static void displayError(String message) {
        System.out.println("Error: " + message);
    }

    public static void displayAdjacencyMatrix(String matrix) {
        System.out.println("Matriks Ketetanggaan:");
        System.out.println(matrix);
    }
}
