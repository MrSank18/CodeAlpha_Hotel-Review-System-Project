package library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Library {
    private List<Book> books;
    private Map<String, List<Book>> categories;

    public Library() {
        books = new ArrayList<>();
        categories = new HashMap<>();
    }

    // Methods for adding/removing books, searching, borrowing, returning, categorizing
    public void addBook(Book book) {
        books.add(book);
        for (String category : book.getCategories()) {
            categories.computeIfAbsent(category, k -> new ArrayList<>()).add(book);
        }
    }

    public List<Book> searchByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    // Other methods
}
