package Breakout;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.Random;


public class ScreenController {

    private Scene scene;
    private Group root;
    private Paddle paddle;
    private Ball ball;
    private ArrayList<Brick> bricks;
    private int paddleDirection;


    public ScreenController() {
        root = new Group();
        // create a place to see the shapes
        scene = new Scene(root, Game.WIDTH, Game.HEIGHT, Game.BACKGROUND);
        scene.setOnKeyPressed(e -> handleKeyPress(e.getCode()));
        scene.setOnKeyReleased(event -> handleKeyRelease(event.getCode()));
        setPaddle();
        setBall();
        setBricks();
        paddleDirection = 0;
    }


    public Scene getScene() {
        return scene;
    }

    public Group getGroup() {
        return root;
    }


    public void setLevel(int level) {



    }




    public void resetBallPaddle() {
        resetPaddle();
        resetBall();
    }


    private void setPaddle() {
        paddle = new Paddle((Game.WIDTH - Game.PADDLE_WIDTH) / 2, 5.0 / 6 * Game.HEIGHT);
        root.getChildren().add(paddle.getView());
    }

    public Paddle getPaddle() {
        return paddle;
    }

    public void movePaddle() {
        getPaddle().move(paddleDirection);
    }

    private void resetPaddle() {
        root.getChildren().remove(paddle.getView());
        setPaddle();
    }


    private void setBallStartState() {
        double paddleMiddle = (getPaddle().getMinX() + getPaddle().getMaxX()) / 2 - Game.BALL_SIZE / 2;
        int xRandVel = Game.getRandomInRange(20, 80);
        int yRandVel = (int) Math.sqrt(Game.BALL_SPEED * Game.BALL_SPEED - xRandVel * xRandVel);
        ball.setX(paddleMiddle);
        ball.setY(getPaddle().getMinY() - (double) Game.BALL_SIZE);
        ball.setXVel(xRandVel);
        ball.setYVel(-yRandVel);
    }
    private void setBall() {
        ball = new Ball();
        setBallStartState();
        root.getChildren().add(ball.getView());
    }

    public Ball getBall() {
        return ball;
    }

    public void moveBall() {
        getBall().move();
    }


    private void resetBall() {
        root.getChildren().remove(ball.getView());
        setBall();
    }

    private void setBricks(int level) {
        bricks = new ArrayList<>();
        int type = 1;
        switch (level) {
            case 1: type = 1;
            case 2: type = 2;
            case 3: type = 3;
        }

        for (int i = 100; i < 300; i += 60) {
            for (int j = 20; j < 300; j += 120) {
                Brick brick = new Brick(j, i, type);
                root.getChildren().add(brick.getView());
                bricks.add(brick);
            }
        }

    }

    public ArrayList getBricks() {
        return bricks;
    }

    public void removeBrick(Brick b) {
        root.getChildren().remove(b.getView());
    }

    public boolean allBricksRemoved() {
        return bricks.isEmpty();
    }

    public void resetBricks() {
        for (Brick brick: bricks) {
            root.getChildren().remove(brick.getView());
        }
        bricks.clear();

        setBricks();
    }

//    What to do each time a key is pressed
    private void handleKeyPress(KeyCode code) {
        if (code == KeyCode.RIGHT) {
            paddleDirection = 1;
        }
        else if (code == KeyCode.LEFT) {
            paddleDirection = -1;
        }
    }

    private void handleKeyRelease(KeyCode code) {
        if (code == KeyCode.RIGHT || code == KeyCode.LEFT) {
            paddleDirection = 0;
        }
    }

}
