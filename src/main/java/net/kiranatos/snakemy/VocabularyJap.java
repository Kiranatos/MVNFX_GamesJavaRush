package net.kiranatos.snakemy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class VocabularyJap {
    //List<String> list_2 = new ArrayList<Integer>(Arrays.asList( 1,2,3,4,5,6 ));
    private static Map<Integer, Signs> japAlphabet = new HashMap<Integer, Signs>();
    private static Map<String, String> words = new HashMap<String, String>();    
    private static int i_1 = Signs.Hiragana_Letter_Small_A.ordinal();
    private static int i_2 = Signs.Hiragana_Letter_Small_Ke.ordinal();
    
    static{
//        enAlphabet.put("A", Signs.CIRCLE_A);
//        enAlphabet.put("B", Signs.CIRCLE_B);
//        enAlphabet.put("C", Signs.CIRCLE_C);        
        
        Signs[] s_matrix = Signs.values();
        
        for (int i = i_1; i <= i_2; i++) {
            japAlphabet.put(i, s_matrix[i]);            
        }
                
        words.put("\u308F\u305F\u3057", "Я");
        words.put("\u308F\u305F\u3057\u305F\u3061", "мы");
        words.put("\u3042\u306A\u305F", "вы");
        words.put("\u3042\u306E\u3072\u3068", "Он, она");
        words.put("\u304C\u304F\u305B\u3044", "студент");
        words.put("\u304B\u3044\u3057\u3084\u3044\u3093", "сотрудник компании");
        words.put("\u304E\u3093\u3053\u3046\u3044\u3093", "Работник банка");
        words.put("\u3044\u3057\u3084", "врач");
        words.put("\u3051\u3093\u304D\u3085\u3046\u3057\u3084", "ученый");
    }
    
    /**
     * Рандом случайной буквы     
     * @return случайную букву Английского алфавита
     */
    public static String randomOneLetter() {
        return randomOneLetter("There are no Easter Eggs up here Go Away!!!");
    }
        
    /**
     * Рандом случайной буквы
     * @param exceptThisLetter - кроме этой буквы
     * @return случайную букву Английского алфавита
     */
    public static String randomOneLetter(String exceptThisLetter) {
        
        //random.nextInt(max - min) + min;
        
        int index = 0;
        int randomNumber = i_1 + new Random().nextInt(i_2 - i_1);
        Signs s = japAlphabet.get(randomNumber);
       
        // Если вдруг Рандом выдасть букву, яку выдавать не нужно
        if(exceptThisLetter.equalsIgnoreCase(s.getHexCodeUTF16BE()))
            if (s.ordinal() == 0) s = Signs.Hiragana_Letter_Vu; // Vu - never saw word with this letter
                    
        return s.getHexCodeUTF16BE();
    }
    
    
    /**
     * Рандом случайного слова
     * @return случайную пару "слово-перевод"
     */
    private static String[] randomWord() {
        String[] s = new String[2];
        int index = 0;
        int stop = new Random().nextInt(words.size());
        s[0] = "\u308F\u305F\u3057";
        s[1] = words.get(s[0]);
        for (String str: words.keySet()) {
            if (index==stop) {
                s[0] = str;
                s[1] = words.get(s[0]);        
                break;
            }
            else index++;
        }
        return s;
    }
    
    public static String[][] randomWordToSquareMatrix() {
        int min;
        String[] two = randomWord();
        char[] jap = two[0].toCharArray();
        char[] tra = two[1].toCharArray();
        
        String[][] word = new String[2][SnakeGame.WIDTH]; // создаем масив 2x17 и обнуляем пробелами
        for (int x = 0; x < word[0].length; x++) { //word[0].length = 17
            for (int y = 0; y < word.length; y++) { //word.length = 2
                word[y][x] = Signs.SPACE.getHexCodeUTF16BE();
            }            
        }
        
//        System.out.println("word[0].length = " + word[0].length);
//        System.out.println("word.length = " + word.length);
//        System.out.println("end = " + eng.length);
//        System.out.println("tra = " + tra.length);

        for (int i = 0; i < jap.length; i++) { // заполням матрицу jap.словом
            if (i==word[0].length) break;
            //Signs tt = enAlphabet.get(String.valueOf(eng[i]).toUpperCase()); // получаем круг-символ из enum
            word[0][i] = String.valueOf(jap[i]);//tt.getHexCodeUTF16BE();
        }
        
        for (int i = 0; i < tra.length; i++) { // заполням матрицу переводом
            if (i==word[1].length) break;
            word[1][i] = String.valueOf(tra[i]);            
        }
        
        return word;        
    }
}
