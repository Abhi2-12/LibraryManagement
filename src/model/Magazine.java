package model;

public class Magazine extends LibraryItem {
    private int issueNumber;
    private String publisher;

    public Magazine(int id, String title, int year, int issueNumber, String publisher) {
        super(id, title, year);
        this.issueNumber = issueNumber;
        this.publisher = publisher;
    }

    public int getIssueNumber () {
        return issueNumber;
    }

    public String getPublisher () {
        return publisher;
    }


    public void setIssueNumber (int issueNumber) {
        this.issueNumber = issueNumber;
    }

    public void setPublisher (String publisher) {
        this.publisher = publisher;
    }

    @Override 
    public void displayInfo() {

        System.out.println("ID : " + getId());
        System.out.println("Title : " + getTitle());
        System.out.println("Year : " + getYear());
        System.out.println("Issue Number : " + getIssueNumber());
        System.out.println("Publisher : " + getPublisher());

    }

}
