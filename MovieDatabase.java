import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Movie Database Program
 * Authors: Jeyadheep V
 * Date: 10/12/2023
 * Features:
 * 1. User registration with unique username and password constraints.
 * 2. User login with email/username and password.
 * 3. Manage Watched and Plan to Watch movies with details such as rating, completion date, and review.
 * 4. Display Movie Database, including Plan to Watch and Watched movies.
 * 5. Update Movie Database: move movies between PTW and Watched, remove completed movies, and more.
 * 6. Log out and exit options.
 */
public class MovieDatabase {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Movie> movies = new ArrayList<>();
    private static ArrayList<User> users = new ArrayList<>();
    private static User currentUser;

    // File path
    private static final String USER_DB_FILE_PATH = "user_db.csv";

    //movie path
    private static final String MOVIE_DB_FILE_PATH = "movie_db.csv";

    public static void main(String[] args) {
        // Load data from CSV
        loadMovieData();

        while (true) {
            System.out.println("\n----------------------------------------------------------\n");
            System.out.println("Welcome to the Movie Database!");
            System.out.println("1. Register\n2. Login\n3. Exit");
            int choice = getUserChoice(3);

            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    loginUser();
                    break;
                case 3:
                    // Save data and exit
                    saveData();
                    System.out.println("Exiting Movie Database. Goodbye!");
                    System.exit(0);
            }

            while (currentUser != null) {
                System.out.println("\n----------------------------------------------------------\n");
                System.out.println("\nMovie Database Options:");
                System.out.println("1. Enter a Watched Movie\n2. Enter a Plan to Watch Movie\n" +
                        "3. Display Movie DB\n4. Update Movie DB\n5. Log out\n6. Exit");
                choice = getUserChoice(6);

                switch (choice) {
                    case 1:
                        enterWatchedMovie();
                        break;
                    case 2:
                        enterPlanToWatchMovie();
                        break;
                    case 3:
                        displayMovieDatabase();
                        break;
                    case 4:
                        updateMovieDatabase();
                        break;
                    case 5:
                        // Save data and log out
                        saveData();
                        logOut();
                        break;
                    case 6:
                        // Save data and exit
                        saveData();
                        System.out.println("\n----------------------------------------------------------\n");
                        System.out.println("Exiting Movie Database. Goodbye!\n");
                        System.exit(0);
                }
            }
        }
    }


    // User class to store user information.
    static class User {
        String email;
        String username;
        String password;
        ArrayList<Movie> watchedMovies;
        ArrayList<Movie> planToWatchMovies;

        public User(String email, String username, String password) {
            this.email = email;
            this.username = username;
            this.password = password;
            this.watchedMovies = new ArrayList<>();
            this.planToWatchMovies = new ArrayList<>();
        }
    }

    // Movie class to store movie information.
    static class Movie {
        String name;
        String status; // "PTW" or "Watched"
        int rating;
        String completionDate;
        String review;
        User user; // New field to establish a relationship with the user

        public Movie(String name, String status, int rating, String completionDate, String review, User user) {
            this.name = name;
            this.status = status;
            this.rating = rating;
            this.completionDate = completionDate;
            this.review = review;
            this.user = user;
        }
    }

    // Utility method to get user input within a specified range.
    private static int getUserChoice(int maxChoice) {
        int choice = -1;
        do {
            System.out.print("Enter your choice: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        } while (choice < 1 || choice > maxChoice);

        return choice;
    }


    private static void registerUser() {
        System.out.println("\n----------------------------------------------------------\n");
        System.out.println("Enter your email: ");
        String email = scanner.nextLine();

        System.out.println("Enter a username (must be unique): ");
        String username;
        while (true) {
            username = scanner.nextLine();
            if (isUsernameUnique(username)) {
                break;
            } else {
                System.out.println("Username already taken. Enter a different username: ");
            }
        }

        System.out.println("Enter a password (at least 8 characters, alphanumeric): ");
        String password;
        while (true) {
            password = scanner.nextLine();
            if (isPasswordValid(password)) {
                break;
            } else {
                System.out.println("Invalid password. Password must be at least 8 characters and alphanumeric. Enter a valid password: ");
            }
        }

        User newUser = new User(email, username, password);
        users.add(newUser);
        System.out.println("Registration successful!");
    }

    private static boolean isUsernameUnique(String username) {
        for (User user : users) {
            if (user.username.equals(username)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isPasswordValid(String password) {
        return password.length() >= 8 && password.matches(".*[a-zA-Z].*") && password.matches(".*\\d.*");
    }

    private static void loginUser() {
        System.out.println("\n----------------------------------------------------------\n");
        System.out.println("Enter your email or username: ");
        String loginInput = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if ((user.email.equals(loginInput) || user.username.equals(loginInput)) && user.password.equals(password)) {
                currentUser = user;
                System.out.println("Login successful! Welcome, " + currentUser.username + "!");
                return;
            }
        }

        System.out.println("Invalid credentials. Login failed.");
    }

    private static void enterWatchedMovie() {
        System.out.println("\n----------------------------------------------------------\n");
        System.out.println("Enter the name of the watched movie: ");
        String name = scanner.nextLine();

        System.out.println("Enter the rating (on a scale of 1-10): ");
        int rating = getUserChoice(10);

        System.out.println("Enter the completion date (DD/MM/YYYY): ");
        String completionDate = scanner.nextLine();

        System.out.println("Enter a small review on the movie: ");
        String review = scanner.nextLine();

        Movie watchedMovie = new Movie(name, "Watched", rating, completionDate, review, currentUser);
        currentUser.watchedMovies.add(watchedMovie);
        System.out.println("Movie added to Watched list!");
    }

    private static void enterPlanToWatchMovie() {
        System.out.println("\n----------------------------------------------------------\n");
        System.out.println("Enter the name of the movie you plan to watch: ");
        String name = scanner.nextLine();

        Movie planToWatchMovie = new Movie(name, "PTW", 0, "", "", currentUser);
        currentUser.planToWatchMovies.add(planToWatchMovie);
        movies.add(planToWatchMovie);
        System.out.println("Movie added to Plan to Watch list!");
    }

    private static void displayMovieDatabase() {
        System.out.println("\n----------------------------------------------------------\n");
        System.out.println("\nMovie Database:");

        System.out.println("Plan to Watch Movies:");
        displayMovies(currentUser.planToWatchMovies);

        System.out.println("\nWatched Movies:");
        displayMovies(currentUser.watchedMovies);
    }

    private static void displayMovies(ArrayList<Movie> movies) {
        if (movies.isEmpty()) {
            System.out.println("\n----------------------------------------------------------\n");
            System.out.println("No movies to display.");
            return;
        }

        for (Movie movie : movies) {
            System.out.println("Name: " + movie.name);
            System.out.println("Status: " + movie.status);
            if (movie.status.equals("Watched")) {
                System.out.println("Rating: " + movie.rating);
                System.out.println("Completion Date: " + movie.completionDate);
                System.out.println("Review: " + movie.review);
            }
            System.out.println("-------------------------");
        }
    }

    private static void updateMovieDatabase() {
        System.out.println("\n----------------------------------------------------------\n");
        System.out.println("Update Movie Database Options:");
        System.out.println("1. Move movie from Plan to Watch to Watched\n2. Remove completed movies\n3. Back");
        int choice = getUserChoice(3);

        switch (choice) {
            case 1:
                moveMovieToWatched();
                break;
            case 2:
                removeCompletedMovies();
                break;
            case 3:
                return;
        }
    }

    private static void moveMovieToWatched() {
        System.out.println("\n----------------------------------------------------------\n");
        ArrayList<Movie> planToWatchMovies = currentUser.planToWatchMovies;
    
        if (planToWatchMovies.isEmpty()) {
            System.out.println("No Plan to Watch movies available.");
            return;
        }
    
        System.out.println("Plan to Watch Movies:");
    
        for (int i = 0; i < planToWatchMovies.size(); i++) {
            Movie movie = planToWatchMovies.get(i);
            System.out.println((i + 1) + ". " + movie.name);
        }
    
        int choice;
        do {
            System.out.println("Enter the number of the movie to move to Watched (1-" + planToWatchMovies.size() + "): ");
            choice = getUserChoice(planToWatchMovies.size());
    
            if (choice < 1 || choice > planToWatchMovies.size()) {
                System.out.println("Invalid choice. Please enter a number between 1 and " + planToWatchMovies.size() + ".");
            }
    
        } while (choice < 1 || choice > planToWatchMovies.size());
    
        Movie selectedMovie = planToWatchMovies.get(choice - 1);
    
        System.out.println("Enter the rating for the watched movie (on a scale of 1-10): ");
        int rating = getUserChoice(10);
    
        System.out.println("Enter the completion date for the watched movie (DD/MM/YYYY): ");
        String completionDate = scanner.nextLine();
    
        System.out.println("Enter a small review for the watched movie: ");
        String review = scanner.nextLine();
    
        // Move the movie from Plan to Watch to Watched
        planToWatchMovies.remove(selectedMovie);
        Movie watchedMovie = new Movie(selectedMovie.name, "Watched", rating, completionDate, review, currentUser);
        currentUser.watchedMovies.add(watchedMovie);
        System.out.println("Movie moved to Watched list!");
    }

    private static void removeCompletedMovies() {
        System.out.println("\n----------------------------------------------------------\n");
        ArrayList<Movie> completedMovies = new ArrayList<>();
        for (Movie movie : currentUser.watchedMovies) {
            if (!movie.completionDate.isEmpty()) {
                completedMovies.add(movie);
            }
        }
    
        if (completedMovies.isEmpty()) {
            System.out.println("No completed movies to remove.");
            return;
        }
    
        System.out.println("Completed Movies:");
    
        for (int i = 0; i < completedMovies.size(); i++) {
            Movie movie = completedMovies.get(i);
            System.out.println((i + 1) + ". " + movie.name + " (" + movie.status + ")");
        }
    
        int choice;
        do {
            System.out.println("Enter the number of the movie to remove (1-" + completedMovies.size() + "): ");
            choice = getUserChoice(completedMovies.size());
    
            if (choice < 1 || choice > completedMovies.size()) {
                System.out.println("Invalid choice. Please enter a number between 1 and " + completedMovies.size() + ".");
            }
    
        } while (choice < 1 || choice > completedMovies.size());
    
        Movie selectedMovie = completedMovies.get(choice - 1);
        currentUser.watchedMovies.remove(selectedMovie);
        System.out.println("Movie removed from Watched list!");
    }

    private static void logOut() {
        currentUser = null;
        System.out.println("\n----------------------------------------------------------\n");
        System.out.println("Logged out successfully. Goodbye, and see you next time!");
    }

    private static void loadMovieData() {
        try (BufferedReader userBr = new BufferedReader(new FileReader(USER_DB_FILE_PATH));
             BufferedReader movieBr = new BufferedReader(new FileReader(MOVIE_DB_FILE_PATH))) {
    
            // Load user data
            String userLine;
            boolean isFirstUserLine = true;
            while ((userLine = userBr.readLine()) != null) {
                if (isFirstUserLine) {
                    isFirstUserLine = false;
                    continue;
                }
                String[] userParts = userLine.split(",");
                if (userParts.length >= 4) { // Ensure it's a user data line
                    String email = userParts[1];
                    String username = userParts[2];
                    String password = userParts[3];
    
                    User loadedUser = new User(email, username, password);
                    users.add(loadedUser);
    
                    // Load movies for the user
                    loadMoviesForUser(username, movieBr);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
    
    // Helper method to load movies for a user
private static void loadMoviesForUser(String username, BufferedReader br) {
    String movieLine;
    ArrayList<Movie> userMovies = new ArrayList<>();
    try {
        while ((movieLine = br.readLine()) != null) {
            String[] movieParts = movieLine.split(",");
            if (movieParts.length >= 6) { // Ensure it's a movie data line
                String movieUsername = movieParts[0];
                if (username.equals(movieUsername)) {
                    String movieName = movieParts[1];
                    String status = movieParts[2];
                    int rating = Integer.parseInt(movieParts[3]);
                    String completionDate = movieParts[4];
                    String review = movieParts[5];

                    // Create and add the movie to the userMovies list
                    Movie loadedMovie = new Movie(movieName, status, rating, completionDate, review, findUserByUsername(username));
                    userMovies.add(loadedMovie);
                }
            }
        }
    } catch (IOException e) {
        System.out.println("Error loading movies for user " + username + ": " + e.getMessage());
    }

    // Find the user and add the loaded movies
    User user = findUserByUsername(username);
    if (user != null) {
        if (userMovies.size() > 0) {
            for (Movie userMovie : userMovies) {
                if ("Watched".equals(userMovie.status)) {
                    user.watchedMovies.add(userMovie);
                } else if ("PTW".equals(userMovie.status)) {
                    user.planToWatchMovies.add(userMovie);
                    movies.add(userMovie); // Add to the movies list
                }
            }
        }
    }
}

    private static void saveData() {
        try (PrintWriter userWriter = new PrintWriter(new FileWriter(USER_DB_FILE_PATH));
             PrintWriter movieWriter = new PrintWriter(new FileWriter(MOVIE_DB_FILE_PATH))) {
    
            // Save user data to user_db.csv
            userWriter.println("user_sr.no,email_id,username,password");
    
            for (int i = 0; i < users.size(); i++) {
                User user = users.get(i);
                userWriter.println((i + 1) + "," + user.email + "," + user.username + "," + user.password);
    
                // Save movies to movie_db.csv
                for (Movie movie : user.watchedMovies) {
                    saveMovieData(movie, movieWriter);
                }
                for (Movie movie : user.planToWatchMovies) {
                    saveMovieData(movie, movieWriter);
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    private static void saveMovieData(Movie movie, PrintWriter writer) {
        String line = movie.user.username + "," + movie.name + "," + movie.status + ","
                + movie.rating + "," + movie.completionDate + "," + movie.review;
        
        // Check if the movie is already saved in the file
        if (!isMovieAlreadySaved(movie, writer)) {
            writer.println(line);
            System.out.println("Debug: Saved movie data - " + line);
        }
    }

    private static boolean isMovieAlreadySaved(Movie movie, PrintWriter writer) {
        // Load existing movie data from the file
        ArrayList<Movie> existingMovies = loadExistingMovieData();
    
        // Check if the movie already exists in the file
        for (Movie existingMovie : existingMovies) {
            if (existingMovie.user.username.equals(movie.user.username)
                    && existingMovie.name.equals(movie.name)
                    && existingMovie.status.equals(movie.status)) {
                return true;
            }
        }
    
        return false;
    }

    private static ArrayList<Movie> loadExistingMovieData() {
        ArrayList<Movie> existingMovies = new ArrayList<>();
    
        try (BufferedReader br = new BufferedReader(new FileReader(MOVIE_DB_FILE_PATH))) {
            String line;
            boolean isFirstLine = true; // To skip the header
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] parts = line.split(",");
                if (parts.length >= 5) { // Ensure it's a movie data line
                    String username = parts[0];
                    String movieName = parts[1];
                    String status = parts[2];
                    int rating = Integer.parseInt(parts[3]);
                    String completionDate = parts[4];
                    String review = parts[5];
    
                    // Create and add the movie to the existingMovies list
                    Movie existingMovie = new Movie(movieName, status, rating, completionDate, review, findUserByUsername(username));
                    existingMovies.add(existingMovie);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading existing movie data: " + e.getMessage());
        }
    
        return existingMovies;
    }

    private static User findUserByUsername(String username) {
        for (User user : users) {
            if (user.username.equals(username)) {
                return user;
            }
        }
        return null; // Handle the case where the user is not found
    }

}

