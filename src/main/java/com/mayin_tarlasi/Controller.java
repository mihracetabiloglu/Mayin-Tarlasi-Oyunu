package com.mayin_tarlasi;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;

public class Controller {
@FXML private GridPane grid;
    @FXML private MenuItem kolay, orta, zor;
    @FXML private MenuItem yeniOyun, cikis;

    private GameBoard board;
    private DifficultyLevel zorluk = DifficultyLevel.ORTA; // varsayılan
    private CellButton[][] buttons;

    @FXML
    public void initialize() {
        // Menü event bağlamaları
        kolay.setOnAction(e -> oyunuBaslat(DifficultyLevel.KOLAY));
        orta.setOnAction(e -> oyunuBaslat(DifficultyLevel.ORTA));
        zor.setOnAction(e -> oyunuBaslat(DifficultyLevel.ZOR));

        yeniOyun.setOnAction(e -> oyunuBaslat(zorluk));
        cikis.setOnAction(e -> System.exit(0));
        

        oyunuBaslat(zorluk);
}
private void oyunuBaslat(DifficultyLevel level){
        this.zorluk = level;
        board = new GameBoard(level);
        SoundPlayer.stopMineMusic();
        grid.getChildren().clear();
        grid.setGridLinesVisible(true);

        int rows = level.getRows();
        int cols = level.getCols();

        buttons = new CellButton[rows][cols];

        for(int r=0; r<rows; r++){
            for(int c=0; c<cols; c++){
                CellButton btn = new CellButton(r,c);
                buttons[r][c] = btn;

              btn.setOnMousePressed(e -> {
    if (board.isGameOver || board.checkWin()) return;

    if (e.getButton() == MouseButton.PRIMARY) {
        board.OpenSquaze(btn.getRow(), btn.getCol());
    } 
    else if (e.getButton() == MouseButton.SECONDARY) {
        board.toggleFlag(btn.getRow(), btn.getCol());
    }

    updateUI();
    checkStatus();
});

                grid.add(btn, c, r);
            }
        }
        updateUI();
    }

    private void updateUI(){
        int rows = zorluk.getRows();
        int cols = zorluk.getCols();

        for(int r=0; r<rows; r++){
            for(int c=0; c<cols; c++){
                buttons[r][c].guncelle(board.getCell(r,c));
            }
        }
    }
private void lockBoard() {
    for (CellButton[] row : buttons) {
        for (CellButton btn : row) {
            btn.setDisable(true);
        }
    }
}
private void checkStatus() {
    if (board.isGameOver) {
        SoundPlayer.playMineMusic(); 
        lockBoard();              // grid kilitlenir
        Dialogs.gameOver();       // tablo çıkar
    } 
    else if (board.checkWin()) {
        Dialogs.show("OYUNU KAZANDIN", "Tebrikler! Tüm mayınları buldun.");
    }
}
}
