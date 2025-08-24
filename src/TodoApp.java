import java.util.ArrayList;
import java.util.Scanner;

public class TodoApp {
    public static void main(String[] args) {
        ArrayList<String> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("--- TODO list ---");
            System.out.println("1. Přidat úkol ");
            System.out.println("2. Vypsat úkoly");
            System.out.println("3. Odstranit úkol");
            System.out.println("4. Konec");
            System.out.print("Zvolte možnost: ");

//            try {
//                int choice = scanner.nextInt();
//                scanner.nextLine();
//            } catch(Exception ex) {
//                System.out.println("");
//                scanner.nextLine();
//            }
            if (!scanner.hasNextInt()) {
                System.out.println("To není číslo! Zkus to znovu!");
                scanner.nextLine();
                continue;
            }
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Zadej nový úkol: ");
                    String task = scanner.nextLine();
                    tasks.add(task);
                    System.out.println("Úkol přidán!");
                    break;
                case 2:
                    //tasks.size() <= 0 OR tasks.isEmpty()
                    if (tasks.size() > 0 ) {
                        System.out.println("Seznam úkolů:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + ". " + tasks.get(i));
                        }
                    } else {
                        System.out.println("Seznam je prázdný!");
                    }
                    break;
                case 3:
                    System.out.println("Zadej číslo úkolu k odstranění: ");
                    int index = scanner.nextInt() - 1;
                    if (index >= 0 && index < tasks.size()) {
                        tasks.remove(index);
                        System.out.println("Úkol odstraněn!");
                    } else {
                        System.out.println("Neplatné číslo.");
                    }
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Neplatná volba.");

            }
        }
        scanner.close();
        System.out.println("Aplikace ukončena");
    }
}
