package net.kiranatos.snake;

import net.kiranatos.engine.ColorJavaRush;
import net.kiranatos.engine.Game;

public class Apple extends GameObject {
    private static final String APPLE_SIGN = "\uD83C\uDF4E";
    public boolean isAlive = true;

    public Apple (int x, int y) {
        super(x,y);
    }

    public void draw(Game game) {
        game.setCellValueEx(x, y, ColorJavaRush.NONE, APPLE_SIGN, ColorJavaRush.GREEN, 75);
    }
}