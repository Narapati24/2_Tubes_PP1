package util;

public class MenuView {
    public static int displayMenuUtama(){
        System.out.println("Pilih operasi selanjutnya:");
        System.out.println("1. Tampilkan List Simpul");
        System.out.println("2. Tambah garis");
        System.out.println("3. Tampilkan matriks ketetanggaan");
        System.out.println("4. Selesai");
        return InputUtil.inputInt("Pilih");
    }
    public static int displayMenuTambahSimpul(){
        System.out.println("Tipe Simpul:");
        System.out.println("1. Tambah Dosen");
        System.out.println("2. Tambah Mata Kuliah");
        System.out.println("3. Tambah Mahasiswa");
        System.out.println("4. Tidak akan menambah");
        return InputUtil.inputInt("Pilih");
    }
    public static String displayMenuTampilSimpul(){
        System.out.println("Tipe Simpul Yang Ingin Di Lihat:");
        System.out.println("1. Dosen");
        System.out.println("2. Mata Kuliah");
        System.out.println("3. Mahasiswa");
        System.out.println("4. Semua");
        int choice = InputUtil.inputInt("Pilih");
        return switch (choice){
            case 1 -> "Dosen";
            case 2 -> "Kelas";
            case 3 -> "Mahasiswa";
            case 4 -> null;
            default -> "gaada";
        };
    }
}
