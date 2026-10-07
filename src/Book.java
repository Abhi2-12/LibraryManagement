public class Book {
    private int id;
    private String title;
    private String author;
    private int isbn;
    private int year;


    public Book(int id, String title, String author, int isbn, int year ) {

    this.id = id;
    this.title = title;
    this.author = author;
    this.isbn = isbn;
    this.year = year; 
    }

    public int getId() {
        return id;
    }
    
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getIsbn() {
        return isbn;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if(year > 0) {
            this.year = year;
        }
    }

}


