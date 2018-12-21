//Andrew Wolstenholme 2150 - 001
package cpsc2150.connectX;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameBoardMem implements IGameboard {
    /**
     * Correspondences:  ROWS = rowSize
     *                   COLUMNS = columnSize
     *                   NEED TO WIN = neededToWin
     *                   gameBoard = board
     *
     * @invariant rowSize > MIN_SIZE && rowSize < MAX_SIZE
     * @invariant columnSize > MIN_SIZE && columnSize < MAX_SIZE
     * @invariant neededToWin > MIN_WIN && neededToWin < MAX_WIN and no empty spaces can be under a token
     *
     */

    private int rowSize;
    private int columnSize;
    private int neededToWin;
    private Map<Integer, List<Character>> board;

    /**
     * @param r int value of user input for row size
     * @param c int value of user input for column size
     * @param needed int value of user input for how many in a row needed to win
     *
     * @pre MIN_SIZE < r < MAX_SIZE and MIN_SIZE < c < MAX_SIZE and MIN_WIN < needed < MAX_WIN
     * @post rowSize = r and columnSize = c and neededToWin = needed and board = char[r][c]
     *
     * */
    GameBoardMem(int c, int r, int needed) {
        //set member variables to user given input
        rowSize = r;
        columnSize = c;
        neededToWin = needed;
        board = new HashMap<>();
    }

    public void placeToken(char p, int c) {
        board.putIfAbsent(c, new ArrayList<>());
        //to avoid null ptr error see if key c exists, if not add a new list, then add token to the list
        board.get(c).add(p);
    }

    public char whatsAtPos(int r, int c) {
        if(board.get(c) != null && board.get(c).size() > r)
            return board.get(c).get(r);
        else
            return 0;
    }

    public int getNumRows(){
        return rowSize;
    }


    public int getNumColumns(){
        return columnSize;
    }


    public int getNumToWin(){
        return neededToWin;
    }

    /**
     * @return string representation of Gameboard objects by overriding inherited Object method
     *
     * @post printing a Gameboard object will override the default toString()
     * */
    @Override
    public String toString() {
        //Note: this function should be overriding the method inherited from the Object class.
        //Returns a fully formatted string that displays the current game board (flipped upside down from computer array view).
        String str = "|";
        for(int x = 0; x < columnSize; x++){
            if (x < 10)
                str += " " + x + "|";
            else
                str += x + "|";
        }
        //after first row of labels has been written, account for 2 space with double digits
        str += "\n";
        for(int i = rowSize - 1; i >= 0; i--){
            str += "|";
            for(int j = 0; j < columnSize; j++) {
                if(board.get(j) != null && board.get(j).size() > i)
                    str += board.get(j).get(i) + " |";
                else
                    str += "  |";
            }
            str += "\n";
        }
        return str;
    }
}
