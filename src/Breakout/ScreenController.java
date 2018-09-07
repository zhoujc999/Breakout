package Breakout;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;


public class ScreenController {

    private Scene scene;
    private Group root;
    private Paddle paddle;



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



    public void setPaddle() {
        paddle = new Paddle(180,500);
        root.getChildren().add(paddle.getView());
    }

    public Paddle getPaddle() {
        return paddle;
    }

    public void setBall() {


    }



}
