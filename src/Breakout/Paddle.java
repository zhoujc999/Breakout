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

public class Paddle {
    private static final String PADDLE_IMAGE = "paddle.gif";
    private int PADDLE_WIDTH = 40;
    private int PADDLE_HEIGHT = 10;
    private int speed = 1600;
    private ImageView paddle;
    private final int CORRECTION = 0;


    /**
     * Constructor of the Paddle class.
     */
    public Paddle() {
        new Paddle(180, 500);
    }
    public Paddle(double x, double y) {
        Image image = new Image(getClass().getClassLoader().getResourceAsStream(PADDLE_IMAGE));
        paddle = new ImageView(image);
        paddle.setX(x);
        paddle.setY(y);
        paddle.setFitWidth(PADDLE_WIDTH);
        paddle.setFitHeight(PADDLE_HEIGHT);
    }

    public ImageView getView() {
        return paddle;
    }

    public double getMinX() {
        return paddle.getBoundsInParent().getMinX();
    }

    public double getMaxX() {
        return paddle.getBoundsInParent().getMaxX();
    }

    public double getMinY() {
        return paddle.getBoundsInParent().getMinY();
    }

    public double getMaxY() {
        return paddle.getBoundsInParent().getMaxY();
    }

    public double getWidth() {
        return paddle.getBoundsInParent().getWidth();
    }

    public double getHeight() {
        return paddle.getBoundsInParent().getHeight();
    }

    public void setX(double x) {
        paddle.setX(x);
    }

    public void setY(double y) {
        paddle.setY(y);
    }

    public void setSpeed(double times) {
        speed *= times;
    }

    public void move(int direction) {
        if (direction == 1 && getMaxX() <= (Game.WIDTH - CORRECTION) || direction == -1 && getMinX() >= (0 + CORRECTION)) {
            paddle.setX(getMinX() + speed * direction * Game.SECOND_DELAY);
        }
    }
}
