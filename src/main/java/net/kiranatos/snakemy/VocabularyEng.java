package net.kiranatos.snakemy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class VocabularyEng {
    //List<String> list_2 = new ArrayList<Integer>(Arrays.asList( 1,2,3,4,5,6 ));
    private static Map<String, Signs> enAlphabet = new HashMap<String, Signs>();
    private static Map<String, String> words = new HashMap<String, String>();    
    
    static{
        enAlphabet.put("A", Signs.CIRCLE_A);
        enAlphabet.put("B", Signs.CIRCLE_B);
        enAlphabet.put("C", Signs.CIRCLE_C);
        enAlphabet.put("D", Signs.CIRCLE_D);
        enAlphabet.put("E", Signs.CIRCLE_E);
        enAlphabet.put("F", Signs.CIRCLE_F);
        enAlphabet.put("G", Signs.CIRCLE_G);
        enAlphabet.put("H", Signs.CIRCLE_H);
        enAlphabet.put("I", Signs.CIRCLE_I);
        enAlphabet.put("G", Signs.CIRCLE_J);
        enAlphabet.put("K", Signs.CIRCLE_K);
        enAlphabet.put("L", Signs.CIRCLE_L);
        enAlphabet.put("M", Signs.CIRCLE_M);
        enAlphabet.put("N", Signs.CIRCLE_N);
        enAlphabet.put("O", Signs.CIRCLE_O);
        enAlphabet.put("P", Signs.CIRCLE_P);
        enAlphabet.put("Q", Signs.CIRCLE_Q);
        enAlphabet.put("R", Signs.CIRCLE_R);
        enAlphabet.put("S", Signs.CIRCLE_S);
        enAlphabet.put("T", Signs.CIRCLE_T);
        enAlphabet.put("U", Signs.CIRCLE_U);
        enAlphabet.put("V", Signs.CIRCLE_V);
        enAlphabet.put("W", Signs.CIRCLE_W);
        enAlphabet.put("X", Signs.CIRCLE_X);
        enAlphabet.put("Y", Signs.CIRCLE_Y);
        enAlphabet.put("Z", Signs.CIRCLE_Z);
        
        words.put("expression", "выражение");
        words.put("abdicate", "отказываться");
        words.put("abysm", "бездна");
        words.put("abet", "содействовать");
        words.put("ablepsia", "слепота");
        words.put("abeyant", "бездействующий");
        words.put("abhor", "ненавидеть");        
        words.put("acetum", "уксус");
        words.put("ache", "боль");
        words.put("acknowledge", "подтверждать");
        words.put("acock", "набекрень");
        words.put("acockbill", "на весу");
        words.put("acolyte", "прислужник");
        words.put("acorn", "желудь");
        words.put("acquire", "приобретать");
        words.put("adduce", "приводить");
        words.put("adiposis", "ожирение");
        words.put("adit", "приближение");
        words.put("actuary", "актуарий");
        words.put("adangle", "висящий");
        words.put("adept", "знаток");
        words.put("adieu", "прощание");
        words.put("admirable", "превосходный");
        words.put("adunation", "соединение");
        words.put("adunc", "крючкообразный");
        words.put("adorable", "обожаемый");
        words.put("affect", "влиять");
        words.put("affinity", "свойство");
        words.put("affordable", "доступный");
        words.put("afford", "давать, приносить");
        words.put("agenda", "повестка дня");
        words.put("ahead", "вперед");
        words.put("ahimsa", "принцип ненасилия");
        words.put("ahull", "без парусов");
        words.put("ahum", "бурный");
        words.put("aid", "помогать");
        words.put("embus", "сажать");
        words.put("emend", "изменять(текст)");
        words.put("emendate", "изменять(текст)");
        words.put("ambit", "окружение");
        words.put("amend", "улучшать, исправлять");
        words.put("analepsy", "востановление здоровья");
        words.put("ancestor", "предок");
        words.put("annihilate", "уничтожать");
        words.put("annihilation", "уничтожение");
        words.put("annihilative", "истребительный");
        words.put("annihilator", "уничтожитель");
        words.put("annual", "ежегодный");
        words.put("antediluvial", "старомодный");
        words.put("antediluvian", "допотопный");
        words.put("antehall", "вестибюль перед холлом");
        words.put("antelucan", "предрассветный");
        words.put("antemeridian", "утренний");
        words.put("antenatal", "до рождения");
        words.put("anticipate", "ожидать, предвидеть");
        words.put("argute", "проницательный");
        words.put("arles", "задаток");
        words.put("arid", "сухой");
        words.put("aridity", "засушливость");
        words.put("armament", "вооружение");
        words.put("armarian", "хранитель книг и рукописей");
        words.put("armarium", "хранилище");
        words.put("appal", "пугать");
        words.put("apparel", "одевать");
        words.put("apparency", "очевидность");
        words.put("apparent", "видимый");
        words.put("apparently", "очевидно");
        words.put("apparition", "видение");
        words.put("apparitional", "призрачный");
        words.put("appassionato", "страстно");
        words.put("appreciatory", "благодарный");
        words.put("apprehend", "схватывать, опасаться");
        words.put("apprehensibility", "постижимость");
        words.put("apprehensible", "понятный, постижимый");
        words.put("anoia", "идиотизм");
        words.put("anoint", "намазывать");
        words.put("anoesia", "идиотизм");
        words.put("anoesis", "сумеречное состояние");
        words.put("apprenticeship", "ученичество");
        words.put("appression", "весомость");
        words.put("apprise", "извещать");
        words.put("anxiety", "тревога");
        words.put("anvil", "наковальня");
        
        /*
words.put("anxious", "озабоченный, беспокоящийся о (for, about), тревожный, беспокойный (о деле или времени), сильно желающий (for или to)");
words.put("approach", "приближаться, подходить, делать предложения, начинать переговоры");
words.put("approach", "приближение, подход, подступ");
words.put("approbate", "одобрять, санкционировать");
words.put("approve", "одобрять");
words.put("approval", "одобрение, утверждение, рассмотрение");
words.put("arbitrary", "произвольный");
words.put("arc", "дуга");
words.put("arrant", "настоящий, сущий, отъявленный");
words.put("arise", "возникать, появляться, проистекать, является результатом (from), доноситься (уст. о звуках), восставать (лит.), воскресать (лит.)");
words.put("armipotence", "военная сила");
words.put("armipotent", "мощный");
words.put("armistice", "прекращение военных действий, короткое перемирие");
words.put("arrange", "приводить в порядок, устраиваться, располагать, классифицировать, сговариваться, условливаться, договариваться, улаживать (спор), приходить к соглашению, переделывать (напр., роман для сцены)");
words.put("arrangement", "устройство, привидение в порядок, расположение, классификация, соглашение, договоренность, приготовления (pl), планы (pl)");
words.put("antiviral", "антивирусный");
words.put("antizymic", "препятствующий брожению");
words.put("apeak", "вертикально, вверх, торчком");
words.put("aper", "кабан");
words.put("aperient", "слабительный, послабляющий");
words.put("aperient", "слабительное");
words.put("arbuscle", "древовидный кустарник");
words.put("arbute", "земляничное дерево");
words.put("arbutus", "земляничное дерево");
words.put("arblast", "арбалет");
words.put("arbor", "дерево");
words.put("arbor", "вал, ось, шпиндель");
words.put("arcane", "тайный");
words.put("arenaceous", "песчанистый, песчаный");
words.put("archfiend", "сатана");
words.put("archfoe", "дьявол");
words.put("arbour", "вал, ось, шпиндель");
words.put("arbour", "беседка (из зелени)");
words.put("arboraceous", "древовидный, древесный");
words.put("arboral", "древесный");
words.put("arborary", "древесный");
words.put("arboreous", "лесистый, древовидный");
words.put("arboret", "куст");
words.put("arse", "задница");
words.put("artfulness", "ловкость, хитрость, умение");
words.put("artificial", "искусственный, притворный");
words.put("artifactual", "сделанный человеком");
words.put("artifice", "изобретение, выдумка, хитрость");
words.put("aspire", "стремится, домогаться");
words.put("asperity", "неровность, шероховатость, резкость");
words.put("assail", "нападать на, атаковать");
words.put("assailable", "открытый для нападения, уязвимый");
words.put("assailant", "противник, нападающая сторона");
words.put("assemble", "собирать, созывать, монтировать");*/
    }
    
    
    
    /**
     * Рандом случайной буквы
     * @param exceptThisLetter - кроме этой буквы
     * @return случайную букву Английского алфавита
     */
    public static String randomOneLetter(String exceptThisLetter) {
        int index = 0;
        int stop = new Random().nextInt(enAlphabet.size());        
        Signs s = Signs.DRAGON_FACE;
        for (String str: enAlphabet.keySet()) {
            if (index==stop) {
                s = enAlphabet.get(str);
                break;
            }
            else index++;
        }
        
        //System.out.println(" \uD83C\uDD5E exceptThisLetter = " + exceptThisLetter + " \u306A s.getHexCodeUTF16BE() = " + s.getHexCodeUTF16BE());
        // Если вдруг Рандом выдасть букву, яку выдавать не нужно
        if(exceptThisLetter.equalsIgnoreCase(s.getHexCodeUTF16BE())){            
            if (s.ordinal() == 0) s = Signs.CIRCLE_B; // A
            if (s.ordinal() == 25) s = Signs.CIRCLE_Y; // Z
        }
        
        return s.getHexCodeUTF16BE();
    }
    
    /**
     * Рандом случайной буквы     
     * @return случайную букву Английского алфавита
     */
    public static String randomOneLetter() {
        return randomOneLetter("There are no Easter Eggs up here Go Away!!!");
    }
    
    /**
     * Рандом случайного слова
     * @return случайную пару "слово-перевод"
     */
    public static String[] randomWord() {
        String[] s = new String[2];
        int index = 0;
        int stop = new Random().nextInt(words.size());
        s[0] = "expression";
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
        char[] eng = two[0].toCharArray();
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

        for (int i = 0; i < eng.length; i++) { // заполням матрицу анг.словом
            if (i==word[0].length) break;
            Signs tt = enAlphabet.get(String.valueOf(eng[i]).toUpperCase()); // получаем круг-символ из enum
            word[0][i] = tt.getHexCodeUTF16BE();
        }
        
        for (int i = 0; i < tra.length; i++) { // заполням матрицу переводом
            if (i==word[1].length) break;
            word[1][i] = String.valueOf(tra[i]);            
        }
        
        return word;        
    }
}
