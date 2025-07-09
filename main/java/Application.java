import java.io.*;
import java.util.*;

public class Application {
    private List<Film> filmCollection;
    private OutputDevice outputDevice;

    // Constructor
    public Application(InputDevice inputDevice, OutputDevice outputDevice) {
        this.filmCollection = new ArrayList<>();
        this.outputDevice = outputDevice;
    }

    // Run based on config
    public void run(String config) {
        System.out.println("Starting application with config: " + config);
        if (config.equals("load")) {
            loadFilmsFromFile();
        }
    }

    // Add a film to the collection
    public void addFilm(Film film) throws DuplicateFilmException {
        if (filmCollection.contains(film)) {
            throw new DuplicateFilmException("Film already exists in the collection: " + film.getTitle());
        }
        filmCollection.add(film);
        outputDevice.writeMessage("Film added: " + film);
    }

    // List all films
    public void listFilms() {
        if (filmCollection.isEmpty()) {
            outputDevice.writeMessage("No films in the collection.");
        } else {
            filmCollection.forEach(System.out::println);
        }
    }

    // Filter films by genre
    public void filterByGenre(String genre) {
        filmCollection.stream()
                .filter(film -> film.getGenre().equalsIgnoreCase(genre))
                .forEach(System.out::println);
    }

    // Load films from file
    public void loadFilmsFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("films.dat"))) {
            filmCollection = (List<Film>) ois.readObject();
            outputDevice.writeMessage("Films loaded from file.");
        } catch (FileNotFoundException e) {
            outputDevice.writeMessage("No existing film file found. Starting with an empty collection.");
        } catch (IOException | ClassNotFoundException e) {
            outputDevice.writeMessage("Error loading films: " + e.getMessage());
        }
    }

    // Save films to file
    public void saveFilmsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("films.dat"))) {
            oos.writeObject(filmCollection);
            outputDevice.writeMessage("Films saved to file.");
        } catch (IOException e) {
            outputDevice.writeMessage("Error saving films: " + e.getMessage());
        }
    }
}