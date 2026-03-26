package com.codedotorg;

import java.util.Random;

public class GameLogic {

    /** Whether or not the game is over */
    private boolean gameOver;

    /** Random object for generating the computer's choice */
    private Random random;

    /**
     * Constructor for the GameLogic class.
     * Initializes the gameOver variable to false.
     */
    public GameLogic() {
        gameOver = false;
        random = new Random();
    }

    /**
     * Returns a random choice of "rock", "paper", or
     * "scissors" for the computer player.
     * 
     * @return a String representing the computer's choice
     */
    public String getComputerChoice() {
        String[] choices = {"rock", "paper", "scissors"};
        return choices[random.nextInt(choices.length)];
    }

    /**
     * Determines the winner of a rock-paper-scissors game based on the
     * user's predicted class and the computer's choice.
     * 
     * Model labels yang dikenali: "rock", "paper", "scissors"
     * Label bisa berformat "0 rock", "1 paper", "2 scissors" —
     * sehingga kita ambil kata terakhir saja.
     * 
     * @param predictedClass The user's predicted class from the model.
     * @param computerChoice The computer's choice.
     * @return A string containing the result of the game.
     */
    public String determineWinner(String predictedClass, String computerChoice) {
        if (predictedClass == null || computerChoice == null) {
            return "";
        }

        // Ambil kata terakhir dari predictedClass
        // (model bisa mengembalikan "0 rock", "1 paper", dll.)
        String userChoice = predictedClass.trim().toLowerCase();
        if (userChoice.contains(" ")) {
            userChoice = userChoice.substring(userChoice.lastIndexOf(" ") + 1);
        }

        // Abaikan prediksi "neutral" atau tidak dikenali
        if (!userChoice.equals("rock") && !userChoice.equals("paper") && !userChoice.equals("scissors")) {
            return "";
        }

        // Tentukan pemenang
        if (userChoice.equals(computerChoice)) {
            return getTieResult();
        } else if (
            (userChoice.equals("rock")     && computerChoice.equals("scissors")) ||
            (userChoice.equals("paper")    && computerChoice.equals("rock"))     ||
            (userChoice.equals("scissors") && computerChoice.equals("paper"))
        ) {
            return getUserWinnerResult();
        } else {
            return getComputerWinnerResult();
        }
    }

    /**
     * Sets the game over flag to true and returns a
     * string indicating a tie result.
     * 
     * @return A string indicating a tie result.
     */
    public String getTieResult() {
        gameOver = true;
        return "It's a tie!";
    }

    /**
     * Sets the game over flag to true and returns a string
     * indicating that the user has won.
     * 
     * @return a string indicating that the user has won
     */
    public String getUserWinnerResult() {
        gameOver = true;
        return "You win!";
    }

    /**
     * Sets the game over flag to true and returns a string
     * indicating that the computer has won.
     * 
     * @return A string indicating that the player has lost.
     */
    public String getComputerWinnerResult() {
        gameOver = true;
        return "Computer wins!";
    }

    /**
     * Returns whether the game is over or not.
     * 
     * @return true if the game is over, false otherwise.
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Resets the game logic by setting the gameOver flag to false.
     */
    public void resetLogic() {
        gameOver = false;
    }

}
