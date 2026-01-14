package com.mayin_tarlasi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {
            // 1. FXML Dosyasını Yükle
            // "game.fxml" dosyasının isminin senin projedeki dosya ismiyle AYNI olduğundan emin ol.
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/gameMonitor.fxml")));

            // 2. Sahneyi Oluştur
            Scene scene = new Scene(root);
            
            // 3. Pencere Ayarları
            stage.setTitle("Mayın Tarlası - Mihrace"); // Başlık
            stage.setScene(scene);
            stage.setResizable(false); // Pencere boyutuyla oynanmasın (Tasarım bozulmasın)
            stage.show();

        } catch (Exception e) {
            System.err.println("HATA: FXML dosyası yüklenirken bir sorun oluştu.");
            System.err.println("Lütfen 'game.fxml' dosyasının resources klasöründe olduğundan emin olun.");
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}