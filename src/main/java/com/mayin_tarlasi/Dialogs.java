package com.mayin_tarlasi;


import javafx.scene.control.Alert;

public class Dialogs {
    public static void show(String title, String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
 public static void gameOver(){
  Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Oyun Bitti");
    alert.setHeaderText("💣 BOOM!");
    alert.setContentText("Mayına bastın.\nYeniden deneyin.");

    alert.showAndWait(); 
}
}
