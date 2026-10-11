package app;

import model.Book;
import service.Library;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

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
            System.out.println("9. Add book");
            System.out.println("10. Exit");

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
                    ArrayList<Book> foundBooks = library.findBookByTitle(title);

                    if (foundBooks.isEmpty()) {
                        System.out.println("No book found");
                    } else {
                        System.out.println("Book found : " + foundBooks.size());

                        for (Book book : foundBooks) {
                            book.displayInfo();
                        }
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
                    scanner.nextLine(); // Consume leftover newline

                    System.out.print("Enter book title: ");
                    String tittle = scanner.nextLine();

                    System.out.print("Enter author name: ");
                    String author = scanner.nextLine();

                    System.out.print("Enter publication year: ");
                    int year = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter ISBN: ");
                    String isbn = scanner.nextLine();

                    Book book = new Book(0, tittle, author, isbn, year);
                    library.addBook(book);
                    break;

                case 10:
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}