import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        // Create Book objects
        Book book1 = new Book(1, "Math", "Abhi", 3456789, 2002);
        Book book2 = new Book(2, "Java", "Abhi", 123456, 2026);
        Book book3 = new Book(3, "English", "Abir", 456789, 2016);

        // Overriding + interface
        book1.displayInfo();
        

        // Polymorphism
        LibraryItem item2 = new Book(4, "Java Programming", "Abhi", 123456, 2026);
        item2.displayInfo();

        // Inheritance
        Magazine mag = new Magazine(5, "Spider-Man", 2006, 5678, "ABC");

        // Polymorphism with Magazine
        LibraryItem item3 = new Magazine(6, "Science Monthly", 2026, 10, "ABC");
        item3.displayInfo();

        // Multilevel inheritance + interface
        EBook ebook = new EBook(7, "Java EBook", "Abhi", 987654, 2026, 5.5);
        ebook.download();

        // Method overloading
        // item2.search(4);
        // item2.search("Java");

        // ArrayList
        ArrayList<Book> books = new ArrayList<>();

        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(new Book(8, "Python", "John", 777777, 2025));

        // Get size
        System.out.println("Total books: " + books.size());

        // Get book using index
        System.out.println("First book: " + books.get(0).getTitle());

        // Loop through the list
        for (Book book : books) {
            System.out.println("Book title: " + book.getTitle());
        }

        // Check if an object exists
        System.out.println("Contains book1: " + books.contains(book1));

        // Remove using index
        books.remove(0);
        System.out.println("Books after remove: " + books.size());

        // Remove everything
        books.clear();
        System.out.println("Books after clear: " + books.size());


        //ArrayList<LibraryItem> + Polymorphism
        ArrayList<LibraryItem> items = new ArrayList<>();
        items.add(book1);
        items.add(mag);
        items.add(ebook);

        for (LibraryItem item : items) {
            item.displayInfo();
        }
        System.out.println(items.size());
        items.remove(mag);
        System.out.println(items.size());

        //exception handling
        //book borrow and return
        try {
            book1.borrow();
            book1.borrow();
            book1.returnBook();
            book1.returnBook();
            book1.borrow();
        } catch (BookNotAvailableException e) {
            System.out.println(e.getMessage());
        }


        //check library
        Library library = new Library();

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        library.removeBookById(2);

        library.showBooks();

        Book foundBook = library.findBookByID(10);
        if (foundBook != null) {
            foundBook.displayInfo();
        }else {
            System.out.println("not found");
        }
        

        library.borrowBookById(1);
        library.borrowBookById(1);
    }
}

