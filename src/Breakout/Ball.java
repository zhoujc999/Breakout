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

public class Ball {
    private double xVel;
    private double yVel;
    private ImageView ball;



    /**
     * Constructor of the Paddle class.
     */

    public Ball() {
        Image image = new Image(getClass().getClassLoader().getResourceAsStream(Game.BALL_PATH));
        ball = new ImageView(image);
        ball.setX(0);
        ball.setY(0);
        xVel = 0;
        yVel = 0;
        ball.setFitWidth(Game.BALL_SIZE);
        ball.setFitHeight(Game.BALL_SIZE);
    }

    public ImageView getView() {
        return ball;
    }

    public double getMinX() {
        return ball.getBoundsInParent().getMinX();
    }

    public double getMaxX() {
        return ball.getBoundsInParent().getMaxX();
    }

    public double getMinY() {
        return ball.getBoundsInParent().getMinY();
    }

    public double getMaxY() {
        return ball.getBoundsInParent().getMaxY();
    }

    public double getXVel() {
        return xVel;
    }

    public double getYVel() {
        return yVel;
    }

    public void setX(double x) {
        ball.setX(x);
    }

    public void setY(double y) {
        ball.setY(y);
    }

    public void setXVel(double xV) {
        xVel = xV;
    }

    public void setYVel(double yV) {
        yVel = yV;
    }

    public void setSpeed(double times) {
        xVel *= times;
        yVel *= times;
    }

    public void move() {
        ball.setX(ball.getX() + Game.SECOND_DELAY * xVel);
        ball.setY(ball.getY() + Game.SECOND_DELAY * yVel);
    }

    public void xBounce() {
        if (yVel < 3 && yVel > -3) {
            yVel += Game.getRandomInRange(-5, 5);
        }
        double randVal = Game.getRandomInRange(950, 1050) / 1000.0;
        xVel *= -randVal;
    }
    public void yBounce() {
        if (xVel < 3 && xVel > -3) {
            xVel += Game.getRandomInRange(-5, 5);
        }
        double randVal = Game.getRandomInRange(950, 1050) / 1000.0;
        yVel *= -randVal;
    }
}
