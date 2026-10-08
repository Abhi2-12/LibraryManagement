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

   public Book findBookByID (int id) {

    for (Book book : books) {
        if (book.getId() == id) {
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
   }

   public void borrowBookById (int id) {

    for (Book book : books) {
        if (book.getId() == id) {
            try {
                book.borrow();
            } catch (BookNotAvailableException e) {
                System.out.println("book not available ");
            }
        }
    }


   }
}
