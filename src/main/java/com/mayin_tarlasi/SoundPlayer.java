package com.mayin_tarlasi;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundPlayer {

    private static MediaPlayer minePlayer;

    public static void playMineMusic() {
        try {
            if (minePlayer != null) {
                minePlayer.stop();
            }

            Media media = new Media(
                SoundPlayer.class
                    .getResource("/sounds/mayin_tarlasi.wav")

                    .toExternalForm()
            );

            minePlayer = new MediaPlayer(media);
            minePlayer.setVolume(0.7);
            minePlayer.play();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stopMineMusic() {
        if (minePlayer != null) {
            minePlayer.stop();
        }
    }
}
    
