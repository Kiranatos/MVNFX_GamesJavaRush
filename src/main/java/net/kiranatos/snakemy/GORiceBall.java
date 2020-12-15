package net.kiranatos.snakemy;

import net.kiranatos.engine.Game;

public class GORiceBall extends GameObject {
    public static boolean isAlive = true;
    public static GORiceBall riceBall = null;

    public GORiceBall (int x, int y) {
        super(x, y,
                GameGUIMasterManager.getRiceBallColor(ObjectCondition.TEXT), 
                GameGUIMasterManager.getRiceBallColor(ObjectCondition.BACKGROUND),
                GameGUIMasterManager.getAppleSign(ObjectCondition.SIGN_RICE_BALL));
    }

    public void draw(Game game) {
        DrawAdapter.drawCell(game, this);
    }
    
    /**
     * Перевірка чи не накладується створенний обєкт на rice ball
     */
    public boolean checkCollision(GameObject gameObject) {
        if ((this.getX()==gameObject.getX())&&(this.getY()==gameObject.getY())) return true;
        return false;
    }
    
    public static void hideRiceBall() {
        riceBall = null;
    }   
    
    public static boolean existRiceBall() {
        return !(riceBall == null);
    }   
   
}