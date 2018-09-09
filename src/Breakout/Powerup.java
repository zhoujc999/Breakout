//package Breakout;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javafx.animation.KeyFrame;
//import javafx.animation.Timeline;
//import javafx.application.Application;
//import javafx.scene.Group;
//import javafx.scene.Scene;
//import javafx.scene.image.*;
//import javafx.scene.input.KeyCode;
//import javafx.scene.paint.Color;
//import javafx.scene.paint.Paint;
//import javafx.scene.shape.Rectangle;
//import javafx.scene.shape.Shape;
//import javafx.stage.Stage;
//import javafx.util.Duration;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.scene.control.Button;
//import javafx.scene.layout.StackPane;
//
//
//public class Powerup {
//    public Powerup(double theType, Brick brick){
//        String path = POWERUP_IMAGE1;
//        if (theType <= 0.1){
//            type = 1;
//            path = POWERUP_IMAGE1;
//        }
//        else if (theType <= 0.2){
//            type = 2;
//            path = POWERUP_IMAGE2;
//        }
//        else if (theType <= 0.3){
//            type = 3;
//            path = POWERUP_IMAGE3;
//        }
//        else{
//            type = 4;
//            path = POWERUP_IMAGE4;
//        }
//        Image image = new Image(getClass().getClassLoader().getResourceAsStream(path));
//        powerup = new ImageView(image);
//
//        Random rn = new Random();
//        powerup_speed = rn.nextInt(101) + 50;
//
//        double x = brick.getMinX() + brick.getWidth() / 2;
//        double y = brick.getMinY() + brick.getHeight();
//        powerup.setX(x);
//        powerup.setY(y);
//    }
//
//    public ImageView getPowerup(){
//        return powerup;
//    }
//
//    public int getType(){
//        return type;
//    }
//
//    public double getMinX(){
//        return powerup.getBoundsInParent().getMinX();
//    }
//
//    public double getMaxX(){
//        return powerup.getBoundsInParent().getMaxX();
//    }
//
//    public double getMinY(){
//        return powerup.getBoundsInParent().getMinY();
//    }
//
//    public double getMaxY(){
//        return powerup.getBoundsInParent().getMaxY();
//    }
//
//    public double getWidth(){
//        return powerup.getBoundsInParent().getWidth();
//    }
//
//    public double getHeight(){
//        return powerup.getBoundsInParent().getHeight();
//    }
//
//    public boolean getRemovalMark(){
//        return toBeRemoved;
//    }
//
//    /**
//     * Define movement of Powerup.
//     * @param elapsedTime
//     */
//    public void powerupMove(double elapsedTime){
//        powerup.setY(getMinY() + elapsedTime * powerup_speed);
//    }
//
//    public void setRemovalMark(){
//        toBeRemoved = true;
//    }
//}