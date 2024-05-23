package util;

import java.util.Scanner;

public class InputUtil {
    private static Scanner scanner = new Scanner(System.in);
    public static Integer inputInt(String info) {
        System.out.print(info + " : ");
        while (true) {
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Input harus berupa angka, coba lagi: ");
            }
        }
    }

    public static Double inputDouble(String info) {
        System.out.print(info + " : ");
        while (true) {
            try {
                String input = scanner.nextLine();
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.print("Input harus berupa angka, coba lagi: ");
            }
        }
    }

    public static String inputString(String info) {
        System.out.print(info + " : ");
        return scanner.nextLine();
    }
}
