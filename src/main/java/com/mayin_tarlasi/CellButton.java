package com.mayin_tarlasi;


import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CellButton extends Button {

    private final int row;
    private final int col;

    public CellButton(int row, int col) {
        this.row = row;
        this.col = col;

        // Buton kare boyut
        setPrefSize(40, 40);

        // Yazı tipi
        setFont(Font.font("Arial", FontWeight.BOLD, 14));

        // Varsayılan görünüm
        setStyle("-fx-background-color:  rgb(248,167,214);");
    }

    // Hücre durumunu ekrana uygula
    public void guncelle(Cell cell) {

        // --- HÜCRE AÇIKSA ---
        if (cell.isOpened()) {
            setDisable(true);
            setOpacity(1.0); // JavaFX disable olunca solması engellendi

            if (cell.isMine()) {
                // Mayın patladı
                setText("💣");
                setStyle("-fx-background-color: red; -fx-opacity: 1;");
            } else {
                // Normal açılmış hücre
                int count = cell.getAdjacentMineCount();
                setText(count == 0 ? "" : String.valueOf(count));
                setStyle("-fx-background-color: #dddddd; -fx-opacity: 1; -fx-text-fill: black;");
            }
            return;
        }

        // --- HÜCRE AÇIK DEĞİL AMA BAYRAKLIYSA ---
        if (cell.isFlagged()) {
            setDisable(false);
            setText("🚩");
            setStyle("-fx-background-color: #f7e96b; -fx-opacity: 1; -fx-text-fill: black;");
            return;
        }

        // --- KAPALI VE BAYRAKSIZ HÜCRE ---
        setDisable(false);
        setText("");
        setStyle("-fx-background-color: rgb(248,167,214); -fx-opacity: 1; -fx-border-color: white;");
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
