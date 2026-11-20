package model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class LibraryItem {
    private static final Logger logger = LoggerFactory.getLogger(LibraryItem.class);
    private int id;
    private String title;
    private int year;

    public abstract void displayInfo();

    public int search(int id) {
        logger.debug("Searching by id: {}", id);
        return this.id;
    }

    public String search(String title) {
        logger.debug("Searching by title: {}", title);
        return this.title;
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