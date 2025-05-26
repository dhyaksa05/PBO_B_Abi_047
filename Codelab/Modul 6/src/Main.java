import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.util.Random;

public class Main extends Application {

    private int angkaRahasia;
    private int jumlahTebakan;
    private int maksimalTebakan = 10;
    private Random random = new Random();

    private Label judulLabel;
    private Label instruksiLabel;
    private TextField tebakanField;
    private Button tebakButton;
    private Button resetButton;
    private Label hasilLabel;
    private Label skorLabel;

    @Override
    public void start(Stage primaryStage) {
        // Inisialisasi komponen UI terlebih dahulu
        initializeComponents();

        // Kemudian reset game
        resetGame();

        // Layout
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(tebakButton, resetButton);

        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: #87CEEB;");
        root.getChildren().addAll(
                judulLabel,
                instruksiLabel,
                new Label("Masukkan tebakan Anda:"),
                tebakanField,
                buttonBox,
                hasilLabel,
                skorLabel
        );

        Scene scene = new Scene(root, 500, 400);
        primaryStage.setTitle("Game Tebak Angka");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        // Focus pada text field
        tebakanField.requestFocus();
    }

    private void initializeComponents() {
        // Komponen UI
        judulLabel = new Label(" GAME TEBAK ANGKA ");
        judulLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #FFFFFF;");

        instruksiLabel = new Label("Saya memikirkan angka antara 1-100. Bisakah Anda menebaknya?");
        instruksiLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #FFFF00;");

        tebakanField = new TextField();
        tebakanField.setPromptText("Masukkan angka 1-100");
        tebakanField.setMaxWidth(200);
        tebakanField.setStyle("-fx-font-size: 14px; -fx-border-color: #DC143C; -fx-border-width: 2px; -fx-border-radius: 5px;");

        // Event handler untuk Enter key
        tebakanField.setOnAction(e -> prosesTebakan());

        tebakButton = new Button("Tebak!");
        tebakButton.setStyle("-fx-background-color: #A7D8DE; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 8px 16px; -fx-background-radius: 5px;");
        tebakButton.setOnAction(e -> prosesTebakan());

        resetButton = new Button("Main Lagi");
        resetButton.setStyle("-fx-background-color: #000080; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 8px 16px; -fx-background-radius: 5px;");
        resetButton.setOnAction(e -> resetGame());

        hasilLabel = new Label("");
        hasilLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        skorLabel = new Label("");
        skorLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #8B0000;");
    }

    private void prosesTebakan() {
        String input = tebakanField.getText().trim();

        // Validasi input
        if (input.isEmpty()) {
            showAlert("Error", "Silakan masukkan angka!");
            return;
        }

        int tebakan;
        try {
            tebakan = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            showAlert("Error", "Masukkan angka yang valid!");
            tebakanField.clear();
            return;
        }

        if (tebakan < 1 || tebakan > 100) {
            showAlert("Error", "Angka harus antara 1-100!");
            tebakanField.clear();
            return;
        }

        // Proses tebakan
        jumlahTebakan++;
        updateSkor();

        if (tebakan == angkaRahasia) {
            // Menang!
            hasilLabel.setText("🎉 SELAMAT! Anda berhasil menebak angka " + angkaRahasia + "!");
            hasilLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #8B0000;");
            tebakButton.setDisable(true);
            tebakanField.setDisable(true);

            String pesan = "Luar biasa! Anda menebak dengan benar dalam " + jumlahTebakan + " kali percobaan!";
            if (jumlahTebakan <= 3) {
                pesan += "\n🏆 Anda seorang master penebak!";
            } else if (jumlahTebakan <= 6) {
                pesan += "\n👍 Hasil yang bagus!";
            } else {
                pesan += "\n😊 Tidak buruk!";
            }
            showAlert("Selamat!", pesan);

        } else if (jumlahTebakan >= maksimalTebakan) {
            // Kalah
            hasilLabel.setText("😞 Game Over! Angka yang benar adalah " + angkaRahasia);
            hasilLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #DC143C;");
            tebakButton.setDisable(true);
            tebakanField.setDisable(true);
            showAlert("Game Over", "Sayang sekali! Angka yang benar adalah " + angkaRahasia + ".\nCoba lagi!");

        } else {
            // Berikan petunjuk
            if (tebakan < angkaRahasia) {
                hasilLabel.setText("📈 Terlalu kecil! Coba angka yang lebih besar.");
                hasilLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #B22222;");
            } else {
                hasilLabel.setText("📉 Terlalu besar! Coba angka yang lebih kecil.");
                hasilLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #B22222;");
            }
        }

        tebakanField.clear();
        tebakanField.requestFocus();
    }

    private void resetGame() {
        angkaRahasia = random.nextInt(100) + 1;
        jumlahTebakan = 0;

        if (hasilLabel != null) {
            hasilLabel.setText("Selamat bermain! Mulai menebak...");
            hasilLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #8B0000;");
        }

        updateSkor();

        if (tebakButton != null) {
            tebakButton.setDisable(false);
        }
        if (tebakanField != null) {
            tebakanField.setDisable(false);
            tebakanField.clear();
            tebakanField.requestFocus();
        }
    }

    private void updateSkor() {
        if (skorLabel != null) {
            skorLabel.setText("Tebakan: " + jumlahTebakan + "/" + maksimalTebakan);

            // Ubah warna berdasarkan sisa kesempatan
            if (jumlahTebakan >= maksimalTebakan - 2) {
                skorLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #8B0000; -fx-font-weight: bold;");
            } else if (jumlahTebakan >= maksimalTebakan - 5) {
                skorLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #B22222; -fx-font-weight: bold;");
            } else {
                skorLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #8B0000;");
            }
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}