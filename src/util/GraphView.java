package util;

public class GraphView {

    public static int promptMaxVertices() {
        return InputUtil.inputInt("Masukkan jumlah maksimal vertex:");
    }

    public static String promptVertexType() {
        return InputUtil.inputString("Tambah vertex (dosen, kelas, mahasiswa) atau ketik 'selesai' untuk selesai:");
    }

    public static String promptName(String vertexType) {
        return InputUtil.inputString("Masukkan nama " + vertexType + ":");
    }

    public static String promptEdge() {
        return InputUtil.inputString("Tambah edge (format: startLabel endLabel) atau ketik 'selesai' untuk selesai:");
    }

    public static void displayError(String message) {
        System.out.println("Error: " + message);
    }

    public static void displayAdjacencyMatrix(String matrix) {
        System.out.println("Matriks Ketetanggaan:");
        System.out.println(matrix);
    }
}
