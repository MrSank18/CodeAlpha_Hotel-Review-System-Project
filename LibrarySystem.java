package library;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class LibrarySystem extends Application {
    private Library library;
    private User currentUser;
	private Stage primaryStage;
	private Stage primaryStage2;

    @Override
    public void start(Stage primaryStage) {
        library = new Library();

        // Sample books
        library.addBook(new Book("Introduction to Java", "John Doe", "1234567890", true, "Programming"));
        library.addBook(new Book("Data Structures and Algorithms", "Jane Smith", "0987654321", true, "Programming"));
        library.addBook(new Book("History of the World", "Alice Johnson", "5678901234", true, "History"));

        primaryStage.setTitle("Library System");

        // Login Scene
        VBox loginLayout = new VBox(10);
        TextField usernameInput = new TextField();
        usernameInput.setPromptText("Username");
        PasswordField passwordInput = new PasswordField();
        passwordInput.setPromptText("Password");
        Button loginButton = new Button("Login");
        Label loginErrorLabel = new Label();

        loginButton.setOnAction(e -> {
            String username = usernameInput.getText();
            String password = passwordInput.getText();
            if (validateUser(username, password)) {
                currentUser = new User(username);
                primaryStage.setScene(getMainScene());
            } else {
                loginErrorLabel.setText("Invalid username or password");
            }
        });

        loginLayout.getChildren().addAll(usernameInput, passwordInput, loginButton, loginErrorLabel);
        Scene loginScene = new Scene(loginLayout, 300, 200);

        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    private boolean validateUser(String username, String password) {
        // Simple validation logic, replace with your authentication mechanism
        return username.equals("admin") && password.equals("admin");
    }

    private Scene getMainScene() {
        BorderPane mainLayout = new BorderPane();

        // Top layout
        HBox topLayout = new HBox(10);
        TextField searchInput = new TextField();
        searchInput.setPromptText("Search");
        Button searchButton = new Button("Search");
        Button logoutButton = new Button("Logout");
        Label welcomeLabel = new Label("Welcome, " + currentUser.getUsername());
        topLayout.getChildren().addAll(searchInput, searchButton, logoutButton, welcomeLabel);
        topLayout.setAlignment(Pos.CENTER);
        topLayout.setPadding(new Insets(10));

        // Center layout
        VBox centerLayout = new VBox(10);
        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);
        centerLayout.getChildren().add(resultArea);

        // Bottom layout
        HBox bottomLayout = new HBox(10);
        Button borrowButton = new Button("Borrow");
        Button returnButton = new Button("Return");
        bottomLayout.getChildren().addAll(borrowButton, returnButton);
        bottomLayout.setAlignment(Pos.CENTER);
        bottomLayout.setPadding(new Insets(10));

        mainLayout.setTop(topLayout);
        mainLayout.setCenter(centerLayout);
        mainLayout.setBottom(bottomLayout);

        searchButton.setOnAction(e -> {
            String query = searchInput.getText();
            List<Book> searchResults = library.searchByTitle(query);
            if (searchResults.isEmpty()) {
                resultArea.setText("No results found.");
            } else {
                StringBuilder sb = new StringBuilder();
                for (Book book : searchResults) {
                    sb.append(book.getTitle()).append(" by ").append(book.getAuthor()).append("\n");
                }
                resultArea.setText(sb.toString());
            }
        });

        borrowButton.setOnAction(e -> {
            // Add borrow functionality
        });

        returnButton.setOnAction(e -> {
            // Add return functionality
        });

        logoutButton.setOnAction(e -> {
            currentUser = null;
            primaryStage2 = null;
			primaryStage2.setScene(getLoginScene());
        });

        return new Scene(mainLayout, 600, 400);
    }

    private Scene getLoginScene() {
        VBox loginLayout = new VBox(10);
        TextField usernameInput = new TextField();
        usernameInput.setPromptText("Username");
        PasswordField passwordInput = new PasswordField();
        passwordInput.setPromptText("Password");
        Button loginButton = new Button("Login");
        Label loginErrorLabel = new Label();

        loginButton.setOnAction(e -> {
            String username = usernameInput.getText();
            String password = passwordInput.getText();
            if (validateUser(username, password)) {
                currentUser = new User(username);
                primaryStage = null;
				primaryStage.setScene(getMainScene());
            } else {
                loginErrorLabel.setText("Invalid username or password");
            }
        });

        loginLayout.getChildren().addAll(usernameInput, passwordInput, loginButton, loginErrorLabel);
        return new Scene(loginLayout, 300, 200);
    }

    public static void main(String[] args) {
        launch(args);
    }
}






