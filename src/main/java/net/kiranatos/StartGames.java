package net.kiranatos;

import java.io.IOException;

public class StartGames {
    private static String theChoice;
    
    public static void main(String[] args) throws IOException, Exception {
        OzoHelper.printMe(null, 
                "#######################################################################################",
                "#######################################################################################",
                "##### JavaRush Games ####",
                "#######################################################################################",
                "\n Choose games:",
                "1. JavaRush 2020 : Snake (Original)",
                "2. JavaRush 2020 : Galaxian",
                "3. JavaRush 2020 : Tetris",
                "4. JavaRush 2020 : Arcanoid",
                "5. ",
                "6. ",
                "7. JavaRush 2020 : Snake (My variant)",                
                "8. ",
                "9. ",
                "10. ",
                "11. ",
                "12. ",
                "13. Puzzle \t\t|| Youtube channel <<Ирина Галкина: Игра Puzzle. Часть 1-4>>",
                "14. Snake \t\t|| Youtube channel <<Ирина Галкина: создание игры Змейка. Часть 1-3>>",
                "15. Snake \t\t|| Youtube channel <<NomadRussian: Пишем змейку на Java>>",
                "16. Xonix \t\t|| Youtube channel <<Сергей Ирюпин: Пишем игру Xonix>>",
                "17. Tetris \t\t|| Youtube channel <<Сергей Ирюпин: Пишем классический Тетрис>>",
                "18. ",
                "19. ",
                "20. ",
                "21. ",
                "22. Tic Tac Toe 3D \t|| GitHub Repository <<Сергей Ирюпин: github.com/biblelamp>>",
                "23. ",
                "exit",
                "______________________________________________________________");
        
        theChoice = OzoHelper.getRead();
        System.out.println("Your choice: " + theChoice);
        
        switch (theChoice) {
            case "1":
                net.kiranatos.snake.SnakeGame.main(null);
                break;      
            case "2":          
                //Space.main(null);
                break;
            case "3":
                //Tetris.main(null);
                break;
            case "4":
                //Room.main(null);
                break;
            case "7":
                net.kiranatos.snakemy.SnakeGame.main(null);
                break;      
                
                
                
            case "8":             
                System.out.println("Error");
                break;
            case "9": 
                System.out.println("Error");
                break;
                
                
                
            case "10": 
                System.out.println("\nБЛОГГЕР БРОСИЛ КАНАЛ. ИГРА НЕ ЗАКОНЧЕНА\n");
                //StartGameTank.main(null);
                break;                
            case "11": 
                //StartGalaxian.main(null);
                break;
            case "12": 
                //Start2DPlatformer.main(null);
                break;
            case "13": 
                System.out.println("\nБЛОГГЕР БРОСИЛ КАНАЛ. ИГРА НЕ ЗАКОНЧЕНА\n");
                break;
            case "14": 
                //SnakeMainWindow.main(null);
                break;
            case "15": 
                //SnakeGame.main(null);
                break;
            case "16": 
                //GameXonix.main(null);
                break;
            case "17": 
                //GameTetris.main(null);
                break;
            case "18": 
                //GameMines.main(null);
                break;
            case "19": 
                //GameSnake.main(null);
                break;
            case "20": 
                //GameSpaceInvaders.main(null);
                break;
            case "21": 
                //net.kiranatos.youtube.g21battleship.GameBattleShip.main(null);
                break;
            case "22": 
                //StartGameTicTacToe.main(null);
                break;
            case "23": 
                System.out.println("");
                break;
            case "exit": 
                System.exit(0);
                break;
            default: 
                System.out.println("Incorrect choice !!!");
                StartGames.main(null);
                break;
        } // end of switch
    } // end of main method    
}
