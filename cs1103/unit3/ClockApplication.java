import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Clock class is responsible for maintaining
 * and displaying the current system date and time.
 * It uses two separate threads:
 * 1. TimeUpdaterThread  - updates the time
 * 2. TimeDisplayThread  - displays the time
 */
class Clock {

    // Shared time variable between threads
    private volatile LocalDateTime currentTime;

    // Formatter for readable date-time output
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");

    /**
     * Constructor initializes the current time.
     */
    public Clock() {
        currentTime = LocalDateTime.now();
    }

    /**
     * Continuously updates the current time every second.
     */
    public void updateTime() {
        while (!Thread.currentThread().isInterrupted()) {

            currentTime = LocalDateTime.now();

            try {
                Thread.sleep(1000); // Sleep for 1 second
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Time updater thread interrupted.");
            }
        }
    }

    /**
     * Continuously displays the formatted time every second.
     */
    public void displayTime() {
        while (!Thread.currentThread().isInterrupted()) {

            System.out.println(
                    Thread.currentThread().getName()
                            + " -> "
                            + FORMATTER.format(currentTime)
            );

            try {
                Thread.sleep(1000); // Sleep for 1 second
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Display thread interrupted.");
            }
        }
    }
}

/**
 * Main class to start the clock application.
 */
public class ClockApplication {

    public static void main(String[] args) {

        Clock clock = new Clock();

        // Thread responsible for updating time
        Thread timeUpdaterThread =
                new Thread(clock::updateTime, "Time-Updater-Thread");

        // Thread responsible for displaying time
        Thread timeDisplayThread =
                new Thread(clock::displayTime, "Time-Display-Thread");

        // Set thread priorities
        timeUpdaterThread.setPriority(Thread.NORM_PRIORITY);  // Background
        timeDisplayThread.setPriority(Thread.MAX_PRIORITY);   // Higher priority

        // Start both threads
        timeUpdaterThread.start();
        timeDisplayThread.start();
    }
}