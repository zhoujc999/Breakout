package Breakout;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;


public class ScreenController {

    private Scene scene;
    private Group root;
    private Paddle paddle;
    private Ball ball;



    public ScreenController() {
    }
    public void setScene() {
        root = new Group();
        // create a place to see the shapes
        scene = new Scene(root, Game.WIDTH, Game.HEIGHT, Game.BACKGROUND);
        setPaddle();
        setBall();

    }


    public Scene getScene() {
        return scene;
    }



    private void setPaddle() {
        paddle = new Paddle(180,500);
        root.getChildren().add(paddle.getView());
    }

    private Paddle getPaddle() {
        return paddle;
    }

    public void movePaddle(int direction) {
        getPaddle().move(direction);
    }

    private void setBall() {
        double paddleMiddle = (getPaddle().getMinX() + getPaddle().getMaxX()) / 2 - Game.BALL_SIZE / 2;
        ball = new Ball(paddleMiddle, getPaddle().getMinY() - (double) Game.BALL_SIZE, 0, -50);
        root.getChildren().add(ball.getView());
    }

    private Ball getBall() {
        return ball;
    }
    public void moveBall() {
        getBall().move();
    }

}
