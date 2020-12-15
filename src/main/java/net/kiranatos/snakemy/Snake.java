package net.kiranatos.snakemy;

import java.util.ArrayList;
import java.util.List;
import net.kiranatos.engine.Game;

public class Snake {    
    private Direction direction = Direction.LEFT;
    private GOSnake head;
    public static Snake snake1;
    
        
    public Snake (int x, int y) {
        head = new GOSnake(x, y);
        GOSnake tail = new GOSnake(x+1, y);
        head.setNextFromHead(tail);
        tail.setPreviuos(head);      
    }
    
    public void draw(Game game) {     
        head.draw(game);
        GOSnake part = head.getNextFromHead();
        do {
            part.setColorText(
                    (GOSnake.isAlive == false)? GameGUIMasterManager.getSnakeColor(ObjectCondition.DEAD) : GameGUIMasterManager.getSnakeColor(ObjectCondition.ALIVE)
            );      
            part.draw(game);
            
        } while((part = part.getNextFromHead())!=null);
    }

    public void setDirection(Direction direction) {
        switch (this.direction) {
            case LEFT:
                if (!(direction == Direction.RIGHT || head.getX() == head.getNextFromHead().getX())) this.direction = direction;
                break;
            case RIGHT:
                if (!(direction == Direction.LEFT || head.getX() == head.getNextFromHead().getX())) this.direction = direction;
                break;
            case UP:
                if (!(direction == Direction.DOWN || head.getY() == head.getNextFromHead().getY())) this.direction = direction;
                break;
            case DOWN:
                if (!(direction == Direction.UP || head.getY() == head.getNextFromHead().getY())) this.direction = direction;
                break;
        }
    }
    
    /**
     * События в жизни змеи
     * @param apple 
     */
    public void move() {
        GOSnake newHead = createNewHead(); // создание головы
        
        // проверка края экрана, где создалась голова
        if (newHead.getX() < 0) newHead.setX(SnakeGame.WIDTH-1);
        else if (newHead.getY() < 1) newHead.setY(SnakeGame.HEIGHT-2);
        else if (newHead.getX() >= SnakeGame.WIDTH) newHead.setX(0);
        else if (newHead.getY() >= SnakeGame.HEIGHT-1) newHead.setY(1);
                
        if (checkCollision(newHead)) GOSnake.isAlive = false; // Голова укусила хвост
        else if (GOAmanita.checkCollision(newHead)) GOSnake.isAlive = false; // Голова укусила мухомор
        else if (((GOApple.existApple()) && GOApple.apple.checkCollision(newHead))) {    // поглощение яблока: голова добавлена в цепочку, хвост не отброшен, яблуко мертво
            
            head.setPreviuos(newHead); // старой голове добавляется ссылка на новую
            newHead.setNextFromHead(head); // новой голове добавляется ссылка на старую голову(голова превращается в часть тела)
            head = newHead;            
            
            GOApple.isAlive = false; 
        } 
        else if ((GORiceBall.existRiceBall()) && (GORiceBall.riceBall.checkCollision(newHead))) {
            // поглощение rice ball: голова добавлена в цепочку, хвост не отброшен, rice ball мертв
            
            head.setPreviuos(newHead); // старой голове добавляется ссылка на новую
            newHead.setNextFromHead(head); // новой голове добавляется ссылка на старую голову(голова превращается в часть тела)
            head = newHead;            
            
            GORiceBall.isAlive = false;  
            // createNewRiceBall(); //*****************************************************************************************************
        }
        else { // нет событий: голова добавлена в snakeParts, хвост отброшен
            head.setPreviuos(newHead); // старой голове добавляется ссылка на новую
            newHead.setNextFromHead(head); // новой голове добавляется ссылка на старую голову(голова превращается в часть тела)
            head = newHead;            
            removeTail();
        }        
    }

    public GOSnake createNewHead() {
        int headX = head.getX();
        int headY = head.getY();
        
        GOSnake newHead = new GOSnake(headX, headY);
        
        switch (direction) {
            case LEFT:
                newHead.setX(headX-1);
                //newGameObject.setY(headY);
                break;
            case RIGHT:
                newHead.setX(headX+1);
                //newGameObject.setY(headY);
                break;                
            case UP:
                //newGameObject.setX(headX);
                newHead.setY(headY-1);
                break;                
            case DOWN:
                //newGameObject.setX(headX);
                newHead.setY(headY+1);
                break;
         }        
        return newHead;
     }

    public void removeTail() {
        GOSnake second = head.getNextFromHead();
        GOSnake first = head;                
        while(second.getNextFromHead() != null) {
            first = second;
            second = second.getNextFromHead();
        }        
        second.setPreviuos(null);
        first.setNextFromHead(null);
    }

    /**
     * Перевірка чи не накладується створенний обєкт на змію
     */
    public boolean checkCollision(GameObject gameObject) {        
        GOSnake part = head;//.getNextFromHead();
        do {
            if (part.checkCollision(gameObject)) return true;            
        } while((part = part.getNextFromHead())!=null);
        
        return false;
    }
}
