package com.mayin_tarlasi;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundPlayer {

    public static void play(String fileName) {
        try {
            String path = SoundPlayer.class.getResource("/sounds/" + fileName).toString();
            Media media = new Media(path);
            MediaPlayer player = new MediaPlayer(media);
            player.play();
        } catch (Exception e) {
            System.out.println("Ses çalınamadı: " + e.getMessage());
        }
    }
}
