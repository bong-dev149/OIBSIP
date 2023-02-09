import java.util.Scanner;

public class Main {
//  member variables
    private static int score;
    private static int attempts;
    private static boolean gameOver,winStat;

//    method for initializing the  game

    private static  void init() {
        System.out.println(" ____   __ __  ___ ___  ____     ___  ____        ____  __ __    ___  _____ _____ ____  ____    ____       ____   ____  ___ ___    ___ \n" +
                "|    \\ |  |  ||   |   ||    \\   /  _]|    \\      /    ||  |  |  /  _]/ ___// ___/|    ||    \\  /    |     /    | /    ||   |   |  /  _]\n" +
                "|  _  ||  |  || _   _ ||  o  ) /  [_ |  D  )    |   __||  |  | /  [_(   \\_(   \\_  |  | |  _  ||   __|    |   __||  o  || _   _ | /  [_ \n" +
                "|  |  ||  |  ||  \\_/  ||     ||    _]|    /     |  |  ||  |  ||    _]\\__  |\\__  | |  | |  |  ||  |  |    |  |  ||     ||  \\_/  ||    _]\n" +
                "|  |  ||  :  ||   |   ||  O  ||   [_ |    \\     |  |_ ||  :  ||   [_ /  \\ |/  \\ | |  | |  |  ||  |_ |    |  |_ ||  _  ||   |   ||   [_ \n" +
                "|  |  ||     ||   |   ||     ||     ||  .  \\    |     ||     ||     |\\    |\\    | |  | |  |  ||     |    |     ||  |  ||   |   ||     |\n" +
                "|__|__| \\__,_||___|___||_____||_____||__|\\_|    |___,_| \\__,_||_____| \\___| \\___||____||__|__||___,_|    |___,_||__|__||___|___||_____|");
        score = 1000;
        gameOver = false;
        setLevel(1);
    }

//    method to generate a random number from 1 to 100
    private static int genRand(int range) {
        return (int)(Math.random()*1000)%range + 1;
    }

//    method to set level
    private static void setLevel(int lev) {
        switch (lev) {
            case 1: attempts = 10; break;
            case 2: attempts = 5; break;
            case 3: default: attempts = 3;
        }
    }

//    method for calculating the score
    private static void calScore() {
        score -= attempts*10;
    }

//    method for displaying the result
    private static void displayResult() {
        if(winStat) {
            System.out.println("Yayyy!! you've won!!\n Score : " + score);
        } else {
            System.out.println("Better luck next time :(");
        }
    }


//    method for playing the game
    private static void play(int lev,int range,Scanner sc) {
        System.out.println("Guess a number between 1 and " + range);
//        setting the level
        setLevel(lev);
//        generating the random number
        int sysNum = genRand(range);
        while (!gameOver) {
            if(attempts == 0 ) {
                winStat = false;
                gameOver = true;
                continue;
            }
            System.out.println("Enter your guess : ");
            int uNum = sc.nextInt();
            attempts--;
            if(uNum == sysNum) {
                winStat = true;
                gameOver = true;
                continue;
            } else if(uNum > sysNum) {
                System.out.println("The number is too large!!");
            } else {
                System.out.println("The number is too small!!");
            }
            calScore();
            System.out.println(attempts + " attempts left!");
        }
        displayResult();
        sc.close();
    }

    public static void main(String[] args) {
//        initializing the game
        init();
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\nLevel 1 - EASY (10 attempts)\nLevel 2 - MEDIUM (5 attempts)\nLevel 3 - HARD (3 attempts)");
        System.out.println("Enter your choice : ");
        int lev = sc.nextInt();
        System.out.println("Enter a upper limit : ");
        int range = sc.nextInt();
        if(range <= 0) {System.out.println("Range must be greater than zero"); System.exit(0);}
        play(lev,range,sc);
        sc.close();
    }
}