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
    private ImageView brick;
    private int hits;
    private int type;


    public Brick(double x, double y, int brickType) {
        String path = "";
        switch (brickType) {
            case 1:
                path = Game.BRICK1_IMAGE;
                hits = 1;
                break;

            case 2:
                path = Game.BRICK2_IMAGE;
                hits = 3;
                break;

            case 3:
                path = Game.BRICK3_IMAGE;
                hits = -1;
                break;
        }
        type = brickType;
        Image image = new Image(getClass().getClassLoader().getResourceAsStream(path));
        brick = new ImageView(image);
        brick.setX(x);
        brick.setY(y);
        brick.setFitWidth(Game.BRICK_WIDTH);
        brick.setFitHeight(Game.BRICK_HEIGHT);
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

    public int getType(){
        return type;
    }

    public void setX(double x) {
        brick.setX(x);
    }

    public void setY(double y) {
        brick.setY(y);
    }

    public void gotHit() {
        if (hits > 0) {
            hits--;
        }
    }

    public boolean toBeRemoved() {
        return hits == 0;
    }

}
