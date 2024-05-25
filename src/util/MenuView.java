package util;

public class MenuView {
    public static int displayMenu(){
        System.out.println("=== Menu Simpul ===");
        System.out.println("1. Tambah Dosen");
        System.out.println("2. Tambah Mata Kuliah");
        System.out.println("3. Tambah Mahasiswa");
        System.out.println("4. Tidak akan menambah");
        System.out.println("============");
        return InputUtil.inputInt("Ingin menambahkan simpul?");
    }
}
