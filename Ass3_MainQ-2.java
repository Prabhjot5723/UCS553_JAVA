package library.main;

import library.model.LibraryResource;
import library.service.Book;
import library.service.DigitalResource;
import library.service.Printable;
import library.util.InputValidator;

public class Main {
    public static void main(String[] args) {
        LibraryResource.setLibraryName("Central Smart Library");

        LibraryResource[] resources = new LibraryResource[5];
        resources[0] = new Book(101, "Java Programming", "James Gosling");
        resources[1] = new DigitalResource(102, "Data Structures E-Book", "Mark Allen");
        resources[2] = new Book(103, "Object Oriented Programming", "Robert Martin");
        resources[3] = new DigitalResource(104, "Database Systems", "Abraham Silberschatz");
        resources[4] = new Book(105, "Computer Networks", "Andrew Tanenbaum");

        int[] overdueDays = {5, 10, 0, 7, 3};
        double totalFine = 0;

        System.out.println("========================================");
        System.out.println("       SMART LIBRARY RESOURCE SYSTEM");
        System.out.println("       Library: " + LibraryResource.getLibraryName());
        System.out.println("========================================");

        for (int i = 0; i < resources.length; i++) {
            LibraryResource resource = resources[i];

            if (!InputValidator.validateResourceId(resource.getResourceId())) {
                System.out.println("Invalid Resource ID: " + resource.getResourceId());
                continue;
            }

            if (!InputValidator.validateFineDays(overdueDays[i])) {
                System.out.println("Invalid fine days for Resource ID: " + resource.getResourceId());
                continue;
            }

            if (resource instanceof Printable) {
                ((Printable) resource).printDetails();
            }

            double fine = resource.calculateFine(overdueDays[i]);
            System.out.println("Overdue Days  : " + overdueDays[i]);
            System.out.println("Fine          : Rs. " + fine);
            totalFine += fine;
            System.out.println("----------------------------------------");
        }

        System.out.println();
        System.out.println("========================================");
        System.out.println("Total Fine of All Resources: Rs. " + totalFine);
        System.out.println("========================================");
        LibraryResource.displayTotalResources();
    }
}
