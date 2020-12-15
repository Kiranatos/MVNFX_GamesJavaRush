package net.kiranatos.snakemy;

import net.kiranatos.engine.Game;

public class GOSnake extends GameObject {
    public static boolean isAlive = true;
    
    private GOSnake previuos = null;
    private GOSnake next = null;

    public GOSnake (int x, int y) {
        super(x, y,
                GameGUIMasterManager.getSnakeColor(ObjectCondition.ALIVE),
                GameGUIMasterManager.getSnakeColor(ObjectCondition.BACKGROUND),
                GameGUIMasterManager.getSnakeSign(ObjectCondition.SIGN_HEAD_SNAKE));
    }

    public void draw(Game game) {
        DrawAdapter.drawCell(game, this);
    }
    
    /**
     * Перевірка чи не накладується створенний обєкт на яблуко
     */
    public boolean checkCollision(GameObject gameObject) {
        if ((this.getX()==gameObject.getX())&&(this.getY()==gameObject.getY())) return true;
        return false;
    }
    
    public void setPreviuos(GOSnake previuos) { // this перестает быть головой, голова теперь previuos
        this.previuos = previuos;
        
        if (next==null) this.setSign(GameGUIMasterManager.getSnakeSign(ObjectCondition.SIGN_BODY_SNAKE));
        else this.setSign(next.getSign());
    }    
    
    public void setNextFromHead(GOSnake next) {
        this.next = next;
    }    
    
    public GOSnake getNextFromHead() {
        return this.next;        
    }    
    
    public GOSnake getPrevious() {
        return this.previuos;        
    }    
}