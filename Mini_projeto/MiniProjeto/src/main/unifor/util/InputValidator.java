package util;

import java.util.Scanner;

public class InputValidator {

    private static final Scanner scanner = new Scanner(System.in);

    public static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public static Integer readInteger(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("❌ Valor inválido. Digite um número inteiro.");
            }
        }
    }

    public static Long readLong(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                return Long.parseLong(input);
            } catch (NumberFormatException e) {
                System.out.println("❌ Valor inválido. Digite um número.");
            }
        }
    }

    public static boolean readConfirmation(String prompt) {
        System.out.print(prompt + " (S/N): ");
        String input = scanner.nextLine().trim().toUpperCase();
        return input.equals("S") || input.equals("SIM") || input.equals("Y") || input.equals("YES");
    }

    public static void waitForEnter() {
        System.out.print("\nPressione ENTER para continuar...");
        scanner.nextLine();
    }
}