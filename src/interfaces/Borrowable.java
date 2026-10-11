package interfaces;

import exception.BookNotAvailableException;
public interface Borrowable  {

    public void borrow() throws BookNotAvailableException;

} 