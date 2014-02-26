package woche3.model;

import java.util.Observable;

/**
 * @author msyfrig
 */
public class BookDO extends Observable {
    private String            isbn;
    private String            title;
    private String            author;
    private String            publisher;
    private BookConditionEnum condition;

    /**
     * Creates a new instance of this class.
     * 
     * @param aIsbn
     * @param aTitle
     * @param aAuthor
     * @param aPublisher
     * @param aCondition
     */
    public BookDO(String aIsbn, String aTitle, String aAuthor,
            String aPublisher, BookConditionEnum aCondition) {
        super();
        isbn = aIsbn;
        title = aTitle;
        author = aAuthor;
        publisher = aPublisher;
        condition = aCondition;
    }

    /**
     * @return the isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * @param aIsbn
     *            the isbn to set
     */
    public void setIsbn(String aIsbn) {
        isbn = aIsbn;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param aTitle
     *            the title to set
     */
    public void setTitle(String aTitle) {
        title = aTitle;
        doNotify();
    }

    private void doNotify() {
        setChanged();
        notifyObservers();
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param aAuthor
     *            the author to set
     */
    public void setAuthor(String aAuthor) {
        author = aAuthor;
    }

    /**
     * @return the publisher
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * @param aPublisher
     *            the publisher to set
     */
    public void setPublisher(String aPublisher) {
        publisher = aPublisher;
    }

    /**
     * @return the condition
     */
    public BookConditionEnum getCondition() {
        return condition;
    }

    /**
     * @param aCondition
     *            the condition to set
     */
    public void setCondition(BookConditionEnum aCondition) {
        condition = aCondition;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "BookDO [isbn=" + isbn + ", title=" + title + ", author="
                + author + ", publisher=" + publisher + ", condition="
                + condition + "]";
    }
}
