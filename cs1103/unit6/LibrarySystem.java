import java.util.Scanner;

public class LibrarySystem {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        GenericCatalog<Integer> catalog = new GenericCatalog<>();

        while (true) {

            System.out.println("\nLibrary Catalog");
            System.out.println("1. Add Item");
            System.out.println("2. Remove Item");
            System.out.println("3. View Catalog");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();

                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();

                    System.out.print("Enter Item ID: ");
                    int id = scanner.nextInt();

                    LibraryItem<Integer> item =
                            new LibraryItem<>(title, author, id);

                    catalog.addItem(item);
                    break;

                case 2:

                    try {

                        System.out.print("Enter Item ID to remove: ");
                        int removeID = scanner.nextInt();

                        catalog.removeItem(removeID);

                    } catch (Exception e) {

                        System.out.println(e.getMessage());
                    }

                    break;

                case 3:

                    catalog.viewCatalog();
                    break;

                case 4:

                    System.out.println("Exiting system...");
                    return;

                default:

                    System.out.println("Invalid choice.");

            }
        }
    }
}