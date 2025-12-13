package com.mayin_tarlasi;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class Main extends Application {

    private GameBoard board;
    private GridPane gridPane;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Mayın Tarlası");

        ComboBox<DifficultyLevel> difficultyBox = new ComboBox<>();
        difficultyBox.getItems().addAll(DifficultyLevel.values());
        difficultyBox.setValue(DifficultyLevel.KOLAY);

        Button startButton = new Button("Başla");

        HBox topControls = new HBox(10, difficultyBox, startButton);
        topControls.setAlignment(Pos.CENTER);

        gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setTop(topControls);
        root.setCenter(gridPane);

        startButton.setOnAction(e -> startGame(difficultyBox.getValue()));

        Scene scene = new Scene(root, 500, 500);
        stage.setScene(scene);
        stage.show();

        // Ses sistemini başlat
        initAudio();
    }

    private void startGame(DifficultyLevel level) {
        board = new GameBoard(level);
        drawBoard(level);
    }

    private void drawBoard(DifficultyLevel level) {
        gridPane.getChildren().clear();

        for (int r = 0; r < level.getRows(); r++) {
            for (int c = 0; c < level.getCols(); c++) {

                Button cellButton = new Button();
                cellButton.setPrefSize(35, 35);

                final int row = r;
                final int col = c;

                cellButton.setOnMouseClicked(e -> {
                    if (e.getButton() == MouseButton.PRIMARY) {
                        handleLeftClick(row, col, cellButton);
                    } else if (e.getButton() == MouseButton.SECONDARY) {
                        handleRightClick(row, col, cellButton);
                    }
                });

                gridPane.add(cellButton, c, r);
            }
        }
    }

    private void handleLeftClick(int r, int c, Button btn) {
        board.OpenSquaze(r, c);
        updateUI();

        if (board.getCell(r, c).isMine()) {
            board.revealMines();
            updateUI();
            System.out.println("Oyun Bitti!");
        }

        if (board.checkWin()) {
            System.out.println("Kazandın!");
        }
    }

    private void handleRightClick(int r, int c, Button btn) {
        board.toggleFlag(r, c);
        updateUI();
    }

    private void updateUI() {
        for (int r = 0; r < board.getCell(0,0).getClass().getDeclaredFields().length; r++) { }

        for (var node : gridPane.getChildren()) {
            int col = GridPane.getColumnIndex(node);
            int row = GridPane.getRowIndex(node);
            Button b = (Button) node;
            Cell cell = board.getCell(row, col);

            if (cell.isOpened()) {
                if (cell.isMine()) {
                    b.setText("*");
                    b.setStyle("-fx-background-color: red;");
                } else {
                    b.setText(String.valueOf(cell.getAdjacentMineCount()));
                    b.setStyle("-fx-background-color: lightgray;");
                }
            } else if (cell.isFlagged()) {
                b.setText("F");
                b.setStyle("-fx-background-color: yellow;");
            } else {
                b.setText("");
                b.setStyle("");
            }
        }
    }

    private MediaPlayer minePlayer;


private void initAudio() {
    try {
        Media media = new Media(getClass().getResource("/sounds/mayin_tarlasi.mp3")
                .toExternalForm());
        minePlayer = new MediaPlayer(media);
        minePlayer.setVolume(1.0);
    } catch (Exception e) {
        System.out.println("Ses yüklenemedi: " + e.getMessage());
    }
}

private void playMineSound() {
    if (minePlayer == null) return;
    minePlayer.stop();
    minePlayer.play();
}

    public static void main(String[] args) {
        launch(args);
    }
}
