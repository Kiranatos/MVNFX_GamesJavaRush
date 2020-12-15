package net.kiranatos.snakemy;

import net.kiranatos.engine.Game;

public final class DrawAdapter {
    public static void drawCell(Game game, GameObject gameObject) {
        game.setCellValueEx( 
                gameObject.getX(), 
                gameObject.getY(),
                gameObject.getColorBackGround(),
                gameObject.getSign(),
                gameObject.getColorText(),
                75                
        );
    }    
}


