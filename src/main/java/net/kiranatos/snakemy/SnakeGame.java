package net.kiranatos.snakemy;

import java.util.*;
import net.kiranatos.engine.Game;
import net.kiranatos.engine.KeyJavaRush;

public class SnakeGame extends Game {

    //клетки 17х17
    public static final int WIDTH = 17;
    public static final int HEIGHT = 17;
    private static final int GOAL = 28;
    private static byte totalSnake = 0;
    public static boolean winFlag = false;
        
    private int turnDelay = 200;
    private boolean isGameStopped;    
    private int score;
    
    //{ showGrid(false); } //Сетка
    
    public static void main(String[] args) { launch(args); }
    
    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }
    
    private void createGame() {
        GOSnake.isAlive = true;
        winFlag = false;
        score = 0;
        setScore(0);
        Snake.snake1 = new Snake(WIDTH / 2, HEIGHT / 2);        
        createNewApple();
        createNewRiceBall(); //*****************************************************************
        createAmanitas();
        GOCellOnPanel.generatePanels();
        isGameStopped = false;
        drawScene();        
        setTurnTimer(turnDelay);
    }

    /**
     * Create One GOApple
     */
    private void createNewApple() {
        GOApple innerApple;
        do {
            innerApple = new GOApple(getRandomNumber(WIDTH),getRandomNumber(1, HEIGHT-1));      
        } while (Snake.snake1.checkCollision(innerApple));
        GOApple.apple = innerApple;
        GOApple.isAlive = true;
        
        
        //createAmanitas(); // Создание множества мухоморов
    }
    
    /**
     * Create One GORiceBall
     */
    private void createNewRiceBall() {
        GORiceBall innerBall;
        boolean b_apple = false;
        do {
            innerBall = new GORiceBall(getRandomNumber(WIDTH),getRandomNumber(1, HEIGHT-1));   
            if (GOApple.existApple()) b_apple = GOApple.apple.checkCollision(innerBall);
        } while (Snake.snake1.checkCollision(innerBall) || b_apple);
        GORiceBall.riceBall = innerBall;
        GORiceBall.isAlive = true;
    }
    
    /**
     * Создание множества мухоморов
     */
    private void createAmanitas() {
        GOAmanita.bunchOfAmanitas.clear();
        GOAmanita amanita;
        boolean b_apple = false, b_ball = false;
        for (int i = 0; i < GOAmanita.VALUE_OF_AMANITAS; i++) {
            do {
                amanita = new GOAmanita(getRandomNumber(WIDTH),getRandomNumber(1, HEIGHT-1));
                if (GOApple.existApple()) b_apple = GOApple.apple.checkCollision(amanita);
                if (GORiceBall.existRiceBall()) b_ball = GORiceBall.riceBall.checkCollision(amanita);
            } while (Snake.snake1.checkCollision(amanita) || GOAmanita.checkCollision(amanita) || b_apple || b_ball);
            GOAmanita.bunchOfAmanitas.add(amanita);
        }
    }
    
    /**
     * Отрисовка поля, двух панелей, змеи, яблока и мухоморов
     */
    private void drawScene() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 1; y < HEIGHT-1; y++) {                
                DrawAdapter.drawCell(this, new GameObject(x, y,
                        GameGUIMasterManager.getFieldColor(ObjectCondition.TEXT),
                        GameGUIMasterManager.getFieldColor(ObjectCondition.BACKGROUND),
                        GameGUIMasterManager.getFieldSign(ObjectCondition.SIGN_EMPTY)
                ));
            }
        }               
        
        Snake.snake1.draw(this);
        if (GOApple.existApple()) GOApple.apple.draw(this);
        if (GORiceBall.existRiceBall()) GORiceBall.riceBall.draw(this); //*****************************************************************************
        for (GOAmanita amanita: GOAmanita.bunchOfAmanitas) {
            amanita.draw(this);            
        }
        for (GOCellOnPanel cell: GOCellOnPanel.upperPanel) {
            cell.draw(this);            
        }
        for (GOCellOnPanel cell: GOCellOnPanel.downPanel) {
            cell.draw(this);            
        }        
    }

    /**
     * Ключевая точка, цыкл игры, передача данных между разными частями проекта
     * @param step 
     */
    @Override
    public void onTurn(int step) {
        
        Snake.snake1.move();
        
        if (GOApple.isAlive == false) { // APPLE was eaten
            
            //GORiceBall.isAlive = false; // rice ball will be deleted too
            createNewApple();
            createAmanitas();
            
            GameGUIMasterManager.reqestActions(GOApple.apple.getSign()); // transformation in colors & signs
            
            score = score + 5;
            setScore(score);
        }
        // createNewRiceBall(); //***************************************************************************************
        if (GORiceBall.isAlive == false) { // RICE BALL was eaten
            
            //GOApple.isAlive = false;
            createNewRiceBall();
            createAmanitas();
            
            GameGUIMasterManager.reqestActions(GORiceBall.riceBall.getSign()); // transformation in colors & signs
            
            score = score + 5;
            setScore(score);
        }
        
        
        
        if (GOSnake.isAlive == false) gameOver();
        if (winFlag) win();
        
        drawScene();
    }

    @Override
    public void onKeyPress(KeyJavaRush key) {
        switch (key) {
            case LEFT:
                Snake.snake1.setDirection(Direction.LEFT);
                break;
            case RIGHT:
                Snake.snake1.setDirection(Direction.RIGHT);
                break;
            case UP:
                Snake.snake1.setDirection(Direction.UP);
                break;
            case DOWN:
                Snake.snake1.setDirection(Direction.DOWN);
                break;
            case SPACE:
                if (isGameStopped == true) createGame();
                break;
        }
    }

    private void gameOver() {
        if (GOApple.existApple()) GOApple.apple.setColorText(GameGUIMasterManager.getAppleColor(ObjectCondition.TEXT));
        if (GORiceBall.existRiceBall()) GORiceBall.riceBall.setColorText(GameGUIMasterManager.getRiceBallColor(ObjectCondition.TEXT));
        stopTurnTimer();
        GameGUIMasterManager.reset();
        isGameStopped = true;
        showMessageDialog(
                GameGUIMasterManager.getFieldColor(ObjectCondition.BACKGROUND_LOSE), 
                "Змейка №" + (++totalSnake) + " погибла.\nЖизнь сурова и не прощает ошибок.\nЕё смерть на Вашей совести.\nНе ошибайтесь больше...", 
                GameGUIMasterManager.getFieldColor(ObjectCondition.TEXT_LOSE),
                20);
    }

    private void win() {
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(
                GameGUIMasterManager.getFieldColor(ObjectCondition.BACKGROUND_WIN),
                "Змейка №" + (++totalSnake) + "\nотпущена на волю!", 
                GameGUIMasterManager.getFieldColor(ObjectCondition.TEXT_WIN),
                40);
    }
}