import gui.DashboardGUI;
import javax.swing.SwingUtilities;
import service.HotelSystem;

public class Main {

    public static void main(String[] args) {

        // Load saved data
        HotelSystem.initialize();

        // Start application
        SwingUtilities.invokeLater(() -> {

            DashboardGUI dashboard =
                    new DashboardGUI();

            dashboard.setVisible(true);
        });
    }
}