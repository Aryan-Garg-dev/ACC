package practice.systems.book_store;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

public class Store {
  private static Map<Book, Integer> books = new HashMap<>();

  private static final Predicate<Book> isClassic = (book) ->
    book.getDateOfPublication().getYear() < 2000;

  private static final Predicate<Book> isRare = (book) ->
    book.getTitle().length() > 20;

  private static final Predicate<Book> isAvailable = (book) ->
    books.containsKey(book) && books.get(book) > 0;

  private static final Predicate<Book> isEligibleForDiscount = (book) ->
    book.getPrice() > 20;

  private static final Predicate<Book> isPublished = Book::isPublished;

  public static void addBook(Book book){
    books.merge(book, 1, Integer::sum);
  }

  public static void init(Book ...books){
    for (Book book: books) addBook(book);
  }

  public static List<Book> getDiscountedBooks(){
    return books.keySet().stream().filter(isAvailable.and(isPublished).and(isEligibleForDiscount)).toList();
  }

  public static List<Book> getClassicCollection(){
    return books.keySet().stream().filter(isAvailable.and(isClassic)).toList();
  }

  public static List<Book> getUpcomingTitles(){
    return books.keySet().stream().filter(isAvailable.and(isPublished.negate())).toList();
  }

  public static void listBooks(){
    books.keySet().stream().sorted(Comparator.comparingDouble(Book::getPrice)).forEach(System.out::println);
  }

  public static List<Book> getRareBooks(){
    return books.keySet().stream().filter(isAvailable.and(isRare)).toList();
  }

  public static double totalCost(){
    return books.keySet().stream().mapToDouble(book -> books.get(book) * book.getPrice()).sum();
  }

  public static List<String> getListOfTitles(){
    return books.keySet().stream().map(Book::getTitle).toList();
  }

  public static void main(String[] args) {
    init(new Book[]{
      new Book("The Great Gatsby", 15d, true, new Date(1925, 3, 10)),
      new Book("War and Peace and Philosophy of History", 35d, true, new Date(1869, 0, 1)),
      new Book("1984", 25d, true, new Date(1949, 5, 8)),
      new Book("To Kill a Mockingbird", 18d, true, new Date(1960, 6, 11)),
      new Book("The Chronicles of Magical Adventures in Wonderland", 45d, true, new Date(2020, 8, 15)),
      new Book("Pride and Prejudice", 12d, true, new Date(1813, 0, 28)),
      new Book("The Upcoming Revolution in Technology", 30d, false, new Date(2026, 0, 1)),
      new Book("The Catcher in the Rye", 22d, true, new Date(1951, 6, 16)),
      new Book("The Lord of the Rings: The Fellowship of the Ring", 28d, true, new Date(1954, 6, 29)),
      new Book("Future Societies and Their Impact", 40d, false, new Date(2026, 5, 1))
    });
    
    System.out.println("DISCOUNTED BOOKS");
    getDiscountedBooks().forEach(System.out::println);
    System.out.println();

    System.out.println("CLASSIC COLLECTION");
    getClassicCollection().forEach(System.out::println);
    System.out.println();

    System.out.println("UPCOMING TITLES");
    getUpcomingTitles().forEach(book ->
      System.out.printf("%s (Coming Soom)%n", book.getTitle()));
    System.out.println();

    System.out.println("ALL BOOKS");
    listBooks();
    System.out.println();

    System.out.println("RARE BOOKS");
    getRareBooks().forEach(System.out::println);
    System.out.println();
  }

}
