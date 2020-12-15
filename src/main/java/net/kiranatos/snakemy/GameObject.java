package net.kiranatos.snakemy;

import net.kiranatos.engine.ColorJavaRush;

public class GameObject {
    
    private int x, y;
    private ColorJavaRush colorText, colorBackGround;
    private String sign;    
    
    public GameObject (int x, int y, ColorJavaRush colorText, ColorJavaRush colorBackGround, String sign) {
        this.x = x;
        this.y = y;
        this.sign = sign;
        this.colorText = colorText;
        this.colorBackGround = colorBackGround;
    }
    
    
    /************************* SETTERS and GETTERS ******************************/
    
    public ColorJavaRush getColorText() {
        return colorText;
    }

    public ColorJavaRush getColorBackGround() {
        return colorBackGround;
    }
    
    public String getSign() {
        return sign;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }

    public void setColorText(ColorJavaRush colorText) {
        this.colorText = colorText;
    }

    public void setColorBackGround(ColorJavaRush colorBackGround) {
        this.colorBackGround = colorBackGround;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }    

}