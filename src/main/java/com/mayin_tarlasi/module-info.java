module com.mayin_tarlasi {
    // JavaFX kütüphanelerini kullanacağımızı belirtiyoruz
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media; // Müzik çalar eklemiştik

    // FXML dosyasının ve Main sınıfının erişilebilir olmasını sağlıyoruz
    opens com.mayin_tarlasi to javafx.fxml;
    exports com.mayin_tarlasi;
}
