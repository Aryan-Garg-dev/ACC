package practice.systems.book_store;

import java.time.LocalDate;
import java.util.Date;

public class Book {
  private final String title;
  private final Double price;
  private final boolean isPublished;
  private final Date dateOfPublication;

  Book(String title, Double price, boolean isPublished, Date dateOfPublication){
    this.title = title;
    this.price = price;
    this.isPublished = isPublished;
    this.dateOfPublication = dateOfPublication;
  }

  public String getTitle() {
    return title;
  }

  public Double getPrice() {
    return price;
  }

  public Date getDateOfPublication() {
    return dateOfPublication;
  }

  public boolean isPublished() {
    return isPublished;
  }

  @Override
  public String toString() {
    return "Book{" +
      "title='" + title + '\'' +
      ", price=" + price +
      ", isPublished=" + isPublished +
      ", dateOfPublication=" + dateOfPublication +
      '}';
  }
}
