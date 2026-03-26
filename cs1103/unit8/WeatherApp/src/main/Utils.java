import java.time.LocalTime;

public class Utils {

    public static double toFahrenheit(double celsius) {
        return (celsius * 9 / 5) + 32;
    }

    public static boolean isNight() {
        int hour = LocalTime.now().getHour();
        return (hour >= 18 || hour < 6);
    }
}