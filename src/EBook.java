public class EBook extends Book implements Downloadable{
    private double fileSize;

    public EBook(int id, String title, String author, String isbn, int year, double filesize) {
        super(id, title, author, isbn, year);
        this.fileSize = filesize;
    }

    public double getFilesize() {
        return fileSize;
    }
    
    public void setFilesize(double filesize) {
        this.fileSize = filesize;
    }

    @Override 
    public void download() {
        System.out.println("EBook downloaded successfully");        
    }

}
