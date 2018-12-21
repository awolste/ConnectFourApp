//Andrew Wolstenholme 2150 - 001
package cpsc2150.connectX;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestIGameBoard {

    private IGameboard factory(int r, int c, int toWin)
    {
        return new GameBoardMem(r, c, toWin);
        //return new Gameboard(r, c, toWin);
    }

    private String arrayString(char arr [][])
    {
        String str = "|";
        for(int x = 0; x < arr[0].length; x++){
            if (x < 10)
                str += " " + x + "|";
            else
                str += x + "|";
        }
        //after first row of labels has been written, account for 2 space with double digits
        str += "\n";
        for(int i = arr.length - 1; i >= 0; i--){
            str += "|";
            for(int j = 0; j < arr[0].length; j++) {
                if(arr[i][j] != 0)
                    str += arr[i][j] + " |";
                else
                    str += "  |";
            }
            str += "\n";
        }
        return str;
    }

    @Test
    public void testConst_small_3_3()
    {
        char expected[][] = new char [3][3];
        IGameboard gb = factory(3, 3,3);
        String expStr = arrayString(expected);
        assertEquals(expStr, gb.toString());
    }

    @Test
    public void testConst_large_100_100()
    {
        char expected[][] = new char [100][100];
        IGameboard gb = factory(100, 100,25);
        String expStr = arrayString(expected);
        assertEquals(expStr, gb.toString());
    }

    @Test
    public void testCheckIfFree_1_col_full() {
        char expected[][] = new char[3][3];
        IGameboard gb = factory(3, 3, 3);

        expected[0][0] = 'X';
        gb.placeToken('X', 0);
        expected[1][0] = 'X';
        gb.placeToken('X', 0);
        expected[2][0] = 'X';
        gb.placeToken('X', 0);

        assertFalse(gb.checkIfFree(0));
        assertTrue(gb.checkIfFree(1));
        assertTrue(gb.checkIfFree(2));

        String expStr = arrayString(expected);
        assertEquals(expStr, gb.toString());
    }

    @Test
    public void testCheckIfFree_all_free() {
        char expected[][] = new char[3][3];
        IGameboard gb = factory(3, 3, 3);

        expected[0][0] = 'X';
        gb.placeToken('X', 0);
        expected[0][1] = 'X';
        gb.placeToken('X', 1);
        expected[0][2] = 'X';
        gb.placeToken('X', 2);

        assertTrue(gb.checkIfFree(0));
        assertTrue(gb.checkIfFree(1));
        assertTrue(gb.checkIfFree(2));

        String expStr = arrayString(expected);
        assertEquals(expStr, gb.toString());
    }

    @Test
    public void testCheckIfFree_none_free() {
        char expected[][] = new char[3][3];
        IGameboard gb = factory(3, 3, 3);

        expected[0][0] = 'X';
        gb.placeToken('X', 0);
        expected[0][1] = 'X';
        gb.placeToken('X', 1);
        expected[0][2] = 'X';
        gb.placeToken('X', 2);
        expected[1][0] = 'X';
        gb.placeToken('X', 0);
        expected[1][1] = 'X';
        gb.placeToken('X', 1);
        expected[1][2] = 'X';
        gb.placeToken('X', 2);
        expected[2][0] = 'X';
        gb.placeToken('X', 0);
        expected[2][1] = 'X';
        gb.placeToken('X', 1);
        expected[2][2] = 'X';
        gb.placeToken('X', 2);

        assertFalse(gb.checkIfFree(0));
        assertFalse(gb.checkIfFree(1));
        assertFalse(gb.checkIfFree(2));

        String expStr = arrayString(expected);
        assertEquals(expStr, gb.toString());
    }

    /**
     * last placed in middle hor win
     * */
    @Test
    public void testCheckHorizWin_last_middle_win() {
        char expected[][] = new char[5][5];
        IGameboard gb = factory(5, 5, 3);

        expected[0][0] = 'X';
        gb.placeToken('X', 0);
        expected[0][1] = 'X';
        gb.placeToken('X', 1);
        expected[0][2] = 'X';
        gb.placeToken('X', 2);

        assertTrue(gb.checkHorizWin(0,1, 'X'));

        String expStr = arrayString(expected);
        assertEquals(expStr, gb.toString());
    }

    /**
     * 4 not 5
     * */
    @Test
    public void testCheckHorizWin_gap_noWin() {
        char expected[][] = new char[5][5];
        IGameboard gb = factory(5, 5, 3);

        expected[0][0] = 'X';
        gb.placeToken('X', 0);
        expected[0][1] = 'X';
        gb.placeToken('X', 1);
        expected[0][3] = 'O';
        gb.placeToken('O', 3);
        expected[0][4] = 'X';
        gb.placeToken('X', 4);

        assertFalse(gb.checkHorizWin(0,4, 'X'));

        String expStr = arrayString(expected);
        assertEquals(expStr, gb.toString());
    }

    /**
     * last placed on right end
     * */
    @Test
    public void testCheckHorizWin_last_front_win() {
        char expected[][] = new char[3][3];
        IGameboard gb = factory(3, 3, 3);

        expected[0][0] = 'X';
        gb.placeToken('X', 0);
        expected[0][1] = 'X';
        gb.placeToken('X', 1);
        expected[0][2] = 'X';
        gb.placeToken('X', 2);

        assertTrue(gb.checkHorizWin(0,0, 'X'));

        String expStr = arrayString(expected);
        assertEquals(expStr, gb.toString());
    }

    /**
     * full board
     * */
    @Test
    public void testCheckHorizWin_full_diff_char_noWin() {
        char expected[][] = new char[5][5];
        IGameboard gb = factory(5, 5, 3);

        expected[0][0] = 'X';
        gb.placeToken('X', 0);
        expected[0][1] = 'O';
        gb.placeToken('O', 1);
        expected[0][2] = 'X';
        gb.placeToken('X', 2);
        expected[0][3] = 'O';
        gb.placeToken('O', 3);
        expected[0][4] = 'X';
        gb.placeToken('X', 4);
        expected[1][0] = 'O';
        gb.placeToken('O', 0);
        expected[1][1] = 'X';
        gb.placeToken('X', 1);
        expected[1][2] = 'O';
        gb.placeToken('O', 2);
        expected[1][3] = 'X';
        gb.placeToken('X', 3);
        expected[1][4] = 'O';
        gb.placeToken('O', 4);
        expected[2][0] = 'X';
        gb.placeToken('X', 0);
        expected[2][1] = 'O';
        gb.placeToken('O', 1);
        expected[2][2] = 'X';
        gb.placeToken('X', 2);
        expected[2][3] = 'O';
        gb.placeToken('O', 3);
        expected[2][4] = 'X';
        gb.placeToken('X', 4);
        expected[3][0] = 'O';
        gb.placeToken('O', 0);
        expected[3][1] = 'X';
        gb.placeToken('X', 1);
        expected[3][2] = 'O';
        gb.placeToken('O', 2);
        expected[3][3] = 'X';
        gb.placeToken('X', 3);
        expected[3][4] = 'O';
        gb.placeToken('O', 4);
        expected[4][0] = 'X';
        gb.placeToken('X', 0);
        expected[4][4] = 'O';
        gb.placeToken('O', 4);
        expected[4][1] = 'O';
        gb.placeToken('O', 1);
        expected[4][3] = 'X';
        gb.placeToken('X', 3);
        expected[4][2] = 'O';
        gb.placeToken('O', 2);

        assertFalse(gb.checkHorizWin(4,2, 'O'));

        String expStr = arrayString(expected);
        assertEquals(expStr, gb.toString());
    }

    /**
     * count right to left
     * */
    @Test
    public void testCheckHorizWin_last_end_win() {
        char expected[][] = new char[3][3];
        IGameboard gb = factory(3, 3, 3);

        expected[0][0] = 'A';
        gb.placeToken('A', 2);
        expected[0][1] = 'A';
        gb.placeToken('A', 1);
        expected[0][2] = 'A';
        gb.placeToken('A', 0);

        assertTrue(gb.checkHorizWin(0,2, 'A'));

        String expStr = arrayString(expected);
        assertEquals(expStr, gb.toString());
    }

    @Test
    public void testCheckVertWin_col0_win() {
        char expected[][] = new char[3][3];
        IGameboard gb = factory(3, 3, 3);

        expected[0][0] = 'X';
        gb.placeToken('X', 0);
        expected[1][0] = 'X';
        gb.placeToken('X', 0);
        expected[2][0] = 'X';
        gb.placeToken('X', 0);

        assertTrue(gb.checkVertWin(2,0, 'X'));

        String expStr = arrayString(expected);
        assertEquals(expStr, gb.toString());
    }

    @Test
    public void testCheckVertWin_diff_char_noWin() {
        char expected[][] = new char[5][5];
        IGameboard gb = factory(5, 5, 3);

        expected[0][0] = 'X';
        gb.placeToken('X', 0);
        expected[1][0] = 'O';
        gb.placeToken('O', 0);
        expected[2][0] = 'X';
        gb.placeToken('X', 0);
        expected[3][0] = 'O';
        gb.placeToken('O', 0);

        assertFalse(gb.checkVertWin(3,0, 'O'));

        String expStr = arrayString(expected);
        assertEquals(expStr, gb.toString());
    }

    @Test
    public void testCheckVertWin_wrong_char_noWin() {
        char expected[][] = new char[5][5];
        IGameboard gb = factory(5, 5, 3);

        expected[0][0] = 'X';
        gb.placeToken('X', 0);
        expected[0][1] = 'X';
        gb.placeToken('X', 1);
        expected[0][2] = 'X';
        gb.placeToken('X', 2);
        expected[0][3] = 'X';
        gb.placeToken('X', 3);
        expected[0][4] = 'X';
        gb.placeToken('X', 4);

        assertFalse(gb.checkVertWin(4,0, 'O'));

        String expStr = arrayString(expected);
        assertEquals(expStr, gb.toString());
    }

    @Test
    public void testCheckVertWin_not_enough() {
        char expected[][] = new char[5][5];
        IGameboard gb = factory(5, 5, 5);

        expected[0][0] = 'X';
        gb.placeToken('X', 0);
        expected[1][0] = 'X';
        gb.placeToken('X', 0);
        expected[2][0] = 'X';
        gb.placeToken('X', 0);
        expected[3][0] = 'X';
        gb.placeToken('X', 0);

        assertFalse(gb.checkVertWin(3,0, 'O'));

        String expStr = arrayString(expected);
        assertEquals(expStr, gb.toString());
    }

    @Test
    public void testCheckVertWin_under_char_noWin() {
        char expected[][] = new char[5][5];
        IGameboard gb = factory(5, 5, 3);

        expected[0][0] = 'X';
        gb.placeToken('X', 0);
        expected[1][0] = 'X';
        gb.placeToken('X', 0);
        expected[2][0] = 'X';
        gb.placeToken('X', 0);
        expected[3][0] = 'O';
        gb.placeToken('O', 0);

        assertFalse(gb.checkVertWin(3,0, 'X'));

        String expStr = arrayString(expected);
        assertEquals(expStr, gb.toString());
    }

    /**
     * left to right, bottom to top diag win
     *
     * */
    @Test
    public void testCheckDiagWin_left_right_bot_top_win() {
        char expected[][] = new char[5][5];
        IGameboard gb = factory(5, 5, 3);

        expected[0][0] = 'X';
        gb.placeToken('X', 0);
        expected[0][1] = 'O';
        gb.placeToken('O', 1);
        expected[1][1] = 'X';
        gb.placeToken('X', 1);
        expected[0][2] = 'O';
        gb.placeToken('O', 2);
        expected[1][2] = 'X';
        gb.placeToken('X', 2);
        expected[2][2] = 'X';
        gb.placeToken('X', 2);

        assertTrue(gb.checkDiagWin(2,2, 'X'));

        String expStr = arrayString(expected);
        assertEquals(expStr, gb.toString());
    }

    /**
     * bottom to top, right to left diag win
     *
     * */
    @Test
    public void testCheckDiagWin_right_left_bot_top_win() {
        char expected[][] = new char[5][5];
        IGameboard gb = factory(5, 5, 3);

        expected[0][4] = 'X';
        gb.placeToken('X', 4);
        expected[0][3] = 'O';
        gb.placeToken('O', 3);
        expected[1][3] = 'X';
        gb.placeToken('X', 3);
        expected[0][2] = 'O';
        gb.placeToken('O', 2);
        expected[1][2] = 'O';
        gb.placeToken('O', 2);
        expected[2][2] = 'X';
        gb.placeToken('X', 2);

        assertTrue(gb.checkDiagWin(2,2, 'X'));

        String expStr = arrayString(expected);
        assertEquals(expStr, gb.toString());
    }

    /**
     * fill bottom row, left to right bottom to top, not on edges diag win
     *
     * */
    @Test
    public void testCheckDiagWin_bot_full_win() {
        char expected[][] = new char[5][5];
        IGameboard gb = factory(5, 5, 3);

        expected[0][0] = 'O';
        gb.placeToken('O', 0);
        expected[0][1] = 'O';
        gb.placeToken('O', 1);
        expected[0][2] = 'O';
        gb.placeToken('O', 2);
        expected[0][3] = 'O';
        gb.placeToken('O', 3);
        expected[0][4] = 'O';
        gb.placeToken('O', 4);
        expected[1][0] = 'O';
        gb.placeToken('O', 0);
        expected[1][1] = 'X';
        gb.placeToken('X', 1);
        expected[1][2] = 'O';
        gb.placeToken('O', 2);
        expected[2][2] = 'X';
        gb.placeToken('X', 2);
        expected[1][3] = 'O';
        gb.placeToken('O', 3);
        expected[2][3] = 'O';
        gb.placeToken('O', 3);
        expected[3][3] = 'X';
        gb.placeToken('X', 3);

        assertTrue(gb.checkDiagWin(3,3, 'X'));

        String expStr = arrayString(expected);
        assertEquals(expStr, gb.toString());
    }

    /**
     * if last placed is at bottom right
     *
     * */
    @Test
    public void testCheckDiagWin_last_bot_right_win() {
        char expected[][] = new char[5][5];
        IGameboard gb = factory(5, 5, 3);

        expected[0][4] = 'X';
        gb.placeToken('X', 4);
        expected[0][3] = 'O';
        gb.placeToken('O', 3);
        expected[1][3] = 'X';
        gb.placeToken('X', 3);
        expected[0][2] = 'O';
        gb.placeToken('O', 2);
        expected[1][2] = 'O';
        gb.placeToken('O', 2);
        expected[2][2] = 'X';
        gb.placeToken('X', 2);

        assertTrue(gb.checkDiagWin(0,4, 'X'));

        String expStr = arrayString(expected);
        assertEquals(expStr, gb.toString());
    }

    /**
     * left to right, bottom to top, no diag win
     *
     * */
    @Test
    public void testCheckDiagWin_left_right_bot_top_noWin() {
        char expected[][] = new char[5][5];
        IGameboard gb = factory(5, 5, 3);

        expected[0][1] = 'X';
        gb.placeToken('X', 1);
        expected[0][2] = 'O';
        gb.placeToken('O', 2);
        expected[1][2] = 'X';
        gb.placeToken('X', 2);

        assertFalse(gb.checkDiagWin(1,2, 'X'));

        String expStr = arrayString(expected);
        assertEquals(expStr, gb.toString());
    }

    /**
     * left to right, bottom to top, last in middle diag win
     *
     * */
    @Test
    public void testCheckDiagWin_left_right_bot_top_last_middle_win() {
        char expected[][] = new char[5][5];
        IGameboard gb = factory(5, 5, 3);

        expected[0][0] = 'X';
        gb.placeToken('X', 0);
        expected[0][1] = 'O';
        gb.placeToken('O', 1);
        expected[0][2] = 'O';
        gb.placeToken('O', 2);
        expected[1][2] = 'O';
        gb.placeToken('O', 2);
        expected[2][2] = 'X';
        gb.placeToken('X', 2);
        expected[1][1] = 'X';
        gb.placeToken('X', 1);

        assertTrue(gb.checkDiagWin(1,1, 'X'));

        String expStr = arrayString(expected);
        assertEquals(expStr, gb.toString());
    }

    /**
     * no win, wrong char
     *
     * */
    @Test
    public void testCheckDiagWin_wrong_char_noWin() {
        char expected[][] = new char[5][5];
        IGameboard gb = factory(5, 5, 3);

        expected[0][4] = 'X';
        gb.placeToken('X', 4);
        expected[0][3] = 'O';
        gb.placeToken('O', 3);
        expected[0][2] = 'O';
        gb.placeToken('O', 2);
        expected[1][2] = 'O';
        gb.placeToken('O', 2);
        expected[2][2] = 'X';
        gb.placeToken('X', 2);
        expected[1][3] = 'X';
        gb.placeToken('X', 3);


        assertFalse(gb.checkDiagWin(2,2, 'O'));

        String expStr = arrayString(expected);
        assertEquals(expStr, gb.toString());
    }

    /**
     *
     * if last placed was at beginning
     *
     * */
    @Test
    public void testCheckDiagWin_bot_top_left_right_last_left() {
        char expected[][] = new char[3][3];
        IGameboard gb = factory(3, 3, 3);

        expected[0][1] = 'O';
        gb.placeToken('O', 1);
        expected[1][1] = 'X';
        gb.placeToken('X', 1);
        expected[0][2] = 'O';
        gb.placeToken('O', 2);
        expected[1][2] = 'O';
        gb.placeToken('O', 2);
        expected[2][2] = 'X';
        gb.placeToken('X', 2);
        expected[0][0] = 'X';
        gb.placeToken('X', 0);

        assertTrue(gb.checkDiagWin(0,0, 'X'));

        String expStr = arrayString(expected);
        assertEquals(expStr, gb.toString());
    }

    /**
     * full board
     * */
    @Test
    public void testCheckTie_full() {
        char expected[][] = new char[3][3];
        IGameboard gb = factory(3, 3, 3);

        expected[0][0] = 'A';
        gb.placeToken('A', 0);
        expected[0][1] = 'B';
        gb.placeToken('B', 1);
        expected[0][2] = 'C';
        gb.placeToken('C', 2);
        expected[1][0] = 'B';
        gb.placeToken('B', 0);
        expected[1][1] = 'A';
        gb.placeToken('A', 1);
        expected[1][2] = 'C';
        gb.placeToken('C', 2);
        expected[2][0] = 'C';
        gb.placeToken('C', 0);
        expected[2][1] = 'A';
        gb.placeToken('A', 1);
        expected[2][2] = 'B';
        gb.placeToken('B', 2);

        assertTrue(gb.checkTie());

        String expStr = arrayString(expected);
        assertEquals(expStr, gb.toString());
    }

    /**
     * empty
     * */
    @Test
    public void testCheckTie_empty() {
        char expected[][] = new char[3][3];
        IGameboard gb = factory(3, 3, 3);

        assertFalse(gb.checkTie());

        String expStr = arrayString(expected);
        assertEquals(expStr, gb.toString());
    }

    /**
     * one column full
     * */
    @Test
    public void testCheckTie_one_full_col() {
        char expected[][] = new char[3][3];
        IGameboard gb = factory(3, 3, 3);

        expected[0][0] = 'X';
        gb.placeToken('X', 0);
        expected[1][0] = 'X';
        gb.placeToken('X', 0);
        expected[2][0] = 'X';
        gb.placeToken('X', 0);

        assertFalse(gb.checkTie());

        String expStr = arrayString(expected);
        assertEquals(expStr, gb.toString());
    }

    /**
     * one row full
     * */
    @Test
    public void testCheckTie_one_full_row() {
        char expected[][] = new char[3][3];
        IGameboard gb = factory(3, 3, 3);

        expected[0][0] = 'X';
        gb.placeToken('X', 0);
        expected[0][1] = 'X';
        gb.placeToken('X', 1);
        expected[0][2] = 'X';
        gb.placeToken('X', 2);

        assertFalse(gb.checkTie());

        String expStr = arrayString(expected);
        assertEquals(expStr, gb.toString());
    }

    /**
     * nothing there
     * */
    @Test
    public void testWhatsAtPos_empty() {
        char expected[][] = new char[3][3];
        IGameboard gb = factory(3, 3, 3);


        assertEquals(gb.whatsAtPos(0,0), 0);

        String expStr = arrayString(expected);
        assertEquals(expStr, gb.toString());
    }

    /**
     * character in bottom left
     * */
    @Test
    public void testWhatsAtPos_bot_left() {
        char expected[][] = new char[3][3];
        IGameboard gb = factory(3, 3, 3);

        expected[0][0] = 'X';
        gb.placeToken('X', 0);

        assertEquals(gb.whatsAtPos(0,0), 'X');

        String expStr = arrayString(expected);
        assertEquals(expStr, gb.toString());
    }

    /**
     * character in bottom right
     * */
    @Test
    public void testWhatsAtPos_bot_right() {
        char expected[][] = new char[3][3];
        IGameboard gb = factory(3, 3, 3);

        expected[0][2] = 'X';
        gb.placeToken('X', 2);

        assertEquals(gb.whatsAtPos(0,2), 'X');

        String expStr = arrayString(expected);
        assertEquals(expStr, gb.toString());
    }

    /**
     * token in middle of row
     * */
    @Test
    public void testWhatsAtPos_middle_bot() {
        char expected[][] = new char[3][3];
        IGameboard gb = factory(3, 3, 3);

        expected[0][0] = 'X';
        gb.placeToken('X', 0);
        expected[0][1] = 'O';
        gb.placeToken('O', 1);
        expected[0][2] = 'X';
        gb.placeToken('X', 2);

        assertEquals(gb.whatsAtPos(0,1), 'O');

        String expStr = arrayString(expected);
        assertEquals(expStr, gb.toString());
    }

    /**
     * token in middle of col
     * */
    @Test
    public void testWhatsAtPos_middle_middle() {
        char expected[][] = new char[3][3];
        IGameboard gb = factory(3, 3, 3);

        expected[0][1] = 'X';
        gb.placeToken('X', 1);
        expected[1][1] = 'O';
        gb.placeToken('O', 1);
        expected[2][1] = 'X';
        gb.placeToken('X', 1);

        assertEquals(gb.whatsAtPos(1,1), 'O');

        String expStr = arrayString(expected);
        assertEquals(expStr, gb.toString());
    }

    /**
     * character in top left
     * */
    @Test
    public void testWhatsAtPos_top_left() {
        char expected[][] = new char[3][3];
        IGameboard gb = factory(3, 3, 3);

        expected[0][0] = 'X';
        gb.placeToken('X', 0);
        expected[1][0] = 'X';
        gb.placeToken('X', 0);
        expected[2][0] = 'O';
        gb.placeToken('O', 0);

        assertEquals(gb.whatsAtPos(2,0), 'O');

        String expStr = arrayString(expected);
        assertEquals(expStr, gb.toString());
    }

    /**
     * character in top right
     * */
    @Test
    public void testWhatsAtPos_top_right() {
        char expected[][] = new char[3][3];
        IGameboard gb = factory(3, 3, 3);

        expected[0][2] = 'X';
        gb.placeToken('X', 2);
        expected[1][2] = 'X';
        gb.placeToken('X', 2);
        expected[2][2] = 'O';
        gb.placeToken('O', 2);

        assertEquals(gb.whatsAtPos(2,2), 'O');

        String expStr = arrayString(expected);
        assertEquals(expStr, gb.toString());
    }

    /**
     * place in empty board
     * */
    @Test
    public void testPlaceToken_empty_board() {
        char expected[][] = new char[3][3];
        IGameboard gb = factory(3, 3, 3);

        expected[0][0] = 'X';
        gb.placeToken('X', 0);

        String expStr = arrayString(expected);
        assertEquals(expStr, gb.toString());
    }

    /**
     * place on top one almost full col
     * */
    @Test
    public void testPlaceToken_one_space_left_col() {
        char expected[][] = new char[3][3];
        IGameboard gb = factory(3, 3, 3);

        expected[0][0] = 'X';
        gb.placeToken('X', 0);
        expected[1][0] = 'X';
        gb.placeToken('X', 0);
        expected[2][0] = 'X';
        gb.placeToken('X', 0);

        String expStr = arrayString(expected);
        assertEquals(expStr, gb.toString());
    }

    /**
     * place in almost full row
     * */
    @Test
    public void testPlaceToken_one_spce_left_row() {
        char expected[][] = new char[3][3];
        IGameboard gb = factory(3, 3, 3);

        expected[0][0] = 'X';
        gb.placeToken('X', 0);
        expected[0][1] = 'X';
        gb.placeToken('X', 1);
        expected[0][2] = 'X';
        gb.placeToken('X', 2);

        String expStr = arrayString(expected);
        assertEquals(expStr, gb.toString());
    }

    /**
     * attempt to place on full col
     * */
    @Test
    public void testPlaceToken_complete_full_col() {
        char expected[][] = new char[3][3];
        IGameboard gb = factory(3, 3, 3);

        expected[0][0] = 'X';
        gb.placeToken('X', 0);
        expected[1][0] = 'X';
        gb.placeToken('X', 0);
        expected[2][0] = 'X';
        gb.placeToken('X', 0);

        gb.placeToken('X', 0);

        String expStr = arrayString(expected);
        assertEquals(expStr, gb.toString());
    }

    /**
     * place in middle of board
     * */
    @Test
    public void testPlaceToken_middle_middle() {
        char expected[][] = new char[3][3];
        IGameboard gb = factory(3, 3, 3);

        expected[0][1] = 'X';
        gb.placeToken('X', 1);
        expected[1][1] = 'X';
        gb.placeToken('X', 1);


        String expStr = arrayString(expected);
        assertEquals(expStr, gb.toString());
    }
}

