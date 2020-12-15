package net.kiranatos.snakemy;

import net.kiranatos.engine.Game;

public class GOCellOnPanel extends GameObject {
    
    public static GOCellOnPanel[] upperPanel = new GOCellOnPanel[SnakeGame.WIDTH];
    public static GOCellOnPanel[] downPanel = new GOCellOnPanel[SnakeGame.WIDTH];
    private static byte index = 0;
    
    public GOCellOnPanel (int x, int y) {
        super(x, y, 
                GameGUIMasterManager.getFieldColor(ObjectCondition.TEXT_UP), 
                GameGUIMasterManager.getFieldColor(ObjectCondition.BACKGROUND_UP),
                GameGUIMasterManager.getFieldSign(ObjectCondition.SIGN_EMPTY));
    }
    
    /**
     * Прорисовка ячейки
     */
    public void draw(Game game) {
        DrawAdapter.drawCell(game, this);
    }
    
    public static void generatePanels() {
        index = 0;
        for (int i = 0; i < upperPanel.length; i++) {
            upperPanel[i] = new GOCellOnPanel(i, 0);
            downPanel[i] = new GOCellOnPanel(i, SnakeGame.HEIGHT-1);
        }        
    }
    
    /**
     * 
     * @param word - size of matrix[2][n<17] !!!
     */
    public static void writeWordOnBothPanels(String[][] word) {
        for (int i = 0; i < upperPanel.length; i++) {
            upperPanel[i].setSign(word[0][i]);
            downPanel[i].setSign(word[1][i]);        
        }                
    }
    
    public static void changeColorOneLetterInQueue() {
        upperPanel[index].setColorBackGround(GameGUIMasterManager.getFieldColor(ObjectCondition.BACKGROUND_LOSE));
        upperPanel[index].setColorText(GameGUIMasterManager.getSnakeColor(ObjectCondition.DEAD));
        if (index < upperPanel.length) index++;
        else index = 0;
    }
}
