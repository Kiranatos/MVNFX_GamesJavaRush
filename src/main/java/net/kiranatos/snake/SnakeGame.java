package net.kiranatos.snake;

import net.kiranatos.engine.ColorJavaRush;
import net.kiranatos.engine.Game;
import net.kiranatos.engine.KeyJavaRush;

public class SnakeGame extends Game {

    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;
    private static final int GOAL = 28;
    private Snake snake;
    private Apple apple;
    private int turnDelay;
    private boolean isGameStopped;
    private int score;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }
    
    private void createGame() {
        score = 0;
        setScore(0);
        snake = new Snake(WIDTH / 2, HEIGHT / 2);
        //apple = new Apple(5,5);
        createNewApple();
        isGameStopped = false;
        drawScene();
        //Apple apple = new Apple(7,7);
        //apple.draw(this);
        turnDelay = 300;
        setTurnTimer(turnDelay);
    }

    private void createNewApple() {
        do {
            apple = new Apple(getRandomNumber(WIDTH),getRandomNumber(HEIGHT));
        } while (snake.checkCollision(apple));
    }
    
    private void drawScene() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                //setCellColor(x, y, Color.DARKSEAGREEN);
                setCellValueEx(x, y, ColorJavaRush.DARKSEAGREEN, "");
                }
        }
        snake.draw(this);
        apple.draw(this);
    }

    @Override
    public void onTurn(int step) {
        snake.move(apple);
        if (apple.isAlive == false) {
            createNewApple();
            score = score + 5;
            setScore(score);
            turnDelay = turnDelay - 10;
            setTurnTimer(turnDelay);
        }
        if (snake.isAlive == false) gameOver();
        if (snake.getLength() > GOAL) win();
        drawScene();
    }

    @Override
    public void onKeyPress(KeyJavaRush key) {
        switch (key) {
            case LEFT:
                snake.setDirection(Direction.LEFT);
                break;
            case RIGHT:
                snake.setDirection(Direction.RIGHT);
                break;
            case UP:
                snake.setDirection(Direction.UP);
                break;
            case DOWN:
                snake.setDirection(Direction.DOWN);
                break;
            case SPACE:
                if (isGameStopped == true) createGame();
                break;
        }
    }

    private void gameOver() {
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(ColorJavaRush.BLACK, "Game Over!", ColorJavaRush.CYAN, 20);
    }

    private void win() {
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(ColorJavaRush.BLACK, "You Won!", ColorJavaRush.CYAN, 50);
    }
}