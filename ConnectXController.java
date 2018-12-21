package cpsc2150.connectX;

/**
 * The controller class will handle communication between our View and our Model (IGameBoard)
 *
 * This is where you will write code
 *
 * You will need to include your IGameBoard interface
 * and both of the IGameBoard implementations from Homework 3
 * If your code was correct you will not need to make any changes to your IGameBoard implementation class
 */

public class ConnectXController {
    //our current game that is being played
    private IGameboard curGame;


    //The screen that provides our view
    private ConnectXView screen;

    public static final int MAX_PLAYERS = 10;
    //our play tokens are hard coded. We could make a screen to get those from the user, but
    //I want to keep this example simple
    private char[] players = {'X', 'O', 'Y', 'Z', 'A', 'K', 'E', 'J', 'N', 'H'};

   private int numPlayers;
   private int currentPlayer = 0;
   private boolean gameOver = false;


    /**
     *
     * @param model the board implementation
     * @param view the screen that is shown
     * @post the controller will respond to actions on the view using the model.
     */
    ConnectXController(IGameboard model, ConnectXView view, int np){
        this.curGame = model;
        this.screen = view;
        numPlayers = np;

    }

    /**
     *
     *
     * @param col the column of the activated button
     * @post will allow the player to place a token in the column if it is not full, otherwise it will display an error
     * and allow them to pick again. Will check for a win as well. If a player wins it will allow for them to play another
     * game hitting any button
     */
    public void processButtonClick(int col) {
        //first check if global boolean has been made true to restart game
        if (gameOver)
            newGame();
        //check free column, wont have to worry about this when placing token in free
        if (!curGame.checkIfFree(col))
             screen.setMessage("Column is full");
        else{
            //loop to find the first empty row in the column. One is guaranteed to be free
            for(int i = 0; i < curGame.getNumRows(); i++){
                if(curGame.whatsAtPos(i, col) == 0){
                    //place in both gameBoard and screen
                    screen.setMarker(i, col, players[currentPlayer]);
                    curGame.placeToken(players[currentPlayer], col);
                    break; //break so as to only place once
                }
            }
            //check for win and tie, set global boolean accordingly
            if (curGame.checkForWin(col)){
                gameOver = true;
                screen.setMessage("Player " + players[currentPlayer] + " Won! Click to start a new game.");
            }
            else if (curGame.checkTie()){
                gameOver = true;
                screen.setMessage("It was a tie! Click to start a new game.");
            }
            //change player if no win or tie, if at last player, go to player 0
            else{
                if (numPlayers == currentPlayer+1)
                    currentPlayer = 0;
                else
                    currentPlayer++;
                screen.setMessage("It is " + players[currentPlayer] + "'s turn.");
            }
        }
    }

    /**
     * This method will start a new game by returning to the setup screen and controller
     */
    private void newGame()
    {
        //close the current screen
        screen.dispose();
        //start back at the set up menu
        SetupView screen = new SetupView();
        SetupController controller = new SetupController(screen);
        screen.registerObserver(controller);
    }
}
