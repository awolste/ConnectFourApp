//Andrew Wolstenholme 2150 - 001
package cpsc2150.connectX;
public class Gameboard implements IGameboard{
    /**
     * Correspondences:  ROWS = rowSize
     *                   COLUMNS = columnSize
     *                   NEED TO WIN = neededToWin
     *                   gameBoard = board
     *
     * @invariant rowSize > MIN_SIZE && rowSize < MAX_SIZE
     * @invariant columnSize > MIN_SIZE && columnSize < MAX_SIZE
     * @invariant needed > MIN_WIN && needed < MAX_WIN and no empty spaces can be under a token
     *
    */

    private int rowSize;
    private int columnSize;
    private int neededToWin;
    private char[][] board;

    /**
     * @param r int value of user input for row size
     * @param c int value of user input for column size
     * @param needed int value of user input for how many in a row needed to win
     *
     * @pre MIN_SIZE < r < MAX_SIZE and MIN_SIZE < c < MAX_SIZE and MIN_WIN < needed < MAX_WIN
     * @post rowSize = r and columnSize = c and neededToWin = needed and board = char[r][c]
     *
     * */
    Gameboard(int c, int r, int needed) {
        //set member variables to user given input
        rowSize = r;
        columnSize = c;
        neededToWin = needed;
        board = new char[r][c];
    }

    public void placeToken(char p, int c) {
        for (int i = 0; i < rowSize; i++) {
            if (board[i][c] == 0) {
                board[i][c] = p;
                return;
                //return immediately so only 1 token is placed
            }
        }
    }

    public char whatsAtPos(int r, int c) {
        //accesses board member  therefore is primary
            return board[r][c];
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
                if(board[i][j] != 0)
                    str += board[i][j] + " |";
                else
                    str += "  |";
            }
            str += "\n";
        }
        return str;
    }

    public int getNumRows() { return rowSize;}

    public int getNumColumns() { return columnSize;}

    public int getNumToWin() { return neededToWin;}
}
