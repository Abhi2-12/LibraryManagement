public class Book extends LibraryItem {

    private String author;
    private int isbn;



    public Book(int id, String title, String author, int isbn, int year ) {

    super(id, title, year);
    this.author = author;
    this.isbn = isbn;

    }

    public String getAuthor() {
        return author;
    }

    public int getIsbn() {
        return isbn;
    }


    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }


}


