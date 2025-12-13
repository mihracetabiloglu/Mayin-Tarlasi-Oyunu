module com.mayin_tarlasi {
    // JavaFX kütüphanelerini kullanacağımızı belirtiyoruz
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media; // Müzik çalar eklemiştik

    // Eğer uygulama native erişim gerektiren kütüphaneler kullanıyorsa,
    // ek izinler veya --add-opens may be required at runtime.

    // FXML dosyasının ve Main sınıfının erişilebilir olmasını sağlıyoruz
    opens com.mayin_tarlasi to javafx.fxml;
    exports com.mayin_tarlasi;
}
