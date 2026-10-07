public class Main {
    public static void main(String[] args) {

        /*Book book1 = new Book();
        book1.id = 1;
        book1.title = "math advance";
        book1.author = "abhi";
        book1.isbn = 234567894;
        book1.year = 2002;

     */

        Book book1 = new Book(1, "math", "abhi", 3456789, 2002);
        //Book book2 = new Book(2, "English", "abir", 0, 2016);

        System.out.println(book1.getId() + " " + book1.getTitle() + " " + book1.getAuthor());
        book1.setYear(-700);
        System.out.println(book1.getYear());
        
        
        
    }
    
}
