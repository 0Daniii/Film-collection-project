import java.util.Scanner;

public class InputDevice {
    private Scanner scanner = new Scanner(System.in);

    // Read a film from user input
    public Film readFilm() throws InvalidFilmException {
        System.out.print("Enter film title: ");
        String title = scanner.nextLine();

        System.out.print("Enter film director: ");
        String director = scanner.nextLine();

        System.out.print("Enter film year: ");
        int year;
        try {
            year = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new InvalidFilmException("Year must be a valid number.");
        }

        System.out.print("Enter film genre: ");
        String genre = scanner.nextLine();

        return new Film(title, director, year, genre);
    }
}