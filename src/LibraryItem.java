public abstract class LibraryItem {

    private int id;
    private String title;
    private int year;

    public abstract void displayInfo();

    public int search(int id) {
        System.out.println("id");
        return id;
    }

    public String search(String title) {
        System.out.println("tittle");
        return title;
    }

    public LibraryItem(int id, String title, int year) {
        this.id = id;
        this.title = title;
        this.year = year;
    }

    public int getId() {
        return id;
    }
    
    public String getTitle() {
        return title;
    }

        public int getYear() {
        return year;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(int year) {
        if(year > 0) {
            this.year = year;
        }
    }
}