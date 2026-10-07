public class EBook extends Book {
    private double fileSize;

    public EBook(int id, String title, String author, int isbn, int year, double filesize) {
        super(id, title, author, isbn, year);
        this.fileSize = filesize;
    }

    public double getFilesize() {
        return fileSize;
    }
    
    public void setFilesize(double filesize) {
        this.fileSize = filesize;
    }

}
