package library;

import java.util.ArrayList;
import java.util.List;

class Book {
    private List<String> categories;

    public Book(String title, String author, String ISBN, boolean available, String... categories) {
        this.categories = new ArrayList<>();
        for (String category : categories) {
            this.categories.add(category);
        }
    }

	public String[] getCategories() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getAuthor() {
		// TODO Auto-generated method stub
		return null;
	}

    // Getters, setters
}
