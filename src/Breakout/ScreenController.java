package Breakout;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.Random;


public class ScreenController {

    private Scene scene;
    private Group root;
    private Paddle paddle;
    private Ball ball;
    private ArrayList<Brick> bricks;


    public ScreenController() {
        root = new Group();
        // create a place to see the shapes
        scene = new Scene(root, Game.WIDTH, Game.HEIGHT, Game.BACKGROUND);
        setPaddle();
        setBall();
        setBricks();
    }



    public Scene getScene() {
        return scene;
    }

    public Group getGroup() {
        return root;
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

    public void movePaddle(int direction) {
        getPaddle().move(direction);
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

    private void setBricks() {
        bricks = new ArrayList<>();
        for (int i = 100; i < 300; i += 60) {
            for (int j = 20; j < 300; j += 120) {
                Brick brick = new Brick(j, i, 1);
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

}
