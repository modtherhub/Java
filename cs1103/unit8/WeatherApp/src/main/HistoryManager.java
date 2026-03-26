import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HistoryManager {

    private DefaultListModel<String> historyModel = new DefaultListModel<>();

    public DefaultListModel<String> getModel() {
        return historyModel;
    }

    public void addHistory(String city) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        historyModel.addElement(city + " - " + dtf.format(LocalDateTime.now()));
    }
}