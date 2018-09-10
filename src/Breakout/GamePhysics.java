package Breakout;

import java.util.ArrayList;

public class GamePhysics {
    private LevelScreenController lsc;
    private Ball ball;
    private Paddle paddle;
    private ArrayList<Brick> bricks;
    private ArrayList<Brick> bricksToBeRemoved;
    private ArrayList<Powerup> powerups;
    private ArrayList<Powerup> powerupsToBeRemoved;
    private int powerupType;



    public GamePhysics(LevelScreenController levelScreenController) {
        lsc = levelScreenController;
        bricksToBeRemoved = new ArrayList<>();
        powerupsToBeRemoved = new ArrayList<>();
        getScreenElements();
    }


    public void getScreenElements() {
        ball = lsc.getBall();
        paddle = lsc.getPaddle();
        bricks = lsc.getBricks();
        powerups = lsc.getPowerups();
        powerupType = 0;
    }

    public void moveAllScreenElements() {
        lsc.moveBall();
        lsc.movePaddle();
        lsc.movePowerups();
    }

    public void onlyMovePaddle() {
        lsc.movePaddle();
    }

    public void collisionEffects() {
        ballOutTop();
        ballWithWall();
        ballWithPaddle();
        ballWithBricks();
        deactivatePowerup();
        paddleWithPowerups();
    }


    private void ballWithWall() {
        double ballMinX = ball.getMinX();
        double ballMaxX = ball.getMaxX();
        if (ballMinX <= 0 || ballMaxX >= Game.WIDTH) {
            ball.xBounce();
        }
    }

    public boolean ballOutBottom() {
        return ball.getMaxY() >= Game.HEIGHT;
    }


    private void ballOutTop() {
        if (ball.getMinY() <= 0) {
            ball.yBounce();
        }
    }

    private void ballWithPaddle() {
        double ballMinX = ball.getMinX();
        double ballMaxX = ball.getMaxX();
        double ballMinY = ball.getMinY();
        double ballMaxY = ball.getMaxY();
        double paddleMinX = paddle.getMinX();
        double paddleMaxX = paddle.getMaxX();
        double paddleMinY = paddle.getMinY();
        double paddleMaxY = paddle.getMaxY();

        double paddleHalf = Game.PADDLE_WIDTH / 2;

        // check for hit on the upper edge
        if (ball.getYVel() > 0 && ballMaxY >= paddleMinY && ballMinY <= paddleMinY && ((ballMaxX >= paddleMinX) && (ballMaxX <= paddleMaxX) || (ballMinX >= paddleMinX && ballMinX <= paddleMaxX))) {
            ball.yBounce();

            if (ball.getXVel() > 0 && (paddleMinX + paddleHalf) > ballMaxX) {
                ball.xBounce();
            }
            if (ball.getXVel() < 0 && (paddleMinX + paddleHalf) < ballMinX) {
                ball.xBounce();
            }
        }

    }

//    Detect ball collision with bricks
    private void ballWithBricks() {
        bricksToBeRemoved.clear();
        for (Brick brick: bricks) {
            ballWithBrick(brick);
            if (brick.toBeRemoved()) {
                bricksToBeRemoved.add(brick);
            }

        }
        bricks.removeAll(bricksToBeRemoved);

    }

//    Detect collision with individual brick
    private void ballWithBrick(Brick b) {
        double ballMinX = ball.getMinX();
        double ballMaxX = ball.getMaxX();
        double ballMinY = ball.getMinY();
        double ballMaxY = ball.getMaxY();
        double brickMinX = b.getMinX();
        double brickMaxX = b.getMaxX();
        double brickMinY = b.getMinY();
        double brickMaxY = b.getMaxY();

        // check for hit on the upper edge
        if (ball.getYVel() > 0 && ballMaxY >= brickMinY && ballMinY <= brickMinY && ((ballMaxX >= brickMinX) && (ballMaxX <= brickMaxX) || (ballMinX >= brickMinX && ballMinX <= brickMaxX))) {
            ball.yBounce();
            b.gotHit();
        }
        // check for hit on the lower edge
        else if (ball.getYVel() < 0 && ballMinY <= brickMaxY && ballMaxY >= brickMaxY && ((ballMaxX >= brickMinX) && (ballMaxX <= brickMaxX) || (ballMinX >= brickMinX && ballMinX <= brickMaxX))) {
            ball.yBounce();
            b.gotHit();
        }
        // check for hit on the left edge
        else if (ball.getXVel() > 0 && ballMaxX >= brickMinX && ballMinX <= brickMinX && ((ballMaxY >= brickMinY && ballMaxY <= brickMaxY) || (ballMinY >= brickMinY && ballMinY <= brickMaxY))) {
            ball.xBounce();
            b.gotHit();
        }
        // check for hit on the right edge
        else if (ball.getXVel() < 0 && ballMinX <= brickMaxX && ballMaxX >= brickMaxX && ((ballMaxY >= brickMinY && ballMaxY <= brickMaxY) || (ballMinY >= brickMinY && ballMinY <= brickMaxY))) {
            ball.xBounce();
            b.gotHit();
        }
        if (b.toBeRemoved()) {
            lsc.removeBrick(b);
        }
    }

    public void removeBricksEffect() {
        bricksToBeRemoved.clear();
        for (Brick brick: bricks) {
            if (brick.getType() == 3) {
                lsc.removeBrick(brick);
                bricksToBeRemoved.add(brick);
            }

        }
        bricks.removeAll(bricksToBeRemoved);
        lsc.clearNumPermBlocks();
    }

    private void paddleWithPowerups() {
        powerupsToBeRemoved.clear();
        for (Powerup powerup: powerups) {
            paddleWithPowerup(powerup);
            powerupOutBottom(powerup);
            if (powerup.toBeRemoved()) {
                powerupsToBeRemoved.add(powerup);
            }

        }
        powerups.removeAll(powerupsToBeRemoved);

    }

    private void paddleWithPowerup(Powerup powerup) {
        double powerupMinX = powerup.getMinX();
        double powerupMaxX = powerup.getMaxX();
        double powerupMinY = powerup.getMinY();
        double powerupMaxY = powerup.getMaxY();
        double paddleMinX = paddle.getMinX();
        double paddleMaxX = paddle.getMaxX();
        double paddleMinY = paddle.getMinY();
        double paddleMaxY = paddle.getMaxY();


        // check whether paddle catches power-up
        if (paddle.getView().intersects(powerup.getView().getBoundsInParent())) {
            activatePowerup(powerup.getType());
            powerup.remove();
            lsc.removePowerup(powerup);
        }

    }


    private void activatePowerup(int type) {
        powerupType = type;
    }

    public int getPowerup() {
        return powerupType;
    }
    private void deactivatePowerup() {
        powerupType = 0;
    }


    private void powerupOutBottom(Powerup powerup) {
        if (powerup.getMaxY() >= Game.HEIGHT) {
            powerup.remove();
            lsc.removePowerup(powerup);

        }
    }


}


