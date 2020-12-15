package net.kiranatos.snake;

import java.util.ArrayList;
import java.util.List;
import net.kiranatos.engine.ColorJavaRush;
import net.kiranatos.engine.Game;

public class Snake {
    public boolean isAlive = true;
    private Direction direction = Direction.LEFT;
    private static final String HEAD_SIGN = "\uD83D\uDC7E";
    private static final String BODY_SIGN = "\u26AB";
    private List<GameObject> snakeParts = new ArrayList<GameObject>();
    
     public Snake (int x, int y) {
        snakeParts.add(new GameObject(x,y));
        snakeParts.add(new GameObject(x+1,y));
        snakeParts.add(new GameObject(x+2,y));
    }
    
    public void draw(Game game) {
         ColorJavaRush c = ColorJavaRush.BLACK;
         if (isAlive == false) c = ColorJavaRush.RED;

        game.setCellValueEx(snakeParts.get(0).x, snakeParts.get(0).y, ColorJavaRush.NONE, HEAD_SIGN, c, 75);
        for (int i = 1; i < snakeParts.size(); i++) {
            game.setCellValueEx(snakeParts.get(i).x, snakeParts.get(i).y, ColorJavaRush.NONE, BODY_SIGN, c, 75);
        }
    }

    public void setDirection(Direction direction) {
        switch (this.direction) {
            case LEFT:
                if (!(direction == Direction.RIGHT || snakeParts.get(0).x == snakeParts.get(1).x)) this.direction = direction;
                break;
            case RIGHT:
                if (!(direction == Direction.LEFT || snakeParts.get(0).x == snakeParts.get(1).x)) this.direction = direction;
                break;
            case UP:
                if (!(direction == Direction.DOWN || snakeParts.get(0).y == snakeParts.get(1).y)) this.direction = direction;
                break;
            case DOWN:
                if (!(direction == Direction.UP || snakeParts.get(0).y == snakeParts.get(1).y)) this.direction = direction;
                break;
        }
    }

    public void move(Apple apple) {
        GameObject gameObject = createNewHead();

        if ( (gameObject.x < 0) ||
                (gameObject.y < 0) ||
                (gameObject.x >= SnakeGame.WIDTH) ||
                (gameObject.y >= SnakeGame.HEIGHT) ||
                (checkCollision(gameObject))
        ) isAlive = false;
        else if ((gameObject.x == apple.x)&&(gameObject.y == apple.y)) {
            snakeParts.add(0, gameObject);
            apple.isAlive = false;
            //removeTail();
        } else {
            snakeParts.add(0, gameObject);
            removeTail();
        }
    }

    public GameObject createNewHead() {
         int headX = snakeParts.get(0).x;
         int headY = snakeParts.get(0).y;

         switch (direction) {
             case LEFT:
                 return new GameObject(headX-1, headY);
                 case RIGHT:
                     return new GameObject(headX+1, headY);
                     case UP:
                         return new GameObject(headX, headY-1);
                         case DOWN:
                             return new GameObject(headX, headY + 1);
                             default:
                                 return new GameObject(headX, headY + 1);
         }
     }

    public void removeTail() {
         snakeParts.remove(snakeParts.size()-1);
    }

    public boolean checkCollision(GameObject gameObject) {
         for (GameObject go: snakeParts) {
             if ((go.x==gameObject.x)&&(go.y==gameObject.y)) return true;
         }
         return false;
    }

    public int getLength() {
         return snakeParts.size();
    }
}
