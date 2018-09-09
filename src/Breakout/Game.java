package Breakout;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.Random;

public class Game {
    public static final String TITLE = "Breakout";
    public static final int WIDTH = 400;
    public static final int HEIGHT = 600;
    public static final int FRAMES_PER_SECOND = 100;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

    public static final Paint BACKGROUND = Color.rgb(245,245,245);

    public static final int PADDLE_WIDTH = 60;
    public static final int PADDLE_HEIGHT = 15;

    public static final int BRICK_WIDTH = 80;
    public static final int BRICK_HEIGHT = 24;

    public static final int BALL_SPEED = 300;
    public static final int BALL_SIZE = 16;

//    Returns an "interesting", non-zero random value in the range (min, max)
    public static final int getRandomInRange (int min, int max) {
        return min + dice.nextInt(max - min) + 1;
    }




    private ScreenController screenController;
    private GameController gameController;
    private GamePhysics physics;

    private static Random dice = new Random();



    public void initialize(Stage stage) {
//        Initialize screen controller for levels
        screenController = new ScreenController();
        Scene scene = screenController.getScene();
        stage.setScene(scene);
        stage.setTitle(TITLE);
        stage.show();


        gameController = new GameController();
//        Initialize game mechanics
        physics = new GamePhysics(screenController);
//        Attach "game loop" to timeline to play it
        var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step());
        var animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }



//    Performs methods every frame
    private void step () {
        physics.collisionEffects();
        physics.moveScreenElements();
        if (physics.ballOutBottom()) {
            gameController.decreaseLife();
        }

        if (screenController.allBricksRemoved()) {
            gameController.increaseLevel();
        }
    }







}
