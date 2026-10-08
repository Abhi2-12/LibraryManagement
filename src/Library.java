import java.util.ArrayList;
import java.util.Iterator;

public class Library {

   private ArrayList<Book> books = new ArrayList<>();
 
   public void addBook(Book book) {

    books.add(book);
   }

   public void showBooks() {
    for (Book book : books) {
         book.displayInfo();
    }
   }

   //find by id
   public Book findBookByID (int id) {

    for (Book book : books) {
        if (book.getId() == id) {
            return book;
        }
    }
    return null;

   }

   //find by tittle
   public Book findBookByTitle (String title) {

    for (Book book : books) {
        if (book.getTitle().equals(title)) {
            return book;
        }
    }
    return null;
   }


   //remove book

   public void removeBookById(int id) {

    Iterator<Book> iterator = books.iterator();

    while (iterator.hasNext()) {

        Book book = iterator.next();

        if (book.getId() == id) {
            iterator.remove();
            return;
        }
    }
    System.out.println("Book not found");
   }


   //borrow 
   public void borrowBookById (int id) {

    boolean found = false;

    for (Book book : books) {
        if (book.getId() == id) {

            found = true;

            try {
                book.borrow();
            } catch (BookNotAvailableException e) {
                System.out.println("Book not available ");
            }
            return;
        }
        
    }

    if (!found) {
        System.out.println("Book not found");
    }
    
   }

    //return method
    public void returnById (int id) {

        for (Book book : books) {
            if (book.getId() == id) {
                book.returnBook();
                return;
            }
        }
        System.out.println("Book not found");
    }

    //show available books
    public  void showAvailableBooks() {

        for (Book book : books) {
            if (!book.isBorrowed()) {
                book.displayInfo();
            }
        }
    }

    //show borrowed books 
    public void showBorrowedBooks() {

        for (Book book : books) {
            if (book.isBorrowed()) {
                book.displayInfo();
            }
        }
    }


}

