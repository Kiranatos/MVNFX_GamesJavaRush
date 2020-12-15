package net.kiranatos.snakemy;

import net.kiranatos.engine.ColorJavaRush;

public class GameGUIMasterManager {
    
    
    
    /**************************** COLORS *************************************/    
    private static final ColorJavaRush DEFAULT_TEXT = ColorJavaRush.YELLOW;
    private static final ColorJavaRush ERROR = ColorJavaRush.BROWN;
    private static final ColorJavaRush BACKGROUND_OF_FIELD = ColorJavaRush.GREEN;
    private static final ColorJavaRush COLOR_OF_ALIVE_SNAKE = ColorJavaRush.WHITE;
    private static final ColorJavaRush COLOR_OF_DEAD_SNAKE = ColorJavaRush.RED;
    private static final ColorJavaRush COLOR_OF_RED_APPLE = ColorJavaRush.RED;
    private static final ColorJavaRush COLOR_OF_RICE_BALL = ColorJavaRush.BLUEVIOLET;
    private static final ColorJavaRush COLOR_OF_AMANITA = DEFAULT_TEXT;   
    
    private static final ColorJavaRush COLOR_OF_UPPER_BACKGROUND_PANEL = ColorJavaRush.BLUE;
    private static final ColorJavaRush COLOR_OF_UPPER_TEXT = DEFAULT_TEXT;
    private static final ColorJavaRush COLOR_OF_DOWN_BACKGROUND_PANEL = COLOR_OF_UPPER_BACKGROUND_PANEL;
    private static final ColorJavaRush COLOR_OF_DOWN_TEXT = DEFAULT_TEXT;
    
    private static final ColorJavaRush COLOR_OF_LOSER_BACKGROUND = ColorJavaRush.BLACK;
    private static final ColorJavaRush COLOR_OF_LOSER_TEXT = ColorJavaRush.CYAN;
    private static final ColorJavaRush COLOR_OF_WIN_BACKGROUND = ColorJavaRush.BLUE;
    private static final ColorJavaRush COLOR_OF_WIN_TEXT = ColorJavaRush.YELLOW;
    
    public static ColorJavaRush getSnakeColor(ObjectCondition status) {
        switch (status) {
            case ALIVE : return COLOR_OF_ALIVE_SNAKE;            
            case DEAD : return COLOR_OF_DEAD_SNAKE;
            case BACKGROUND : return BACKGROUND_OF_FIELD;
            default:  { 
                System.out.println("Error: No Default color!!!"); 
                return ERROR; 
            }
        }        
    }
    
    public static ColorJavaRush getAppleColor(ObjectCondition status) {
        switch (status) {
            case TEXT : return COLOR_OF_RED_APPLE;
            //case DEAD : return COLOR_OF_ALIVE_SNAKE;
            case BACKGROUND : return BACKGROUND_OF_FIELD;
            default:  { 
                System.out.println("Error: No Default color!!!"); 
                return ERROR; 
            }
        }        
    }
    
    public static ColorJavaRush getRiceBallColor(ObjectCondition status) {
        switch (status) {
            case TEXT : return COLOR_OF_RICE_BALL;
            //case DEAD : return COLOR_OF_ALIVE_SNAKE;
            case BACKGROUND : return BACKGROUND_OF_FIELD;
            default:  { 
                System.out.println("Error: No Default color!!!"); 
                return ERROR; 
            }
        }        
    }
    
    public static ColorJavaRush getAmanitaColor(ObjectCondition status) {
        switch (status) {
            case TEXT : return COLOR_OF_AMANITA;
            //case DEAD : return COLOR_OF_ALIVE_SNAKE;
            case BACKGROUND : return BACKGROUND_OF_FIELD;
            default: { 
                System.out.println("Error: No Default color!!!"); 
                return ERROR; 
            }
        }        
    }
    
    public static ColorJavaRush getFieldColor(ObjectCondition status) {
        switch (status) {
            case TEXT : return DEFAULT_TEXT;
            case TEXT_UP : return COLOR_OF_UPPER_TEXT;
            case BACKGROUND_UP : return COLOR_OF_UPPER_BACKGROUND_PANEL;
            case TEXT_DOWN : return COLOR_OF_DOWN_TEXT;
            case BACKGROUND_DOWN : return COLOR_OF_DOWN_BACKGROUND_PANEL;
            case BACKGROUND : return BACKGROUND_OF_FIELD;
            case TEXT_WIN : return COLOR_OF_WIN_TEXT;
            case BACKGROUND_WIN : return COLOR_OF_WIN_BACKGROUND;
            case TEXT_LOSE : return COLOR_OF_LOSER_TEXT;
            case BACKGROUND_LOSE : return COLOR_OF_LOSER_BACKGROUND;                        
            default: { 
                System.out.println("Error: No Default color!!!"); 
                return ERROR; 
            }
        }        
    }
    
    /**************************** SYMBOLS *************************************/
    private static String DEFAULT_SIGN = Signs.SPACE.getHexCodeUTF16BE();
    private static String DRAGON_SIGN = Signs.DRAGON_FACE.getHexCodeUTF16BE();
    private static String HEAD_SIGN = Signs.ALIEN_MONSTER.getHexCodeUTF16BE();
    private static String BODY_SIGN = Signs.MEDIUM_BLACK_CIRCLE.getHexCodeUTF16BE();
    private static String AMANITA_SIGN = Signs.AMANITA.getHexCodeUTF16BE();
    private static String APPLE_SIGN = Signs.APPLE.getHexCodeUTF16BE();
    private static String RICE_SIGN = Signs.RICE_BALL.getHexCodeUTF16BE();
    //private static String RICE_SIGN = Signs.GRAPES.getHexCodeUTF16BE();
    
