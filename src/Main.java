public class Main {
    public static void main(String[] args) {
        
        Book book1 = new Book(1, "math", "abhi", 3456789, 2002);
        //Book book2 = new Book(2, "English", "abir", 0, 2016);

        System.out.println(book1.getId() + " " + book1.getTitle() + " " + book1.getAuthor() + " " +book1.getIsbn() + " " + book1.getYear());
        book1.setYear(-700);
        System.out.println(book1.getYear());

        Magazine mag = new Magazine(1, "spiderman", 2006, 5678, "abc");
        System.out.println(mag.getId() + " " + mag.getTitle() + " " + mag.getYear() + " " + mag.getIssueNumber() + " " + mag.getPublisher());
           
    }
    
}
