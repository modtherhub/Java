import java.util.ArrayList;
import java.util.List;

// Generic Catalog Class
public class GenericCatalog<T> {

    private List<LibraryItem<T>> items;

    public GenericCatalog() {
        items = new ArrayList<>();
    }

    // Add item
    public void addItem(LibraryItem<T> item) {
        items.add(item);
        System.out.println("Item added successfully.");
    }

    // Remove item
    public void removeItem(T itemID) throws Exception {

        boolean found = false;

        for (LibraryItem<T> item : items) {

            if (item.getItemID().equals(itemID)) {
                items.remove(item);
                System.out.println("Item removed successfully.");
                found = true;
                break;
            }
        }

        if (!found) {
            throw new Exception("Error: Item with ID " + itemID + " not found.");
        }
    }

    // View catalog
    public void viewCatalog() {

        if (items.isEmpty()) {
            System.out.println("Catalog is empty.");
            return;
        }

        for (LibraryItem<T> item : items) {
            System.out.println(item);
        }
    }
}