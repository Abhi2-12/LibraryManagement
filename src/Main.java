public class Main {

    public static void main(String[] args) {

        Book book1 = new Book(1, "Math", "Abhi", 3456789, 2002);
        Book book2 = new Book(2, "Java", "Abhi", 123456, 2026);
        Book book3 = new Book(3, "English", "Abir", 456789, 2016);

        Library library = new Library();

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        System.out.println("All books:");
        library.showBooks();

        System.out.println("\nFind book:");
        Book foundBook = library.findBookByID(2);

        if (foundBook != null) {
            foundBook.displayInfo();
        } else {
            System.out.println("Book not found");
        }

        System.out.println("\nBorrow book:");
        library.borrowBookById(1);

        System.out.println("\nBorrow same book again:");
        library.borrowBookById(1);

        System.out.println("\nRemove book:");
        library.removeBookById(2);

        System.out.println("\nBooks after remove:");
        library.showBooks();

        System.out.println("\nFind book by title:");

        Book foundByTitle = library.findBookByTitle("Math");

        if (foundByTitle != null) {
            foundByTitle.displayInfo();
        } else {
            System.out.println("Book not found");
        }

        System.out.println("\nAvailable books:");
        library.showAvailableBooks();

        System.out.println("\nReturn book:");
        library.returnById(1);

        System.out.println("\nAvailable books after return:");
        library.showAvailableBooks();
        library.borrowBookById(99);
        library.removeBookById(99);

        System.out.println("\nBorrowed books:");
        library.borrowBookById(1);
        library.showBorrowedBooks();
    }
}