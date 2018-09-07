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

public class Brick {
    private int BRICK_WIDTH = 70;
    private int BRICK_HEIGHT = 15;
    private static final String BLUEBRICK_IMAGE = "brick1.gif";
    private static final String GREENBRICK_IMAGE = "brick8.gif";
    private static final String GREYBRICK_IMAGE = "brick3.gif";

    private ImageView brick;
    private int hits;
    private boolean toBeRemoved = false;

    public Brick(double x, double y, int type){
        String path = "";
        if (type == 1) {
            path = BLUEBRICK_IMAGE;
            hits = 1;
        }
        else if (type == 2) {
            path = GREENBRICK_IMAGE;
            hits = 3;
        }
        else if (type == 3) {
            path = GREYBRICK_IMAGE;
            hits = -1;
        }

        Image image = new Image(getClass().getClassLoader().getResourceAsStream(path));
        brick = new ImageView(image);
        brick.setX(x);
        brick.setY(y);
        brick.setFitWidth(BRICK_WIDTH);
        brick.setFitHeight(BRICK_HEIGHT);
    }

    public ImageView getView() {
        return brick;
    }

    public double getMinX() {
        return brick.getBoundsInParent().getMinX();
    }

    public double getMaxX() {
        return brick.getBoundsInParent().getMaxX();
    }

    public double getMinY() {
        return brick.getBoundsInParent().getMinY();
    }

    public double getMaxY() {
        return brick.getBoundsInParent().getMaxY();
    }

    public double getWidth() {
        return brick.getBoundsInParent().getWidth();
    }

    public double getHeight() {
        return brick.getBoundsInParent().getHeight();
    }

    public void setX(double x) {
        brick.setX(x);
    }

    public void setY(double y) {
        brick.setY(y);
    }



}
