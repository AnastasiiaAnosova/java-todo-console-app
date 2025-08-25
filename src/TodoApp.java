import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class TodoApp {
    private static final String FILE_NAME = "task.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- TODO list ---");
        System.out.println("1. Načíst seznam ze souboru");
        System.out.println("2. Vytvořit nový seznam");
        System.out.print("Zvolte možnost: ");

        ArrayList<String> tasks;

        if (scanner.hasNextInt()) {
            int startChoice = scanner.nextInt();
            scanner.nextLine();
            if (startChoice == 1) {
                tasks = loadTasksFromFile(FILE_NAME);
                System.out.println("Načtený seznam obsahuje " + tasks.size() + " úkolů.");
            } else {
                tasks = new ArrayList<>();
                System.out.println("Vytvořen nový prázdný seznam.");
            }
        } else {
            System.out.println("Neplatná volba, začínáme s prázdným seznamem.");
            scanner.nextLine();
            tasks = new ArrayList<>();
        }

        boolean running = true;

        while (running) {
            System.out.println("--- TODO list ---");
            System.out.println("1. Přidat úkol");
            System.out.println("2. Vypsat úkoly");
            System.out.println("3. Odstranit úkol");
            System.out.println("4. Uložit úkoly");
            System.out.println("5. Načíst úkoly znovu ze souboru");
            System.out.println("6. Vyčistit seznamu");
            System.out.println("7. Konec");
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
                    if (!scanner.hasNextInt()) {
                        System.out.println("Nesprávný vstup! Zkus to znovu!");
                        scanner.nextLine();
                        break;
                    }
                    int index = scanner.nextInt() - 1;
                    //!tasks.isEmpty()
                    if (index >= 0 && index < tasks.size()) {
                        tasks.remove(index);
                        System.out.println("Úkol odstraněn!");
                    } else {
                        System.out.println("Neplatné číslo.");
                    }
                    break;
                case 4:
                    saveTasksToFile(tasks, FILE_NAME);
                    System.out.println("Úkoly uloženy do souboru.");
                    break;
                case 5:
                    tasks = loadTasksFromFile(FILE_NAME);
                    System.out.println("Úkoly znovu načteny ze souboru");
                    break;
                case 6:
                    //tasks = new ArrayList<>();
                    tasks.clear();
                    saveTasksToFile(tasks, FILE_NAME);
                    System.out.println("Seznam byl vyprázdněn");
                    break;
                case 7:
                    running = false;
                    //saveTasksToFile(tasks, FILE_NAME);
                    break;
                default:
                    System.out.println("Neplatná volba.");

            }
        }
        scanner.close();
        System.out.println("Aplikace ukončena");
    }

    private static void saveTasksToFile(ArrayList<String> tasks, String fileName) {
        try(PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (String task : tasks) {
                writer.println(task);
            }
        } catch (IOException e) {
            System.out.println("Chyba při ukládání: \" + e.getMessage()");
        }
    }

    private static ArrayList<String> loadTasksFromFile(String fileName) {
        ArrayList<String> tasks = new ArrayList<>();
        File file = new File(fileName);
        if (file.exists()) {
            try (Scanner fileScanner = new Scanner(file)) {
                while (fileScanner.hasNextLine()) {
                    tasks.add(fileScanner.nextLine());
                }
                System.out.println("Úkoly načteny ze souboru.");
            }catch (IOException e) {
                System.out.println("Chyba při čtení: \" + e.getMessage()");
            }
        } else {
            System.out.println("Soubor neexistuje, začínáme s prázdným seznamem");
        }
        return tasks;
    }
}
