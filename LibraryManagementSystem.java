import java.util.*;

// Book class
class Book {
    private int id;
    private String title;
    private boolean isIssued;

    public Book(int id, String title) {
        this.id = id;
        this.title = title;
        this.isIssued = false;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void issue() {
        isIssued = true;
    }

    public void returnBook() {
        isIssued = false;
    }

    @Override
    public String toString() {
        return "Book ID: " + id + ", Title: '" + title + "', Issued: " + isIssued;
    }
}

// User class
class User {
    private int userId;
    private String name;

    public User(int userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }
}

// Library class
class Library {
    private List<Book> books;
    private Map<Integer, Integer> issuedBooks; // bookId -> userId

    public Library() {
        books = new ArrayList<>();
        issuedBooks = new HashMap<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void showBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void issueBook(int bookId, User user) {
        for (Book book : books) {
            if (book.getId() == bookId) {
                if (!book.isIssued()) {
                    book.issue();
                    issuedBooks.put(bookId, user.getUserId());
                    System.out.println("Book issued to " + user.getName());
                } else {
                    System.out.println("Book already issued.");
                }
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void returnBook(int bookId, User user) {
        if (issuedBooks.containsKey(bookId) && issuedBooks.get(bookId) == user.getUserId()) {
            for (Book book : books) {
                if (book.getId() == bookId) {
                    book.returnBook();
                    issuedBooks.remove(bookId);
                    System.out.println("Book returned by " + user.getName());
                    return;
                }
            }
        } else {
            System.out.println("Book was not issued to this user.");
        }
    }
}

// Main class
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();

        // Sample Books
        library.addBook(new Book(1, "Java Programming"));
        library.addBook(new Book(2, "Python Essentials"));
        library.addBook(new Book(3, "Data Structures"));

        // Sample Users
        User user1 = new User(101, "Saloni");
        User user2 = new User(102, "Dhara");

        // View all books
        System.out.println("--- All Books ---");
        library.showBooks();

        // Issue Book
        System.out.println("\n--- Issuing Book ID 2 to Saloni ---");
        library.issueBook(2, user1);

        // Try issuing same book again
        System.out.println("\n--- Issuing Book ID 2 to Amit ---");
        library.issueBook(2, user2);

        // Return Book
        System.out.println("\n--- Returning Book ID 2 by Saloni ---");
        library.returnBook(2, user1);

        // Final State
        System.out.println("\n--- Final Book List ---");
        library.showBooks();
    }
}
