package net.kiranatos.snakemy;

import net.kiranatos.engine.Game;

public class GOApple extends GameObject {
    public static boolean isAlive = true;
    public static GOApple apple = null;    

    public GOApple (int x, int y) {
        super(x, y,
                GameGUIMasterManager.getAppleColor(ObjectCondition.TEXT), 
                GameGUIMasterManager.getAppleColor(ObjectCondition.BACKGROUND),
                GameGUIMasterManager.getAppleSign(ObjectCondition.SIGN_APPLE));
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
    
    public static void hideApple() {
        apple = null;
    }   
    
    public static boolean existApple() {
        return !(apple == null);
    }   
}