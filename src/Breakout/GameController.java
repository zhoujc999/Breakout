package Breakout;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;


public class GameController {
    private int level;
    private int life;
    private boolean won;
    private boolean dead;


    public GameController() {
        level = 1;
        life = 3;

    }

    public void increaseLevel() {
        if (level < 4) {
            level++;
        }
    }


    public int getLevel() {
        return level;

    }

    public boolean isWon() {
        return level == 3;
    }

    public void decreaseLife() {
        if (life > 0) {
            life--;
        }

    }

    public void increaseLife() {
        if (life < 5) {
            life++;
        }
    }

    public int getLife() {
        return life;
    }

    public boolean isDead() {
        return life == 0;
    }
}