    public static String getSnakeSign(ObjectCondition status) {        
        switch (status) {
            case SIGN_HEAD_SNAKE : return HEAD_SIGN;            
            case SIGN_BODY_SNAKE : return BODY_SIGN;
            //case BACKGROUND : return BACKGROUND_OF_FIELD;
            default:  { 
                System.out.println("Error: No Default color!!!"); 
                return DEFAULT_SIGN; 
            }
        }        
    }
    
    public static String getAppleSign(ObjectCondition status) {
        switch (status) {
            case SIGN_APPLE : return APPLE_SIGN;                        
            case SIGN_RICE_BALL : return RICE_SIGN;                        
            default:  { 
                System.out.println("Error: No Default color!!!"); 
                return DEFAULT_SIGN; 
            }
        }
    }
    
    public static String getAmanitaSign(ObjectCondition status) {
        switch (status) {
            case SIGN_AMANITA : return AMANITA_SIGN;                        
            default:  { 
                System.out.println("Error: No Default color!!!"); 
                return DEFAULT_SIGN; 
            }
        }        
    }
    
    public static String getFieldSign(ObjectCondition status) {
        return DEFAULT_SIGN;
    } 
    
    /***********************************************************************/
    public static byte statusOfGame = 0; // 0 - nature, 1 - English, 2 - Japanese
    public static boolean Lang_En = false;
    public static boolean Lang_Jp = false;
    public static boolean Nature = false; 
    private static String[][] word = null;
    private static byte marker = 0; // маркер для прохождения по матрице слова и извлечения букв
    
    /**
     * Что-то было съедено
     * @param appleSign - символ съеденого яблока
     */
    public static void reqestActions(String appleSign) { 
        //String appleSign = GOApple.apple.getSign();
        
        if (appleSign.equals(Signs.APPLE.getHexCodeUTF16BE())) statusOfGame = 1; // Если съедено яблуко - учим Англ
        else if (appleSign.equals(Signs.RICE_BALL.getHexCodeUTF16BE())) statusOfGame = 2; // Если съеден рис - учим Японс
        
        if (statusOfGame == 1) { // Английский                        
            if (word == null){
                GORiceBall.hideRiceBall();
                word = VocabularyEng.randomWordToSquareMatrix(); // получаем новое слово
                GOCellOnPanel.writeWordOnBothPanels(word); // устанавливаем анг.слово на верх.панели и перевод на нижней                
            } else GOCellOnPanel.changeColorOneLetterInQueue(); //
            
            if (!word[0][marker].equals(Signs.SPACE.getHexCodeUTF16BE())) {     // если буква не пробел
                
                for (GOAmanita amanita: GOAmanita.bunchOfAmanitas) { // Все грибы заменены на рандомные буквы
                    amanita.setSign(VocabularyEng.randomOneLetter(word[0][marker]));
                }                        
                
                GOApple.apple.setSign(word[0][marker]);                         // ставим эту букву на яблуко
                GOApple.apple.setColorText(/*COLOR_OF_AMANITA);*/ COLOR_OF_UPPER_BACKGROUND_PANEL);    // set Color on Apple - CHANGE TO YELLOW, WHEN BE READY
                if (++marker >= SnakeGame.WIDTH) marker=0;                     // если маркер матрицы превысил размер панели - обнуляем
            }
            else {                                                              // буква это пробел, слово закончилось - всё обнуляем!
                reset();
                SnakeGame.winFlag = true;
            }
        } else if (statusOfGame == 2) { // Japanese            
            if (word == null){
                GOApple.hideApple();
                word = VocabularyJap.randomWordToSquareMatrix(); // получаем новое слово //...............................................
                GOCellOnPanel.writeWordOnBothPanels(word); // устанавливаем яп.слово на верх.панели и перевод на нижней                
            } else GOCellOnPanel.changeColorOneLetterInQueue(); //
            
            if (!word[0][marker].equals(Signs.SPACE.getHexCodeUTF16BE())) {     // если буква не пробел
                
                for (GOAmanita amanita: GOAmanita.bunchOfAmanitas) { // Все грибы заменены на рандомные буквы
                    amanita.setSign(VocabularyJap.randomOneLetter(word[0][marker])); //*...............................................................
                }                                      
                
                GORiceBall.riceBall.setSign(word[0][marker]);                         // ставим эту букву на рис.шарик
                GORiceBall.riceBall.setColorText( /*COLOR_OF_AMANITA);*/ COLOR_OF_UPPER_BACKGROUND_PANEL);    // set Color on рис.шарик - CHANGE TO YELLOW, WHEN BE READY
                if (++marker >= SnakeGame.WIDTH) marker=0;                     // если маркер матрицы превысил размер панели - обнуляем
            }
            else {                                                              // буква это пробел, слово закончилось - всё обнуляем!
                reset();
                SnakeGame.winFlag = true;
            }
        }
    }
    
    /**
     * Обнуление
     */
    public static void reset() {
        statusOfGame = 0;
        marker=0;
        word = null;
        GOCellOnPanel.generatePanels();                                 // просто создаем новые панели
        if (GOApple.existApple()) GOApple.apple.setSign(Signs.APPLE.getHexCodeUTF16BE());         // возвращаем грибам и яблукам их символы
        if (GORiceBall.existRiceBall()) GORiceBall.riceBall.setSign(Signs.RICE_BALL.getHexCodeUTF16BE());
        GOAmanita.setSignAll(Signs.AMANITA.getHexCodeUTF16BE());
    }
}
