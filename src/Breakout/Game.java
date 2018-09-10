package Breakout;

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

    public static final String LEVELONE_PATH = "level1.txt";
    public static final String LEVELTWO_PATH = "level2.txt";
    public static final String LEVELTHREE_PATH = "level3.txt";

    public static final String PADDLE_PATH = "paddle.gif";
    public static final int PADDLE_WIDTH = 60;
    public static final int PADDLE_HEIGHT = 12;
    public static final int PADDLE_SPEED = 160;

    public static final String BRICK1_IMAGE = "brick1.gif";
    public static final String BRICK2_IMAGE = "brick8.gif";
    public static final String BRICK3_IMAGE = "brick3.gif";
    public static final int BRICK_WIDTH = 80;
    public static final int BRICK_HEIGHT = 16;

//    Horizontal and vertical gaps between bricks
    public static final int BRICK_XGAP = 20;
    public static final int BRICK_YGAP = 5;

    public static final String BALL_PATH = "ball.gif";
    public static final int BALL_SPEED = 300;
    public static final int BALL_SIZE = 16;

    public static final String POWERUP1_PATH = "sizepower.gif";
    public static final String POWERUP2_PATH = "pointspower.gif";
    public static final String POWERUP3_PATH = "laserpower.gif";
    public static final int POWERUP_SIZE = 16;
    public static final int POWERUP_MINSPEED = 150;
    public static final int POWERUP_MAXSPEED = 250;

    public static final int POWERUP_DURATION = 5;
    public static final double POWERUP_RATIO = 1.2;



//    Life and level text size
    public static final int TEXT_SIZE = 20;

//    Returns an "interesting", non-zero random value in the range (min, max)
    public static final int getRandomInRange (int min, int max) {
        return min + dice.nextInt(max - min) + 1;
    }




    private LevelScreenController levelScreenController;
    private GameController gameController;
    private GamePhysics physics;
    private Timeline animation;

    private static Random dice = new Random();

    private boolean paused = false;

    private boolean timerStarted = false;
    private int timer = 0;
    private boolean specialPaused = false;

    public void initialize(Stage stage) {
//        Initialize screen controller for levels
        levelScreenController = new LevelScreenController(1, 3, 0);
        Scene scene = levelScreenController.getScene();
        stage.setScene(scene);
        stage.setTitle(TITLE);
        stage.show();


        gameController = new GameController();
//        Initialize game mechanics
        physics = new GamePhysics(levelScreenController);
//        Attach "game loop" to timeline to play it
        var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step());
//        Respond to key actions
        scene.setOnKeyPressed(event -> handleKeyPress(event.getCode()));
        scene.setOnKeyReleased(event -> handleKeyRelease(event.getCode()));
        animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        pause();
    }



//    Performs methods every frame
    private void step () {
        if (!specialPaused) {
//            Detect collisions
            physics.collisionEffects();
//            Move all objects
            physics.moveAllScreenElements();
//            Life lost
            if (physics.ballOutBottom()) {
                loseLife();
            }
//            Level Completed
            if (levelScreenController.allBricksRemoved()) {
                nextLevel();
            }

            if (physics.getPowerup() != 0) {
                if (physics.getPowerup() == 1) {
                    physics.removeBricksEffect();
                }
                else if (physics.getPowerup() == 2) {
                    tempPaddleEffects();
                }
                else {
                    gameController.increasePower();
                    levelScreenController.changePowerText(gameController.getPower());
                }
            }
        }
        else {
            physics.onlyMovePaddle();
        }
        if (timerStarted) {
            if (timer > 0) {
                timer--;
            }
            else {
                stopTimer();
            }
        }
    }

    private void tempPaddleEffects() {
        levelScreenController.getPaddle().setSpeed(POWERUP_RATIO);
        levelScreenController.getPaddle().setWidth(PADDLE_WIDTH * POWERUP_RATIO);
        timer = POWERUP_DURATION * FRAMES_PER_SECOND;
        timerStarted = true;
    }


    private void stopTimer() {
        levelScreenController.getPaddle().setSpeed(1 / POWERUP_RATIO);
        levelScreenController.getPaddle().setWidth(1 / PADDLE_WIDTH * POWERUP_RATIO);
        timerStarted = false;
    }

    private void loseLife() {
        gameController.decreaseLife();
        if (gameController.isDead()) {
//            animation.stop();
            pause();
        }
        levelScreenController.resetBallPaddle();
        levelScreenController.changeLifeText(gameController.getLife());
        physics.getScreenElements();
        pause();
    }

    private void nextLevel() {
        gameController.increaseLevel();
        if (gameController.isWon()) {
            animation.stop();
        }
        levelScreenController.setLevel(gameController.getLevel());
        physics.getScreenElements();
        pause();
    }


    //    What to do each time a key is pressed
    private void handleKeyPress(KeyCode code) {
//        Pause and resume game
        if (code == KeyCode.SPACE) {
            pause();
        }
//        Move paddle to the right
        else if (code == KeyCode.RIGHT) {
            levelScreenController.setPaddleDirection(1);
        }
//        Move paddle to the left
        else if (code == KeyCode.LEFT) {
            levelScreenController.setPaddleDirection(-1);
        }
        else if (code == KeyCode.P) {
            specialPause();
        }
    }

    private void handleKeyRelease(KeyCode code) {
//        Stop paddle movement when left or right is released
        if (code == KeyCode.RIGHT || code == KeyCode.LEFT) {
            levelScreenController.setPaddleDirection(0);
        }
    }


    private void pause() {
        if (paused) {
            animation.play();
            paused = false;
        }
        else {
            animation.pause();
            paused = true;
        }
    }

    private void specialPause() {
        if (!specialPaused && gameController.getPower() > 0) {
            gameController.usePower();
            levelScreenController.changePowerText(gameController.getPower());
            specialPaused = true;
        }
        else {
            specialPaused = false;
        }
    }

}
