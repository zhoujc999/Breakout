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


public class Powerup {
    private ImageView powerup;
    private Brick brick;
    private int yVel;
    private int type;
    private boolean remove;

    public Powerup(int powerupType, Brick b) {
        String path = "";
        brick = b;
        remove = false;
        type = powerupType;

        switch (type) {
            case 1:
                path = Game.POWERUP1_PATH;
                break;

            case 2:
                path = Game.POWERUP2_PATH;
                break;

            case 3:
                path = Game.POWERUP3_PATH;
                break;
        }
        Image image = new Image(getClass().getClassLoader().getResourceAsStream(path));
        powerup = new ImageView(image);

        yVel = Game.getRandomInRange(Game.POWERUP_MINSPEED, Game.POWERUP_MAXSPEED);
        double x = brick.getMinX() + Game.BRICK_WIDTH / 2;
        double y = brick.getMinY();
        powerup.setX(x);
        powerup.setY(y);
        powerup.setFitWidth(Game.POWERUP_SIZE);
        powerup.setFitHeight(Game.POWERUP_SIZE);
    }

    public ImageView getView() {
        return powerup;
    }

    public int getType(){
        return type;
    }

    public double getMinX(){
        return powerup.getBoundsInParent().getMinX();
    }

    public double getMaxX(){
        return powerup.getBoundsInParent().getMaxX();
    }

    public double getMinY(){
        return powerup.getBoundsInParent().getMinY();
    }

    public double getMaxY(){
        return powerup.getBoundsInParent().getMaxY();
    }

    public void remove() {
        remove = true;
    }

    public boolean toBeRemoved() {
        return remove;
    }

    public void move(){
        if (brick.toBeRemoved()) {
            powerup.setY(powerup.getY() + Game.SECOND_DELAY * yVel);
        }
    }
}