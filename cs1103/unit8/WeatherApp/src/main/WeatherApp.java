
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class WeatherApp extends JFrame {

    private JTextField cityField;
    private JTextArea resultArea;
    private JLabel iconLabel;

    private WeatherService service;
    private HistoryManager history;

    public WeatherApp() {
        service = new WeatherService();
        history = new HistoryManager();

        setTitle("Weather App");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top Panel
        JPanel top = new JPanel();
        cityField = new JTextField(15);
        JButton searchBtn = new JButton("Search");

        top.add(new JLabel("City: "));
        top.add(cityField);
        top.add(searchBtn);

        add(top, BorderLayout.NORTH);

        // Center
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        // Icon
        iconLabel = new JLabel();
        add(iconLabel, BorderLayout.WEST);

        // History
        JList<String> historyList = new JList<>(history.getModel());
        add(new JScrollPane(historyList), BorderLayout.EAST);

        // Button Action
        searchBtn.addActionListener((ActionEvent e) -> fetchWeather());

        setVisible(true);
    }

    private void fetchWeather() {
        String city = cityField.getText().trim();

        if (city.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter city name!");
            return;
        }

        try {
            WeatherModel data = service.getWeather(city);

            String result = "Temperature: " + data.getTemperature() + " °C\n"
                    + "Humidity: " + data.getHumidity() + "%\n"
                    + "Wind: " + data.getWindSpeed() + " m/s\n"
                    + "Condition: " + data.getCondition();

            resultArea.setText(result);

            // Change background
            if (Utils.isNight()) {
                getContentPane().setBackground(Color.DARK_GRAY);
            } else {
                getContentPane().setBackground(Color.WHITE);
            }

            // Simple icon logic
            if (data.getCondition().contains("cloud")) {
                iconLabel.setText("☁️");
            } else if (data.getCondition().contains("rain")) {
                iconLabel.setText("🌧️");
            } else {
                iconLabel.setText("☀️");
            }

            history.addHistory(city);

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching data: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new WeatherApp();
    }
}
