import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Entry point for the Film Collection Management System
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize scenes
        AdminScene adminScene = new AdminScene();
        UserScene userScene = new UserScene();
        ClientScene clientScene = new ClientScene();

        boolean running = true;
        while (running) {
            System.out.println("Main Menu:");
            System.out.println("1. Admin ");
            System.out.println("2. User ");
            System.out.println("3. Client");
            System.out.println("4. Exit");

            try {
                System.out.print("Select an option: ");
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        adminScene.run();
                        break;
                    case 2:
                        userScene.run();
                        break;
                    case 3:
                        handleMultipleClients(clientScene);
                        break;
                    case 4:
                        running = false;
                        System.out.println("Exiting the application. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }

        scanner.close();
    }

    // Simulates handling multiple clients using threads
    private static void handleMultipleClients(ClientScene clientScene) {
        System.out.println("\nSimulating multiple clients...");

        Thread client1 = new Thread(() -> {
            System.out.println("Client 1 accessing the system...");
            clientScene.run();
        });

        Thread client2 = new Thread(() -> {
            System.out.println("Client 2 accessing the system...");
            clientScene.run();
        });

        client1.start();
        client2.start();

        try {
            client1.join();
            client2.join();
        } catch (InterruptedException e) {
            System.out.println("Client threads interrupted.");
        }

        System.out.println("All clients have finished their tasks.");
    }
}

class AdminScene {
    private List<String> users = new ArrayList<>();

    public AdminScene() {
        // Initialize with some default users
        users.add("admin");
        users.add("manager");
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nAdmin Scene:");
            System.out.println("1. Add User");
            System.out.println("2. Remove User");
            System.out.println("3. List Users");
            System.out.println("4. Back to Main Menu");

            try {
                System.out.print("Select an option: ");
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Enter username to add: ");
                        String newUser = scanner.nextLine();
                        users.add(newUser);
                        System.out.println("User added: " + newUser);
                        break;
                    case 2:
                        System.out.print("Enter username to remove: ");
                        String userToRemove = scanner.nextLine();
                        if (users.remove(userToRemove)) {
                            System.out.println("User removed: " + userToRemove);
                        } else {
                            System.out.println("User not found.");
                        }
                        break;
                    case 3:
                        System.out.println("Users:");
                        for (String user : users) {
                            System.out.println("- " + user);
                        }
                        break;
                    case 4:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    public List<String> getUsers() {
        return users;
    }
}

class UserScene {
    private List<Film> films = new ArrayList<>();

    public UserScene() {
        // Initialize with some default films
        films.add(new Film("Inception", "Sci-Fi", 2010, "Christopher Nolan"));
        films.add(new Film("The Godfather", "Crime", 1972, "Francis Ford Coppola"));
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nUser Scene:");
            System.out.println("1. Add Film");
            System.out.println("2. Remove Film");
            System.out.println("3. List Films");
            System.out.println("4. Back to Main Menu");

            try {
                System.out.print("Select an option: ");
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Enter film title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter film genre: ");
                        String genre = scanner.nextLine();
                        System.out.print("Enter release year: ");
                        int year = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter director: ");
                        String director = scanner.nextLine();
                        films.add(new Film(title, genre, year, director));
                        System.out.println("Film added: " + title);
                        break;
                    case 2:
                        System.out.print("Enter film title to remove: ");
                        String titleToRemove = scanner.nextLine();
                        if (films.removeIf(film -> film.getTitle().equalsIgnoreCase(titleToRemove))) {
                            System.out.println("Film removed: " + titleToRemove);
                        } else {
                            System.out.println("Film not found.");
                        }
                        break;
                    case 3:
                        System.out.println("Films:");
                        for (Film film : films) {
                            System.out.println(film);
                        }
                        break;
                    case 4:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    public List<Film> getFilms() {
        return films;
    }
}

class ClientScene {
    public void run() {
        System.out.println("\nClient Scene: Simulated client actions here.");
    }
}

class Film {
    private String title;
    private String genre;
    private int year;
    private String director;

    public Film(String title, String genre, int year, String director) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.director = director;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }

    public String getDirector() {
        return director;
    }

    @Override
    public String toString() {
        return "Film [Title=" + title + ", Genre=" + genre + ", Year=" + year + ", Director=" + director + "]";
    }
}
