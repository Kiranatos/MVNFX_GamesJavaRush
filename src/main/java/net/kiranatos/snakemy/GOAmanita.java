package net.kiranatos.snakemy;

import java.util.ArrayList;
import java.util.List;
//import net.kiranatos.engine.ColorJavaRush;
import net.kiranatos.engine.Game;


public class GOAmanita extends GameObject {        
    //public boolean isAlive = true;
    public static final int VALUE_OF_AMANITAS = 5;
    public static List<GOAmanita> bunchOfAmanitas = new ArrayList<GOAmanita>();

    public GOAmanita (int x, int y) {
        super(x, y, 
                GameGUIMasterManager.getAmanitaColor(ObjectCondition.TEXT), 
                GameGUIMasterManager.getAmanitaColor(ObjectCondition.BACKGROUND),
                GameGUIMasterManager.getAmanitaSign(ObjectCondition.SIGN_AMANITA));
    }

    /**
     * Прорисовка мухомора
     */
    public void draw(Game game) {
        DrawAdapter.drawCell(game, this);
    }
    
    /**
     * Перевірка чи не накладується створенний обєкт на всі мухомори
     */
    public static boolean checkCollision(GameObject gameObject) {
        for (GOAmanita go: bunchOfAmanitas) {
            if ((go.getX()== gameObject.getX())&&(go.getY()==gameObject.getY())) return true;
        }         
        return false;
    }    
    
    public static void setSignAll(String sign) {
        for (GOAmanita amanita: bunchOfAmanitas) { // Все грибы заменены на symbol
                amanita.setSign(sign);
            }
    }
}