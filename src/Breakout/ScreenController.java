package Breakout;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;
import java.util.ArrayList;
import javafx.scene.text.Text;
import javafx.scene.text.Font;



public class ScreenController {

    private Scene scene;
    private Group root;
    private Paddle paddle;
    private Ball ball;
    private ArrayList<Brick> bricks;
    private int paddleDirection;
    private int numPermanentBlocks;
    private Text levelText;
    private Text lifeText;

    private int level;
    private int life;

    public ScreenController(int gameLevel, int gameLife) {
        level = gameLevel;
        life = gameLife;
        root = new Group();
        // create a place to see the shapes
        scene = new Scene(root, Game.WIDTH, Game.HEIGHT, Game.BACKGROUND);
        setPaddle();
        setBall();
        setBricks();
        setLevelText();
        setLifeText();
        paddleDirection = 0;
    }


    public Scene getScene() {
        return scene;
    }

    public Group getGroup() {
        return root;
    }


    public void setLevel(int l) {
        level = l;
        resetBallPaddle();
        removeAllBricks();
        setBricks();
        changeLevelText();
    }

    public void setLife(int l) {
        life = l;
        changeLifeText();
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

    public void setPaddleDirection(int direction) {
        paddleDirection = direction;
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
        int dir = Game.getRandomInRange(-1, 1);
        int xRandVel = Game.getRandomInRange(30, 80);
        if (dir == 0) {
            xRandVel *= -1;
        }
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
        int type = 1;
        switch (level) {
            case 1: type = 1;
                break;
            case 2: type = 2;
                break;
            case 3: type = 3;
                break;
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

    private void removeAllBricks() {
        for (Brick brick: bricks) {
            root.getChildren().remove(brick.getView());
        }
        bricks.clear();
    }
    public boolean allBricksRemoved() {
        return bricks.isEmpty();
    }


    private void setLevelText() {
        levelText = new Text(Game.WIDTH * 4 / 5, Game.HEIGHT / 30, "Level: " + level);
        Font font = new Font(Game.DISPLAY_SIZE);
        levelText.setFont(font);
        root.getChildren().add(levelText);
    }

    private void changeLevelText() {
        levelText.setText("Level: " + level);
    }

    private void setLifeText() {
        lifeText = new Text(0, Game.HEIGHT / 30, "Lives: " + life);
        Font font = new Font(Game.DISPLAY_SIZE);
        lifeText.setFont(font);
        root.getChildren().add(lifeText);
    }

    private void changeLifeText() {
        lifeText.setText("Lives: " + life);
    }

}
