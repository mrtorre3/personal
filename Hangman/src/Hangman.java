import java.util.Random;
import java.util.Scanner;

/**
 * This program imitates the childhood Hangman game. 
 * 
 * @author Madison Torres
 */
public class Hangman {

    /** Guess number the user is on */
    private static int guess = 0;

    /** Number of incorrect guesses */
    private static int wrongGuess = 0;

    /** Word bank for words to guess */
    private static String[] wordBank = {"Shave", "Crush", "Faith", "Ulcer", "Grace", "Adieu"};

    /** Word to guess for user */
    private static String word = ""; 

    /** Array for the current word to print */
    private static String[] currentWord = new String[5];

    public static void main(String[] args) throws Exception {
        System.out.println("Weclome to Hangman! ");
        printView();
        //initialize the current word array to empty
        for ( int i = 0; i < currentWord.length; i++ ){
            currentWord[i] = "_";
            System.out.print(currentWord[i] + " ");
        }
        System.out.println();
        chooseWord();
        //System.out.println(word);
        System.out.println("Enter 'quit' to exit.");
        System.out.println("Please enter a letter guess and press 'Enter': ");
        Scanner in = new Scanner(System.in);
        

        //This continues to take guesses and checks the words until either user has guessed, lost, or quit
        while ( in.hasNext()) {
            String input = in.next();

            //exit case
            if ( input.equals("quit")) {
                break;
            }

            //make sure it is one letter and not longer
            else if ( input.length()!= 1 ){
                System.out.println( "Guess must be 1 letter. Please try again.");
            }

            //guessing case
            else {
                int index = checkWord(input);
                guess++;
                printView();
                printWord(index);
                if (checkWin()) {
                    win();
                }
            }

        }  
        
        reset();
        in.close();
        System.out.println("Thank you for playing!\n");
        System.exit(0);

    }

    /**
     * This method prints out the hangman shown to the user.
     */
    private static void printView() {
        //this prints out the hangman
        System.out.println("---------");
        if ( wrongGuess == 0 ) {
            System.out.println("|      |");
            System.out.println("|      ");
            System.out.println("|      ");
            System.out.println("|      ");
            System.out.println("|      ");
        }

        else if ( wrongGuess == 1 ) {
            System.out.println("|      |");
            System.out.println("|      0");
            System.out.println("|      ");
            System.out.println("|      ");
            System.out.println("|      ");
        }

        else if ( wrongGuess == 2 ) {
            System.out.println("|      |");
            System.out.println("|      0");
            System.out.println("|      |");
            System.out.println("|      ");
            System.out.println("|      ");
        }

        else if ( wrongGuess == 3 ) {
            System.out.println("|      |");
            System.out.println("|      0");
            System.out.println("|     /|");
            System.out.println("|      ");
            System.out.println("|      ");
        }

        else if ( wrongGuess == 4 ) {
            System.out.println("|      |");
            System.out.println("|      0");
            System.out.println("|     /|\\");
            System.out.println("|      ");
            System.out.println("|      ");
        }

        else if ( wrongGuess == 5 ) {
            System.out.println("|      |");
            System.out.println("|      0");
            System.out.println("|     /|\\");
            System.out.println("|      /");
            System.out.println("|      ");
        }

        else if ( wrongGuess == 6 ) {
            System.out.println("|      |");
            System.out.println("|      0");
            System.out.println("|     /|\\");
            System.out.println("|      /\\");
            System.out.println("|      \n");
            System.out.println("You have guessed " + guess + " time(s) and have " 
         + wrongGuess + " incorrect guesses.");
            lost();
        }
        System.out.println("-----");
        System.out.println();
       
    }

    /**
     * This method prints out the word with the guesses to the user.
     * @param index
     */
    private static void printWord (int index) {
        
        for (int i = 0; i < word.length(); i++ ) {
            if ( index == i ) {
                currentWord[i] = word.charAt(index) + "";
            }
            System.out.print(currentWord[i] + " ");
        }
        System.out.println();
        
         //print out guesses 
         System.out.println("You have guessed " + guess + " time(s) and have " 
         + wrongGuess + " incorrect guesses.");

    }

    /**
     * This method checks if the guess is correct or not and returns the index the correct letter is at. 
     * @param guessInput the letter guessed
     * @return index the index of the correct letter in the word
     */
    public static int checkWord( String guessInput ) {
        //TODO: handle multiple letter cases
        int index = -1;
        char letter = guessInput.toLowerCase().charAt(0);
        for ( int i = 0; i < word.length(); i++ ) {
            if ( word.toLowerCase().charAt(i) == letter ) {
                index = i;
            }
        }

        //check the index
        if ( index == -1 ){
            wrongGuess++;
        }

        return index;
    }

    /**
     * This method chooses a random word from the given word bank for the user to guess
     * @return word the randomly chosen word
     */
    public static String chooseWord() {
        Random rand = new Random();
        int index = rand.nextInt(wordBank.length);
        word = wordBank[index];
        return word;
    } 

    /**
     * Resets the variables and map
     */
    public static void reset() {
        guess = 0; 
        wrongGuess = 0;
        word = "";
    }

    /**
     * Checks if the user has figured out the word completely.
     * @return
     */
    public static boolean checkWin () {
        boolean win = true; 
        for ( int i = 0; i < currentWord.length; i++ ) {
            if ( !currentWord[i].equals( word.charAt(i) + "")){
                win = false;
            }
        }
        return win;
    }

    /**
     * If the user has guessed wrong six times, they will be shown below as they have lost the game. 
     */
    public static void lost() {
        System.out.println("You have guessed incorrectly too many times and lost the game.");
        System.out.println("The word was " + word + ".");
        System.out.println("Thanks for playing!\n");
        System.exit(0);
    }

    /**
     * If the user has guessed correctly before 6 times, they will be shown below as they have won the game.  
     */
    public static void win() {
        System.out.println("You have guessed all letters correctly and won the game!");
        System.out.println("The word was " + word + ".");
        System.out.println("Thanks for playing!\n");
        System.exit(0);
    }


}
