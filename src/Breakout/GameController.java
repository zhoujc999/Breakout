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
    private int power;

    public GameController() {
        level = 1;
        life = 3;
        power = 0;
    }

    public void increaseLevel() {
        if (level < 4) {
            level++;
        }
    }

    public void setLevel(int l) {
        level = l;
    }


    public int getLevel() {
        return level;

    }

    public boolean isWon() {
        return level == 2;
    }

    public void increaseLife() {
        if (life < 5) {
            life++;
        }
    }

    public void decreaseLife() {
        if (life > 0) {
            life--;
        }

    }


    public int getLife() {
        return life;
    }

    public boolean isDead() {
        return life == 0;
    }

    public void increasePower() {
            power++;
    }

    public int getPower() {
        return power;

    }

    public void usePower() {
        if (power > 0) {
            power--;
        }
    }
}
