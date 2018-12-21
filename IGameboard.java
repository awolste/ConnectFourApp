//Andrew Wolstenholme 2150 - 001
package cpsc2150.connectX;

         /**
         * This interface plays games of Connect Four which can be played by only 2 players who can win by
         * creating rows of horizontal, vertical or diagonal tokens.
         *
         * Defines: ROWS: R
         *          COLUMNS: R
         *          NEED TO WIN: R
         *          gameBoard
         *
         * Constraints: ROWS >= 3 and ROWS <= 100 and COLUMNS >= 3 and COLUMNS <= 100
         *              and NEED TO WIN >= 3 and ROWS <= 25
         *
         * Initialization Ensures: gameBoard [ROWS][COLUMNS] and every char index == ' '
         *
         * */
public interface IGameboard {

    /**
     * @param c int location of column to check if full
     * @return boolean value, false if column is full
     *
     * @pre 0 <= c < getNumColumns();
     * @post true iff board [0][c] == 0
     * */
    default boolean checkIfFree(int c) {
        //use primary methods to access ROWS private variable and find if a character at the top of column c
        if(whatsAtPos(getNumRows()-1, c) == 0) {
            return true;
        }
        return false;
    }

    /**
     * @param c int location of last placed token
     * @return boolean value true if last placed token results in getNumToWin() in a row horizontally, diagonally, or vertically
     *
     * @pre 0 <= c < getNumColumns() and all conditions for checkHorizWin(), checkVertWin(), checKDiagWin() are met
     * @post true if checkHorizWin() || checkVertWin() || checKDiagWin()
     * */
    default boolean checkForWin(int c) {
        //check from top down to find last placed token
        for(int i = getNumRows()-1; i >= 0; i--){
            //step down column until a character is found
            if (whatsAtPos(i, c) != 0){
                //remember which type of token to look for
                char tokenType = whatsAtPos(i, c);
                //only one condition needs to be true for a win
                if(checkHorizWin(i, c, tokenType) || checkVertWin(i, c, tokenType) || checkDiagWin(i, c, tokenType)){
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    /**
     * @param p char representing the symbol of the current player
     * @param c int of column for symbol to be placed in lowest null space
     *
     * @pre 0 <= c < getNumColumns() and p != 0 and checkIfFree(c)
     * @post column c has at least one non null space with char p. Char p is placed on the lowest null space
     * */
    void placeToken(char p, int c);

    /**
     * @param r int of the row last token was placed in
     * @param c int of the column last token was placed in
     * @param p char representation of the player. symbol to be checked for getNumToWin() in a row
     * @return true if symbol matching p is found getNumToWin() in a row horizontally
     *
     * @pre r >=0 and r < getNumRows() and c >=0 and c < getNumColumns() and p != 0 and
     *      checkForWin() called first
     * @post true if getNumToWin() horizontal char p in a row, false otherwise
     * */
    default boolean checkHorizWin(int r, int c, char p) {
        int count = 0;
        //step to the farthest left of a possible row of p characters
        //if there are none to the left or already at row 0, stop and count each to right
        while (c > 0 && whatsAtPos(r, c-1) == p){
            c--;
        }
        while (c < getNumColumns() && whatsAtPos(r, c) == p){
            count++;
            c++;
            if (count == getNumToWin())
                return true;
        }
        return false;
    }

    /**
     * @param r int of the row last token was placed in
     * @param c int of the column last token was placed in
     * @param p char representation of the player. symbol to be checked for getNumToWin() in a row
     * @return true if symbol matching p is found getNumToWin() in a row vertically
     *
     * @pre r >=0 and r < getNumRows() and c >= 0 and c < getNumColumns() and p != 0 and
     *      checkForWin() called first
     * @post true if getNumToWin() vertical char p in a row, false otherwise
     * */
    default boolean checkVertWin(int r, int c, char p) {
        int count = 0;
        //since check for win finds the highest token in a column, only step down
        //count down until not char p
        while (r >= 0 && whatsAtPos(r, c) == p){
            count++;
            r--;
            if (count == getNumToWin())
                return true;
        }
        return false;
    }

    /**
     * @param r int of the row last token was placed in
     * @param c int of the column last token was placed in
     * @param p char representation of the player. symbol to be checked for 4 in a row
     * @return true if symbol matching p is found getNumToWin() in a row horizontally
     *
     * @pre r >=0 and r < getNumRows() and c >=0 and c < getNumColumns() and p != 0 and
     *      checkForWin() called first
     * @post true if getNumToWin() right diagonal or left diagonal char p in a row, false otherwise
     * */
    default boolean checkDiagWin(int r, int c, char p) {
       //make deep copy
        Integer c2 = new Integer(c);
        Integer r2 = new Integer(r);
        int count = 0;
        //step up bottom left to top right while the character up and over is also p
        while (c < getNumColumns()-1 && r < getNumRows()-1 && whatsAtPos(r+1, c+1) == p){
            c++;
            r++;
        }
        //step back down and over counting this time until a character other than p is found
        while (c >= 0 && r >= 0 && whatsAtPos(r, c) == p){
            count++;
            c--;
            r--;
            if (count == getNumToWin())
                return true;
        }
        //test 2 for bottom right to top left
        count = 0;
        while (c2 > 0 && r2 < getNumRows()-1 && whatsAtPos(r2+1, c2-1) == p){
            c2--;
            r2++;
        }
        while (c2 < getNumColumns() && r2 >= 0 && whatsAtPos(r2, c2) == p){
            //step down and over counting all instances of p in a row
            count++;
            c2++;
            r2--;
            if (count == getNumToWin())
                return true;
        }
        return false;
    }

    /**
     * @param r int row location to check for current occupant
     * @param c int col location to check for current occupant
     * @return char representation of item the 2D array index (r,c)
     *
     * @pre r >=0 and r < getNumRows() and c >=0 and c < getNumColumns() and
     * @post whatsAtPos(r,c) == 0 || whatsAtPos(r,c) == char
     * */
    char whatsAtPos(int r, int c);

    /**
     * @return boolean if entire board is full
     *
     * @pre !checkForWin
     * @post True if all moves have been made ie no free columns. board[0][0] != ' ' ... board[r][c] != ' '
     * */
    default boolean checkTie() {
        boolean isTie = true;
        //see if any open columns by using another secondary member method
        for(int j = 0; j < getNumColumns(); j++){
            if(checkIfFree(j))
                isTie =  false;
        }
        return isTie;
    }

    /**
     * @return int value of user set row size
     *
     * @pre ROWS > MIN_SIZE && ROWS < MAX_SIZE
     * @post ROWS
     * */
    int getNumRows();

    /**
     * @return int value of user set column size
     *
     * @pre COLUMNS > MIN_SIZE && COLUMNS < MAX_SIZE
     * @post COLUMNS
     * */
    int getNumColumns();

    /**
     * @return int value of user set number to win size
     *
     * @pre NUMBER TO WIN > MIN_WIN && NUMBER TO WIN < MAX_WIN
     * @post NUMBER TO WIN
     * */
    int getNumToWin();
}
