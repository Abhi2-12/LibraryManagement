import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        Book book1 = new Book(1, "Math", "Abhi", 3456789, 2002);
        Book book2 = new Book(2, "Java", "Abhi", 123456, 2026);
        Book book3 = new Book(3, "English", "Abir", 456789, 2016);

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        while (true) {

            System.out.println("\n===== Library Management System =====");
            System.out.println("1. Show all books");
            System.out.println("2. Search book by ID");
            System.out.println("3. Search book by title");
            System.out.println("4. Borrow book");
            System.out.println("5. Return book");
            System.out.println("6. Show available books");
            System.out.println("7. Show borrowed books");
            System.out.println("8. Remove book");
            System.out.println("9. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    library.showBooks();
                    break;

                case 2:
                    System.out.println("Enter the book id : ");
                    int id = scanner.nextInt();
                    Book foundBook = library.findBookByID(id);

                    if (foundBook != null) {
                        foundBook.displayInfo();
                    }else {
                        System.out.println("Book not found...");
                    }
                    break;

                //Search by title
                case 3:
                    System.out.println("Enter Title : ");
                    scanner.nextLine();

                    String title = scanner.nextLine();     
                    Book foundBook1 = library.findBookByTitle(title);

                    if (foundBook1 != null) {
                        foundBook1.displayInfo();
                    }else {
                        System.out.println("Book not found... ");
                    }
                    break;
                //Borrow

                case 4:
                    System.out.println("Enter book id to borrow");
                    int id1 = scanner.nextInt();
                    library.borrowBookById(id1);
                    break;

                    //Return book
                case 5:
                    System.out.println("Enter Book id to return");
                    int id3 = scanner.nextInt();
                    library.returnById(id3);
                    break;

                    //show available book
                case 6:
                    library.showAvailableBooks();
                    break;

                case 7:
                    library.showBorrowedBooks();
                    break;

                case 8:
                    System.out.println("Enter book id to remove book");
                    int id4 = scanner.nextInt();
                    library.removeBookById(id4);
                    break;

                case 9:
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}